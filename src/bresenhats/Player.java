package bresenhats;

import java.io.IOException;
import java.util.Arrays;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends RigidBody {

  private double health;

  /** Walking animation for going left */
  private AnimatedImage walkingAnimationL;

  /** Walking animation for going right */
  private AnimatedImage walkingAnimationR;
  
  public boolean walkingLeft = false;
  public boolean walkingRight = false;

  private static final double WALKING_DURATION = 0.07;

  /**
   * Constructs a new player with a sprite sheet
   * 
   * @param x the x coordinate of the player
   * @param y the y coordinate of the player
   * @param width the width of the player
   * @param height the height of the player
   * @param col the number of columns in the sprite sheet
   * @param row the number of rows in the sprite sheet
   * @param spriteSheet the path to the sprite sheet for the walking animation
   * @throws IOException
   */
  public Player(int x, int y, int width, int height, int col, int row, String spriteSheet, double hVelocity, double vVelocity) throws IOException {
     super(x, y, width, height, hVelocity, vVelocity);

    Image[] animation = SpriteSheetReader.readSpriteSheet(col, row, spriteSheet);

    int middle = animation.length / 2;

    this.walkingAnimationL = new AnimatedImage(Arrays.copyOfRange(animation, 0, middle), Player.WALKING_DURATION);
    this.walkingAnimationR = new AnimatedImage(Arrays.copyOfRange(animation, middle, animation.length), Player.WALKING_DURATION);
    
    this.health = 100;
  }

  /**
   * Constructs a new player with the lowest possible y and with width and height of the sprite used
   * 
   * @param x the x coordinate of the player
   * @param col the number of columns in the sprite sheet
   * @param row the number of rows in the sprite sheet
   * @param spriteSheet the path to the sprite sheet for the walking animation
   * @throws IOException
   */
  public Player(int x, int col, int row, String spriteSheet, double hVelocity, double vVelocity) throws IOException {
    super(x, 0, 0, 0, hVelocity, vVelocity);

    Image[] animation = SpriteSheetReader.readSpriteSheet(col, row, spriteSheet);

    int middle = animation.length / 2;

    this.walkingAnimationL = new AnimatedImage(Arrays.copyOfRange(animation, 0, middle), Player.WALKING_DURATION);
    this.walkingAnimationR = new AnimatedImage(Arrays.copyOfRange(animation, middle, animation.length), Player.WALKING_DURATION);
    
    this.setWidth(this.walkingAnimationL.getWidth());
    this.setHeight(this.walkingAnimationL.getHeight());
    this.setY(Main.HEIGHT - this.getHeight());

    this.health = 100;
  }

  public double getHealth() {
    return this.health;
  }

  public void setHealth(double health) {
    this.health = health;
  }
  
  /** Draws the player */
  @Override
  public void draw(GraphicsContext gc, double time, Camera camera) {
    if (this.walkingLeft) {
      gc.drawImage(this.walkingAnimationL.getFrame(time), this.getPosition().getX() + camera.getPosition().getX(), this.getPosition().getY() + camera.getPosition().getY());
    } 
    else if (this.walkingRight) {
      gc.drawImage(this.walkingAnimationR.getFrame(time), this.getPosition().getX() + camera.getPosition().getX(), this.getPosition().getY() + camera.getPosition().getY());
    } 
    else if (!this.walkingLeft && !this.walkingRight) {
      gc.drawImage(this.walkingAnimationL.getRestingFrame(), this.getPosition().getX() + camera.getPosition().getX(), this.getPosition().getY() + camera.getPosition().getY());
    }
  }

}
