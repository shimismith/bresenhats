package bresenhats;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Level {

  /** Image displaying map of static objects for collision purposes */
  private Image collisionLayer;

  /** Image of level that is displayed */
  private Image graphicsLayer;
  
  /** Image for displaying graphics overtop all other objects */
  private Image overLayer; 

  private Vector2D startPosition;

  /**
   * Precondition - level files must follow the following naming format
   * <world-number><level-number>c.png for the collisionLayer and <world-number><level-number>g.png
   * for the graphics layer and <world-number><level-number>o.png for the over layer.
   * 
   * As an example level 1 of world 1 would be 11c.png and 11g.png and 11o.png
   * 
   * @param world the world number
   * @param level the level number
   * @param startX
   * @param startY
   */
  public Level(int world, int level, int startX, int startY) {
    String collisionFileName = "res/" + world + level + "c.png";
    String graphicsFileName = "res/" + world + level + "g.png";
    String overFileName = "res/" + world + level + "o.png";

    this.collisionLayer = new Image(new File(collisionFileName).toURI().toString());
    this.graphicsLayer = new Image(new File(graphicsFileName).toURI().toString());
    this.overLayer = new Image(new File(overFileName).toURI().toString());

    this.startPosition = new Vector2D(startX, startY);

  }
  
  
  /**
   * Checks if a point overlaps a point in the level
   * @param x x coordinate of point being checked
   * @param y y coordinate of point being checked
   * @return true if point overlaps a point in the level. 
   * Note: we also return true if the point is outside the level
   */
  public boolean isOverlapping(int x, int y) {
    // make sure in bounds of image file
    if (x > 0 && x < this.collisionLayer.getWidth()) {
      if (y > 0 && y < this.collisionLayer.getHeight()) {
        // return if there is a pixel in the file
        return this.collisionLayer.getPixelReader().getColor(x, y).equals(Color.BLACK);
      }
    }
    
    return true;
  }
  
  public void drawBackground(GraphicsContext gc, Camera camera){
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    gc.drawImage(this.getGraphicsLayer(), 
                 camera.getPosition().getX(),
                 camera.getPosition().getY(),
                 this.getGraphicsLayer().getWidth(),
                 this.getGraphicsLayer().getHeight());
  }
  
  public void drawForeground(GraphicsContext gc, Camera camera){
    gc.drawImage(this.getOverLayer(), camera.getPosition().getX(), camera.getPosition().getY());
  }
  
  public Image getGraphicsLayer(){
    return graphicsLayer;
  }
  
  public Image getOverLayer(){
    return overLayer;
  }
  
}
