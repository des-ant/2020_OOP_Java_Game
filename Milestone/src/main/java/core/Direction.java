package core;

import java.util.EnumSet;

public enum Direction {

  UP(0, -1, 130), DOWN(0, 1, 310), LEFT(-1, 0, 220), RIGHT(1, 0, 40), NONE(0, 0, 0);

  private final int x;
  private final int y;
  private final int angle;

  private Direction(int x, int y, int angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getAngle() {
    return angle;
  }

  public static EnumSet<Direction> validMovements() {
    return EnumSet.of(UP, DOWN, LEFT, RIGHT);
  }

}