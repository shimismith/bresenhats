package bresenhats;

public abstract class RigidBody extends MovableGameObject{
    
    private AABB boundingBox;
    boolean collided;
    
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
        
        collided = false;
        
        for(int yPos = this.getPosition().getYInt(); yPos <= this.getPosition().getYInt() + this.getHeight() && !collided; yPos++){
            for(int xPos = this.getPosition().getXInt(); xPos <= this.getPosition().getXInt() + this.getWidth() && !collided; xPos++){

                //Check Collision
                if(level.isOverlapping(xPos, yPos)){
                    return true;
                }
                //Check Collision


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
