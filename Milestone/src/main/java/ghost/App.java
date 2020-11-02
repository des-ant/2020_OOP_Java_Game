package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

  public static final int WIDTH = 448;
  public static final int HEIGHT = 576;

  private Config config;
  private Player player;
  private Ghost ghost;
  private Map map;

  public App() {
    //Set up your objects
  }

  public void setup() {
    frameRate(60);

    // Draw images centered at the given x and y position
    imageMode(CENTER);

    // Show black background on window launch
    background(0, 0, 0);

    // Load Map and Config file
    this.config = new Config("config.json");
    this.map = new Map(config.getMap(), this);

    // Load images here
    this.player = new Player(map.getPlayerX(), map.getPlayerY(), this, config.getSpeed(), config.getLives());
    this.ghost = new Ghost(map.getGhostX(), map.getGhostY(), this);
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    // Loop
    background(0, 0, 0);
    this.map.draw(this);

    this.player.tick();

    this.player.draw(this);
    this.ghost.draw(this);
  }

  public void keyPressed() {
    // Arrow keys input
    if (key == CODED) {
      if (keyCode == UP) {
        player.moveU();
      } else if (keyCode == DOWN) {
        player.moveD();
      } else if (keyCode == LEFT) {
        player.moveL();
      } else if (keyCode == RIGHT) {
        player.moveR();
      }
    }
  }

  public static void main(String[] args) {
    PApplet.main("ghost.App");
  }

}
