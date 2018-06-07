package com.rsclauncher.api;

public interface Client {

  public int getRegionX();

  public int getRegionY();

  public int getPlane();

  public int[] getEquippedItems();




  public int getLocalRegionX();

  public int getLocalRegionY();

  public String[] getQuestNames();

  public String[] getSkillNames();

  public int[] getSkillLevels();

  public int[] getSkillExperiences();

  public GameCharacter getLocalPlayer();

  public GameCharacter[] getNearbyPlayers();

  public GameCharacter[] getNpcs();



}
