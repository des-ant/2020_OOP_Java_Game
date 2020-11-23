package core.movement;

import java.awt.Point;
import java.util.List;

import core.Direction;
import core.MapGrid;

public class ChaserTarget implements Target {

  private MapGrid mapGrid;
  private Point targetCoord;

  public ChaserTarget(MapGrid mapGrid) {
    this.mapGrid = mapGrid;
  };

  public Direction chosenDirection(List<Direction> availableDirections) {
    return Direction.RIGHT;
  }
}