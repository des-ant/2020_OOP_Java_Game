package core.actors;

import processing.core.PApplet;

import core.Actor;

public class Player extends Actor {

  private int lives;

  public Player(int x, int y, PApplet app, int speed, int lives) {
    super(x, y, app.loadImage("src/main/resources/playerClosed.png"), speed);
    this.lives = lives;
  }

  public void tick() {
    // Handles logic
    this.x += this.xVel;
    this.y += this.yVel;
  }

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