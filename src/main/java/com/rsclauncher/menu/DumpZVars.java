package com.rsclauncher.menu;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.rsclauncher.util.FieldFormatter;
import com.rsclauncher.util.JsonGeneratorFactory;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class DumpZVars implements MenuItem {

  private final ClassLoader classLoader;

  public DumpZVars(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public String title() {
    return "Dump Z vars";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final JarInputStream jarInputStream = new JarInputStream(new FileInputStream(new File("rsclassic.jar")));
      final List<String> classFiles = new ArrayList<>();

      JarEntry entry;
      while ((entry = jarInputStream.getNextJarEntry()) != null) {
        final String entryName = entry.getName();

        if (entryName.endsWith(".class")) {
          classFiles.add(entryName);
        }
      }

      final JsonGenerator generator = JsonGeneratorFactory.createGenerator();
      generator.writeStartObject();

      for (String fileName : classFiles) {
        final String className = fileName.replaceFirst("\\.class$", "");

        // these doesn't like being loaded for whatever reason, seems to try and import
        // microsoft-specific packages
        // TODO: Handle on classloader side of things
        if (Arrays.asList("h", "ha", "mb", "n", "tb", "u").contains(className)) {
          continue;
        }

        try {
          final Class<?> clazz = classLoader.loadClass(className);
          final Field zField = clazz.getDeclaredField("z");

          if (!zField.getType().isAssignableFrom(String[].class)) {
            continue;
          }

          zField.setAccessible(true);

          generator.writeFieldName(className);
          FieldFormatter.STRING_ARRAY.render(
              zField.get(null),
              generator
          );

        } catch (NoSuchFieldException ex) {}
      }

      generator.writeEndObject();
      generator.flush();
      System.out.println("");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static String decryptString(String s, char[] key) {
    final char[] out = s.toCharArray();

    for (int i = 0; i < out.length; ++i) {
      out[i] = (char)(out[i] ^ key[i % key.length]);
    }

    return new String(out);
  }
}
