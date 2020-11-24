package core.actors;

import core.Actor;
import core.App;
import core.Game;
import core.GhostMode;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GhostTest {

  private App app;
  private Game game;

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
  }

  // Test ghost constructor
  @Test
  public void testConstruction() {
    Ghost ghost = new Ghost(16, 16, app, 1, game.getMapGrid(), GhostType.CHASER);
    assertNotNull(ghost);
  }

  // Ghost mode should be initialised as CHASE
  @Test
  public void getGhostMode() {
    Ghost ghost = new Ghost(16, 16, app, 1, game.getMapGrid(), GhostType.AMBUSHER);
    assertEquals(GhostMode.CHASE, ghost.getGhostMode());
  }
  
}