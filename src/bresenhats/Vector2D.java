package bresenhats;

public class Vector2D {
  private double x;
  private double y;
  
  public static final Vector2D GRAVITY = new Vector2D(0, 1);
  
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public int getXInt() {
    return (int) this.x;
  }
  
  public int getYInt() {
    return (int) this.y;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public void increase(Vector2D vel, double time) {
    this.setX(this.getX() + vel.getX() * time);
    this.setY(this.getY() + vel.getY() * time);  // problamatic
  }
  
}
