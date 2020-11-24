package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DirectionTest {

  // Test Direction enum returns correct X value
  @Test
  public void testUpX() {
    assertEquals(0, Direction.UP.getX());
  }

  // Test Direction enum returns correct Y value
  @Test
  public void testRightY() {
    assertEquals(0, Direction.RIGHT.getY());
  }

  // Test Direction enum returns correct valid movements
  @Test
  public void validMovements() {
    assertTrue(Direction.validMovements().contains(Direction.UP));
  }

  // Test Direction enum returns correct valid movements
  @Test
  public void invalidMovements() {
    assertFalse(Direction.validMovements().contains(Direction.NONE));
  }

}