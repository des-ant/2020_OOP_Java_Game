package core.actors;

import java.util.List;
import java.awt.Point;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.movement.TargetMovement;
import core.GhostMode;
import core.actors.GhostType;
import core.movement.ChaserTarget;
import core.movement.RandomTarget;
import core.movement.Scatter;
import core.movement.ScatterMode;
import processing.core.PApplet;
import processing.core.PImage;

public class Ghost extends Actor{

  private PImage sprite;
  private GhostMode ghostMode;


  public Ghost(int x, int y, PApplet app, int speed, 
  MapGrid mapGrid, GhostType ghostType) {
    super(x, y, speed, new TargetMovement(mapGrid, Direction.NONE, null, 
    new ChaserTarget(mapGrid), new Scatter(mapGrid, ghostType.getScatterMode()),
    new RandomTarget(mapGrid)));
    this.sprite = app.loadImage(ghostType.getSprite());
    this.ghostMode = GhostMode.CHASE;
  }
  
  /**
  * Draws Actor to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app, boolean debug) {
    // Handling graphics
    // Draw debug line
    if (debug) {
      int targetX = (int) PointMaths.toPixelCoords(getTargetCoord()).getX();
      int targetY = (int) PointMaths.toPixelCoords(getTargetCoord()).getY();
      app.line(x, y, targetX, targetY);
    }

    // Draw ghost
    app.image(this.sprite, this.x, this.y);
  }

  public GhostMode getGhostMode() {
    return ghostMode;
  }

  public void setGhostMode(GhostMode ghostMode) {
    this.ghostMode = ghostMode;
    ((TargetMovement) movement).setTargetMode(ghostMode);
  }

  public Point getTargetCoord() {
    return ((TargetMovement) movement).getTargetCoord();
  }

  public Direction getDirection() {
    return movement.getDirection();
  }

}