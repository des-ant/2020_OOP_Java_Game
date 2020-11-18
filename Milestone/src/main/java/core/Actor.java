package core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Actor {
  
  // Each grid space is 16x16 pixels
  public static int SIZE = 16;

  // Attributes accessible by subclass Pacman, Ghost
  protected int x;
  protected int y;
  protected int xVel = 0;
  protected int yVel = 0;
  protected int speed;

  protected PImage sprite;

  public Actor(int x, int y, PImage sprite, int speed) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.speed = speed;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, sprite.width, sprite.height);
    app.fill(255, 0, 0);
    app.rect(this.x, this.y, SIZE, SIZE);
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

}