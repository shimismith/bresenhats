package bresenhats;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends MovableGameObject {

  private double health;
  private Image sprite;

  /** True if the player is on the ground or a platform */
  private boolean onGround;

  public Player(int x, int y, int width, int height, String name) throws IOException {
    super(x, y, width, height);

    this.health = 100;

    FileInputStream inputStream = new FileInputStream("res/" + name);
    this.sprite = new Image(inputStream, this.getWidth(), this.getHeight(), true, true);
    inputStream.close();
  }

  /** This constructor doesn't take in y, it puts the player as low as possible */
  public Player(int x, int width, int height, String name) throws IOException {
    this(x, Main.HEIGHT - height, width, height, name);
  }

  /** This constructor just used the width and height of the image */
  public Player(int x, String name) throws IOException {
    super(x, 0, 0, 0); // had to put zero cause constructor needs to be called but I still have to
                       // get the values

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

  @Override
  public void move(double time) {
    super.move(time);
    // gravity
    if (!this.onGround || this.getVelY() < 0) {
      this.setVelY(this.getVelY() + 0.98 * time);
      int newY = (int) (this.getY() + this.getVelY() * time);

      if (newY + this.getHeight() >= Main.HEIGHT) {
        this.onGround = true;
        this.setVelY(0);
        this.setY(Main.HEIGHT - this.getHeight());
      } else {
        this.setY(newY);
      }
    }
  }

  public boolean isOnGround() {
    return this.onGround;
  }

  public void setOnGround(boolean onGround) {
    this.onGround = onGround;
   }

  /** Draws the player */
  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(this.getSprite(), this.getX(), this.getY());
  }



}
