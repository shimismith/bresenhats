package bresenhats;

import javafx.scene.input.KeyEvent;

public class Controller {

  /** The player this controller controls */
  private Player player;

  public Controller(Player player) {
    this.player = player;
  }

  /** Handles key inputs */
  public void handle(KeyEvent e) {
    switch (e.getCode()) {
      case LEFT:
        player.move(player.getX() - 10, player.getY());
        break;
      case RIGHT:
        player.move(player.getX() + 10, player.getY());
        break;
    }

  }
}
