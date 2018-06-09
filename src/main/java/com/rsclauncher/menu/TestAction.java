package com.rsclauncher.menu;

import com.rsclauncher.api.Client;

import java.awt.event.ActionEvent;

public class TestAction implements MenuItem {

  private final Client client;

  public TestAction(Client client) {
    this.client = client;
  }

  @Override
  public String title() {
    return "Test action";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    client.addMessage(1, "yo", 2, "yo", 3, "yo", "yo", false);
  }

}
