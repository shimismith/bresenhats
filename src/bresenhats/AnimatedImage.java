package bresenhats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
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
  
  /** If the animation is running */
  private boolean running;
  
  /** The frame displayed when the animation is not running */
  private Image restingFrame;
  
  /**
   * Creates the AnimatedImage from a sprite sheet
   * 
   * @param col the number of columns in the sprite sheet
   * @param row the number of rows in the sprite sheet
   * @param spriteSheetPath the path to the sprite sheet
   * @throws IOException
   */
  public AnimatedImage(int col, int row, String spriteSheetPath, double duration) throws IOException {

    this.frames = new Image[col * row];

    BufferedImage spriteSheet = ImageIO.read(new File(spriteSheetPath));
    
    this.width = spriteSheet.getWidth() / col;
    this.height = spriteSheet.getHeight() / row;

    for (int y = 0; (y + 1) * this.height <= spriteSheet.getHeight(); y++) {
      for (int x = 0; (x + 1) * this.width <= spriteSheet.getWidth(); x++) {
        this.frames[col * y + x] = SwingFXUtils.toFXImage(spriteSheet.getSubimage(x * this.width, y*this.height, this.width, this.height), null);
      }
    }
    
    this.duration = duration;
    this.restingFrame = this.frames[0];
  }
  
  public Image getFrame(double time) {
    
    if(!this.isRunning()) {
      return this.restingFrame;
    }
    
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
  
  public boolean isRunning() {
    return this.running;
  }
  
  public void setRunning(boolean running) {
    this.running = running;
  }
}
