package core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

  private Game game;

  @BeforeEach
  public void setUp() throws Exception {
    game = new Game(new App());
  }

  @Test 
  public void testConstruction() {
    assertNotNull(game);
  }

  @Test 
  public void getGhosts() {
    game.setup();
    assertEquals(4, game.getGhosts().size());
  }

}