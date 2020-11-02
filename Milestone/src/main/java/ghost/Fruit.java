package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class Fruit extends Cell{

  public Fruit(int x, int y, PApplet app) {
    super(x, y, app.loadImage("src/main/resources/fruit.png"));
  }
  
}