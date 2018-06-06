package com.rsclauncher;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RSCLauncher {

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

  private Object client = null;
  private RSCFrame frame = null;
  private RSCClassLoader classLoader = null;

  public void run() throws Exception {
    frame = new RSCFrame("RSCLauncher");

    // Set up menu
    final JMenuBar menuBar = new JMenuBar();
    final JMenu actionsMenu = new JMenu("Debug");
    final JMenuItem printStaticVarItem = new JMenuItem("Print static variable");
    final JMenuItem printSceneVarItem = new JMenuItem("Print scene variable");

    printSceneVarItem.addActionListener(new PrintSceneVariables());
    printStaticVarItem.addActionListener(new PrintStaticVariables());
    actionsMenu.add(printStaticVarItem);
    actionsMenu.add(printSceneVarItem);
    menuBar.add(actionsMenu);
    frame.setJMenuBar(menuBar);

    // Load client class and add to frame
    classLoader = new RSCClassLoader();
    classLoader.init();

    final Class<?> clientClass = classLoader.loadClass("client");

    client = clientClass.newInstance();

    final Applet clientApplet = Applet.class.cast(client);
    // final Client client = Client.class.cast(client);
    // System.out.println(client.getSkillLevels()[0]);

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

  public class PrintStaticVariables implements ActionListener {

    final JTextField classNameField;
    final JTextField fieldNameField;
    final JPanel inputForm;

    public PrintStaticVariables() {
      classNameField = new JTextField(10);
      fieldNameField = new JTextField(10);

      inputForm = new JPanel();
      inputForm.setLayout(new BoxLayout(inputForm, BoxLayout.PAGE_AXIS));

      final Box classBox = Box.createHorizontalBox();
      classBox.add(new JLabel("Class:"));
      classBox.add(classNameField);
      inputForm.add(classBox);
      inputForm.add(Box.createVerticalStrut(15)); // a spacer

      final Box fieldBox = Box.createHorizontalBox();
      fieldBox.add(new JLabel("Field:"));
      fieldBox.add(fieldNameField);
      inputForm.add(fieldBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      final int result = JOptionPane.showConfirmDialog(null, inputForm,
          "Specify the static variable to print", JOptionPane.OK_CANCEL_OPTION);

      if (result != JOptionPane.OK_OPTION) {
        return;
      }

      try {
        final String className = classNameField.getText();
        final String fieldName = fieldNameField.getText();

        System.out.println(className + "::" + fieldName);

        final Class<?> abClass = classLoader.loadClass(className);
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

  public class PrintSceneVariables implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        final Class<?> sceneClass = classLoader.loadClass("p");
        final Field[] sceneFields = sceneClass.getDeclaredFields();
        final Class<?> clientClass = client.getClass();
        final Field clientSceneField = clientClass.getDeclaredField("nf");

        clientSceneField.setAccessible(true);
        final Object scene = clientSceneField.get(client);

        if (scene == null) {
          System.err.println("Scene not initialized!");
          return;
        }

        final HashMap<String, Integer> integerVariables = new HashMap<>();

        Stream.of(sceneFields)
            .filter(field -> (field.getModifiers() & Modifier.STATIC) == 0)
            .filter(field -> field.getType().isAssignableFrom(int.class))
            .forEach(field -> {
              field.setAccessible(true);

              try {
                integerVariables.put(field.getName(), field.getInt(scene));
              } catch (IllegalAccessException ex) {
                ex.printStackTrace(System.err);
              }
            });

        final JsonFactory factory = new JsonFactory();
        final JsonGenerator generator = factory.createGenerator(System.out);

        generator.writeStartObject();
        for (Map.Entry<String, Integer> integerVariable : integerVariables.entrySet()) {
          generator.writeNumberField(
              integerVariable.getKey(),
              integerVariable.getValue()
          );
        }
        generator.writeEndObject();
        generator.flush();

      } catch (Exception ex) {
        ex.printStackTrace(System.err);
      }
    }
  }
}
