package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class Ghost {

  private int x;
  private int y;

  private PImage sprite;

  public Ghost(int x, int y, PImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }
  
}