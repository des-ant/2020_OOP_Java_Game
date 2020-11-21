package core.actors;

import core.Actor;
import processing.core.PApplet;

public class Ghost extends Actor{

  public Ghost(int x, int y, PApplet app, int speed) {
    super(x, y, app.loadImage("src/main/resources/ghost.png"), speed, null);
  }
  
}