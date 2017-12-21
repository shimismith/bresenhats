package bresenhats;

public class Player extends MovableGameObject{
  
  private double health;
  
  public Player(int x, int y, int width, int height) {
    super(x, y, width, height);
    
    this.health = 100;
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

}
