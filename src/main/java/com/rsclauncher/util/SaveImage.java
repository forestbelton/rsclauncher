package com.rsclauncher.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// TODO: clean up or remove this later
public class SaveImage {

  public static void save(Image image) {
    try {
      final int width = image.getWidth(null);
      final int height = image.getHeight(null);

      final BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      final Graphics2D g2d = bimage.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();

      final String filename = "image-" + width + "-" + height + ".png";

      new File("out").mkdir();
      ImageIO.write(bimage, "PNG", new File("out/" + filename));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
