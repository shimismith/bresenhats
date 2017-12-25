package bresenhats;

public abstract class MovableGameObject extends GameObject{
  
  private Vector2D vel;
  
  /** True if the player is on the ground or a platform */
  private boolean onGround;
  
  public MovableGameObject(int x, int y, int width, int height) {
    super(x, y, width, height);
    this.vel = new Vector2D(0, 0);
  }
  
  public Vector2D getVel() {
    return this.vel;
  }
  
  @Override
  public void move(double time) {
    this.getPosition().increase(this.getVel(), time);
    
    if(this.getX() < 0) {
      this.setX(0);
    }
    else if(this.getX() + this.getWidth() > Main.WIDTH) {
      this.setX(Main.WIDTH - this.getWidth());
    }
    
    if(this.getY() + this.getHeight() > Main.HEIGHT) {
      this.setY(Main.HEIGHT - this.getHeight());
      this.vel.setY(0);
      this.onGround = true;
    }
  }

  public boolean isOnGround() {
    return this.onGround;
  }

  public void setOnGround(boolean onGround) {
    this.onGround = onGround;
   }
  
}
