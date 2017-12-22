package bresenhats;

public abstract class MovableGameObject extends GameObject{
  
  private double velX;
  private double velY;
  
  public MovableGameObject(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void move(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
  
  public void move(double time) {
    this.setX((int) (this.getX() + velX * time));
    this.setX((int) (this.getX() + velX * time));
  }
  
  public void setVelX(double velX) {
    this.velX = velX;
  }
  
  public void setVelY(double velY) {
    this.velY = velY;
  }
  
  public double getVelX() {
    return this.velX;
  }
  
  public double getVelY() {
    return this.velY;
  }
  
}
