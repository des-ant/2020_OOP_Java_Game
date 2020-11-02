package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

  public static final int WIDTH = 448;
  public static final int HEIGHT = 576;

  private Player player;
  // private Ghost ghost;
  // private Map map;

  public App() {
    //Set up your objects
  }

  public void setup() {
    frameRate(60);

    // Show black background on window launch
    background(0, 0, 0);

    // Load images here
    this.player = new Player(30, 30, this.loadImage("src/main/resources/playerClosed.png"));
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    // Loop
    background(0, 0, 0);

    this.player.draw(this);

  }

  public static void main(String[] args) {
    PApplet.main("ghost.App");
  }

}
