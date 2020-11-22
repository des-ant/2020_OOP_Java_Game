package core;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.tiles.Fruit;
import core.tiles.Wall;
import processing.core.PApplet;

public class MapGrid {
  public final static int MAPHEIGHT = 36;
  public final static int MAPWIDTH = 28;

  // Location
  private int wakaX;
  private int wakaY;

  private int ghostX;
  private int ghostY;

  // Each grid space is 16x16 pixels
  public final static int GRIDSIZE = 16;
  private int xOffset = GRIDSIZE / 2;
  private int yOffset = GRIDSIZE / 2;

  private List<Tile> tileList = new ArrayList<Tile>();

  public MapGrid(String mapFilename, PApplet app) {
    readMapGrid(mapFilename, app);
  }

  public List<Tile> getTileList() {
    return tileList;
  }

  // Check if tile allows movement
  public boolean canMove(Point coords) {
    Tile checkTile = tileAt(coords);
    // Empty space allows movement
    if (checkTile == null) {
      return true;
    }
    return checkTile.isMovable();
  }

  // Get tile at specific coordinate
  // Returns null if tile does not exist
  public Tile tileAt(Point coords) {
    if (coords == null) {
      return null;
    }
    for (Tile tile : tileList) {
      if (coords.equals(tile.getCoords())) {
        return tile;
      }
    }
    return null;
  }

  public int getWakaX() {
    return wakaX;
  }

  public int getWakaY() {
    return wakaY;
  }

  public void setWakaX(int wakaX) {
    this.wakaX = wakaX;
  }

  public void setWakaY(int wakaY) {
    this.wakaY = wakaY;
  }

  public int getGhostX() {
    return ghostX;
  }

  public int getGhostY() {
    return ghostY;
  }

  public void setGhostX(int ghostX) {
    this.ghostX = ghostX;
  }

  public void setGhostY(int ghostY) {
    this.ghostY = ghostY;
  }

  // Helper method for opening file
  public Scanner openFile(String filename) {
    try {
      File f = new File(filename);
      Scanner scan = new Scanner(f);
      return scan;
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  // Transform the input into a grid.
  public void readMapGrid(String filename, PApplet app) {
    // If the file doesn't exist, return null.
    if (filename == null) {
      return;
    }
    Scanner scan = openFile(filename);
    if (scan == null) {
      return;
    }

    // If the file does exist, parse file
    int x = xOffset;
    int y = yOffset;
    while (scan.hasNextLine()) {
      x = xOffset;
      String line = scan.nextLine();
      for (int i = 0; i < line.length(); i++) {
        char currentChar = line.charAt(i);
        switch (currentChar) {
          // Empty tile
          case '0':
            break;
          // Horizontal wall
          case '1':
            tileList.add(new Wall(x, y, app, 1));
            break;
          // Vertical wall
          case '2':
            tileList.add(new Wall(x, y, app, 2));
            break;
          // Corner wall (up + left
          case '3':
            tileList.add(new Wall(x, y, app, 3));
            break;
          // Corner wall (up + right)
          case '4':
            tileList.add(new Wall(x, y, app, 4));
            break;
          // Corner wall (down + left)
          case '5':
            tileList.add(new Wall(x, y, app, 5));
            break;
          // Corner wall (down + right)
          case '6':
            tileList.add(new Wall(x, y, app, 6));
            break;
          case '7':
            tileList.add(new Fruit(x, y, app));
            break;
          case 'p':
            setWakaX(x);
            setWakaY(y);
            break;
          case 'g':
            setGhostX(x);
            setGhostY(y);
            break;
          default:
            break;
        }
        x += GRIDSIZE;
      }
      y += GRIDSIZE;
    }
  }

  public void draw(PApplet app) {
    for (Tile tile : tileList) {
      tile.draw(app);
    }
  }

}