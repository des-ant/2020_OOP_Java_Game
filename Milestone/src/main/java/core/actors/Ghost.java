package core.actors;

import java.util.List;
import java.awt.Point;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.movement.TargetMovement;
import core.GhostMode;
import core.movement.ChaserTarget;
import processing.core.PApplet;
import processing.core.PImage;

public class Ghost extends Actor{

  protected PImage sprite;
  protected GhostMode ghostMode;
  protected List<Integer> modeLengths;

  public Ghost(int x, int y, PApplet app, int speed, List<Integer> modeLengths, 
  MapGrid mapGrid) {
    super(x, y, speed, new TargetMovement(mapGrid, Direction.NONE, null, 
    new ChaserTarget(mapGrid), new ChaserTarget(mapGrid)));
    this.sprite = app.loadImage("src/main/resources/ghost.png");
    this.ghostMode = GhostMode.SCATTER;
    this.modeLengths = modeLengths;
  }
  
  /**
  * Draws Actor to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app) {
    // Handling graphics
    // Draw debug line
    int targetX = (int) PointMaths.toPixelCoords(getTargetCoord()).getX();
    int targetY = (int) PointMaths.toPixelCoords(getTargetCoord()).getY();
    app.line(x, y, targetX, targetY);

    // Draw ghost
    app.image(this.sprite, this.x, this.y);
  }

  public GhostMode getGhostMode() {
    return ghostMode;
  }

  public void setGhostMode(GhostMode ghostMode) {
    this.ghostMode = ghostMode;
  }

  public List<Integer> getModeLengths() {
    return modeLengths;
  }

  public Point getTargetCoord() {
    return ((TargetMovement) movement).getTargetCoord();
  }

  // /**
  // * Set direction of Waka
  // *
  // * @param  direction  the Direction to be applied  
  // */
  // public void setNextDirection(Direction direction) {
  //   ((TargetMovement) movement).setNextDirection(direction);
  // }

  // /**
  // * Set direction of Waka using arrow keys
  // *
  // * @param  app  the current PApplet window that Waka is in  
  // */
  // public void move() {
  //   setNextDirection(Direction.RIGHT);
  // }

}