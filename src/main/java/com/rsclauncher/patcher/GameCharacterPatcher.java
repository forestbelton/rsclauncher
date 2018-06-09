package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class GameCharacterPatcher extends ClassPatcher {

  private static final String O_CLASS_NAME = "nb";
  private static final String O_GET_NAME = "d";
  private static final String O_GET_CURRENT_HEALTH = "o";
  private static final String O_GET_MAX_HEALTH = "r";
  private static final String O_GET_COMBAT_LEVEL = "n";
  private static final String O_GET_DAMAGE_TAKEN = "z";
  private static final String O_GET_NPC_ID = "m";
  private static final String O_GET_EQUIPPED_ITEMS = "l";
  private static final String O_GET_OVERHEAD_MESSAGE = "q";

  @Override
  public ClassNode patch(ClassNode classNode) {
    ClassNode newClassNode = ASMUtils.copyClassNode(classNode);

    newClassNode.interfaces.add("com/rsclauncher/api/GameCharacter");

    MethodNode getName = new MethodNode(ACC_PUBLIC, "getRegionX", "()Ljava/lang/String;", null, null);
    getName.instructions.add(new VarInsnNode(ALOAD, 0));
    getName.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NAME, "Ljava/lang/String;"));
    getName.instructions.add(new InsnNode(ARETURN));
    getName.maxStack = 2;
    getName.maxLocals = 1;
    newClassNode.methods.add(getName);

    MethodNode getCurrentHealth = new MethodNode(ACC_PUBLIC, "getCurrentHealth", "()I", null, null);
    getCurrentHealth.instructions.add(new VarInsnNode(ALOAD, 0));
    getCurrentHealth.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_CURRENT_HEALTH, "I"));
    getCurrentHealth.instructions.add(new InsnNode(IRETURN));
    getCurrentHealth.maxStack = 2;
    getCurrentHealth.maxLocals = 1;
    newClassNode.methods.add(getCurrentHealth);

    MethodNode getMaxHealth = new MethodNode(ACC_PUBLIC, "getMaxHealth", "()I", null, null);
    getMaxHealth.instructions.add(new VarInsnNode(ALOAD, 0));
    getMaxHealth.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_MAX_HEALTH, "I"));
    getMaxHealth.instructions.add(new InsnNode(IRETURN));
    getMaxHealth.maxStack = 2;
    getMaxHealth.maxLocals = 1;
    newClassNode.methods.add(getMaxHealth);

    MethodNode getCombatLevel = new MethodNode(ACC_PUBLIC, "getCombatLevel", "()I", null, null);
    getCombatLevel.instructions.add(new VarInsnNode(ALOAD, 0));
    getCombatLevel.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_COMBAT_LEVEL, "I"));
    getCombatLevel.instructions.add(new InsnNode(IRETURN));
    getCombatLevel.maxStack = 2;
    getCombatLevel.maxLocals = 1;
    newClassNode.methods.add(getCombatLevel);

    MethodNode getDamageTaken = new MethodNode(ACC_PUBLIC, "getDamageTaken", "()I", null, null);
    getDamageTaken.instructions.add(new VarInsnNode(ALOAD, 0));
    getDamageTaken.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_DAMAGE_TAKEN, "I"));
    getDamageTaken.instructions.add(new InsnNode(IRETURN));
    getDamageTaken.maxStack = 2;
    getDamageTaken.maxLocals = 1;
    newClassNode.methods.add(getDamageTaken);

    MethodNode getNpcId = new MethodNode(ACC_PUBLIC, "getNpcId", "()I", null, null);
    getNpcId.instructions.add(new VarInsnNode(ALOAD, 0));
    getNpcId.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NPC_ID, "I"));
    getNpcId.instructions.add(new InsnNode(IRETURN));
    getNpcId.maxStack = 2;
    getNpcId.maxLocals = 1;
    newClassNode.methods.add(getNpcId);

    MethodNode getEquippedItems = new MethodNode(ACC_PUBLIC, "getEquippedItems", "()[I", null, null);
    getEquippedItems.instructions.add(new VarInsnNode(ALOAD, 0));
    getEquippedItems.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_EQUIPPED_ITEMS, "[I"));
    getEquippedItems.instructions.add(new InsnNode(ARETURN));
    getEquippedItems.maxStack = 2;
    getEquippedItems.maxLocals = 1;
    newClassNode.methods.add(getEquippedItems);

    MethodNode getOverheadMessage = new MethodNode(ACC_PUBLIC, "getOverheadMessage", "()Ljava/lang/String;", null, null);
    getOverheadMessage.instructions.add(new VarInsnNode(ALOAD, 0));
    getOverheadMessage.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_OVERHEAD_MESSAGE, "Ljava/lang/String;"));
    getOverheadMessage.instructions.add(new InsnNode(ARETURN));
    getOverheadMessage.maxStack = 2;
    getOverheadMessage.maxLocals = 1;
    newClassNode.methods.add(getOverheadMessage);

    return newClassNode;
  }

}
