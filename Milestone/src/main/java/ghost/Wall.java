package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class Wall {

  // Location
  private int x;
  private int y;
  // Image
  private PImage sprite;

  public Wall(int x, int y, PApplet app, int type) {
    this.x = x;
    this.y = y;
    switch(type) {
      // Horizontal wall
      case 1:
        this.sprite = app.loadImage("src/main/resources/horizontal.png");
        break;
      // Vertical wall
      case 2:
        this.sprite = app.loadImage("src/main/resources/vertical.png");
        break;
      // Corner wall (up + left)
      case 3:
        this.sprite = app.loadImage("src/main/resources/upLeft.png");
        break;
      // Corner wall (up + right)
      case 4:
        this.sprite = app.loadImage("src/main/resources/upRight.png");
        break;
      // Corner wall (down + left)
      case 5:
        this.sprite = app.loadImage("src/main/resources/downLeft.png");
        break;
      // Corner wall (down + right)
      case 6:
        this.sprite = app.loadImage("src/main/resources/downRight.png");
        break;
      default:
        break;
    }
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }
  
}