package bresenhats;

import javafx.scene.canvas.GraphicsContext;

/** This class represents any object in the game **/
public abstract class GameObject {
  
  private Vector2D pos;
  private int width;
  private int height;
  
  public GameObject(int x, int y, int width, int height){
    this.pos = new Vector2D(x, y);
    this.width = width;
    this.height = height;
  }

  public Vector2D getPosition() {
    return this.pos;
  }

  
  public double getX() {
    return this.pos.getX();
  }
  
  public double getY() {
    return this.pos.getY();
  }
  
  public void setX(double x) {
    this.pos.setX(x);
  }
  
  public void setY(double y) {
    this.pos.setY(y);
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
