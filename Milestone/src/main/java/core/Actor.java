package core;

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
    return (x + Tile.SIZE/2) / Tile.SIZE;
  }

  public int getCoordY() {
    return (y + Tile.SIZE/2) / Tile.SIZE;
  }

  public String getCoord() {
    return getCoordX() + ", " + getCoordY();
  }

}