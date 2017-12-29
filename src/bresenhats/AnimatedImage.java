package bresenhats;

import javafx.scene.image.Image;

public class AnimatedImage {

  private Image[] frames;
  
  /** The duration of each frame */
  private double duration;
  /** Time since last frame */
  private double prevTime;
  
  private int currFrame;
  
  private int width;
  private int height;
  
  /** The frame displayed when the animation is not running */
  private Image restingFrame;
  
  public AnimatedImage(Image[] images, double duration) {
    this.frames = images.clone();

    this.width = (int) frames[0].getWidth();
    this.height = (int) frames[0].getHeight();
    this.duration = duration;
    this.restingFrame = this.frames[0];
  }
  
  public Image getFrame(double time) {
    
    this.prevTime += time;  // update time
    
    // update currFrame
    if(this.prevTime >= this.duration) {
      // next frame
      currFrame = currFrame == this.frames.length - 1 ? 0 : currFrame + 1;
      this.prevTime = 0;
    }

    return this.frames[this.currFrame];
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }
    
  public Image getRestingFrame() {
    return this.restingFrame;
  }
}
