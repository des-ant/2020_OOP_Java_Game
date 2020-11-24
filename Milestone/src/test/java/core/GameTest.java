package core;

import processing.core.PApplet;
import processing.core.PImage;
import core.actors.Ghost;
import core.actors.Waka;
import core.actors.GhostType;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

  private App app;
  private Game game;

  @BeforeEach
  public void setUp() throws Exception {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(250);
    // This stops draw() being called automatically
    app.noLoop();
    app.keyPressed();
    this.game = new Game(app);
    game.setup();
  }

  // Test game constructor
  @Test 
  public void testConstruction() {
    assertNotNull(game);
  }

  // Test game return correct number of ghosts
  @Test 
  public void getGhosts() {
    assertEquals(4, game.getGhosts().size());
  }

  // Debug mode should be off initially
  @Test 
  public void testDebug() {
    assertFalse(game.getDebug());
    app.key = ' ';
    game.keyPressed();
    game.draw();
    assertTrue(game.getDebug());
    app.key = ' ';
    game.keyPressed();
    game.draw();
    assertFalse(game.getDebug());
  }

}