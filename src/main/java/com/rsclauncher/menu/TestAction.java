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
    client.addMessage(0, "A", 2, "B", 3, null, "C", false);
  }

}
