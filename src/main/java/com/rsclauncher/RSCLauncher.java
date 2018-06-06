package com.rsclauncher;

import com.rsclauncher.menu.MenuBarFactory;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class RSCLauncher {

  private static String decryptString(String s, char[] key) {
    final char[] out = s.toCharArray();

    for (int i = 0; i < out.length; ++i) {
      out[i] = (char)(out[i] ^ key[i % key.length]);
    }

    return new String(out);
  }

  public static class ClientDebugThread extends Thread {

    public final Object client;

    public ClientDebugThread(Object client) {
      this.client = client;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {}
      }
    }
  }

  public static void main(String[] args) throws Exception {
    final RSCLauncher launcher = new RSCLauncher();
    launcher.run();
  }

  public void run() throws Exception {
    final RSCClassLoader classLoader = new RSCClassLoader();
    classLoader.init();

    final RSCFrame frame = new RSCFrame("RSCLauncher");

    // Load client instance
    final Class<?> clientClass = classLoader.loadClass("client");
    final Object client = clientClass.newInstance();

    // Set up menu
    final JMenuBar menuBar = MenuBarFactory.newMenuBar(client, classLoader);
    frame.setJMenuBar(menuBar);

    final Applet clientApplet = Applet.class.cast(client);
    clientApplet.setStub(frame);

    // final Client client = Client.class.cast(client);
    // System.out.println(client.getSkillLevels()[0]);

    frame.setContentPane(clientApplet);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.getContentPane().setPreferredSize(new Dimension(512, 346));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    // Start client
    clientApplet.init();
    clientApplet.start();

    new ClientDebugThread(client).start();
  }
}
