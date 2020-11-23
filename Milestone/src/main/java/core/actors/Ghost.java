package core.actors;

import java.util.List;

import core.Actor;
import core.PointMaths;
import core.GhostMode;
import processing.core.PApplet;
import processing.core.PImage;

public class Ghost extends Actor{

  protected PImage sprite;
  protected GhostMode ghostMode;
  protected List<Integer> modeLengths;

  public Ghost(int x, int y, PApplet app, int speed, List<Integer> modeLengths) {
    super(x, y, speed, null);
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
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, sprite.width, sprite.height);
    app.fill(255, 0, 0, 120);
    app.rect(this.x, this.y, SIZE, SIZE);
    app.fill(255, 100, 100, 120);
    int closestX = (int) PointMaths.toPixelCoords(getCoords()).getX();
    int closestY = (int) PointMaths.toPixelCoords(getCoords()).getY();
    app.rect(closestX, closestY, SIZE, SIZE);
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

}