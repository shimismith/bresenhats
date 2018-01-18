package bresenhats;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

  /** Duration in seconds of each frame */
  public static final int WIDTH = 1080;
  public static final int HEIGHT = 720;
  
  public static final int PLAYER_WIDTH = 80;
  public static final int PLAYER_HEIGHT = 100;

  private static ArrayList<GameObject> gameObjects;

  /** Manages the key controls for the player */
  private static Controller controller;
  
  private static Camera camera;
  private static Level lev;
  private static Player player;

  public static void main(String[] args) {
    try {
      initGame();
    } catch (IOException e) {
      Platform.exit();
    }

    Application.launch(args);
  }

  private static void initGame() throws IOException {
    gameObjects = new ArrayList<GameObject>();
    lev = new Level(1, 1, 0, 0);
    player = new Player(lev.getStartingPosition().getXInt(), lev.getStartingPosition().getYInt(), PLAYER_WIDTH, PLAYER_HEIGHT, 9, 2, "res/spriteSheet.png", 3.5, 22);
    controller = new Controller();
    camera = new Camera(-Main.WIDTH/2, -Main.HEIGHT/2);
    gameObjects.add(player);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Many Hats - Beeline V2");

    Group root = new Group();
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(controller::handleKeyPress);
    scene.setOnKeyReleased(controller::handleKeyRelease);

    primaryStage.setScene(scene);
    Canvas canvas = new Canvas(Main.WIDTH, Main.HEIGHT);  // for drawing stuff
    root.getChildren().add(canvas);

    GraphicsContext gc = canvas.getGraphicsContext2D();

    drawWorld(gc, 0);

    new CustomAnimationTimer() {
      public void handle(long currentNanoTime) {
        double t = (currentNanoTime - this.getPrevNanoTime()) / 1000000000.0;
        this.setPrevNanoTime(currentNanoTime);
        
        
        // respond to controls
        
        if(controller.goLeft()){
          player.addInstantaniousAcceleration(new Vector2D(-player.getHorizontalVelocity(), 0));
        }
        
        if(controller.goRight()){
            player.addInstantaniousAcceleration(new Vector2D(player.getHorizontalVelocity(), 0));
        }
        
        player.addInstantaniousAcceleration(Vector2D.GRAVITY);
        
        if(controller.jump()){
            if(player.isOnGround()){
              player.addInstantaniousAcceleration(new Vector2D(0, -player.getVerticalVelocity()));
            }
            player.setOnGround(false);
        }
        
        player.handleLevelCollisions(lev);

        // background image clears canvas
        gc.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
        
        // I just add 1 to the time instead of having stupidly large velocities
        // ^ Changing the time step to 0.9 actually helps the collision system, 
        // if this is no good i'll try to figure out a better way
        player.move(t + 0.9);  // move the player
        
        drawWorld(gc, t);  // t here is used strictly for animation
      }
    }.start();

    primaryStage.show();

  }

  /** Draws all the objects in the game */
  private static void drawWorld(GraphicsContext gc, double time) {
    
    camera.adjustToPlayerPosition(player);
    
    lev.drawBackground(gc, camera);
    
    // TODO only draw things that are on the screen
    for (GameObject gameObject : Main.gameObjects) {  
      gameObject.draw(gc, time, camera);
    }
    
    lev.drawForeground(gc, camera);
  }

}
