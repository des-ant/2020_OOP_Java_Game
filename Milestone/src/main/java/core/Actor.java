package core;

import java.awt.Point;

import core.movement.Movement;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Actor {

  // Attributes accessible by subclass Pacman, Ghost
  protected int x;
  protected int y;
  protected int xVel = 0;
  protected int yVel = 0;
  protected int speed;

  protected Movement movement;

  protected PImage sprite;

  public Actor(int x, int y, PImage sprite, int speed, Movement movement) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.speed = speed;
    this.movement = movement;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, sprite.width, sprite.height);
    app.fill(255, 0, 0);
    app.rect(this.x, this.y, Tile.SIZE, Tile.SIZE);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return Tile.SIZE;
  }

  public int getHeight() {
    return Tile.SIZE;
  }

  public int getEdgeLeft() {
    return x - (getWidth() / 2);
  }

  public int getEdgeRight() {
    return x + (getWidth() / 2);
  }

  public int getEdgeTop() {
    return y - (getHeight() / 2);
  }

  public int getEdgeBottom() {
    return y + (getHeight() / 2);
  }

  public int getCoordX() {
    return (x - Tile.SIZE / 2) / Tile.SIZE;
  }

  public int getCoordY() {
    return (y - Tile.SIZE / 2) / Tile.SIZE;
  }

  public String getCoord() {
    return getCoordX() + ", " + getCoordY();
  }

  public Point getCoords() {
    return new Point(getCoordX(), getCoordY());
  }

  public Point getPixelCoords() {
    return new Point(x, y);
  }

  public void setPixelCoords(Point coords) {
    Double doubleX = coords.getX();
    Double doubleY = coords.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    this.x = intX;
    this.y = intY;
  }

  private Point getNextCoords(Point currentCoords) {
    Direction nextDirection = movement.getNextDirection(currentCoords);
    Point newCoords = new Point(currentCoords);
    newCoords.translate(nextDirection.getX(), nextDirection.getY());
    return newCoords;
  }

  public void tick() {
    moveActor(speed);
  }

  private void moveActor(int distance) {
    // Get coordinates of Actor
    Point currentCoords = getCoords();
    // Get next coordinates
    Point nextCoords = getNextCoords(currentCoords);
    // Get centre pixel of next tile
    Point nextTileCentre = Tile.toPixelCoords(nextCoords);
    // Get pixel distance from next position to current position
    Point pixelDistance = Tile.subtract(nextTileCentre, getPixelCoords());
    // Next position lies along a diagonal, adjust distance to next tile
    if (Tile.isDiagonal(pixelDistance)) {
      // Get centre pixel of current tile being occupied by Actor
      Point currentTileCentre = Tile.toPixelCoords(currentCoords);
      // Get pixel distance from Tile centre to Actor centre
      int pixelMagnitude = Tile.magnitude(Tile.subtract(currentTileCentre, getPixelCoords()));
      distance -= pixelMagnitude;
      pixelDistance = Tile.subtract(nextTileCentre, currentTileCentre);
      setPixelCoords(currentTileCentre);
    }

    int pxDistMag = Tile.magnitude(pixelDistance);

    if (pxDistMag > 0) {
      if (pxDistMag == distance) {
        setPixelCoords(Tile.remainder(nextTileCentre));
      } else if (pxDistMag < distance) {
        setPixelCoords(Tile.remainder(nextTileCentre));
        moveActor(distance - pxDistMag);
      } else {
        Point distMoved = Tile.times(Tile.unit(pixelDistance), distance);
        Point pxMoved = Tile.add(getPixelCoords(), distMoved);
        Point pxRemainder = Tile.remainder(pxMoved);
        setPixelCoords(pxRemainder);
      }
    }
  }

}