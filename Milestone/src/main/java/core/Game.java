package core;

import core.actors.Ghost;
import core.actors.Waka;
import processing.core.PApplet;

public class Game {
  
  private Config config;
  private Waka waka;
  private Ghost ghost;
  private MapGrid mapGrid;
  private PApplet app;

  /**
  * Constructs Game from given Papplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public Game(PApplet app) {
    // Load Papplet
    this.app = app;

    // Load Config file
    this.config = new Config("config.json");
  }

  /**
  * Set up game
  */
  public void setup() {
    app.frameRate(60);

    // Draw images centered at the given x and y position
    app.imageMode(app.CENTER);

    // Show black background on window launch
    app.background(0, 0, 0);

    app.rectMode(app.CENTER);
    app.stroke(255, 255, 255);

    // MapGrid will load wall and fruit images
    this.mapGrid = new MapGrid(config.getMapGrid(), app);
    // Load images here
    this.waka = new Waka(mapGrid.getWakaX(), mapGrid.getWakaY(), app, config.getSpeed(), config.getLives(), mapGrid);
    this.ghost = new Ghost(mapGrid.getGhostX(), mapGrid.getGhostY(), app, config.getSpeed());
    
  }
  
  /**
  * Draw game to PApplet window
  */
  public void draw() {
    app.background(0, 0, 0);
    mapGrid.draw(app);
    waka.tick();

    ghost.draw(app);
    waka.draw(app);
  }

  /**
  * Send user input to PApplet window
  */
  public void keyPressed() {
    // Arrow keys input
    if (app.key == app.CODED) {
      waka.move(app);
    }
  }

}
