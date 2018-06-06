package com.rsclauncher;


import com.rsclauncher.patcher.ClientPatcher;
import com.rsclauncher.patcher.GameCharacterPatcher;
import com.rsclauncher.patcher.ImageLoaderPatcher;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static org.objectweb.asm.Opcodes.ASM4;

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

        final ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor;

        if (className.equals("client")) {
          classVisitor = new ClientPatcher(ASM4, classWriter);
        } else if (className.equals("nb")) {
          classVisitor = new GameCharacterPatcher(ASM4, classWriter);
        } else if (className.equals("i")) {
          classVisitor = new ImageLoaderPatcher(ASM4, classWriter);
        } else {
          classVisitor = classWriter;
        }

        final ClassReader classReader = new ClassReader(classBytes);
        classReader.accept(classVisitor, 0);

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
