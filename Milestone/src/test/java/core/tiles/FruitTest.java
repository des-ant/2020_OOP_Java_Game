package core.tiles;

import core.Tile;
import core.App;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FruitTest {

  private App app;

  @BeforeEach
  public void setUp() throws Exception {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(250);
    // This stops draw() being called automatically
    app.noLoop();
    app.keyPressed();
  }

  // Test fruit constructor
  @Test
  public void testConstruction() {
    Fruit fruit = new Fruit(16, 16, app);
    assertNotNull(fruit);
  }

  // Fruit should be movable
  @Test
  public void isMovable() {
    Fruit fruit = new Fruit(16, 16, app);
    assertTrue(fruit.isMovable());
  }
  
}