package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

  public static final int WIDTH = 448;
  public static final int HEIGHT = 576;

  private Player player;
  private Ghost ghost;
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
    this.ghost = new Ghost(60, 60, this.loadImage("src/main/resources/ghost.png"));
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
