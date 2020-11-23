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
import core.movement.Scatter;
import core.movement.ScatterMode;
import processing.core.PApplet;
import processing.core.PImage;

public class Ghost extends Actor{

  protected PImage sprite;
  protected GhostMode ghostMode;
  protected List<Integer> modeLengths;

  public Ghost(int x, int y, PApplet app, int speed, List<Integer> modeLengths, 
  MapGrid mapGrid) {
    super(x, y, speed, new TargetMovement(mapGrid, Direction.NONE, null, 
    new ChaserTarget(mapGrid), new Scatter(mapGrid, ScatterMode.TL)));
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
    ((TargetMovement) movement).setTargetMode(ghostMode);
  }

  public List<Integer> getModeLengths() {
    return modeLengths;
  }

  public Point getTargetCoord() {
    return ((TargetMovement) movement).getTargetCoord();
  }

}