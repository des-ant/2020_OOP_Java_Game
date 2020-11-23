package core.movement;

import java.util.List;

import core.Direction;

public interface Target {
  Direction chosenDirection(List<Direction> availableDirections);
}