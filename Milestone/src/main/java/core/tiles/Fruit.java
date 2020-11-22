package core.tiles;

import core.Tile;
import processing.core.PApplet;

public class Fruit extends Tile {

  public Fruit(int x, int y, PApplet app) {
    super(x, y, app.loadImage("src/main/resources/fruit.png"));
  }
  
  /**
  * Returns true if Fruit tile allows Actor movement
  *
  * @return  true if Fruit tile allows Actor movement
  */
  @Override
  public boolean isMovable() {
    return true;
  }

  public void msg() {
    System.out.println("Fruit at " + getX() + ", " + getY());
  }

}
