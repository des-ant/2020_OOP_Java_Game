package core.movement;

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

  public Direction getNextDirection(int coordX, int coordY) {
    if (canMove(coordX, coordY, nextDirection)) {
      currentDirection = nextDirection;
    } else if (!canMove(coordX, coordY, currentDirection)) {
      previousDirection = currentDirection;
      currentDirection = Direction.NONE;
    }
    return currentDirection;
  }

  private boolean canMove(int coordX, int coordY, Direction direction) {
    int newCoordX = coordX + direction.getX();
    int newCoordY = coordY + direction.getY();
    return mapGrid.canMove(newCoordX, newCoordY);
  }

}