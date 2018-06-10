package com.rsclauncher;

import com.rsclauncher.patcher.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class RSCClassLoader extends ClassLoader {

  private final Map<String, byte[]> classes = new HashMap<>();

  public void init() throws IOException {
    final JarInputStream jarInputStream = new JarInputStream(new FileInputStream(new File("rsclassic.jar")));

    JarEntry entry = null;
    while ((entry = jarInputStream.getNextJarEntry()) != null) {
      final String entryName = entry.getName();

      if (entryName.endsWith(".class")) {
        final String className = entryName.substring(0, entryName.indexOf(".class"));

        final byte[] buffer = new byte[1024];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int readSize;
        while ((readSize = jarInputStream.read(buffer, 0, buffer.length)) != -1)
          baos.write(buffer, 0, readSize);

        final byte[] classBytes = baos.toByteArray();
        baos.close();

        final ClassReader classReader = new ClassReader(classBytes);
        final ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

        final ClassPatcher patcher;
        if (classNode.name.equals("client")) {
          patcher = new ClientPatcher();
        } else if (classNode.name.equals("nb")) {
          patcher = new GameCharacterPatcher();
        } else if (classNode.name.equals("vb")) {
          patcher = new GameShellPatcher();
        } else if (classNode.name.equals("ab")) {
          patcher = new abPatcher();
        } else if (classNode.name.equals("mb")) {
          patcher = new ClientStreamPatcher();
        } else {
          patcher = null;
        }

        final ClassNode newClassNode;
        if (patcher != null) {
          newClassNode = patcher.patch(ASMUtils.copyClassNode(classNode));
        } else {
          newClassNode = classNode;
        }

        final ClassWriter classWriter = new ClassWriter(0);
        newClassNode.accept(classWriter);

        final byte[] modifiedClassBytes = classWriter.toByteArray();
        classes.put(className, modifiedClassBytes);
      }
    }

    jarInputStream.close();
  }

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    final byte[] classBytes = classes.get(name);
    if (classBytes == null) {
      throw new ClassNotFoundException();
    }

    return defineClass(name, classBytes, 0, classBytes.length);
  }

}
