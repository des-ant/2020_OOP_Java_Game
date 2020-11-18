package core.actors;

import java.util.List;

import core.Actor;
import core.Tile;
import processing.core.PApplet;

public class Player extends Actor {

  private int lives;

  private int[] currentMove = {0, 0, 0, 0};

  private List<Tile> tileList;

  public int desiredMove = 0;

  private int[] nextMove = {0, 0, 0, 0};

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
    // applyNextMove();
    this.x += this.xVel;
    this.y += this.yVel;
  }

  public void collide() {
    this.currentMove = new int[] {0, 0, 0, 0};
    this.xVel = 0;
    this.yVel = 0;
  }

  public void applyNextMove() {
    switch(desiredMove) {
      case 1:
        nextMove = new int[] {-1, 0, 0, 0};
        if (canMove()) {
          moveU();
          desiredMove = 0;
        }
        break;
      case 2:
        nextMove = new int[] {0, 1, 0, 0};
        if (canMove()) {
          moveD();
          desiredMove = 0;
        }
        break;
      case 3:
        nextMove = new int[] {0, 0, -1, 0};
        if (canMove()) {
          moveL();
          desiredMove = 0;
        }
        break;
      case 4:
        nextMove = new int[] {0, 0, 0, 1};
        if (canMove()) {
          moveR();
          desiredMove = 0;
        }
        break;
      default:
        break;
    }
  }

  // public boolean canMove() {
  //   switch(desiredMove) {
  //     case 1:
  //       break;
  //     case 2:
  //       break;
  //     case 3:
  //       break;
  //     case 4:
  //       break;
  //     default:
  //       break;
  //   }
  //   return !checkCollision();
  // }

  // Need to fix this
  public boolean canMove() {
    for (Tile tile : this.tileList) {
      if (this.getEdgeRight() + this.nextMove[3] > tile.getEdgeLeft() && 
      this.getEdgeLeft() + this.nextMove[2] < tile.getEdgeRight() &&
      this.getEdgeBottom() + this.nextMove[1] > tile.getEdgeTop() &&
      this.getEdgeTop() + this.nextMove[0] < tile.getEdgeBottom()) {
        if (!tile.isMovable()) {
          tile.msg();
          return true;
        }
      }
    }
    return false;
  }

  public boolean isMoving() {
    if (this.xVel != 0 || this.yVel != 0) {
      return true;
    }
    return false;
  }

  public boolean checkCollision() {
    for (Tile tile : this.tileList) {
      if (this.getEdgeRight() + this.currentMove[3] > tile.getEdgeLeft() && 
      this.getEdgeLeft() + this.currentMove[2] < tile.getEdgeRight() &&
      this.getEdgeBottom() + this.currentMove[1] > tile.getEdgeTop() &&
      this.getEdgeTop() + this.currentMove[0] < tile.getEdgeBottom()) {
        if (!tile.isMovable()) {
          // System.out.println("Height" + getHeight());
          // System.out.println("Width" + getWidth());
          // System.out.println("Right" + getEdgeRight());
          // System.out.println("Left" + getEdgeLeft());
          // System.out.println("Bottom" + getEdgeBottom());
          // System.out.println("Top" + getEdgeTop());
          // System.out.println("Tile Right" + tile.getEdgeRight());
          // System.out.println("Tile Left" + tile.getEdgeLeft());
          // System.out.println("Tile Bottom" + tile.getEdgeBottom());
          // System.out.println("Tile Top" + tile.getEdgeTop());
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
      if (isMoving()) {
        desiredMove = 1;
        System.out.println(desiredMove);
        return;
      }
      moveU();
    } else if (app.keyCode == app.DOWN) {
      if (isMoving()) {
        desiredMove = 2;
        System.out.println(desiredMove);
        return;
      }
      moveD();
    } else if (app.keyCode == app.LEFT) {
      if (isMoving()) {
        desiredMove = 3;
        System.out.println(desiredMove);
        return;
      }
      moveL();
    } else if (app.keyCode == app.RIGHT) {
      System.out.println(canMove());
      if (isMoving()) {
        desiredMove = 4;
        System.out.println(desiredMove);
        return;
      }
      moveR();
    }
  }

  public void moveU() {
    this.currentMove = new int[] {-1, 0, 0, 0};
    this.xVel = 0;
    this.yVel = -1 * speed;
  }

  public void moveD() {
    this.currentMove = new int[] {0, 1, 0, 0};
    this.xVel = 0;
    this.yVel = 1 * speed;
  }

  public void moveL() {
    this.currentMove = new int[] {0, 0, -1, 0};
    this.xVel = -1 * speed;
    this.yVel = 0;
  }

  public void moveR() {
    this.currentMove = new int[] {0, 0, 0, 1};
    this.xVel = 1 * speed;
    this.yVel = 0;
  }

}