package com.rsclauncher.menu;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoveCamera implements MenuItem {

  private static final String CLIENT_SCENE_FIELD_NAME = "nf";

  private final Object client;
  private final ClassLoader classLoader;

  // GUI data
  private final JPanel inputForm;
  private final JTextField mField;
  private final JTextField zbField;
  private final JTextField qbField;
  private final JTextField yField;
  private final JTextField hField;
  private final JTextField tbField;

  public MoveCamera(Object client, ClassLoader classLoader) {
    this.client = client;
    this.classLoader = classLoader;

    inputForm = new JPanel();

    mField = addField("m");
    inputForm.add(Box.createVerticalStrut(15));

    zbField = addField("zb");
    inputForm.add(Box.createVerticalStrut(15));

    qbField = addField("qb");
    inputForm.add(Box.createVerticalStrut(15));

    yField = addField("y");
    inputForm.add(Box.createVerticalStrut(15));

    hField = addField("h");
    inputForm.add(Box.createVerticalStrut(15));

    tbField = addField("tb");
  }

  private JTextField addField(String fieldName) {
    final JTextField field = new JTextField(10);
    final Box fieldBox = Box.createHorizontalBox();

    fieldBox.add(new JLabel(fieldName));
    fieldBox.add(field);
    inputForm.add(fieldBox);

    return field;
  }

  @Override
  public String title() {
    return "Move camera";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final Class<?> clientClass = client.getClass();
      final Field clientSceneField = clientClass.getDeclaredField(CLIENT_SCENE_FIELD_NAME);

      clientSceneField.setAccessible(true);
      final Object scene = clientSceneField.get(client);

      if (scene == null) {
        System.err.println("Scene not initialized!");
        return;
      }

      mField.setText(getIntField(scene, "m"));
      zbField.setText(getIntField(scene, "zb"));
      qbField.setText(getIntField(scene, "qb"));
      yField.setText(getIntField(scene, "y"));
      hField.setText(getIntField(scene, "h"));
      tbField.setText(getIntField(scene, "tb"));

      final int result = JOptionPane.showConfirmDialog(null, inputForm,
          "Set the camera values you want", JOptionPane.OK_CANCEL_OPTION);

      if (result != JOptionPane.OK_OPTION) {
        return;
      }

      final int m = Integer.valueOf(mField.getText());
      final int zb = Integer.valueOf(zbField.getText());
      final int qb = Integer.valueOf(qbField.getText());
      final int y = Integer.valueOf(yField.getText());
      final int h = Integer.valueOf(hField.getText());
      final int tb = Integer.valueOf(tbField.getText());

      setIntField(scene, "m", m);
      setIntField(scene, "zb", zb);
      setIntField(scene, "qb", qb);
      setIntField(scene, "y", y);
      setIntField(scene, "h", h);
      setIntField(scene, "tb", tb);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }

  private void setIntField(Object scene, String field, int value) throws Exception {
    final Class<?> sceneClass = scene.getClass();
    final Field sceneField = sceneClass.getDeclaredField(field);

    System.out.println("Setting " + field + " to " + value);

    sceneField.setAccessible(true);
    sceneField.setInt(scene, value);
  }

  private String getIntField(Object scene, String field) throws Exception {
    final Class<?> sceneClass = scene.getClass();
    final Field sceneField = sceneClass.getDeclaredField(field);

    sceneField.setAccessible(true);
    final int value = sceneField.getInt(scene);

    return Integer.toString(value);
  }
}
