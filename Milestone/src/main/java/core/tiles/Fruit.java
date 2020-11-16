package core.tiles;

import processing.core.PApplet;
import processing.core.PImage;

import core.Tile;

public class Fruit extends Tile {

  public Fruit(int x, int y, PApplet app) {
    super(x, y, app.loadImage("src/main/resources/fruit.png"));
  }
  
}