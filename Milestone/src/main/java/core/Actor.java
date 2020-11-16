package core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Actor {
  
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
  }

}