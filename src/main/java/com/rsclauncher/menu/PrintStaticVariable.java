package com.rsclauncher.menu;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.awt.event.ActionEvent;
import java.io.IOException;
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

      System.out.print(className + "::" + fieldName + " = ");

      final Class<?> aClass = classLoader.loadClass(className);
      final Field aField = aClass.getDeclaredField(fieldName);
      aField.setAccessible(true);

      final JsonFactory jsonFactory = new JsonFactory();
      final JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out);

      final DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
      prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
      jsonGenerator.setPrettyPrinter(prettyPrinter);

      for (TypeHandler handler : TypeHandler.values()) {
        if (!aField.getType().isAssignableFrom(handler.typeClass())) {
          continue;
        }

        handler.render(aField.get(null), jsonGenerator);
        jsonGenerator.flush();
        System.out.println("");

        return;
      }
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }

  private enum TypeHandler {
    BOOLEAN {
      @Override
      public Class<?> typeClass() {
        return boolean.class;
      }

      @Override
      public void render(Object field, JsonGenerator generator) throws IOException {
        final Boolean booleanValue = (Boolean)field;
        generator.writeBoolean(booleanValue);
      }
    },
    INT {
      @Override
      public Class<?> typeClass() {
        return int.class;
      }

      @Override
      public void render(Object field, JsonGenerator generator) throws IOException {
        final Number number = (Number)field;
        final int intValue = number.intValue();

        generator.writeNumber(intValue);
      }
    },
    INT_ARRAY {
      @Override
      public Class<?> typeClass() {
        return int[].class;
      }

      @Override
      public void render(Object field, JsonGenerator generator) throws IOException {
        final int[] arrayField = (int[])field;

        generator.writeStartArray();

        for (int i : arrayField) {
          generator.writeNumber(i);
        }

        generator.writeEndArray();
      }
    },
    STRING_ARRAY {
      @Override
      public Class<?> typeClass() {
        return String[].class;
      }

      @Override
      public void render(Object field, JsonGenerator generator) throws IOException {
        final String[] arrayField = (String[])field;

        generator.writeStartArray();

        for (String str : arrayField) {
          if (str == null) {
            generator.writeNull();
          } else {
            generator.writeString(str);
          }
        }

        generator.writeEndArray();
      }
    };

    public abstract Class<?> typeClass();
    public abstract void render(Object field, JsonGenerator generator) throws IOException;
  }
}