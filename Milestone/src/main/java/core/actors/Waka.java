package core.actors;

import java.util.List;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.Game;
import core.actors.Ghost;
import core.movement.PlayerMovement;
import processing.core.PApplet;
import processing.core.PImage;

public class Waka extends Actor {

  private int lives;
  private Game game;
  private PImage spriteU;
  private PImage spriteD;
  private PImage spriteL;
  private PImage spriteR;
  private PImage spriteC;

  private long frames;

  private int livesY = (MapGrid.MAPHEIGHT - 1) * SIZE;

  public Waka(int x, int y, PApplet app, int speed, int lives, MapGrid mapGrid, 
  Game game) {
    // Inherit attributes and methods from Actor
    super(x, y, speed, new PlayerMovement(mapGrid, Direction.NONE));
    this.lives = lives;
    this.spriteC = app.loadImage("src/main/resources/playerClosed.png");
    this.spriteU = app.loadImage("src/main/resources/playerUp.png");
    this.spriteD = app.loadImage("src/main/resources/playerDown.png");
    this.spriteL = app.loadImage("src/main/resources/playerLeft.png");
    this.spriteR = app.loadImage("src/main/resources/playerRight.png");
    this.game = game;
  }

  /**
  * Set direction of Waka
  *
  * @param  direction  the Direction to be applied  
  */
  public void setNextDirection(Direction direction) {
    ((PlayerMovement) movement).setNextDirection(direction);
  }

  public void resetDirection() {
    ((PlayerMovement) movement).resetDirection();
  }

  // For animation
  public Direction getDirection() {
    return movement.getDirection();
  }

  public boolean eatFruit() {
    return ((PlayerMovement) movement).eatFruit(getCoords());
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

  // Helper function to get correct sprite for direction
  private PImage getSprite() {
    // Alternate between open-mouth and closed-mouth every 8 frames
    if (Math.floorDiv(frames++, 8) % 2 != 0) {
      return spriteC;
    } else if (getDirection() == Direction.UP) {
      return spriteU;
    } else if (getDirection() == Direction.DOWN) {
      return spriteD;
    } else if (getDirection() == Direction.LEFT) {
      return spriteL;
    } else if (getDirection() == Direction.RIGHT) {
      return spriteR;
    } else {
      return spriteR;
    }
  }

  /**
  * Draws Waka and number of lives to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app) {
    // Handling graphics
    // Draw Waka
    app.image(getSprite(), this.x, this.y);
    // Draw Lives
    for (int i = 0; i < lives; i++) {
      app.image(this.spriteR, (spriteR.width + spriteR.width/8) * i + (SIZE + spriteR.width) / 2, livesY);
    }
  }

  private Ghost touchGhost() {
    for (Ghost ghost : game.getGhosts()) {
      if (getCoords().equals(ghost.getCoords())) {
        return ghost;
      }
    }
    return null;
  }

  public boolean isAlive() {
    if (lives > 0) {
      return true;
    }
    return false;
  }

  public boolean checkKilled() {
    if (touchGhost() == null) {
      return false;
    }
    if (isAlive()) {
      lives--;
    }
    for (Ghost ghost : game.getGhosts()) {
      ghost.resetPixelCoords();
    }
    resetDirection();
    resetPixelCoords();
    return true;
  }

}
