package bresenhats;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

  // flags for what the player has to do
  private boolean goRight = false;
  private boolean goLeft = false;
  private boolean jump = false;

  /** Handles key inputs */
  public void handleKeyPress(KeyEvent e) {
    KeyCode code = e.getCode();
    if (code == KeyCode.LEFT) {
      this.goLeft = true;
    } 
    if (code == KeyCode.RIGHT) {
      this.goRight = true;
    }
    if (code == KeyCode.UP) {
      this.jump = true;
    }
  }

  /** Handles key inputs */
  public void handleKeyRelease(KeyEvent e) {

    KeyCode code = e.getCode();

    if (code == KeyCode.LEFT) {      
      this.goLeft = false;
    } 
    if (code == KeyCode.RIGHT) {      
      this.goRight = false;
    }
    if (code == KeyCode.UP) {
      this.jump = false;
    }
  }
  
  public boolean goLeft() {
    return this.goLeft;
  }
  
  public boolean goRight() {
    return this.goRight;
  }
  
  public boolean jump() {
    return this.jump;
  }
}
