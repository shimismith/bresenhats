package bresenhats;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends MovableGameObject {

  private double health;
  private Image sprite;

  public Player(int x, int y, int width, int height, String path) throws IOException {
    super(x, y, width, height);

    this.health = 100;

    FileInputStream inputStream = new FileInputStream(path);
    this.sprite = new Image(inputStream);
    inputStream.close();
    
  }

  @Override
  public void move(int x, int y) {
    // TODO check collisions and move properly
    super.move(x, y);
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
