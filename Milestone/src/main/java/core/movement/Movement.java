package core.movement;

import java.awt.Point;

import core.Direction;

public interface Movement {
  /**
  * Returns Point coordinate of next movable direction
  *
  * @param  coords  the current Actor Point coordinate
  * @param  x       the current Actor pixel horizontal coordinate
  * @param  y       the current Actor pixel vertical coordinate
  * @return         coordinate of next movable direction
  */
  Direction getNextDirection(Point coords, int x, int y);

  /**
  * Returns Direction that Actor is facing
  *
  * @return         Direction that Actor is facing
  */
  Direction getDirection();

  /**
  * Returns true if Actor is not stationary
  *
  * @return         true if Actor is not stationary
  */
  boolean isMoving();
}
