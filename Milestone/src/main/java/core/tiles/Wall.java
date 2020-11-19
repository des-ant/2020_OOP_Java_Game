package core.tiles;

import core.Tile;
import processing.core.PApplet;

public class Wall extends Tile {

  PApplet app;

  public Wall(int x, int y, PApplet app, int type) {
    super(x, y, app.loadImage(wallSprite(type)));
    this.app = app;
  }

  private static String wallSprite(int type) {
    String src = "";
    switch (type) {
      // Horizontal wall
      case 1:
        src = "src/main/resources/horizontal.png";
        break;
      // Vertical wall
      case 2:
        src = "src/main/resources/vertical.png";
        break;
      // Corner wall (up + left)
      case 3:
        src = "src/main/resources/upLeft.png";
        break;
      // Corner wall (up + right)
      case 4:
        src = "src/main/resources/upRight.png";
        break;
      // Corner wall (down + left)
      case 5:
        src = "src/main/resources/downLeft.png";
        break;
      // Corner wall (down + right)
      case 6:
        src = "src/main/resources/downRight.png";
        break;
      default:
        break;
    }
    return src;
  }
  
  public boolean isMovable() {
    return false;
  }

  public void msg() {
    System.out.println("Wall at " + (getX() - 8)/16 + ", " + (getY() - 8)/16);
  }

}