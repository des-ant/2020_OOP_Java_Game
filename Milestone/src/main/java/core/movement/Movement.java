package core.movement;

import java.awt.Point;

import core.Direction;

public interface Movement {
  Direction getNextDirection(Point coords, int x, int y);

  Direction getDirection();

  boolean isMoving();
}