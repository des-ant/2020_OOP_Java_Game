package core.movement;

import java.awt.Point;

import core.Direction;

public interface Movement {
  Direction getNextDirection(Point coords);

  Direction getDirection();

  boolean isMoving();
}