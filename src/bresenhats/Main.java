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
  
  private static ArrayList<GameObject> gameObjects;
  
  /** Manages the key controls for the player */
  private static Controller controller;
  
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
    Player player = new Player(50, 2, 4, "res/runningcat.png");
    controller = new Controller(player);
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
        
        // background image clears canvas
        gc.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
        // I just add 1 to the time instead of having stupidly large velocities
        gameObjects.get(0).move(t + 1);
        drawWorld(gc, t);
      }
    }.start();
        
    primaryStage.show();

  }
  
  /** Draws all the objects in the game */
  private static void drawWorld(GraphicsContext gc, double time) {
    // TODO only draw things that are on the screen
    for (GameObject gameObject : Main.gameObjects) {
      gameObject.draw(gc, time);
    }
  }
 
  
}
