package bresenhats;

public class MovableGameObject extends GameObject{
  public MovableGameObject(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void move(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
}
