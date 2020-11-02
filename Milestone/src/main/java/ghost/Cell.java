package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Cell {

  private int x;
  private int y;

  private PImage sprite;

  public Cell(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }
  
}