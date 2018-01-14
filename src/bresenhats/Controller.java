package bresenhats;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

  public boolean goRight = false, goLeft = false, jump = false;
  
  /** The player this controller controls */
  private Player player;

  public Controller(Player player) {
    this.player = player;
  }

  /** Handles key inputs */
  public void handleKeyPress(KeyEvent e) {
    KeyCode code = e.getCode();
    if (code == KeyCode.LEFT) {
      goLeft = true;
    } 
    if (code == KeyCode.RIGHT) {
      goRight = true;
    }
    if (code == KeyCode.UP) {
      jump = true;
    }
  }

  /** Handles key inputs */
  public void handleKeyRelease(KeyEvent e) {

    KeyCode code = e.getCode();

    if (code == KeyCode.LEFT) {      
      goLeft = false;
    } 
    if (code == KeyCode.RIGHT) {      
      goRight = false;
    }
    if (code == KeyCode.UP) {
      jump = false;
    }
  }
}
