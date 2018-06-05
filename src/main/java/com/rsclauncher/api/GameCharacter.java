package com.rsclauncher.api;

public interface GameCharacter {

  public int getName();

  public int getAccountName();

  public int getCombatLevel();

  public int getMaxHealth();

  public int getCurrentHealth();

  public int getDamageTaken();

  public int getNpcId();

  public int[] getEquippedItems();

  public String getOverheadMessage();

}
