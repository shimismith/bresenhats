package bresenhats;

import javafx.scene.input.KeyEvent;

public class Controller {

  /** The player this controller controls */
  private MovableGameObject player;

  public Controller(MovableGameObject player) {
    this.player = player;
  }

  /** Handles key inputs */
  public void handleKeyPress(KeyEvent e) {
    switch (e.getCode()) {
      case LEFT:
        this.player.getVel().setX(-10);        
        break;
      case RIGHT:
        this.player.getVel().setX(10);
        break;
      case UP:
        // only can jump when on ground
        if (this.player.isOnGround()) {
          this.player.getVel().setY(-20);
          this.player.setOnGround(false);
        }
        break;
    }
  }

  /** Handles key inputs */
  public void handleKeyRelease(KeyEvent e) {
    switch (e.getCode()) {
      case LEFT:
        // if going left - could be going right but left button was being held then released
        if (this.player.getVel().getX() < 0) {
          this.player.getVel().setX(0);
        }
        break;
      case RIGHT:
        if (this.player.getVel().getX() > 0) {
          this.player.getVel().setX(0);
        }
        break;
    }
  }
}
