package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class ClientPatcher extends ClassPatcher {

  private static final String O_CLASS_NAME = "client";
  private static final String O_GET_LOCAL_REGION_X = "kk";
  private static final String O_GET_LOCAL_REGION_Y = "lc";
  private static final String O_GET_REGION_X = "Ed";
  private static final String O_GET_REGION_Y = "Zk";
  private static final String O_GET_QUEST_NAMES = "Jj";
  private static final String O_GET_SKILL_NAMES = "Yh";
  private static final String O_GET_SKILL_LEVELS = "Ri";
  private static final String O_GET_SKILL_EXPERIENCES = "Vc";
  private static final String O_GET_LOCAL_PLAYER = "Hh";
  private static final String O_GET_NEARBY_PLAYERS = "Nj";
  private static final String O_GET_NPCS = "Rg";

  @Override
  public ClassNode patch(ClassNode classNode) {
    ClassNode newClassNode = ASMUtils.copyClassNode(classNode);

    newClassNode.interfaces.add("com/rsclauncher/api/Client");

    MethodNode getRegionX = new MethodNode(ACC_PUBLIC, "getRegionX", "()I", null, null);
    getRegionX.instructions.add(new VarInsnNode(ALOAD, 0));
    getRegionX.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_REGION_X, "I"));
    getRegionX.instructions.add(new InsnNode(IRETURN));
    getRegionX.maxStack = 2;
    getRegionX.maxLocals = 1;
    newClassNode.methods.add(getRegionX);

    MethodNode getRegionY = new MethodNode(ACC_PUBLIC, "getRegionY", "()I", null, null);
    getRegionY.instructions.add(new VarInsnNode(ALOAD, 0));
    getRegionY.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_REGION_Y, "I"));
    getRegionY.instructions.add(new InsnNode(IRETURN));
    getRegionY.maxStack = 2;
    getRegionY.maxLocals = 1;
    newClassNode.methods.add(getRegionY);

    MethodNode getLocalRegionX = new MethodNode(ACC_PUBLIC, "getLocalRegionX", "()I", null, null);
    getLocalRegionX.instructions.add(new VarInsnNode(ALOAD, 0));
    getLocalRegionX.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_LOCAL_REGION_X, "I"));
    getLocalRegionX.instructions.add(new InsnNode(IRETURN));
    getLocalRegionX.maxStack = 2;
    getLocalRegionX.maxLocals = 1;
    newClassNode.methods.add(getLocalRegionX);

    MethodNode getLocalRegionY = new MethodNode(ACC_PUBLIC, "getLocalRegionY", "()I", null, null);
    getLocalRegionY.instructions.add(new VarInsnNode(ALOAD, 0));
    getLocalRegionY.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_LOCAL_REGION_Y, "I"));
    getLocalRegionY.instructions.add(new InsnNode(IRETURN));
    getLocalRegionY.maxStack = 2;
    getLocalRegionY.maxLocals = 1;
    newClassNode.methods.add(getLocalRegionY);

    MethodNode getQuestNames = new MethodNode(ACC_PUBLIC, "getQuestNames", "()[Ljava/lang/String;", null, null);
    getQuestNames.instructions.add(new VarInsnNode(ALOAD, 0));
    getQuestNames.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_QUEST_NAMES, "[Ljava/lang/String;"));
    getQuestNames.instructions.add(new InsnNode(ARETURN));
    getQuestNames.maxStack = 2;
    getQuestNames.maxLocals = 1;
    newClassNode.methods.add(getQuestNames);

    MethodNode getSkillNames = new MethodNode(ACC_PUBLIC, "getSkillNames", "()[Ljava/lang/String;", null, null);
    getSkillNames.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillNames.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_NAMES, "[Ljava/lang/String;"));
    getSkillNames.instructions.add(new InsnNode(ARETURN));
    getSkillNames.maxStack = 2;
    getSkillNames.maxLocals = 1;
    newClassNode.methods.add(getSkillNames);

    MethodNode getSkillLevels = new MethodNode(ACC_PUBLIC, "getSkillLevels", "()[I", null, null);
    getSkillLevels.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillLevels.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_LEVELS, "[I"));
    getSkillLevels.instructions.add(new InsnNode(ARETURN));
    getSkillLevels.maxStack = 2;
    getSkillLevels.maxLocals = 1;
    newClassNode.methods.add(getSkillLevels);

    MethodNode getSkillExperiences = new MethodNode(ACC_PUBLIC, "getSkillExperiences", "()[I", null, null);
    getSkillExperiences.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillExperiences.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_EXPERIENCES, "[I"));
    getSkillExperiences.instructions.add(new InsnNode(ARETURN));
    getSkillExperiences.maxStack = 2;
    getSkillExperiences.maxLocals = 1;
    newClassNode.methods.add(getSkillExperiences);

    MethodNode getLocalPlayer = new MethodNode(ACC_PUBLIC, "getLocalPlayer", "()Lcom/rsclauncher/api/GameCharacter;", null, null);
    getLocalPlayer.instructions.add(new VarInsnNode(ALOAD, 0));
    getLocalPlayer.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_LOCAL_PLAYER, "Lnb;"));
    getLocalPlayer.instructions.add(new InsnNode(ARETURN));
    getLocalPlayer.maxStack = 2;
    getLocalPlayer.maxLocals = 1;
    newClassNode.methods.add(getLocalPlayer);

    MethodNode getNearbyPlayers = new MethodNode(ACC_PUBLIC, "getNearbyPlayers", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
    getNearbyPlayers.instructions.add(new VarInsnNode(ALOAD, 0));
    getNearbyPlayers.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NEARBY_PLAYERS, "[Lnb;"));
    getNearbyPlayers.instructions.add(new InsnNode(ARETURN));
    getNearbyPlayers.maxStack = 2;
    getNearbyPlayers.maxLocals = 1;
    newClassNode.methods.add(getNearbyPlayers);

    MethodNode getNpcs = new MethodNode(ACC_PUBLIC, "getNpcs", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
    getNpcs.instructions.add(new VarInsnNode(ALOAD, 0));
    getNpcs.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NPCS, "[Lnb;"));
    getNpcs.instructions.add(new InsnNode(ARETURN));
    getNpcs.maxStack = 2;
    getNpcs.maxLocals = 1;
    newClassNode.methods.add(getNpcs);

    return newClassNode;
  }

}
