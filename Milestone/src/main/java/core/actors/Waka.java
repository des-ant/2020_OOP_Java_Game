package core.actors;

import java.util.List;

import core.Actor;
import core.Direction;
import core.MapGrid;
import core.Tile;
import core.movement.PlayerMovement;
import processing.core.PApplet;

public class Waka extends Actor {

  private int lives;

  private int[] currentMove = { 0, 0, 0, 0 };

  private List<Tile> tileList;

  public int desiredMove = 0;

  private int[] nextMove = { 0, 0, 0, 0 };

  public Waka(int x, int y, PApplet app, int speed, int lives, MapGrid mapGrid) {
    // Inherit attributes and methods from Actor
    super(x, y, app.loadImage("src/main/resources/playerClosed.png"), speed, new PlayerMovement(mapGrid, Direction.NONE));
    this.lives = lives;
  }

  public void setTileList(List<Tile> tileList) {
    this.tileList = tileList;
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

  // For animation
  public boolean isMoving() {
    return movement.isMoving();
  }

  // public void tick() {
    // Handles logic


    // setTileList(tileList);
    // // System.out.println(getCoord());
    // if (checkCollision(currentMove)) {
    //   this.collide();
    //   return;
    // }
    // // && setCoord() goes in if condition
    // if (desiredMove != 0) {
    //   if (checkCollision(nextMove)) {
    //     System.out.println("next collides");
    //   } else {
    //     applyNextMove();
    //   }
    // }
    // this.x += this.xVel;
    // this.y += this.yVel;
  // }

  // public boolean isMoving() {
  // return movementStrategy.isMoving();
  // }

  // public boolean setCoord() {
  // int newCoordX = getCoordX();
  // int newCoordY = getCoordY();
  // if (coordX != newCoordX && coordY != newCoordY) {
  // coordX = newCoordX;
  // coordY = newCoordY;
  // return true;
  // } else if (coordX != newCoordX) {
  // coordX = newCoordX;
  // return true;
  // } else if (coordY != newCoordY) {
  // coordY = newCoordY;
  // return true;
  // }
  // return false;
  // }

  // public void collide() {
  //   this.currentMove = new int[] { 0, 0, 0, 0 };
  //   this.xVel = 0;
  //   this.yVel = 0;
  // }

  // public void applyNextMove() {
  //   switch (desiredMove) {
  //     case 1:
  //       moveU();
  //       desiredMove = 0;
  //       break;
  //     case 2:
  //       moveD();
  //       desiredMove = 0;
  //       break;
  //     case 3:
  //       moveL();
  //       desiredMove = 0;
  //       break;
  //     case 4:
  //       moveR();
  //       desiredMove = 0;
  //       break;
  //     default:
  //       break;
  //   }
  // }

  // public boolean isMoving() {
  //   if (this.xVel != 0 || this.yVel != 0) {
  //     return true;
  //   }
  //   return false;
  // }

  // public boolean checkCollision(int[] moveArray) {
  //   for (Tile tile : this.tileList) {
  //     if (this.getEdgeRight() + moveArray[3] > tile.getEdgeLeft()
  //         && this.getEdgeLeft() + moveArray[2] < tile.getEdgeRight()
  //         && this.getEdgeBottom() + moveArray[1] > tile.getEdgeTop()
  //         && this.getEdgeTop() + moveArray[0] < tile.getEdgeBottom()) {
  //       if (!tile.isMovable()) {
  //         tile.msg();
  //         System.out.println(getCoordX());
  //         System.out.println(getCoordY());
  //         return true;
  //       } else {
  //         tile.msg();
  //         tileList.remove(tile);
  //         return false;
  //       }
  //     }
  //   }
  //   return false;
  // }

  // public void setNextDirection(Direction direction) {
  // ((UserControlledMovementStrategy)
  // movementStrategy).setNextDirection//(direction);
  // }

  // public void move(PApplet app) {
  //   if (app.keyCode == app.UP) {
  //     if (isMoving()) {
  //       desiredMove = 1;
  //       System.out.println(desiredMove);
  //       nextMove = new int[] { -Tile.SIZE / 16, 0, 0, 0 };
  //       return;
  //     }
  //     moveU();
  //   } else if (app.keyCode == app.DOWN) {
  //     if (isMoving()) {
  //       desiredMove = 2;
  //       System.out.println(desiredMove);
  //       nextMove = new int[] { 0, Tile.SIZE / 16, 0, 0 };
  //       return;
  //     }
  //     moveD();
  //   } else if (app.keyCode == app.LEFT) {
  //     if (isMoving()) {
  //       desiredMove = 3;
  //       System.out.println(desiredMove);
  //       nextMove = new int[] { 0, 0, -Tile.SIZE / 16, 0 };
  //       return;
  //     }
  //     moveL();
  //   } else if (app.keyCode == app.RIGHT) {
  //     if (isMoving()) {
  //       desiredMove = 4;
  //       System.out.println(desiredMove);
  //       nextMove = new int[] { 0, 0, 0, Tile.SIZE / 16 };
  //       return;
  //     }
  //     moveR();
  //   }
  // }

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

  // public void move(PApplet app) {
  //   if (app.keyCode == app.UP)
  //   switch (app.keyCode) {
  //     case app.UP:
  //       setNextDirection(Direction.UP);
  //       break;
  //     case app.DOWN:
  //       setNextDirection(Direction.DOWN);
  //       break;
  //     case app.LEFT:
  //       setNextDirection(Direction.LEFT);
  //       break;
  //     case app.RIGHT:
  //       setNextDirection(Direction.RIGHT);
  //       break;
  //   }
  // }

  // public void moveU() {
  //   this.currentMove = new int[] { -Tile.SIZE / 16, 0, 0, 0 };
  //   this.xVel = 0;
  //   this.yVel = -1 * speed;
  // }

  // public void moveD() {
  //   this.currentMove = new int[] { 0, Tile.SIZE / 16, 0, 0 };
  //   this.xVel = 0;
  //   this.yVel = 1 * speed;
  // }

  // public void moveL() {
  //   this.currentMove = new int[] { 0, 0, -Tile.SIZE / 16, 0 };
  //   this.xVel = -1 * speed;
  //   this.yVel = 0;
  // }

  // public void moveR() {
  //   this.currentMove = new int[] { 0, 0, 0, Tile.SIZE / 16 };
  //   this.xVel = 1 * speed;
  //   this.yVel = 0;
  // }

}
