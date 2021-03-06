package com.fivan.gameone.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Sheet that contains sprites on a grid.
 */
public class SpriteSheet {

  /**
   * Sprite sheet image pixels.
   */
  int[] pixels;

  /**
   * Size of sheet to load.
   */
  final int size;

  /**
   * Path to sprite sheet image.
   */
  private String path;

  public static SpriteSheet tiles = new SpriteSheet("/texture/spritesheet.png", 256);

  private SpriteSheet(String path, int size) {
    this.path = path;
    this.size = size;
    pixels = new int[size * size];
    load();
  }

  private void load() {
    try {
      BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
