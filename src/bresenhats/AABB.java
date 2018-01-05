package bresenhats;

public class AABB {
    
    //Defines a rectangle
    private Vector2D topLeft;
    private Vector2D bottomRight;
    
    public AABB(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY){
        topLeft.setX(topLeftX);
        topLeft.setY(topLeftY);
        bottomRight.setX(bottomRightX);
        bottomRight.setY(bottomRightY);
    }
    
    //Returns true if bounding boxes have intersected
    boolean hasIntersected(AABB secondBoundingBox){
        
        //Check if boxes are seperate
        if(this.bottomRight.getX() < secondBoundingBox.topLeft.getX() || this.topLeft.getX() > secondBoundingBox.bottomRight.getX())
            return false;
        if(this.bottomRight.getY() < secondBoundingBox.topLeft.getY() || this.topLeft.getY() > secondBoundingBox.bottomRight.getY())
            return false;
        
        //If boxes aren't seperate, there is a collision
        return true;
        
    }
    
}
