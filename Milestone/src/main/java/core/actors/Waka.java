package core.actors;

import java.util.List;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.Tile;
import core.movement.PlayerMovement;
import processing.core.PApplet;

public class Waka extends Actor {

  private int lives;

  public Waka(int x, int y, PApplet app, int speed, int lives, MapGrid mapGrid) {
    // Inherit attributes and methods from Actor
    super(x, y, app.loadImage("src/main/resources/playerClosed.png"), speed, new PlayerMovement(mapGrid, Direction.NONE));
    this.lives = lives;
  }

  /**
  * Set direction of Waka
  *
  * @param  direction  the Direction to be applied  
  */
  public void setNextDirection(Direction direction) {
    ((PlayerMovement) movement).setNextDirection(direction);
  }

  // For animation
  public Direction getDirection() {
    return movement.getDirection();
  }

  // For animation
  public boolean isMoving() {
    return movement.isMoving();
  }

  /**
  * Set direction of Waka using arrow keys
  *
  * @param  app  the current PApplet window that Waka is in  
  */
  public void move(PApplet app) {
    if (app.keyCode == app.UP) {
      setNextDirection(Direction.UP);
    } else if (app.keyCode == app.DOWN) {
      setNextDirection(Direction.DOWN);
    } else if (app.keyCode == app.LEFT) {
      setNextDirection(Direction.LEFT);
    } else if (app.keyCode == app.RIGHT) {
      setNextDirection(Direction.RIGHT);
    }
  }

}
