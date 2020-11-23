package core;

import java.awt.Point;

public class PointMaths {

  private static int SIZE = MapGrid.GRIDSIZE;
  
  // /**
  // * Returns value as pixel location on game display
  // *
  // * @param  value  the value of a coordinate point
  // * @return        value as pixel location on game display
  // */
  // public static int toPx(int value) {
  //   int Px = value * SIZE + SIZE / 2;
  //   return Px;
  // }

  public static Point toPixelCoords(Point coords) {
    Double doubleX = coords.getX() * SIZE + SIZE / 2;
    Double doubleY = coords.getY() * SIZE + SIZE / 2;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    Point pixelCoords = new Point(intX, intY);
    return pixelCoords;
  }

  public static Double distance(Point coords1, Point coords2) {
    Double x1 = coords1.getX();
    Double y1 = coords1.getY();
    Double x2 = coords2.getX();
    Double y2 = coords2.getY();
    Double distanceSq = Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
    Double distance = Math.sqrt(distanceSq);
    return distance;
  }

  public static boolean isDiagonal(Point coords) {
    return coords.getX() != 0 && coords.getY() != 0;
  }

  public static int magnitude(Point coords) {
    return (int) Math.sqrt(coords.getX() * coords.getX() + coords.getY() * coords.getY());
  }

  public static Point subtract(Point coords1, Point coords2) {
    Double doubleX = coords1.getX() - coords2.getX();
    Double doubleY = coords1.getY() - coords2.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point remainder(Point coords) {
    Double doubleX = (coords.getX() + MapGrid.MAPWIDTH * SIZE) % MapGrid.MAPWIDTH * SIZE;
    Double doubleY = (coords.getY() + MapGrid.MAPHEIGHT * SIZE) % MapGrid.MAPHEIGHT * SIZE;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point unit(Point coords) {
    return new Point(unit(coords.getX()), unit(coords.getY()));
  }

  private static int unit(double value) {
    return (int) Math.signum(value);
  }

  public static Point times(Point coords, int scale) {
    Double doubleX = coords.getX() * scale;
    Double doubleY = coords.getY() * scale;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

  public static Point add(Point coords1, Point coords2) {
    Double doubleX = coords1.getX() + coords2.getX();
    Double doubleY = coords1.getY() + coords2.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    return new Point(intX, intY);
  }

}