package com.rsclauncher.patcher;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ClientVisitor extends ClassVisitor {

  public ClientVisitor(int i, ClassVisitor cv) {
    super(i, cv);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    final String[] newInterfaces = new String[] { "com/rsclauncher/api/Client" };

    cv.visit(version, access, name, signature, superName, newInterfaces);
  }

  @Override
  public void visitEnd() {
    final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getRegionX", "()I", null, null);

    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitFieldInsn(GETSTATIC, "client", "vi", "I");
    mv.visitInsn(IRETURN);
    mv.visitMaxs(2, 1);
    mv.visitEnd();

    cv.visitEnd();
  }

}
