package core.movement;

import core.MapGrid;

public enum ScatterMode {
  BL(0, MapGrid.MAPHEIGHT - 1), 
  BR(MapGrid.MAPWIDTH - 1, MapGrid.MAPHEIGHT - 1), 
  TL(0, 0), 
  TR(MapGrid.MAPWIDTH - 1, 0);

  private final int x;
  private final int y;

  // Set target for scatter
  private ScatterMode(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}