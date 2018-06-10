package com.rsclauncher;

import com.rsclauncher.api.Client;
import com.rsclauncher.menu.MenuBarFactory;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;

public class RSCLauncher {

  public static class ClientDebugThread extends Thread {

    public final Object client;
    // private String lastBytes;

    public ClientDebugThread(Object client) {
      this.client = client;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  public static class UpdateCoordsThread extends Thread {

    private final Client client;
    private final JLabel coordLabel;

    public UpdateCoordsThread(Client client, JLabel coordLabel) {
      this.client = client;
      this.coordLabel = coordLabel;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(100);

          final int worldX = client.getRegionX() + client.getLocalRegionX();
          final int worldY = client.getRegionY() + client.getLocalRegionY();
          final String coordText = String.format("(%d, %d)", worldX, worldY);

          this.coordLabel.setText(coordText);

          final int textWidth = 100;
          final int textHeight = (int)Math.ceil(coordLabel.getPreferredSize().getHeight());

          this.coordLabel.setBounds(0, 0, textWidth, textHeight);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
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
    final Client clientObject = Client.class.cast(client);

    // Set up menu
    final JMenuBar menuBar = MenuBarFactory.newMenuBar(client, classLoader);
    frame.setJMenuBar(menuBar);

    final Applet clientApplet = Applet.class.cast(client);
    clientApplet.setStub(frame);
    clientApplet.setBounds(0, 0, 512, 346);

    final JLabel coordLabel = new JLabel("(0,0)");
    coordLabel.setForeground(Color.black);
    coordLabel.setBackground(Color.black);
    coordLabel.setBounds(0, 0, (int)coordLabel.getPreferredSize().getWidth(), (int)coordLabel.getPreferredSize().getHeight());

    final JLayeredPane layeredPane = new JLayeredPane();

    layeredPane.add(clientApplet, new Integer(2), 1);
    layeredPane.add(coordLabel, new Integer(2),0);

    frame.setContentPane(layeredPane);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.getContentPane().setPreferredSize(new Dimension(512, 346));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    // Start client
    clientApplet.init();
    clientApplet.start();

    new ClientDebugThread(client).start();
    new UpdateCoordsThread(clientObject, coordLabel).start();
  }
}
