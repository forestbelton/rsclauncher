package com.rsclauncher.menu;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrintStaticVariable implements MenuItem {

  private final ClassLoader classLoader;

  final JTextField classNameField;
  final JTextField fieldNameField;
  final JPanel inputForm;

  public PrintStaticVariable(ClassLoader classLoader) {
    this.classLoader = classLoader;

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
  public String title() {
    return "Print static variable";
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