package com.rsclauncher.patcher;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class ClientVisitor extends ClassVisitor {

  private static final String O_CLASS_NAME = "client";
  private static final String O_GET_QUEST_NAMES = "Jj";
  private static final String O_GET_SKILL_NAMES = "Yh";
  private static final String O_GET_SKILL_LEVELS = "Ri";
  private static final String O_GET_SKILL_EXPERIENCES = "Vc";
  private static final String O_GET_LOCAL_PLAYER = "Hh";
  private static final String O_GET_NEARBY_PLAYERS = "Nj";
  private static final String O_GET_NPCS = "Rg";

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
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_QUEST_NAMES, "[Ljava/lang/String;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillNames", "()[Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_SKILL_NAMES, "[Ljava/lang/String;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillLevels", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_SKILL_LEVELS, "[I");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getSkillExperiences", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_SKILL_EXPERIENCES, "[I");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getLocalPlayer", "()Lcom/rsclauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_LOCAL_PLAYER, "Lcom/rsclauncher/api/GameCharacter;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNearbyPlayers", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_NEARBY_PLAYERS, "[Lcom/rsclauncher/api/GameCharacter;");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNpcs", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD,  O_CLASS_NAME, O_GET_NPCS, "[Lcom/rsclauncher/api/GameCharacter;");
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
