package bresenhats;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends MovableGameObject {

  private double health;
  private Image sprite;

  public Player(int x, int y, int width, int height, String name) throws IOException {
    super(x, y, width, height);

    this.health = 100;

    FileInputStream inputStream = new FileInputStream("res/" + name);
    this.sprite = new Image(inputStream, this.getWidth(), this.getHeight(), true, true);
    inputStream.close();
  }
  
  /** This constructor doesn't take in y, it puts the player as low as possible */
  public Player(int x, int width, int height, String name) throws IOException{
    this(x, Main.HEIGHT - height, width, height, name);
  }
  
  /** This constructor just used the width and height of the image */
  public Player(int x, String name) throws IOException{
    super(x, 0, 0, 0);  // had to put zero cause constructor needs to be called but I still have to get the values
    
    FileInputStream inputStream = new FileInputStream("res/" + name);
    this.sprite = new Image(inputStream);
    inputStream.close();
    
    this.setWidth((int) this.sprite.getWidth());
    this.setHeight((int) this.sprite.getHeight());
    this.setY(Main.HEIGHT - this.getHeight());
  }

  public double getHealth() {
    return this.health;
  }

  public void setHealth(double health) {
    this.health = health;
  }

  public Image getSprite() {
    return this.sprite;
  }

  /** Draws the player */
  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(this.getSprite(), this.getX(), this.getY());
  }

}
