package core.movement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import core.Direction;
import core.MapGrid;
import core.PointMaths;
import core.GhostMode;
import core.Actor;
import core.App;
import core.Game;
import core.GhostMode;
import core.actors.Waka;
import core.actors.Ghost;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovementsTest {

  private App app;
  private Game game;
  private Waka waka;
  private List<Ghost> ghosts;

  @BeforeEach
  public void setUp() throws Exception {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(100);
    // This stops draw() being called automatically
    app.noLoop();
    this.game = new Game(app, "config2.json");
    game.setup();
    this.waka = game.getWaka();
    this.ghosts = game.getGhosts();
  }

  // Tests movements implemented by ghost
  @Test
  public void testMovements() {
    Ghost ghost1 = ghosts.get(0);
    // Ghost should not get stuck if trapped
    for (int i = 0; i < 5; i++) {
      game.draw();
    }
    // Ghost should be initially moving left
    assertEquals(Direction.LEFT, ghost1.getDirection());
    int x1 = ghost1.getX();
    int y1 = ghost1.getY();
    for (int i = 0; i < 180; i++) {
      game.draw();
    }
    int x2 = ghost1.getX();
    int y2 = ghost1.getY();
    // Ghost move be moving back when trapped
    assertTrue(x2 > x1);
    // Ghost is only moving horizontally
    assertEquals(y1, y2);
    // Check ghostModes can be implemented
    ghost1.setGhostMode(GhostMode.SCATTER);
    assertEquals(GhostMode.SCATTER, ghost1.getGhostMode());
    // Ghost should scatter randomly
    ghost1.setGhostMode(GhostMode.FRIGHTENED);
    assertEquals(GhostMode.FRIGHTENED, ghost1.getGhostMode());
    for (int i = 0; i < 300; i++) {
      game.draw();
    }
  }

  // Tests that ghost is not moving if directly surrounded by walls
  @Test
  public void testTrapped() {
    this.app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    // Wait for assets to be loaded in
    app.delay(100);
    // This stops draw() being called automatically
    app.noLoop();
    this.game = new Game(app, "config3.json");
    game.setup();
    this.waka = game.getWaka();
    this.ghosts = game.getGhosts();
    Ghost ghost1 = ghosts.get(0);
    assertEquals(Direction.NONE, ghost1.getDirection());
  }

  
}