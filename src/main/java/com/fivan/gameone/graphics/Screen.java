package com.fivan.gameone.graphics;

import com.fivan.gameone.level.tile.Tile;

import java.util.Random;

/**
 * Represents image for a game screen.
 */
public class Screen {

  public int[] pixels;
  private final int MAP_SIZE = 64;
  private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

  public int xOffset, yOffset;

  public int width, height;
  private Random random = new Random();

  /**
   * Screen generation with randomly colored tilesInt.
   *
   * @param width  width
   * @param height height
   */
  public Screen(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new int[width * height];

    for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
      tiles[i] = random.nextInt(0xffffff);
    }
    tiles[0] = 0;
  }


  /**
   * Clears screen.
   */
  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }

  /**
   * Method for rendering a {@link Tile}.
   *
   * @param xp   x position
   * @param yp   y position
   * @param tile {@link Tile} object
   */
  public void renderTile(int xp, int yp, Tile tile) {
    xp -= xOffset;
    yp -= yOffset;
    for (int y = 0; y < tile.sprite.size; y++) {
      int ya = y + yp;
      for (int x = 0; x < tile.sprite.size; x++) {
        int xa = x + xp;
        if (xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height) { break; }
        if (xa < 0) { xa = 0; }
        pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.size];
      }
    }
  }

  public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
    xp -= xOffset;
    yp -= yOffset;
    for (int y = 0; y < 32; y++) {
      int ya = y + yp;
      int ys = y;
      if (flip == 2 || flip == 3) {
        ys = 31 - y;
      } else {}
      for (int x = 0; x < 32; x++) {
        int xa = x + xp;
        int xs = x;
        if (flip == 1 || flip == 3) {
          xs = 31 - x;
        }
        if (xa < -32 || xa >= width || ya < 0 || ya >= height) { break; }
        if (xa < 0) { xa = 0; }
        int col = sprite.pixels[xs + ys * 32];
        if (col != 0xffff00ff) {
          pixels[xa + ya * width] = col;
        }
      }
    }
  }

  /**
   * Sets an offset.
   *
   * @param xOffset x offset
   * @param yOffset y offset
   */
  public void setOffset(int xOffset, int yOffset) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }

}
