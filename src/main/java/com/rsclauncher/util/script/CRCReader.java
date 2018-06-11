package com.rsclauncher.util.script;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

public class CRCReader {

  private static final String crcDataFilePath = "/tmp/rsc/contentcrcs163eb48bb4b";

  public static void main(String[] args) throws Exception {
    final FileInputStream crcDataFile = new FileInputStream(crcDataFilePath);
    final String[] crcStrings = readCRCs(crcDataFile);

    Arrays.stream(crcStrings)
        .forEach(System.out::println);

    crcDataFile.close();
  }

  public static String[] readCRCs(InputStream inputStream) throws IOException {
    final byte[] crcRaw = IOUtils.toByteArray(inputStream);
    final ByteBuffer crcBuffer = ByteBuffer.wrap(crcRaw);

    final int[] crcValues = new int[12];
    crcBuffer.asIntBuffer().get(crcValues);

    return Arrays.stream(crcValues)
        .boxed()
        .map(Long::toHexString)
        .toArray(String[]::new);
  }
}
