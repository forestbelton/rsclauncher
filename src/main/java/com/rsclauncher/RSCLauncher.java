package com.rsclauncher;

import com.rsclauncher.api.Client;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RSCLauncher implements ActionListener {

  public static class RSCFrame extends JFrame implements AppletStub {

    private static final int world = 2;
    private static final HashMap<String, String> gameParameters = new HashMap<>();

    static {
      gameParameters.put("java_arguments", "-Xmx96m -Dsun.java2d.noddraw=true");
      gameParameters.put("nodeid", "5004");
      gameParameters.put("modewhere", "0");
      gameParameters.put("modewhat", "0");
      gameParameters.put("servertype", "1");
      gameParameters.put("advertsuppressed", "1");
      gameParameters.put("js", "1");
      gameParameters.put("affid", "0");
    }

    public RSCFrame(String name) {
      super(name);
    }

    @Override
    public URL getDocumentBase() {
      return getCodeBase();
    }

    @Override
    public URL getCodeBase() {
      try {
        return new URL("http://classic" + world + ".runescape.com/");
      } catch (MalformedURLException ex) {
        return null;
      }
    }

    @Override
    public String getParameter(String name) {
      return gameParameters.get(name);
    }

    @Override
    public AppletContext getAppletContext() {
      return null;
    }

    @Override
    public void appletResize(int width, int height) {
    }
  }

  private static String decryptString(String s, char[] key) {
    final char[] out = s.toCharArray();

    for (int i = 0; i < out.length; ++i) {
      out[i] = (char)(out[i] ^ key[i % key.length]);
    }

    return new String(out);
  }

  public static class AppletThread extends Thread {

    public final Applet applet;

    public AppletThread(Applet applet) {
      this.applet = applet;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {}
      }
    }
  }

  public static void main(String[] args) throws Exception {
    final RSCLauncher launcher = new RSCLauncher();
    launcher.run();
  }

  private RSCFrame frame = null;

  public void run() throws Exception {
    frame = new RSCFrame("RSCLauncher");

    // Set up menu
    final JMenuBar menuBar = new JMenuBar();
    final JMenu actionsMenu = new JMenu("Actions");
    final JMenuItem debugHookItem = new JMenuItem("Debug hook");

    debugHookItem.addActionListener(this);
    actionsMenu.add(debugHookItem);
    menuBar.add(actionsMenu);
    frame.setJMenuBar(menuBar);

    // Load client class and add to frame
    final RSCClassLoader classLoader = new RSCClassLoader();
    classLoader.init();

    final Class<?> clientClass = classLoader.loadClass("client");
    final Applet clientApplet = Applet.class.cast(clientClass.newInstance());
    final Client client = Client.class.cast(clientApplet);

    System.out.println(client.getSkillLevels()[0]);

    clientApplet.setStub(frame);
    frame.setContentPane(clientApplet);

    frame.getContentPane().setBackground(Color.BLACK);
    frame.getContentPane().setPreferredSize(new Dimension(512, 346));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    clientApplet.init();
    clientApplet.start();

    new AppletThread(clientApplet).start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //printStaticVariable("ab", "y");
    //printStaticVariable("ab", "b");
    //printStaticVariable("ab", "Fb");
    //printStaticVariable("ab", "Qb");

    final JTextField xField = new JTextField(5);
    final JTextField yField = new JTextField(5);

    final JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Class:"));
    myPanel.add(xField);
    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    myPanel.add(new JLabel("Field:"));
    myPanel.add(yField);

    final int result = JOptionPane.showConfirmDialog(null, myPanel,
        "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {
      return;
    }

    printStaticVariable(xField.getText(), yField.getText());
  }

  private final void printStaticVariable(String className, String fieldName) {
    try {
      System.out.println(className + "::" + fieldName);

      final Class<?> abClass = ClassLoader.getSystemClassLoader().loadClass(className);
      final Field yField = abClass.getDeclaredField(fieldName);

      yField.setAccessible(true);
      final String[] yArray = (String[])yField.get(null);

      System.out.println(yArray.length);
      for (int i = 0; i < yArray.length; ++i) {
        if (yArray[i] != null) {
          System.out.println("[" + i + "] \"" + yArray[i] + "\"");
        }
      }

    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }
}
