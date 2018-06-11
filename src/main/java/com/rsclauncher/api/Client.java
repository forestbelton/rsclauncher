package com.rsclauncher.api;

public interface Client {

  int getRegionX();

  int getRegionY();

  int getPlane();

  int[] getEquippedItems();

  int getLocalRegionX();

  int getLocalRegionY();

  String[] getQuestNames();

  String[] getSkillNames();

  int[] getSkillLevels();

  int[] getSkillExperiences();

  GameCharacter getLocalPlayer();

  GameCharacter[] getNearbyPlayers();

  GameCharacter[] getNpcs();

  void addMessage(int crownId, String content, int messageType, String target, int unknown,
      String colorOverride, String targetClan, boolean forceShow);


  // Panels
  Panel getWelcomePanel();

  int getWelcomePanelClickControl();

}
