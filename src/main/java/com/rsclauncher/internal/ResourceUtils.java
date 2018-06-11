package com.rsclauncher.internal;

import java.io.*;

public class ResourceUtils {

  public static void print(int a, int b, String c, int d) {
    System.out.println(a + " " + b + " " + c + " " + d);
  }

  public static void print2(int a, String b, int c, String d) {
    System.out.println(a + " " + b + " " + c + " " + d);
  }

  public static void print3(int a, byte b, byte[] c, byte[] d, String e) {
    new File("data").mkdir();

    try {
      FileOutputStream fos = new FileOutputStream(new File("data/" + e));
      fos.write(c);

      System.out.println(a + " " + b + " " + c.length + " " + e);
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

}
