package com.rsclauncher.menu;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PrintClientVariables implements MenuItem {

  private final Object client;
  private final ClassLoader classLoader;

  public PrintClientVariables(Object client, ClassLoader classLoader) {
    this.client = client;
    this.classLoader = classLoader;
  }

  @Override
  public String title() {
    return "Print client variables";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final Class<?> clientClass = client.getClass();
      final Field[] clientFields = clientClass.getDeclaredFields();

      final HashMap<String, Integer> integerVariables = new HashMap<>();

      Stream.of(clientFields)
          .filter(field -> (field.getModifiers() & Modifier.STATIC) == 0)
          .filter(field -> field.getType().isAssignableFrom(int.class))
          .forEach(field -> {
            field.setAccessible(true);

            try {
              integerVariables.put(field.getName(), field.getInt(client));
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

      System.out.println("");

    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }
}