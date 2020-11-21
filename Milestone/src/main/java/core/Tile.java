package core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {

  // Each grid space is 16x16 pixels
  public static int SIZE = 16;

  protected int x;
  protected int y;

  protected int coordX;
  protected int coordY;

  protected PImage sprite;

  protected int edgeLeft;
  protected int edgeRight;
  protected int edgeTop;
  protected int edgeBottom;

  public Tile(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.edgeLeft = x - (sprite.width / 2);
    this.edgeRight = x + (sprite.width / 2);
    this.edgeTop = y - (sprite.height / 2);
    this.edgeBottom = y + (sprite.height / 2);
    this.coordX = (x + SIZE/2)/SIZE;
    this.coordY = (y + SIZE/2)/SIZE;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, getWidth(), getHeight());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return SIZE;
  }

  public int getHeight() {
    return SIZE;
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

  public int getCoordX() {
    return coordX;
  }

  public int getCoordY() {
    return coordY;
  }

  public abstract boolean isMovable();

  public abstract void msg();
  
}