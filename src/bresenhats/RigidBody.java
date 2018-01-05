package bresenhats;

public abstract class RigidBody extends MovableGameObject{
    
    private AABB boundingBox;
        
    public RigidBody(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        boundingBox = new AABB(this.getPosition().getXInt(),
                               this.getPosition().getYInt(),
                               this.getPosition().getXInt() + this.getWidth(),
                               this.getPosition().getYInt() + this.getHeight());
    }
    
    /**
     * Checks if this rigid body has collided with another
     * @param secondBody the other rigid body
     * @return return true iff these rigid bodies have collided
     */
    public boolean hasCollidedWithRigidBody(RigidBody secondBody){
        return boundingBox.hasIntersected(secondBody.getBoundingBox());
    }
    
    /**
     * Checks if this rigid body has collided with the level
     * @param level
     * @return true iff this rigid body has collided
     */
    public boolean hasCollidedWithLevel(Level level){
        
        //Loops iterate in a rectangle around object, checking for level intersections
        for(int yPos = this.getPosition().getYInt(); yPos <= this.getPosition().getYInt() + this.getHeight(); yPos++){
            for(int xPos = this.getPosition().getXInt(); xPos <= this.getPosition().getXInt() + this.getWidth(); xPos++){

                //Check Collision
                if(level.isOverlapping(xPos, yPos)){
                    return true;
                }


                if(yPos > this.getPosition().getYInt() && yPos < this.getPosition().getYInt() + this.getHeight() && xPos == this.getPosition().getXInt()) {
                    xPos = this.getPosition().getXInt() + this.getWidth() - 1;
                }
            }
        }
        
        return false;
    }
    
    public void addInstantaniousAcceleration(Vector2D acceleration){
       // TODO complete
    }
    
    public void addConstantAcceleration(){
      // TODO complete
    }
    
    public void applyAllAccelerations(){
        // TODO complete
    }
    
    public AABB getBoundingBox(){
        return boundingBox;
    }
}
