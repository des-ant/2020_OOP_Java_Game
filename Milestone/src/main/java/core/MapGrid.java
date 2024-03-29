package core;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.tiles.Fruit;
import core.tiles.Wall;
import core.tiles.WallType;
import core.actors.GhostType;
import processing.core.PApplet;

public class MapGrid {
  public final static int MAPHEIGHT = 36;
  public final static int MAPWIDTH = 28;
  public final static Double MAXDIST = 
  Math.sqrt(Math.pow(MAPWIDTH, 2) + Math.pow(MAPHEIGHT, 2));

  // Current game instance
  private Game game;

  // Location
  private Point wakaCoord;
  private List<Point> ghostCoords = new ArrayList<Point>();
  private List<GhostType> ghostTypes = new ArrayList<GhostType>();

  // Each grid space is 16x16 pixels
  public final static int GRIDSIZE = 16;
  private int xOffset = GRIDSIZE / 2;
  private int yOffset = GRIDSIZE / 2;

  private List<Tile> tileList = new ArrayList<Tile>();
  private int numFruit = 0;

  public MapGrid(String mapFilename, PApplet app, Game game) {
    readMapGrid(mapFilename, app);
    this.game = game;
  }

  public Game getGame() {
    return game;
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

  // Remove tile from list
  public boolean removeTile(Tile tileRemove) {
    if (tileRemove == null) {
      return false;
    }
    for (Tile tile : tileList) {
      if (tile == tileRemove) {
        tileList.remove(tile);
        numFruit--;
        return true;
      }
    }
    return false;
  }

  public Point getWakaCoord() {
    return wakaCoord;
  }

  public List<Point> getGhostCoords() {
    return ghostCoords;
  }

  public List<GhostType> getGhostTypes() {
    return ghostTypes;
  }

  public int getNumFruit() {
    return numFruit;
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
  public boolean readMapGrid(String filename, PApplet app) {
    // If the file doesn't exist, return null.
    if (filename == null) {
      return false;
    }
    Scanner scan = openFile(filename);
    if (scan == null) {
      return false;
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
            tileList.add(new Wall(x, y, app, WallType.H));
            break;
          // Vertical wall
          case '2':
            tileList.add(new Wall(x, y, app, WallType.V));
            break;
          // Corner wall (up + left)
          case '3':
            tileList.add(new Wall(x, y, app, WallType.UL));
            break;
          // Corner wall (up + right)
          case '4':
            tileList.add(new Wall(x, y, app, WallType.UR));
            break;
          // Corner wall (down + left)
          case '5':
            tileList.add(new Wall(x, y, app, WallType.DL));
            break;
          // Corner wall (down + right)
          case '6':
            tileList.add(new Wall(x, y, app, WallType.DR));
            break;
          case '7':
            tileList.add(new Fruit(x, y, app));
            numFruit++;
            break;
          case 'a':
            ghostCoords.add(new Point(x, y));
            ghostTypes.add(GhostType.AMBUSHER);
            break;
          case 'c':
            ghostCoords.add(new Point(x, y));
            ghostTypes.add(GhostType.CHASER);
            break;
          case 'i':
            ghostCoords.add(new Point(x, y));
            ghostTypes.add(GhostType.IGNORANT);
            break;
          case 'p':
            wakaCoord = new Point(x, y);
            break;
          case 'g':
            ghostCoords.add(new Point(x, y));
            ghostTypes.add(GhostType.CHASER);
            break;
          case 'w':
            ghostCoords.add(new Point(x, y));
            ghostTypes.add(GhostType.WHIM);
            break;
          default:
            break;
        }
        x += GRIDSIZE;
      }
      y += GRIDSIZE;
    }
    return true;
  }

  public void draw(PApplet app) {
    for (Tile tile : tileList) {
      tile.draw(app);
    }
  }

}