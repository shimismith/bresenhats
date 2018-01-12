package bresenhats;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

  /** The player this controller controls */
  private Player player;

  public Controller(Player player) {
    this.player = player;
  }

  /** Handles key inputs */
  public void handleKeyPress(KeyEvent e) {
    KeyCode code = e.getCode();
    if (code == KeyCode.LEFT) {
      player.addInstantaniousAcceleration(new Vector2D(-player.getHorizontalVelocity(), 0));  // TODO don't hardcode speed
    } 
    else if (code == KeyCode.RIGHT) {
      player.addInstantaniousAcceleration(new Vector2D(player.getHorizontalVelocity(), 0));
    }
    else if (code == KeyCode.UP) {
      // only can jump when on ground
      if (this.player.isOnGround()) {
        player.addInstantaniousAcceleration(new Vector2D(0, -player.getVerticalVelocity()));
        this.player.setOnGround(false);
      }
    }
  }

  /** Handles key inputs */
  public void handleKeyRelease(KeyEvent e) {

    KeyCode code = e.getCode();

    if (code == KeyCode.LEFT) {      
      // if going left - could be going right but left button was being held then released
      if (this.player.getVel().getX() < 0) {
        this.player.getVel().setX(0);
      }
    } 
    else if (code == KeyCode.RIGHT) {      
      if (this.player.getVel().getX() > 0) {
        this.player.getVel().setX(0);
      }
    }
  }
}
