package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

  public static final int WIDTH = 448;
  public static final int HEIGHT = 576;

  private Game game;

  public App() {
    //Set up your objects

    // Create game instance
    this.game = new Game(this);
  }

  public void setup() {
    game.setup();
  }

  public void settings() {
    size(WIDTH, HEIGHT);
  }

  public void draw() {
    // Loop
    game.draw();
  }

  public void keyPressed() {
    game.keyPressed();
  }

  public static void main(String[] args) {
    PApplet.main("ghost.App");
  }

}
