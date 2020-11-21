package core.movement;

import java.awt.Point;

import core.Direction;
import core.MapGrid;
import core.Tile;

public class PlayerMovement implements Movement {

  private MapGrid mapGrid;
  private Direction nextDirection;
  private Direction previousDirection;
  private Direction currentDirection;

  public PlayerMovement(MapGrid mapGrid, Direction initialDirection) {
    this.mapGrid = mapGrid;
    this.currentDirection = initialDirection;
    this.nextDirection = initialDirection;
    this.previousDirection = initialDirection;
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

  public boolean hasReachedTile(Point coords, int x, int y) {
    Point newCoords = new Point(coords);
    // Centre of player must reach centre of Tile first
    Point currentTile = Tile.toPixelCoords(coords);
    int currentTileX = (int) currentTile.getX();
    int currentTileY = (int) currentTile.getY();
    if (x == currentTileX && y == currentTileY) {
      return true;
    }
    return false;
  }

  public Direction getNextDirection(Point coords, int x, int y) {
    if (canMove(coords, nextDirection, x, y)) {
      currentDirection = nextDirection;
    } else if (!canMove(coords, currentDirection, x, y)) {
      if (isMoving() && !hasReachedTile(coords, x, y)) {
        return currentDirection;
      }
      previousDirection = currentDirection;
      currentDirection = Direction.NONE;
    }
    return currentDirection;
  }

  private boolean canMove(Point coords, Direction direction, int x, int y) {
    Point newCoords = new Point(coords);
    // Centre of player must reach centre of Tile first
    if (!hasReachedTile(coords, x, y)) {
      return false;
    }
    // Calculate new coordinates
    newCoords.translate(direction.getX(), direction.getY());
    return mapGrid.canMove(newCoords);
  }

}