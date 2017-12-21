package bresenhats;

/** This is a game object that does not move **/
public abstract class StaticGameObject extends GameObject{
  
  public StaticGameObject(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  /** Move does nothing and no StaticGameObject can move
   * This function is strictly to document and enforce the above statement.
   * **/
  // it's meant to be unused it's to prevent idiots from breaking this code
  @SuppressWarnings("unused")
  private final void move() {}
  
  
}
