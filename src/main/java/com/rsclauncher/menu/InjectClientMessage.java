package com.rsclauncher.menu;

import com.rsclauncher.api.Client;

import java.awt.event.ActionEvent;

public class InjectClientMessage implements MenuItem {

  private final Client client;

  public InjectClientMessage(Client client) {
    this.client = client;
  }

  @Override
  public String title() {
    return "Inject client message";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    client.addMessage(0, "A", 2, "B", 3, null, "C", false);
  }
}
