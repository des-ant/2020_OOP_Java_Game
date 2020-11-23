package core.actors;

public enum GhostType {
  AMBUSHER("src/main/resources/ambusher.png"),
  CHASER,
  IGNORANT,
  WHIM;

  private final String sprite;

  public String getSprite() {
    return sprite;
  }
}