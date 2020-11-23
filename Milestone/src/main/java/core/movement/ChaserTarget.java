package core.movement;

import java.awt.Point;
import java.util.List;

import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.actors.Waka;

public class ChaserTarget implements Target {

  private MapGrid mapGrid;
  private int SIZE = mapGrid.GRIDSIZE;
  private Point targetCoord;
  private Waka waka;

  public ChaserTarget(MapGrid mapGrid) {
    this.mapGrid = mapGrid;
    this.waka = mapGrid.getGame().getWaka();
    this.targetCoord = mapGrid.getGame().getWaka().getCoords();
  };

  @Override
  public Direction chosenDirection(List<Direction> availableDirections, int x, int y) {
    // Target Waka location
    targetCoord = waka.getCoords();
    // Filter out available directions based on euclidian distance
    Direction chosenDirection = Direction.NONE;
    Double minDistance = mapGrid.MAXDIST;
    for (Direction direction : availableDirections) {
      // Store current ghost position
      Point nextCoords = new Point((int) x / SIZE, (int) y / SIZE);
      nextCoords.translate(direction.getX(), direction.getY());
      Double distance = PointMaths.distance(targetCoord, nextCoords);
      if (distance < minDistance) {
        minDistance = distance;
        chosenDirection = direction;
      }
    }
    return chosenDirection;
  }

  // For debug mode
  @Override
  public Point getTargetCoord() {
    return this.targetCoord;
  }
}