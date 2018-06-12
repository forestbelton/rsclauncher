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
    try {
      client.getWelcomePanel().setControlClicked(client.getWelcomeControlContinue());

      Thread.sleep(1000);

      client.getLoginPanel().setControlText(client.getLoginControlUsername(), "test");

      Thread.sleep(1000);

      client.getLoginPanel().setControlText(client.getLoginControlPassword(), "test");

      Thread.sleep(1000);

      client.getLoginPanel().setControlClicked(client.getLoginControlCancel());
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

//    client.addMessage(0, "A", 2, "B", 3, null, "C", false);
  }
}
