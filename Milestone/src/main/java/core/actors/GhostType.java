package core.actors;

import core.movement.ScatterMode;

public enum GhostType {
  AMBUSHER("src/main/resources/ambusher.png", ScatterMode.TR),
  CHASER("src/main/resources/chaser.png", ScatterMode.TL),
  IGNORANT("src/main/resources/ignorant.png", ScatterMode.BL),
  WHIM("src/main/resources/whim.png", ScatterMode.BR);

  private final String sprite;
  private final ScatterMode scatterMode;

  private GhostType(String sprite, ScatterMode scatterMode) {
    this.sprite = sprite;
    this.scatterMode = scatterMode;
  }

  public String getSprite() {
    return sprite;
  }

  public ScatterMode getScatterMode() {
    return scatterMode;
  }
}