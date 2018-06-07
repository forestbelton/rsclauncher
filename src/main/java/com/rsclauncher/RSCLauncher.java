package com.rsclauncher;

import com.rsclauncher.menu.MenuBarFactory;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
    // private String lastBytes;

    public ClientDebugThread(Object client) {
      this.client = client;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000);
/*
          final Field ChField = client.getClass().getDeclaredField("Ch");

          ChField.setAccessible(true);
          final Object Ch = ChField.get(client);
          final

          final StringBuilder output = new StringBuilder();

          if (Ch == null) {
            continue;
          }

          String theseBytes = Base64.getEncoder().encodeToString(vk);
          if (lastBytes != null && lastBytes.equals(theseBytes)) {
            continue;
          }

          lastBytes = theseBytes;
          output.append("===\n");

          for (int i = 0; i < vk.length; ++i) {
            output.append(String.format("%x", Byte.toUnsignedInt(vk[i])));

            if (i != vk.length - 1) {
              output.append(' ');
            }
          }

          output.append('\n');
          Files.write(Paths.get("/Users/case/Projects/rsclauncher/packet.log"), output.toString().getBytes(), StandardOpenOption.APPEND);*/
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  // client.Gf = cameraRotation


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

    try {
      final Field[] clientFields = client.getClass().getDeclaredFields();

      for (Field clientField : clientFields) {
        if (!clientField.getType().isAssignableFrom(byte[].class)) {
          continue;
        }

        if (Modifier.isStatic(clientField.getModifiers())) {
          continue;
        }

        /*clientField.setAccessible(true);
        final int fieldValue = clientField.getInt(client);

        if (fieldValue == 912) {
          System.out.println("axisRotation: " + clientField.getName());
          break;
        }*/

        System.out.println("byte[] " + clientField.getName());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    // Set up menu
    final JMenuBar menuBar = MenuBarFactory.newMenuBar(client, classLoader);
    frame.setJMenuBar(menuBar);

    final Applet clientApplet = Applet.class.cast(client);
    clientApplet.setStub(frame);
    clientApplet.setBounds(0, 0, 512, 346);

    final JLabel coordLabel = new JLabel("(0, 0)");
    coordLabel.setForeground(Color.black);
    coordLabel.setBackground(Color.black);
    coordLabel.setBounds(0, 0, (int)coordLabel.getPreferredSize().getWidth(), (int)coordLabel.getPreferredSize().getHeight());

    final JLayeredPane layeredPane = new JLayeredPane();

    layeredPane.add(clientApplet, new Integer(2), 1);
    layeredPane.add(coordLabel, new Integer(2),0);

    // final Client client = Client.class.cast(client);
    // System.out.println(client.getSkillLevels()[0]);

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
  }
}
