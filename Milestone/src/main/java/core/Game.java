package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import core.actors.Ghost;
import core.actors.Waka;
import core.actors.GhostType;
import processing.core.PApplet;

public class Game {
  
  private Config config;
  private Waka waka;
  private List<Ghost> ghosts = new ArrayList<Ghost>();
  private List<Integer> modeLengths;
  private MapGrid mapGrid;
  private PApplet app;
  private boolean debug;
  private long initialTimestamp;
  private long currentTimestamp;
  private int modeIndex;

  public MapGrid getMapGrid() {
    return mapGrid;
  }

  public Waka getWaka() {
    return waka;
  }

  public boolean getDebug() {
    return debug;
  }

  public List<Ghost> getGhosts() {
    return ghosts;
  }

  public long timer() {
    return currentTimestamp - initialTimestamp;
  }

  public void setInitialTimestamp() {
    this.initialTimestamp = System.currentTimeMillis() / 1000;
  }

  public void setCurrentTimestamp() {
    this.currentTimestamp = System.currentTimeMillis() / 1000;
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
    // Debug mode off
    this.debug = false;
    // Set initial time
    this.initialTimestamp = System.currentTimeMillis() / 1000;
    this.currentTimestamp = System.currentTimeMillis() / 1000;
    this.modeIndex = 0;
  }

  /**
  * Constructs Game from given Papplet window
  * Overloaded method for testing
  *
  * @param  app     the PApplet window to be drawn to
  * @param  config  the name of config file
  */
  public Game(PApplet app, String config) {
    // Load Papplet
    this.app = app;
    // Load Config file
    this.config = new Config(config);
    // Debug mode off
    this.debug = false;
    // Set initial time
    this.initialTimestamp = System.currentTimeMillis() / 1000;
    this.currentTimestamp = System.currentTimeMillis() / 1000;
    this.modeIndex = 0;
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
    this.waka = new Waka((int) mapGrid.getWakaCoord().getX(),
     (int) mapGrid.getWakaCoord().getY(), app, config.getSpeed(), 
     config.getLives(), mapGrid, this);
    // Store mode lengths
    this.modeLengths = config.getModeLengths();
    // Create ghosts from coordinates
    List<GhostType> ghostTypes = mapGrid.getGhostTypes();
    int index = 0;
    for (Point point : mapGrid.getGhostCoords()) {
      this.ghosts.add(new Ghost((int) point.getX(), (int) point.getY(), 
      app, config.getSpeed(), mapGrid, 
      ghostTypes.get(index)));
      index++;
    }
    
  }
  
  /**
  * Draw game to PApplet window
  */
  public void draw() {
    // Game will continue to run until no fruits in map
    // and Waka still alive
    if (mapGrid.getNumFruit() > 0 && waka.isAlive()) {
      app.background(0, 0, 0);

      // Change mode according to config file
      setCurrentTimestamp();
      if (timer() >= modeLengths.get(modeIndex)) {
        if (modeIndex < modeLengths.size() - 1) {
          modeIndex++;
        } else {
          modeIndex = 0;
        }
        setInitialTimestamp();
      }

      mapGrid.draw(app);
      for (Ghost ghost : ghosts) {
        if (modeIndex % 2 == 0) {
          ghost.setGhostMode(GhostMode.CHASE);
        } else {
          ghost.setGhostMode(GhostMode.SCATTER);
        }
        ghost.tick();
        ghost.draw(app, debug);
      }
      // Reset mode if Waka killed
      if (waka.checkKilled()) {
        setInitialTimestamp();
        modeIndex = 0;
      };
      waka.eatFruit();
      waka.tick();
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
    if (app.key == ' ') {
      if (debug) {
        debug = false;
      } else {
        debug = true;
      }
    }
  }

}
