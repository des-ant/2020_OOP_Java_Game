package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import core.actors.Ghost;
import core.actors.Waka;
import processing.core.PApplet;

public class Game {
  
  private Config config;
  private Waka waka;
  private List<Ghost> ghosts = new ArrayList<Ghost>();
  private MapGrid mapGrid;
  private PApplet app;

  public Waka getWaka() {
    return waka;
  }

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
    this.mapGrid = new MapGrid(config.getMapGrid(), app, this);
    // Create waka from coordinates
    this.waka = new Waka((int) mapGrid.getWakaCoord().getX(), (int) mapGrid.getWakaCoord().getY(), app, config.getSpeed(), config.getLives(), mapGrid);
    // Create ghosts from coordinates
    for (Point point : mapGrid.getGhostCoords()) {
      this.ghosts.add(new Ghost((int) point.getX(), (int) point.getY(), app, config.getSpeed(), config.getModeLengths(), mapGrid));
    }
    
  }
  
  /**
  * Draw game to PApplet window
  */
  public void draw() {
    // Game will continue to run until no fruits in map
    if (mapGrid.getNumFruit() > 0) {
      app.background(0, 0, 0);
      mapGrid.draw(app);
      waka.tick();
      waka.eatFruit();
  
      for (Ghost ghost : ghosts) {
        ghost.setGhostMode(GhostMode.SCATTER);
        ghost.tick();
        ghost.draw(app);
      }
      waka.draw(app);
    } else {
      app.background(150, 150, 150);
    }
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
