package core;

import java.awt.Point;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {

  // Each grid space is 16x16 pixels
  public static int SIZE = 16;

  protected int x;
  protected int y;

  protected Point coords;

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
    this.coords = new Point((x - SIZE / 2) / SIZE, (y - SIZE / 2) / SIZE);
  }

  /**
  * Draws Tile to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, getWidth(), getHeight());
  }

  /**
  * Returns true if Tile allows Actor movement
  *
  * @return  true if Tile allows Actor movement
  */
  public abstract boolean isMovable();

  public abstract void msg();

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

  public static Point toPixelCoords(Point coords) {
    Double doubleX = coords.getX() * SIZE + SIZE / 2;
    Double doubleY = coords.getY() * SIZE + SIZE / 2;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    Point pixelCoords = new Point(intX, intY);
    return pixelCoords;
  }

  public static boolean isDiagonal(Point coords) {
    return coords.getX() != 0 && coords.getY() != 0;
  }

  public static int magnitude(Point coords) {
    return (int) Math.sqrt(coords.getX() * coords.getX() + coords.getY() * coords.getY());
  }

  public static Point subtract(Point coords1, Point coords2) {
    Double doubleX = coords1.getX() - coords2.getX();
    Double doubleY = coords1.getY() - coords2.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point remainder(Point coords) {
    Double doubleX = (coords.getX() + MapGrid.MAPWIDTH * SIZE) % MapGrid.MAPWIDTH * SIZE;
    Double doubleY = (coords.getY() + MapGrid.MAPHEIGHT * SIZE) % MapGrid.MAPHEIGHT * SIZE;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point unit(Point coords) {
    return new Point(unit(coords.getX()), unit(coords.getY()));
  }

  private static int unit(double value) {
    return (int) Math.signum(value);
  }

  public static Point times(Point coords, int scale) {
    Double doubleX = coords.getX() * scale;
    Double doubleY = coords.getY() * scale;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point add(Point coords1, Point coords2) {
    Double doubleX = coords1.getX() + coords2.getX();
    Double doubleY = coords1.getY() + coords2.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

}
