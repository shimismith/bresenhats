package bresenhats;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
  
  /** Duration in seconds of each frame */
  private static final double FRAME_SPEED = 0.016; // approx 60 FPS
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
    Player player = new Player(50, "person.png");
    controller = new Controller(player);
    gameObjects.add(player);
  }
  
  /**
   * Credits to Paul Gries CSC207 A2 starter code
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Many Hats - Beeline V2");
    
    Group root = new Group();
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(controller::handle);
    
    primaryStage.setScene(scene);
    Canvas canvas = new Canvas(Main.WIDTH, Main.HEIGHT);  // for drawing stuff
    root.getChildren().add(canvas);
    
    GraphicsContext gc = canvas.getGraphicsContext2D();

    drawWorld(gc);

    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);

    // this is what is done each frame
    KeyFrame kf = new KeyFrame(Duration.seconds(Main.FRAME_SPEED), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent ae) {
        
        // TODO loop through everything - update and draw

        // Clear the canvas
        gc.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
        drawWorld(gc);
      }
    });

    gameLoop.getKeyFrames().add(kf);
    gameLoop.play();
    
    primaryStage.show();

  }
  
  /** Draws all the objects in the game */
  private static void drawWorld(GraphicsContext gc) {
    // TODO only draw things that are on the screen
    for (GameObject gameObject : Main.gameObjects) {
      gameObject.draw(gc);
    }
  }
 
  
}
