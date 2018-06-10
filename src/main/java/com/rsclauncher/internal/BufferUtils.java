package com.rsclauncher.internal;

public class BufferUtils {

  public static void printWriteBuffer(byte[] buffer, int offset, int length) {
    System.out.print("W: ");
    for (int i = offset; i < offset + length; i++) {
      System.out.print(String.format("%02X", buffer[i]) + " ");
    }
    System.out.println();
  }

  public static void printReadBuffer(byte[] buffer, int offset, int length) {
    System.out.print("R: ");
    for (int i = offset; i < offset + length; i++) {
      System.out.print(String.format("%02X", buffer[i]) + " ");
    }
    System.out.println();
  }

}
