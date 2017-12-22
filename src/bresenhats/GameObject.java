package bresenhats;

import javafx.scene.canvas.GraphicsContext;

/** This class represents any object in the game **/
public abstract class GameObject {

  private int x;
  private int y;
  private int width;
  private int height;
  
  public GameObject(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }
  
  protected void setHeight(int height) {
    this.height = height;
  }
  
  protected void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }
  
  public abstract void draw(GraphicsContext gc);
  
  public abstract void move(double time);
}
