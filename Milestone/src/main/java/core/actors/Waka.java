package core.actors;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.movement.PlayerMovement;
import processing.core.PApplet;
import processing.core.PImage;

public class Waka extends Actor {

  private int lives;

  private PImage spriteU;
  private PImage spriteD;
  private PImage spriteL;
  private PImage spriteR;
  private PImage spriteC;

  private long frames;

  private int livesY = (MapGrid.MAPHEIGHT - 1) * SIZE;

  public Waka(int x, int y, PApplet app, int speed, int lives, MapGrid mapGrid) {
    // Inherit attributes and methods from Actor
    super(x, y, speed, new PlayerMovement(mapGrid, Direction.NONE));
    this.lives = lives;
    this.spriteC = app.loadImage("src/main/resources/playerClosed.png");
    this.spriteU = app.loadImage("src/main/resources/playerUp.png");
    this.spriteD = app.loadImage("src/main/resources/playerDown.png");
    this.spriteL = app.loadImage("src/main/resources/playerLeft.png");
    this.spriteR = app.loadImage("src/main/resources/playerRight.png");
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
    app.image(getSprite(), this.x, this.y);
    // app.fill(0, 0);
    // app.rect(this.x, this.y, spriteR.width, spriteR.height);
    // app.fill(255, 0, 0, 120);
    // app.rect(this.x, this.y, SIZE, SIZE);
    // app.fill(255, 100, 100, 120);
    // int closestX = (int) PointMaths.toPixelCoords(getCoords()).getX();
    // int closestY = (int) PointMaths.toPixelCoords(getCoords()).getY();
    // app.rect(closestX, closestY, SIZE, SIZE);

    for (int i = 0; i < lives; i++) {
      app.image(this.spriteR, (spriteR.width + spriteR.width/8) * i + (SIZE + spriteR.width) / 2, livesY);
    }
  }

}
