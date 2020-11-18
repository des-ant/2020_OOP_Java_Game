package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.tiles.Fruit;
import core.tiles.Wall;
import processing.core.PApplet;

public class Map {

  // Location
  private int playerX;
  private int playerY;

  private int ghostX;
  private int ghostY;

  private int gridSpace = 16;
  private int xOffset = gridSpace / 2;
  private int yOffset = gridSpace / 2;

  private List<Tile> tileList = new ArrayList<Tile>();

  public Map(String mapFilename, PApplet app) {
    readMap(mapFilename, app);
  }

  public List<Tile> getTileList() {
    return tileList;
  }
  
  public int getPlayerX() {
    return playerX;
  }

  public int getPlayerY() {
    return playerY;
  }

  public void setPlayerX(int playerX) {
    this.playerX = playerX;
  }

  public void setPlayerY(int playerY) {
    this.playerY = playerY;
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
  public void readMap(String filename, PApplet app) {
    // If the file doesn't exist, return null.
    if (filename == null) {
      return;
    }
    Scanner scan = openFile(filename);
    if (scan == null) {
      return;
    }
    
    // If the file does exist, parse file
    scan.nextLine();
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
            setPlayerX(x);
            setPlayerY(y);
            break;
          case 'g':
            setGhostX(x);
            setGhostY(y);
            break;
          default:
            break;
        }
        x += gridSpace;
      }
      y += gridSpace;
    }
  }

  public void draw(PApplet app) {
    for (Tile tile : tileList) {
      tile.draw(app);
    }
  }

}