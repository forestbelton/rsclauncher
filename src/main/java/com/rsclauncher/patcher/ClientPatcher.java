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

  private static final String O_GET_WELCOME_PANEL = "ff";
  private static final String O_GET_WELCOME_CONTINUE = "xc";

  private static final String O_GET_LOGIN_PANEL = "ki";
  private static final String O_GET_LOGIN_CONTROL_USERNAME = "sj";
  private static final String O_GET_LOGIN_CONTROL_PASSWORD = "Qd";
  private static final String O_GET_LOGIN_CONTROL_OK = "ie";
  private static final String O_GET_LOGIN_CONTROL_CANCEL = "Dh";

  @Override
  public ClassNode patch(ClassNode classNode) {
    classNode.interfaces.add("com/rsclauncher/api/Client");

    classNode.methods.add(createIntGetter("getRegionX", O_CLASS_NAME, O_GET_REGION_X));
    classNode.methods.add(createIntGetter("getRegionY", O_CLASS_NAME, O_GET_REGION_Y));
    classNode.methods.add(createIntGetter("getLocalRegionX", O_CLASS_NAME, O_GET_LOCAL_REGION_X));
    classNode.methods.add(createIntGetter("getLocalRegionY", O_CLASS_NAME, O_GET_LOCAL_REGION_Y));

    MethodNode getQuestNames = new MethodNode(ACC_PUBLIC, "getQuestNames", "()[Ljava/lang/String;", null, null);
    getQuestNames.instructions.add(new VarInsnNode(ALOAD, 0));
    getQuestNames.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_QUEST_NAMES, "[Ljava/lang/String;"));
    getQuestNames.instructions.add(new InsnNode(ARETURN));
    getQuestNames.maxStack = 2;
    getQuestNames.maxLocals = 1;
    classNode.methods.add(getQuestNames);

    MethodNode getSkillNames = new MethodNode(ACC_PUBLIC, "getSkillNames", "()[Ljava/lang/String;", null, null);
    getSkillNames.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillNames.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_NAMES, "[Ljava/lang/String;"));
    getSkillNames.instructions.add(new InsnNode(ARETURN));
    getSkillNames.maxStack = 2;
    getSkillNames.maxLocals = 1;
    classNode.methods.add(getSkillNames);

    MethodNode getSkillLevels = new MethodNode(ACC_PUBLIC, "getSkillLevels", "()[I", null, null);
    getSkillLevels.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillLevels.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_LEVELS, "[I"));
    getSkillLevels.instructions.add(new InsnNode(ARETURN));
    getSkillLevels.maxStack = 2;
    getSkillLevels.maxLocals = 1;
    classNode.methods.add(getSkillLevels);

    MethodNode getSkillExperiences = new MethodNode(ACC_PUBLIC, "getSkillExperiences", "()[I", null, null);
    getSkillExperiences.instructions.add(new VarInsnNode(ALOAD, 0));
    getSkillExperiences.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_SKILL_EXPERIENCES, "[I"));
    getSkillExperiences.instructions.add(new InsnNode(ARETURN));
    getSkillExperiences.maxStack = 2;
    getSkillExperiences.maxLocals = 1;
    classNode.methods.add(getSkillExperiences);

    MethodNode getLocalPlayer = new MethodNode(ACC_PUBLIC, "getLocalPlayer", "()Lcom/rsclauncher/api/GameCharacter;", null, null);
    getLocalPlayer.instructions.add(new VarInsnNode(ALOAD, 0));
    getLocalPlayer.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_LOCAL_PLAYER, "Lnb;"));
    getLocalPlayer.instructions.add(new InsnNode(ARETURN));
    getLocalPlayer.maxStack = 2;
    getLocalPlayer.maxLocals = 1;
    classNode.methods.add(getLocalPlayer);

    MethodNode getNearbyPlayers = new MethodNode(ACC_PUBLIC, "getNearbyPlayers", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
    getNearbyPlayers.instructions.add(new VarInsnNode(ALOAD, 0));
    getNearbyPlayers.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NEARBY_PLAYERS, "[Lnb;"));
    getNearbyPlayers.instructions.add(new InsnNode(ARETURN));
    getNearbyPlayers.maxStack = 2;
    getNearbyPlayers.maxLocals = 1;
    classNode.methods.add(getNearbyPlayers);

    MethodNode getNpcs = new MethodNode(ACC_PUBLIC, "getNpcs", "()[Lcom/rsclauncher/api/GameCharacter;", null, null);
    getNpcs.instructions.add(new VarInsnNode(ALOAD, 0));
    getNpcs.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_NPCS, "[Lnb;"));
    getNpcs.instructions.add(new InsnNode(ARETURN));
    getNpcs.maxStack = 2;
    getNpcs.maxLocals = 1;
    classNode.methods.add(getNpcs);

    MethodNode addMessage = new MethodNode(ACC_PUBLIC, "addMessage", "(ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Z)V", null, null);
    addMessage.instructions.add(new VarInsnNode(ALOAD, 0));
    addMessage.instructions.add(new VarInsnNode(ILOAD, 1));
    addMessage.instructions.add(new VarInsnNode(ALOAD, 2));
    addMessage.instructions.add(new VarInsnNode(ILOAD, 3));
    addMessage.instructions.add(new VarInsnNode(ALOAD, 4));
    addMessage.instructions.add(new VarInsnNode(ILOAD, 5));
    addMessage.instructions.add(new VarInsnNode(ALOAD, 6));
    addMessage.instructions.add(new VarInsnNode(ALOAD, 7));
    addMessage.instructions.add(new VarInsnNode(ILOAD, 8));
    addMessage.instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "client", "a", "(ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Z)V", false));
    addMessage.instructions.add(new InsnNode(RETURN));
    addMessage.maxStack = 10;
    addMessage.maxLocals = 9;
    classNode.methods.add(addMessage);

    //
    // Panels
    //

    MethodNode getWelcomePanel = new MethodNode(ACC_PUBLIC, "getWelcomePanel", "()Lcom/rsclauncher/api/Panel;", null, null);
    getWelcomePanel.instructions.add(new VarInsnNode(ALOAD, 0));
    getWelcomePanel.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_WELCOME_PANEL, "Lab;"));
    getWelcomePanel.instructions.add(new InsnNode(ARETURN));
    getWelcomePanel.maxStack = 2;
    getWelcomePanel.maxLocals = 1;
    classNode.methods.add(getWelcomePanel);

    classNode.methods.add(createIntGetter("getWelcomeControlContinue", O_CLASS_NAME, O_GET_WELCOME_CONTINUE));

    MethodNode getLoginPanel = new MethodNode(ACC_PUBLIC, "getLoginPanel", "()Lcom/rsclauncher/api/Panel;", null, null);
    getLoginPanel.instructions.add(new VarInsnNode(ALOAD, 0));
    getLoginPanel.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_GET_LOGIN_PANEL, "Lab;"));
    getLoginPanel.instructions.add(new InsnNode(ARETURN));
    getLoginPanel.maxStack = 2;
    getLoginPanel.maxLocals = 1;
    classNode.methods.add(getLoginPanel);
    
    classNode.methods.add(createIntGetter("getLoginControlUsername", O_CLASS_NAME, O_GET_LOGIN_CONTROL_USERNAME));
    classNode.methods.add(createIntGetter("getLoginControlPassword", O_CLASS_NAME, O_GET_LOGIN_CONTROL_PASSWORD));
    classNode.methods.add(createIntGetter("getLoginControlOk", O_CLASS_NAME, O_GET_LOGIN_CONTROL_OK));
    classNode.methods.add(createIntGetter("getLoginControlCancel", O_CLASS_NAME, O_GET_LOGIN_CONTROL_CANCEL));

    return classNode;
  }

  public MethodNode createIntGetter(String methodName, String className, String fieldName) {
    MethodNode methodNode = new MethodNode(ACC_PUBLIC, methodName, "()I", null, null);
    methodNode.instructions.add(new VarInsnNode(ALOAD, 0));
    methodNode.instructions.add(new FieldInsnNode(GETFIELD, className, fieldName, "I"));
    methodNode.instructions.add(new InsnNode(IRETURN));
    methodNode.maxStack = 2;
    methodNode.maxLocals = 1;

    return methodNode;
  }

}
