package core;

import java.awt.Point;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {

  private int SIZE = MapGrid.GRIDSIZE;

  protected int x;
  protected int y;

  protected Point coords;

  protected PImage sprite;

  public Tile(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.coords = new Point((int) x / SIZE, (int) y / SIZE);
  }

  /**
  * Draws Tile to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }

  /**
  * Returns true if Tile allows Actor movement
  *
  * @return  true if Tile allows Actor movement
  */
  public abstract boolean isMovable();

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Point getCoords() {
    return coords;
  }

  public int getCoordX() {
    Double doubleX = coords.getX();
    int intX = (int) Math.round(doubleX);
    return intX;
  }

  public int getCoordY() {
    Double doubleY = coords.getY();
    int intY = (int) Math.round(doubleY);
    return intY;
  }

}
