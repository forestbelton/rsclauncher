package com.rsclauncher.menu;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PrintSceneVariables implements MenuItem {

  private final String SCENE_CLASS_NAME = "p";
  private final String CLIENT_SCENE_FIELD_NAME = "nf";

  private final Object client;
  private final ClassLoader classLoader;

  public PrintSceneVariables(Object client, ClassLoader classLoader) {
    this.client = client;
    this.classLoader = classLoader;
  }

  @Override
  public String title() {
    return "Print scene variables";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final Class<?> sceneClass = classLoader.loadClass(SCENE_CLASS_NAME);
      final Field[] sceneFields = sceneClass.getDeclaredFields();
      final Class<?> clientClass = client.getClass();
      final Field clientSceneField = clientClass.getDeclaredField(CLIENT_SCENE_FIELD_NAME);

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