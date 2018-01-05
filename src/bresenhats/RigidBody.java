package bresenhats;

public abstract class RigidBody extends MovableGameObject{
    
    private AABB boundingBox = new AABB(this.getPosition().getXInt(), this.getPosition().getYInt(), this.getPosition().getXInt() + this.getWidth(), this.getPosition().getYInt() + this.getHeight());
    private boolean collided;
    
    //ArrayList<String> accelerations = new ArrayList<String>();
    
    
    public RigidBody(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    //Check if AABB has collided with another RigidBody
    public boolean hasCollidedWithRigidBody(RigidBody secondBody){
        return boundingBox.hasIntersected(secondBody.getBoundingBox());
    }
    
    //Check if rectangle around rigidBody has collided with the level
    public boolean hasCollidedWithLevel(Level level){
        
        //Loops iterate in a rectangle around object, checking for level intersections
        for(int yPos = this.getPosition().getYInt(); yPos <= this.getPosition().getYInt() + this.getHeight(); yPos++){
            for(int xPos = this.getPosition().getXInt(); xPos <= this.getPosition().getXInt() + this.getWidth(); xPos++){

                //Check Collision
                if(level.isOverlapping(xPos, yPos)){
                    return true;
                }


                if(yPos > this.getPosition().getYInt() && yPos < this.getPosition().getYInt() + this.getHeight() && xPos == this.getPosition().getXInt())
                    xPos = this.getPosition().getXInt() + this.getWidth() - 1;
            }
        }
        return false;
    }
    
    public void addInstantaniousAcceleration(Vector2D acceleration){
        
    }
    
    public void addConstantAcceleration(){
        
    }
    
    public void applyAllAccelerations(){
        
    }
    
    public AABB getBoundingBox(){
        return boundingBox;
    }
}
