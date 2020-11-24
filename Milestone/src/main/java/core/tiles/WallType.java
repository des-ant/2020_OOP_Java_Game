package core.tiles;

public enum WallType {
  // Horizontal wall
  H("src/main/resources/horizontal.png"),
  // Vertical wall
  V("src/main/resources/vertical.png"),
  // Corner wall (up + left)
  UL("src/main/resources/upLeft.png"),
  // Corner wall (up + right)
  UR("src/main/resources/upRight.png"),
  // Corner wall (down + left)
  DL("src/main/resources/downLeft.png"),
  // Corner wall (down + right)
  DR("src/main/resources/downRight.png");

  private final String sprite;

  private WallType(String sprite) {
    this.sprite = sprite;
  }

  public String getSprite() {
    return sprite;
  }
}