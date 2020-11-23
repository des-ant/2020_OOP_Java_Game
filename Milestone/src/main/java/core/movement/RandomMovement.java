package core.movement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.Direction;
import core.MapGrid;
import core.PointMaths;

public class RandomMovement implements Movement {

  private MapGrid mapGrid;
  private Direction nextDirection;
  private Direction previousDirection;
  private Direction currentDirection;
  private Point previousCoords;
  private final Random random = new Random();

  /**
  * Constructs Movement with given map and initial direction
  *
  * @param  mapGrid           the map containing the player
  * @param  initialDirection  the initial direction that is applied to the player
  */
  public RandomMovement(MapGrid mapGrid, Direction initialDirection, 
  Point previousCoords) {
    this.mapGrid = mapGrid;
    this.currentDirection = initialDirection;
    this.nextDirection = initialDirection;
    this.previousDirection = initialDirection;
    this.previousCoords = previousCoords;
  }

  /**
  * Returns Direction that player is facing
  *
  * @return  Direction that player is facing
  */
  @Override
  public Direction getDirection() {
    if (isMoving()) {
      return currentDirection;
    }
    return previousDirection;
  }

  /**
  * Returns true if player is not stationary
  *
  * @return  true if player is not stationary
  */
  @Override
  public boolean isMoving() {
    return currentDirection != Direction.NONE;
  }

  /**
  * Returns true if player is in centre of tile
  *
  * @param  coords  the current player Point coordinate
  * @param  x       the current player pixel horizontal coordinate
  * @param  y       the current player pixel vertical coordinate
  * @return         true if player is in centre of tile
  */
  public boolean hasReachedTile(Point coords, int x, int y) {
    // Centre of player must reach centre of Tile first
    Point currentTile = PointMaths.toPixelCoords(coords);
    int currentTileX = (int) currentTile.getX();
    int currentTileY = (int) currentTile.getY();
    if (x == currentTileX && y == currentTileY) {
      return true;
    }
    return false;
  }

  /**
  * Returns Point coordinate of next movable direction
  *
  * @param  coords  the current player Point coordinate
  * @param  x       the current player pixel horizontal coordinate
  * @param  y       the current player pixel vertical coordinate
  * @return         coordinate of next movable direction
  */
  @Override
  public Direction getNextDirection(Point coords, int x, int y) {
    // Check next position is different to previous position
    // Only updates next direction if ghost has moved
    if (!coords.equals(previousCoords)) {
      List<Direction> availableDirections = getPossibleDirections(coords, x, y);
      int randomIndex = random.nextInt(availableDirections.size());
      nextDirection = availableDirections.get(randomIndex);
      previousCoords = coords;
    }
    // Check if can move in next direction
    // Update current direction if can move
    if (canMove(coords, nextDirection, x, y)) {
      currentDirection = nextDirection;
    } else if (!canMove(coords, currentDirection, x, y)) {
      // Return current direction if player is moving and has not reached
      // centre of current Tile
      if (isMoving() && !hasReachedTile(coords, x, y)) {
        return currentDirection;
      }
      previousDirection = currentDirection;
      currentDirection = Direction.NONE;
    }
    return currentDirection;
  }

  /*
  * Returns true if next move is possible
  *
  * @param  coords     the current Point coordinate
  * @param  direction  the direction to be applied
  * @param  x          the current pixel horizontal coordinate
  * @param  y          the current pixel vertical coordinate
  * @return            true if next move is possible
  */
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

  private List<Direction> getPossibleDirections(Point coords, int x, int y) {
    List<Direction> availableDirections = new ArrayList<Direction>();
    for (Direction direction : Direction.validMovements()) {
        // Store current ghost position to prevent change to current position
        Point nextCoords = new Point(coords);
        // Get next position
        nextCoords.translate(direction.getX(), direction.getY());
        // Check next move is possible and does not allow backtracking
        if (mapGrid.canMove(nextCoords) && !nextCoords.equals(previousCoords)) {
            availableDirections.add(direction);
        }
    }
    return availableDirections;
  }

}
