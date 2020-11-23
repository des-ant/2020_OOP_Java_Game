package core.movement;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.actors.Waka;

public class RandomTarget implements Target {

  private MapGrid mapGrid;
  private int SIZE = mapGrid.GRIDSIZE;
  private Point targetCoord;
  private final Random random = new Random();

  public RandomTarget(MapGrid mapGrid) {
    this.mapGrid = mapGrid;
    this.targetCoord = new Point(0, 0);
  };

  @Override
  public Direction chosenDirection(List<Direction> availableDirections, int x, int y) {
    int randomIndex = random.nextInt(availableDirections.size());
    Direction chosenDirection = availableDirections.get(randomIndex);
    Point nextCoords = new Point((int) x / SIZE, (int) y / SIZE);
    nextCoords.translate(chosenDirection.getX(), chosenDirection.getY());
    targetCoord = nextCoords;
    return chosenDirection;
  }

  // For debug mode
  @Override
  public Point getTargetCoord() {
    return this.targetCoord;
  }
}