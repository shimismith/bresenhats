package bresenhats;

import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;

public class Player extends MovableGameObject {

  private double health;
  private AnimatedImage walkingAnimation;
    
  private static final double WALKING_DURATION = 0.07;

  /**
   * Constructs a new player with a sprite that doesn't change
   * 
   * @param x the x coordinate of the player
   * @param y the y coordinate of the player
   * @param width the width of the player
   * @param height the height of the player
   * @param path to the player's sprite
   * @throws IOException
   */
  public Player(int x, int y, int width, int height, String path) throws IOException {
    this(x, y, width, height, 1, 1, path);
  }
  
  /**
   * Constructs a new player with a sprite sheet
   * 
   * @param x the x coordinate of the player
   * @param y the y coordinate of the player
   * @param width the width of the player
   * @param height the height of the player
   * @param col the number of columns in the sprite sheet
   * @param row the number of rows in the sprite sheet
   * @param path the path to the sprite sheet
   * @throws IOException
   */
  public Player(int x, int y, int width, int height, int col, int row, String path) throws IOException{
    super(x, y, width, height);
    this.health = 100;
    
    this.walkingAnimation = new AnimatedImage(col, row, path, Player.WALKING_DURATION);
  }
  
  /**
   * Constructs a new player with the lowest possible y and with width and height of the sprite used
   * 
   * @param x
   * @param col
   * @param row
   * @param path
   * @throws IOException
   */
  public Player(int x, int col, int row, String path) throws IOException{
    super(x, 0, 0, 0);
    this.walkingAnimation = new AnimatedImage(2, 4, path, Player.WALKING_DURATION);
    
    this.setWidth(this.walkingAnimation.getWidth());
    this.setHeight(this.walkingAnimation.getHeight());
    this.setY(Main.HEIGHT - this.getHeight());

    this.health = 100;
  }

  public double getHealth() {
    return this.health;
  }

  public void setHealth(double health) {
    this.health = health;
  }

  @Override
  public void move(double time) {
   
    if(!this.isOnGround()) {
      this.getVel().increase(Vector2D.GRAVITY, time); 
      
      // can't walk while jumping
      this.setWalking(false);
    }
    else if(this.getVel().getX() != 0) {  // going left or right
      this.setWalking(true);
    }
    else if(this.getVel().getX() == 0) {  // not moving left or right
      this.setWalking(false);
    }
    
    super.move(time);
    
  }

  /** Draws the player */
  @Override
  public void draw(GraphicsContext gc, double time) {
    gc.drawImage(this.walkingAnimation.getFrame(time), this.getX(), this.getY());
  }
  
  public void setWalking(boolean walking) {
    this.walkingAnimation.setRunning(walking);
  }



}
