package core;

import java.awt.Point;

public class PointMaths {

  private static int SIZE = MapGrid.GRIDSIZE;
  
  public static Point toPixelCoords(Point coords) {
    Double doubleX = coords.getX() * SIZE + SIZE / 2;
    Double doubleY = coords.getY() * SIZE + SIZE / 2;
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    Point pixelCoords = new Point(intX, intY);
    return pixelCoords;
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