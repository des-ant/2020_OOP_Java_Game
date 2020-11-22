package core;

import java.awt.Point;

import core.movement.Movement;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Actor {

  // Attributes accessible by subclass Pacman, Ghost
  protected int x;
  protected int y;
  protected int startX;
  protected int startY;
  protected int xVel = 0;
  protected int yVel = 0;
  protected int speed;

  protected int SIZE = MapGrid.GRIDSIZE;

  protected Movement movement;

  protected PImage sprite;

  /**
  * Constructs Actor from given position, image, speed, movement
  *
  * @param  x         the initial horizontal pixel coordinate of Actor
  * @param  y         the initial vertical pixel coordinate of Actor
  * @param  sprite    the image used to present Actor
  * @param  speed     the speed at which Actor will move
  * @param  movement  the type of movement being implemented
  */
  public Actor(int x, int y, PImage sprite, int speed, Movement movement) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.speed = speed;
    this.movement = movement;
    this.startX = x;
    this.startY = y;
  }

  /**
  * Draws Actor to PApplet window
  *
  * @param  app  the PApplet window to be drawn to
  */
  public void draw(PApplet app) {
    // Handling graphics
    app.image(this.sprite, this.x, this.y);
    app.fill(0, 0);
    app.rect(this.x, this.y, sprite.width, sprite.height);
    app.fill(255, 0, 0, 120);
    app.rect(this.x, this.y, SIZE, SIZE);
    app.fill(255, 100, 100, 120);
    int closestX = (int) PointMaths.toPixelCoords(getCoords()).getX();
    int closestY = (int) PointMaths.toPixelCoords(getCoords()).getY();
    app.rect(closestX, closestY, SIZE, SIZE);
  }

  /**
  * Returns horizontal pixel coordinate of Actor centre
  *
  * @return  horizontal pixel coordinate of Actor centre
  */
  public int getX() {
    return x;
  }

  /**
  * Returns vertical pixel coordinate of Actor centre
  *
  * @return  vertical pixel coordinate of Actor centre
  */
  public int getY() {
    return y;
  }

  /**
  * Returns horizontal tile coordinate of Actor centre
  *
  * @return  horizontal tile coordinate of Actor centre
  */
  public int getCoordX() {
    return x / SIZE;
    // return Math.round((x - SIZE / 2) / SIZE);
  }

  /**
  * Returns vertical tile coordinate of Actor centre
  *
  * @return  vertical tile coordinate of Actor centre
  */
  public int getCoordY() {
    return y / SIZE;
    // return Math.round((y - SIZE / 2) / SIZE);
  }

  /**
  * Returns Point at tile coordinate of Actor centre
  *
  * @return  Point at tile coordinate of Actor centre
  */
  public Point getCoords() {
    return new Point(getCoordX(), getCoordY());
  }

  /**
  * Returns Point at pixel coordinate of Actor centre
  *
  * @return  Point at pixel coordinate of Actor centre
  */
  public Point getPixelCoords() {
    return new Point(x, y);
  }
  
  /**
  * Resets Actor to initial position
  */
  public void resetPixelCoords() {
    x = startX;
    y = startY;
  }

  public void setPixelCoords(Point coords) {
    Double doubleX = coords.getX();
    Double doubleY = coords.getY();
    int intX = (int) Math.round(doubleX);
    int intY = (int) Math.round(doubleY);
    this.x = intX;
    this.y = intY;
  }

  private Point getNextCoords(Point currentCoords) {
    Direction nextDirection = movement.getNextDirection(currentCoords, x, y);
    Point newCoords = new Point(currentCoords);
    newCoords.translate(nextDirection.getX(), nextDirection.getY());
    return newCoords;
  }

  public void tick() {
    Point currentCoords = getCoords();
    Direction nextDirection = movement.getNextDirection(currentCoords, x, y);
    xVel = nextDirection.getX() * speed;
    yVel = nextDirection.getY() * speed;
    x += xVel;
    y += yVel;
  }

  // private void moveActor(int distance) {
  //   // Get coordinates of Actor
  //   Point currentCoords = getCoords();
  //   // Get next coordinates
  //   Point nextCoords = getNextCoords(currentCoords);
  //   // Get centre pixel of next tile
  //   Point nextTileCentre = Tile.toPixelCoords(nextCoords);
  //   // Get pixel distance from next Tile centre to current Actor centre
  //   Point pixelDistance = Tile.subtract(nextTileCentre, getPixelCoords());
  //   // Next position lies along a diagonal, adjust distance to next tile
  //   if (Tile.isDiagonal(pixelDistance)) {
  //     // Get centre pixel of current tile being occupied by Actor
  //     Point currentTileCentre = Tile.toPixelCoords(currentCoords);
  //     // Get pixel distance from Tile centre to Actor centre
  //     int pixelMagnitude = Tile.magnitude(Tile.subtract(currentTileCentre, getPixelCoords()));
  //     // Adjust distance
  //     distance -= pixelMagnitude;
  //     // Get pixel distance from next Tile centre to current Tile centre
  //     pixelDistance = Tile.subtract(nextTileCentre, currentTileCentre);
  //     setPixelCoords(currentTileCentre);
  //   }

  //   int pxDistMag = Tile.magnitude(pixelDistance);

  //   if (pxDistMag > 0) {
  //     if (pxDistMag == distance) {
  //       setPixelCoords(Tile.remainder(nextTileCentre));
  //     } else if (pxDistMag < distance) {
  //       setPixelCoords(Tile.remainder(nextTileCentre));
  //       moveActor(distance - pxDistMag);
  //     } else {
  //       Point distMoved = Tile.times(Tile.unit(pixelDistance), distance);
  //       Point pxMoved = Tile.add(getPixelCoords(), distMoved);
  //       Point pxRemainder = Tile.remainder(pxMoved);
  //       setPixelCoords(pxRemainder);
  //     }
  //   }
  // }

}
