package core.actors;

import core.Actor;
import core.App;
import core.Game;
import core.Direction;
import core.GhostMode;
import jdk.jfr.Timestamp;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WakaTest {

  private App app;
  private Game game;
  private Waka waka;

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
    this.waka = game.getWaka();
  }

  // Test waka constructor
  @Test
  public void testConstruction() {
    Waka wakaNew = new Waka(16, 16, app, 1, 2, game.getMapGrid(), game);
    assertNotNull(wakaNew);
  }

  // Test direction from key input
  // Also checks for collision and valid movements
  @Test
  public void testDirections() {
    // Direction should start at none
    assertEquals(Direction.NONE, waka.getDirection());
    app.keyCode = app.RIGHT;
    app.keyPressed();
    // Move right until hit wall
    for (int i = 0; i < 180; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    // Move up now that there is space
    app.keyCode = app.UP;
    app.keyPressed();
    for (int i = 0; i < 30; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    assertEquals(Direction.UP, waka.getDirection());
    // Move right until hit wall
    app.keyCode = app.RIGHT;
    app.keyPressed();
    for (int i = 0; i < 180; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    // Move down now that there is space
    app.keyCode = app.DOWN;
    app.keyPressed();
    for (int i = 0; i < 30; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    assertEquals(Direction.DOWN, waka.getDirection());
    // Waka should ba able to move back and forth
    app.keyCode = app.LEFT;
    app.keyPressed();
    for (int i = 0; i < 30; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    app.keyCode = app.RIGHT;
    app.keyPressed();
    for (int i = 0; i < 30; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    assertEquals(Direction.RIGHT, waka.getDirection());
    app.keyCode = app.LEFT;
    app.keyPressed();
    for (int i = 0; i < 30; i++) {
      waka.move(app);
      waka.tick();
      waka.draw(app);
      game.draw();
    }
    assertEquals(Direction.LEFT, waka.getDirection());
  }

  // Check if ghost collision causes lives to decrease
  @Test
  public void touchGhost() {
    assertTrue(waka.isAlive());
    for (int i = 0; i < 15000; i++) {
      game.draw();
    }
    // Assumes ghosts will eventually kill waka
    assertFalse(waka.isAlive());
  }
  
}