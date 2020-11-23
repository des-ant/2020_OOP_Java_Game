package core.movement;

import java.awt.Point;
import java.util.List;

import core.Direction;

public interface Target {
  Direction chosenDirection(List<Direction> availableDirections, int x, int y);

  Point getTargetCoord();
}