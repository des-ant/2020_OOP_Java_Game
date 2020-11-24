package core.tiles;

import core.Tile;
import processing.core.PApplet;

public class Wall extends Tile {

  PApplet app;

  public Wall(int x, int y, PApplet app, WallType wallType) {
    super(x, y, app.loadImage(wallType.getSprite()));
    this.app = app;
  }

  /**
  * Returns true if Wall tile allows Actor movement
  *
  * @return  true if Wall tile allows Actor movement
  */
  @Override
  public boolean isMovable() {
    return false;
  }

}
