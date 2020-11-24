package core;

import core.tiles.Wall;
import core.tiles.WallType;

import java.io.FileNotFoundException;

import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapGridTest {
  
  private App app;
  private Game game;
  private MapGrid mapGrid;

  @BeforeEach
  public void setUp() throws Exception {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(100);
    // This stops draw() being called automatically
    app.noLoop();
    this.game = new Game(app);
    game.setup();
    this.mapGrid = game.getMapGrid();
  }

  // Negative test cases
  @Test
  public void negativeTest() {
    assertFalse(mapGrid.removeTile(null));
    assertFalse(mapGrid.removeTile(new Wall(16, 16, app, WallType.H)));
    assertFalse(mapGrid.readMapGrid(null, app));
    assertFalse(mapGrid.readMapGrid("not exist", app));
    // Test that exception is thrown and caught
    assertNull(mapGrid.openFile("notexist"));
  }
}