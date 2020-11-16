package core;

import processing.core.PApplet;
import processing.core.PImage;

import core.actors.*;

public class Game {
  
  private Config config;
  private Player player;
  private Ghost ghost;
  private Map map;
  private PApplet app;

  public Game(PApplet app) {
    // Load Papplet
    this.app = app;

    // Load Config file
    this.config = new Config("config.json");
  }

  public void setup() {
    app.frameRate(60);

    // Draw images centered at the given x and y position
    app.imageMode(app.CENTER);

    // Show black background on window launch
    app.background(0, 0, 0);

    // Map will load wall and fruit images
    this.map = new Map(config.getMap(), app);

    // Load images here
    this.player = new Player(map.getPlayerX(), map.getPlayerY(), app, config.getSpeed(), config.getLives());
    this.ghost = new Ghost(map.getGhostX(), map.getGhostY(), app, config.getSpeed());
    
  }

  public void draw() {
    app.background(0, 0, 0);

    map.draw(app);

    player.tick();

    player.draw(app);
    ghost.draw(app);
  }

  public void keyPressed() {
    // Arrow keys input
    if (app.key == app.CODED) {
      player.move(app);
    }
  }

}