package com.rsclauncher.api;

public interface Client {

  public int getRegionX();

  public int getRegionY();

  public int getLocalRegionX();

  public int getLocalRegionY();

  public int getPlane();



  public String[] getQuestNames();

  public String[] getSkillNames();

  public int[] getSkillLevels();

  public int[] getSkillExperiences();


  public int[] getEquippedItems();

  public GameCharacter getLocalPlayer();

  public GameCharacter[] getNearbyPlayers();

  public GameCharacter[] getNpcs();

}
