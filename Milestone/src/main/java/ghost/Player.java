package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class Player {

  private int x;
  private int y;

  private PImage sprite;

  private int xVel = 0;
  private int yVel = 0;

  private int speed;
  private int lives;

  public Player(int x, int y, PApplet app, int speed, int lives) {
    this.x = x;
    this.y = y;
    this.sprite = app.loadImage("src/main/resources/playerClosed.png");
    this.speed = speed;
    this.lives = lives;
  }

  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
  }
  
  public void tick() {
    // Handles logic
    this.x += this.xVel;
    this.y += this.yVel;
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