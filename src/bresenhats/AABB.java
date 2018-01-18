package bresenhats;

/** Axis aligned boundary box */
public class AABB {
    
    //Defines a rectangle
    private Vector2D topLeft;
    private Vector2D bottomRight;
    
    public AABB(double topLeftX, double topLeftY, double bottomRightX, double bottomRightY){
        this.topLeft = new Vector2D(topLeftX, topLeftY);
        this.bottomRight = new Vector2D(bottomRightX, bottomRightY);
    }
    
    /**
     * Checks if bounding boxes have intersected
     * @param secondBoundingBox
     * @return true if bounding boxes have intersected, false otherwise
     */
    boolean hasIntersected(AABB secondBoundingBox){
        
        //Check if boxes are seperate
        if(this.bottomRight.getX() < secondBoundingBox.topLeft.getX() || this.topLeft.getX() > secondBoundingBox.bottomRight.getX()) {
            return false;
        }
        if(this.bottomRight.getY() < secondBoundingBox.topLeft.getY() || this.topLeft.getY() > secondBoundingBox.bottomRight.getY()) {
            return false;
        }
        
        //If boxes aren't seperate, there is a collision
        return true;
        
    }
    
}
