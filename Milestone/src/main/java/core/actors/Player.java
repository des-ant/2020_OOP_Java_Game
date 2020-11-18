package core.actors;

import java.util.List;

import core.Actor;
import core.Tile;
import processing.core.PApplet;

public class Player extends Actor {

  private int lives;

  public Player(int x, int y, PApplet app, int speed, int lives) {
    // Inherit attributes and methods from Actor
    super(x, y, app.loadImage("src/main/resources/playerClosed.png"), speed);
    this.lives = lives;
  }

  public void tick() {
    // Handles logic
    this.x += this.xVel;
    this.y += this.yVel;
  }

  public void collide() {
    this.xVel = 0;
    this.yVel = 0;
  }

  public void checkCollision(List<Tile> tileList) {
    for (Tile tile : tileList) {
      if (this.edgeRight > tile.getEdgeLeft() && 
      this.edgeLeft < tile.getEdgeRight() &&
      this.edgeBottom > tile.getEdgeTop() &&
      this.edgeTop < tile.getEdgeBottom()) {
        this.collide();
        return;
      }
    }
  }

  // public void setNextDirection(Direction direction) {
  // ((UserControlledMovementStrategy)
  // movementStrategy).setNextDirection//(direction);
  // }

  public void move(PApplet app) {
    if (app.keyCode == app.UP) {
      moveU();
    } else if (app.keyCode == app.DOWN) {
      moveD();
    } else if (app.keyCode == app.LEFT) {
      moveL();
    } else if (app.keyCode == app.RIGHT) {
      moveR();
    }
  }

  public void moveU() {
    this.xVel = 0;
    this.yVel = -1 * speed;
  }

  public void moveD() {
    this.xVel = 0;
    this.yVel = 1 * speed;
  }

  public void moveL() {
    this.xVel = -1 * speed;
    this.yVel = 0;
  }

  public void moveR() {
    this.xVel = 1 * speed;
    this.yVel = 0;
  }

}