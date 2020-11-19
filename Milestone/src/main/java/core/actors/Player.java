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

  public int coordX;
  public int coordY;

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
    // System.out.println(getCoord());
    if (checkCollision(currentMove)) {
      this.collide();
      return;
    }
    // && setCoord() goes in if condition
    if (desiredMove != 0) {
      if (checkCollision(nextMove)) {
        System.out.println("next collides");
      } else {
        applyNextMove();
      }
    }
    this.x += this.xVel;
    this.y += this.yVel;
  }

  // public boolean isMoving() {
  //   return movementStrategy.isMoving();
  // }

  // public boolean setCoord() {
  //   int newCoordX = getCoordX();
  //   int newCoordY = getCoordY();
  //   if (coordX != newCoordX && coordY != newCoordY) {
  //     coordX = newCoordX;
  //     coordY = newCoordY;
  //     return true;
  //   } else if (coordX != newCoordX) {
  //     coordX = newCoordX;
  //     return true;
  //   } else if (coordY != newCoordY) {
  //     coordY = newCoordY;
  //     return true;
  //   }
  //   return false;
  // }

  public void collide() {
    this.currentMove = new int[] {0, 0, 0, 0};
    this.xVel = 0;
    this.yVel = 0;
  }

  public void applyNextMove() {
    switch(desiredMove) {
      case 1:
        moveU();
        desiredMove = 0;
        break;
      case 2:
        moveD();
        desiredMove = 0;
        break;
      case 3:
        moveL();
        desiredMove = 0;
        break;
      case 4:
        moveR();
        desiredMove = 0;
        break;
      default:
        break;
    }
  }

  // // Need to fix this
  // public boolean canMove() {
  //   if ((getX() - 8) % SIZE != 0 && (getX() - 8) % SIZE != 0) {
  //     return true;
  //   }
  //   return false;
  // }

  public boolean isMoving() {
    if (this.xVel != 0 || this.yVel != 0) {
      return true;
    }
    return false;
  }

  public boolean checkCollision(int[] moveArray) {
    for (Tile tile : this.tileList) {
      if (this.getEdgeRight() + moveArray[3] > tile.getEdgeLeft() && 
      this.getEdgeLeft() + moveArray[2] < tile.getEdgeRight() &&
      this.getEdgeBottom() + moveArray[1] > tile.getEdgeTop() &&
      this.getEdgeTop() + moveArray[0] < tile.getEdgeBottom()) {
        if (!tile.isMovable()) {
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
        nextMove = new int[] {-SIZE/16, 0, 0, 0};
        return;
      }
      moveU();
    } else if (app.keyCode == app.DOWN) {
      if (isMoving()) {
        desiredMove = 2;
        System.out.println(desiredMove);
        nextMove = new int[] {0, SIZE/16, 0, 0};
        return;
      }
      moveD();
    } else if (app.keyCode == app.LEFT) {
      if (isMoving()) {
        desiredMove = 3;
        System.out.println(desiredMove);
        nextMove = new int[] {0, 0, -SIZE/16, 0};
        return;
      }
      moveL();
    } else if (app.keyCode == app.RIGHT) {
      if (isMoving()) {
        desiredMove = 4;
        System.out.println(desiredMove);
        nextMove = new int[] {0, 0, 0, SIZE/16};
        return;
      }
      moveR();
    }
  }

  public void moveU() {
    this.currentMove = new int[] {-SIZE/16, 0, 0, 0};
    this.xVel = 0;
    this.yVel = -1 * speed;
  }

  public void moveD() {
    this.currentMove = new int[] {0, SIZE/16, 0, 0};
    this.xVel = 0;
    this.yVel = 1 * speed;
  }

  public void moveL() {
    this.currentMove = new int[] {0, 0, -SIZE/16, 0};
    this.xVel = -1 * speed;
    this.yVel = 0;
  }

  public void moveR() {
    this.currentMove = new int[] {0, 0, 0, SIZE/16};
    this.xVel = 1 * speed;
    this.yVel = 0;
  }

}