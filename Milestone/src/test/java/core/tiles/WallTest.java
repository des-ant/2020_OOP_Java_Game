package core.tiles;

import core.Tile;
import core.App;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {

  private App app;

  @BeforeEach
  public void setUp() throws Exception {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(100);
    // This stops draw() being called automatically
    app.noLoop();
  }

  // Test wall constructor
  @Test
  public void testConstruction() {
    Wall wall = new Wall(16, 16, app, WallType.H);
    assertNotNull(wall);
    // Test getters
    assertEquals(16, wall.getX());
    assertEquals(16, wall.getY());
    assertEquals(1, wall.getCoordX());
    assertEquals(1, wall.getCoordY());
  }

  // Wall should not be movable
  @Test
  public void notMovable() {
    Wall wall = new Wall(16, 16, app, WallType.V);
    assertFalse(wall.isMovable());
  }
  
}