package bresenhats;

import javafx.animation.AnimationTimer;

/**
 * This is a custom AnimationTimer, it uses time intervals to measure changes in position instead of
 * using the time since the program started by letting you update the previous time
 */
public abstract class CustomAnimationTimer extends AnimationTimer {

  private long prevNanoTime;

  public CustomAnimationTimer() {
    super();
    this.prevNanoTime = System.nanoTime();
  }
  
  public long getPrevNanoTime() {
    return this.prevNanoTime;
  }
  
  public void setPrevNanoTime(long prevNanoTime) {
    this.prevNanoTime = prevNanoTime;
  }
  
  public abstract void handle(long now);

}
