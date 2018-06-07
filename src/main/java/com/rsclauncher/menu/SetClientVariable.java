package com.rsclauncher.menu;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetClientVariable implements MenuItem {

  private final Object client;
  private final JPanel inputForm;
  private final JTextField fieldNameField;
  private final JTextField valueField;

  public SetClientVariable(Object client) {
    this.client = client;

    fieldNameField = new JTextField(10);
    valueField = new JTextField(10);

    inputForm = new JPanel();
    inputForm.setLayout(new BoxLayout(inputForm, BoxLayout.PAGE_AXIS));

    final Box fieldBox = Box.createHorizontalBox();
    fieldBox.add(new JLabel("Field:"));
    fieldBox.add(fieldNameField);
    inputForm.add(fieldBox);
    inputForm.add(Box.createVerticalStrut(15)); // a spacer

    final Box valueBox = Box.createHorizontalBox();
    valueBox.add(new JLabel("Value:"));
    fieldBox.add(valueField);
    inputForm.add(valueBox);
  }

  @Override
  public String title() {
    return "Set client variable";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final int result = JOptionPane.showConfirmDialog(null, inputForm,
        "Set the client variable", JOptionPane.OK_CANCEL_OPTION);

    if (result != JOptionPane.OK_OPTION) {
      return;
    }

    final String fieldName = fieldNameField.getText();
    final String value = valueField.getText();

    try {
      final Field field = client.getClass().getDeclaredField(fieldName);

      field.setAccessible(true);
      field.setInt(client, Integer.valueOf(value, 10));
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }
}
