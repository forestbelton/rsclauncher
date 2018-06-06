package com.rsclauncher.patcher;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GameCharacterVisitor extends ClassVisitor {

  private static final String O_CLASS_NAME = "nb";
  private static final String O_GET_NAME = "d";
  private static final String O_GET_CURRENT_HEALTH = "o";
  private static final String O_GET_MAX_HEALTH = "r";
  private static final String O_GET_COMBAT_LEVEL = "n";
  private static final String O_GET_DAMAGE_TAKEN = "z";
  private static final String O_GET_NPC_ID = "m";
  private static final String O_GET_EQUIPPED_ITEMS = "l";
  private static final String O_GET_OVERHEAD_MESSAGE = "q";

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
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME,  O_GET_NAME, "Ljava/lang/String;");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getCurrentHealth", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_CURRENT_HEALTH, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getMaxHealth", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_MAX_HEALTH, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getCombatLevel", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_COMBAT_LEVEL, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getDamageTaken", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_DAMAGE_TAKEN, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getNpcId", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_NPC_ID, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getEquippedItems", "()[I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_EQUIPPED_ITEMS, "[I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    {
      final MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "getOverheadMessage", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, O_CLASS_NAME, O_GET_OVERHEAD_MESSAGE, "Ljava/lang/String;");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }

    cv.visitEnd();
  }

}
