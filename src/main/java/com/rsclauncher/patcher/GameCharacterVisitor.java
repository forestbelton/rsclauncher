package com.rsclauncher.patcher;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GameCharacterVisitor extends ClassVisitor {

  public GameCharacterVisitor(int i, ClassVisitor cv) {
    super(i, cv);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    final String[] newInterfaces = new String[] { "com/rsclauncher/api/GameCharacter" };

    cv.visit(version, access, name, signature, superName, newInterfaces);
  }

  @Override
  public void visitEnd() {
    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "d", "Ljava/lang/String;");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getCurrentHealth", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "o", "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getMaxHealth", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "r", "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getCombatLevel", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "n", "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getDamageTaken", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "z", "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNpcId", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "m", "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getEquippedItems", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "l", "[I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getOverheadMessage", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "nb", "q", "Ljava/lang/String;");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    cv.visitEnd();
  }

}
