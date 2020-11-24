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

public class GameTest {

  private Game game;

  @BeforeEach
  public void setUp() throws Exception {
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.delay(1000); // Wait for assets to be loaded in
    app.noLoop(); // This stops draw() being called automatically
    app.keyPressed();
    this.game = new Game(app);
    game.setup();
    game.keyPressed();
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

}