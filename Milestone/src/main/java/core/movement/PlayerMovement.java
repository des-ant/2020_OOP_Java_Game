package core.movement;

import java.awt.Point;

import core.Direction;
import core.MapGrid;

public class PlayerMovement implements Movement {

  private MapGrid mapGrid;
  private Direction nextDirection;
  private Direction previousDirection;
  private Direction currentDirection;

  public PlayerMovement(MapGrid mapGrid, Direction initialDirection) {
    this.mapGrid = mapGrid;
    this.currentDirection = initialDirection;
    this.nextDirection = initialDirection;
}

  public void setNextDirection(Direction nextDirection) {
    this.nextDirection = nextDirection;
  }

  public Direction getDirection() {
    if (isMoving()) {
      return currentDirection;
    }
    return previousDirection;
  }

  public boolean isMoving() {
    return currentDirection != Direction.NONE;
  }

  public Direction getNextDirection(Point coords) {
    if (canMove(coords, nextDirection)) {
      currentDirection = nextDirection;
    } else if (!canMove(coords, currentDirection)) {
      previousDirection = currentDirection;
      currentDirection = Direction.NONE;
    }
    return currentDirection;
  }

  private boolean canMove(Point coords, Direction direction) {
    Point newCoords = new Point(coords);
    // Calculate new coordinates
    newCoords.translate(direction.getX(), direction.getY());
    return mapGrid.canMove(newCoords);
  }

}