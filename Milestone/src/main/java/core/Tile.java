package core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {

  protected int x;
  protected int y;

  protected PImage sprite;

  public Tile(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }
  
}