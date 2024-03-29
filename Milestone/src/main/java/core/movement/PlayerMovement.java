package core.movement;

import java.awt.Point;

import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.Tile;

public class PlayerMovement implements Movement {

  private MapGrid mapGrid;
  private Direction initialDirection;
  private Direction nextDirection;
  private Direction previousDirection;
  private Direction currentDirection;

  /**
  * Constructs Movement with given map and initial direction
  *
  * @param  mapGrid           the map containing the player
  * @param  initialDirection  the initial direction that is applied to the player
  */
  public PlayerMovement(MapGrid mapGrid, Direction initialDirection) {
    this.mapGrid = mapGrid;
    this.currentDirection = initialDirection;
    this.nextDirection = initialDirection;
    this.previousDirection = initialDirection;
    this.initialDirection = initialDirection;
  }

  public void resetDirection() {
    this.nextDirection = Direction.NONE;
    this.currentDirection = Direction.NONE;
    this.previousDirection = initialDirection;
  }

  /**
  * Set next Direction of player
  *
  * @param  nextDirection  the direction to be applied
  */
  public void setNextDirection(Direction nextDirection) {
    this.nextDirection = nextDirection;
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

  public boolean eatFruit(Point coords) {
    Tile tileTouched = mapGrid.tileAt(coords);
    if (tileTouched == null || !tileTouched.isMovable()) {
      return false;
    }
    return (mapGrid.removeTile(tileTouched));
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

}
