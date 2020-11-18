package core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {

  // Each grid space is 16x16 pixels
  public static int SIZE = 16;

  protected int x;
  protected int y;

  protected PImage sprite;

  protected int edgeLeft = getX() - (getWidth() / 2);
  protected int edgeRight = getX() + (getWidth() / 2);
  protected int edgeTop = getY() - (getHeight() / 2);
  protected int edgeBottom = getY() + (getHeight() / 2);

  public Tile(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return sprite.width;
  }

  public int getHeight() {
    return sprite.height;
  }

  public int getEdgeLeft() {
    return edgeLeft;
  }

  public int getEdgeRight() {
    return edgeRight;
  }

  public int getEdgeTop() {
    return edgeTop;
  }

  public int getEdgeBottom() {
    return edgeBottom;
  }
  
}