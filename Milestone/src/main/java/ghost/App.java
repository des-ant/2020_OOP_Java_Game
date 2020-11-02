package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

  public static final int WIDTH = 448;
  public static final int HEIGHT = 576;

  private Config config;
  private Player player;
  private Ghost ghost;
  private Wall wall;
  // private Map map;

  public App() {
    //Set up your objects
  }

  public void setup() {
    frameRate(60);

    // Show black background on window launch
    background(0, 0, 0);

    // Load Map and Config file
    this.config = new Config("config.json");

    // Load images here
    this.player = new Player(30, 30, this, config.getSpeed(), config.getLives());
    this.ghost = new Ghost(60, 60, this);
    this.wall = new Wall(90, 90, this, 1);
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    // Loop
    background(0, 0, 0);
    this.player.tick();

    this.player.draw(this);
    this.ghost.draw(this);
    this.wall.draw(this);
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
