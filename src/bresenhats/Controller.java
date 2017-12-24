package bresenhats;

import javafx.scene.input.KeyEvent;

public class Controller {

  /** The player this controller controls */
  private Player player;

  public Controller(Player player) {
    this.player = player;
  }

  /** Handles key inputs */
  public void handleKeyPress(KeyEvent e) {
    switch (e.getCode()) {
      case LEFT:
        this.player.setVelX(-10);
        break;
      case RIGHT:
        this.player.setVelX(10);
        break;
      case UP:
        // only can jump when on ground
        if(this.player.isOnGround()){
          this.player.setVelY(-20);
          this.player.setOnGround(false);
        }
    }
  }

  /** Handles key inputs */
  public void handleKeyRelease(KeyEvent e) {
    switch (e.getCode()) {
      case LEFT:   case RIGHT: this.player.setVelX(0);
    }
  }
}
