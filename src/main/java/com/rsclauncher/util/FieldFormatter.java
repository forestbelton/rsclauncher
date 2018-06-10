package com.rsclauncher.util;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;

public enum FieldFormatter {
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
