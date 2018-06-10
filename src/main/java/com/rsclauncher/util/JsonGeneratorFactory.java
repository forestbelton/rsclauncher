package com.rsclauncher.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;

public class JsonGeneratorFactory {
  public static JsonGenerator createGenerator() throws IOException {
    final JsonFactory jsonFactory = new JsonFactory();
    final JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out);

    final DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    jsonGenerator.setPrettyPrinter(prettyPrinter);

    return jsonGenerator;
  }
}
