package bresenhats;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Level {
    
    private Image collisionLayer;
    private Image graphicsLayer;
    
    private Vector2D startPosition;
    
    /*
    Here is how all level files will be specified:
    <world-number><level-number>c.png for the collisionLayer and
    <world-number><level-number>g.png for the graphics layer.
    
    As an example level 1 of world 1 would be 11c.png and 11g.png
    */
    public Level(int _world, int _level, int _startX, int _startY){
        String collisionFileName = "" + _world + _level + "c.png";
        String graphicsFileName = "" + _world + _level + "g.png";
        
        this.collisionLayer = new Image(collisionFileName);
        this.graphicsLayer = new Image(graphicsFileName);
        
        this.startPosition = new Vector2D(_startX, _startY);

    }
    
    public boolean isOverlapping(int x, int y){
        if(x > 0 && x < this.collisionLayer.getWidth()){
            if(y > 0 && y < this.collisionLayer.getHeight()){
                return this.collisionLayer.getPixelReader().getColor(x, y).equals(Color.BLACK);
            }
        }
        return true;
    }
    
    
    
    
    
    
}
