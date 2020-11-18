package core.actors;

import java.util.List;

import core.Actor;
import core.Tile;
import processing.core.PApplet;

public class Player extends Actor {

  private int lives;

  private int[] nextMove = {0, 0, 0, 0};

  private List<Tile> tileList;

  public Player(int x, int y, PApplet app, int speed, int lives) {
    // Inherit attributes and methods from Actor
    super(x, y, app.loadImage("src/main/resources/playerClosed.png"), speed);
    this.lives = lives;
  }

  public void setTileList(List<Tile> tileList) {
    this.tileList = tileList;
  }

  public void tick(List<Tile> tileList) {
    // Handles logic
    setTileList(tileList);
    if (checkCollision()) {
      this.collide();
      return;
    }
    this.x += this.xVel;
    this.y += this.yVel;
  }

  public void collide() {
    this.xVel = 0;
    this.yVel = 0;
  }

  // public void edges() {
    // System.out.println("Right" + getEdgeRight());
    // System.out.println("Left" + getEdgeLeft());
    // System.out.println("Bottom" + getEdgeBottom());
    // System.out.println("Top" + getEdgeTop());
  // }

  public boolean checkCollision() {
    for (Tile tile : this.tileList) {
      if (this.getEdgeRight() + this.nextMove[3] > tile.getEdgeLeft() && 
      this.getEdgeLeft() + this.nextMove[2] < tile.getEdgeRight() &&
      this.getEdgeBottom() + this.nextMove[1] > tile.getEdgeTop() &&
      this.getEdgeTop() + this.nextMove[0] < tile.getEdgeBottom()) {
        if (!tile.isMovable()) {
          System.out.println("Height" + getHeight());
          System.out.println("Width" + getWidth());
          System.out.println("Right" + getEdgeRight());
          System.out.println("Left" + getEdgeLeft());
          System.out.println("Bottom" + getEdgeBottom());
          System.out.println("Top" + getEdgeTop());
          System.out.println("Tile Right" + tile.getEdgeRight());
          System.out.println("Tile Left" + tile.getEdgeLeft());
          System.out.println("Tile Bottom" + tile.getEdgeBottom());
          System.out.println("Tile Top" + tile.getEdgeTop());
          tile.msg();
          return true;
        }
      }
    }
    return false;
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
    this.nextMove = new int[] {2, 0, 0, 0};
    if (checkCollision()) {
      this.collide();
      this.y = this.y + 2;
      return;
    }
    this.xVel = 0;
    this.yVel = -1 * speed;
  }

  public void moveD() {
    this.nextMove = new int[] {0, 1, 0, 0};
    if (checkCollision()) {
      this.collide();
      this.y = this.y - 2;
      return;
    }
    this.xVel = 0;
    this.yVel = 1 * speed;
  }

  public void moveL() {
    this.nextMove = new int[] {0, 0, 1, 0};
    if (checkCollision()) {
      this.collide();
      this.x++;
      return;
    }
    this.xVel = -1 * speed;
    this.yVel = 0;
  }

  public void moveR() {
    this.nextMove = new int[] {0, 0, 0, 1};
    if (checkCollision()) {
      this.collide();
      this.x--;
      return;
    }
    this.xVel = 1 * speed;
    this.yVel = 0;
  }

}