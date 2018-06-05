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
//    {
//      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getRegionX", "()I", null, null);
//      mv.visitCode();
//      mv.visitVarInsn(ALOAD, 0);
//      mv.visitFieldInsn(GETSTATIC, "client", "vi", "I");
//      mv.visitInsn(IRETURN);
//      mv.visitMaxs(2, 1);
//      mv.visitEnd();
//    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getQuestNames", "()[Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Jj", "[Ljava/lang/String;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillNames", "()[Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Yh", "[Ljava/lang/String;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillLevels", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Ri", "[I");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillExperiences", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Vc", "[I");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getLocalPlayer", "()Lcom/rslauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Hh", "Lcom/rslauncher/api/GameCharacter;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNearbyPlayers", "()[Lcom/rslauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Nj", "[Lcom/rslauncher/api/GameCharacter;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNpcs", "()[Lcom/rslauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, "client", "Rg", "[Lcom/rslauncher/api/GameCharacter;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

//    {
//      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getEquippedItems", "()[I", null, null);
//      mv.visitCode();
//      mv.visitVarInsn(ALOAD, 0);
//      mv.visitFieldInsn(GETFIELD, "client", "l", "[I");
//      mv.visitInsn(IRETURN);
//      mv.visitMaxs(2, 1);
//      mv.visitEnd();
//    }

    cv.visitEnd();
  }

}
