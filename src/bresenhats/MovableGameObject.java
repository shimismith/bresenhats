package bresenhats;

public abstract class MovableGameObject extends GameObject{
  
  private double velX;
  private double velY;
  
  public MovableGameObject(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  @Override
  public void move(double time) {
    int newX = (int) (this.getX() + velX * time);
    if(newX >= 0 && newX + this.getWidth() <= Main.WIDTH) {
      this.setX(newX);
    }
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
