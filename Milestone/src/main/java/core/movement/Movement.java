package core.movement;

import core.Direction;

public interface Movement {
  Direction getNextDirection(int coordX, int coordY);

  Direction getDirection();

  boolean isMoving();
}