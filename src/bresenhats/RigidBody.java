package bresenhats;

public abstract class RigidBody extends MovableGameObject{
    
    private AABB boundingBox;
    
    private double dragForceStrength = 0.1;
    
    private double horizontalVelocity;
    private double horizontalAirVelocity;
    private double verticalVelocity;
        
    public RigidBody(int x, int y, int width, int height, double hVelocity, double vVelocity) {
        super(x, y, width, height);
        
        boundingBox = new AABB(this.getPosition().getX(),
                               this.getPosition().getY(),
                               this.getPosition().getX() + this.getWidth(),
                               this.getPosition().getY() + this.getHeight());
        
        this.setOnGround(false);
        this.horizontalVelocity = hVelocity;
        this.verticalVelocity = vVelocity;
        this.horizontalAirVelocity = hVelocity - 1;
        
    }
    
    /**
     * Checks if this rigid body has collided with another
     * @param secondBody the other rigid body
     * @return return true iff these rigid bodies have collided
     */
    public boolean hasCollidedWithRigidBody(RigidBody secondBody){
        return boundingBox.hasIntersected(secondBody.getBoundingBox());
    }
    
    //Check if the rigidbody is colliding with the level, and stop it
    public void handleLevelCollisions(Level lev){
        if(this.willCollideWithLevelY(lev)){
            if(this.getVel().getY() > 0){
                this.setOnGround(true);
            }
            this.resolveCollisionsY(lev);
        }
        else{
            this.setOnGround(false);
        }
        
        if(this.willCollideWithLevelX(lev)){
            this.resolveCollisionsX(lev);
        }
    }
    
    
    /**
     * Checks if this rigid body has collided with the level
     * @param level
     * @return true iff this rigid body has collided
     */
    public boolean willCollideWithLevelX(Level level){
        
        //Loops iterate in a rectangle around object, checking for level intersections
        for(double yPos = this.getPosition().getY(); yPos <= this.getPosition().getY() + this.getHeight(); yPos++){
            for(double xPos = this.getPosition().getX(); xPos <= this.getPosition().getX() + this.getWidth(); xPos++){

                //Check Collision
                if(level.isOverlapping(xPos + this.getVel().getX(), yPos)){
                    return true;
                }
                
                if(level.isOverlappingRed(xPos + this.getVel().getX(), yPos)){
                    this.respawnBody(level);
                }


                if(yPos > this.getPosition().getY() && yPos < this.getPosition().getY() + this.getHeight() && xPos == this.getPosition().getX()) {
                    xPos = this.getPosition().getX() + this.getWidth() - 1;
                }
            }
        }
        
        return false;
    }
    
    //Checks if the player will overlap with the level in the Y direction
    public boolean willCollideWithLevelY(Level level){
        
        //Loop around rigidbody
        for(double yPos = this.getPosition().getY(); yPos <= this.getPosition().getY() + this.getHeight(); yPos++){
            for(double xPos = this.getPosition().getX(); xPos <= this.getPosition().getX() + this.getWidth(); xPos++){
                
                //Check Collision
                if(level.isOverlapping(xPos, yPos + this.getVel().getY())){
                    return true;
                }
                
                if(level.isOverlappingRed(xPos, yPos + this.getVel().getY())){
                    this.respawnBody(level);
                }
                
                if(yPos > this.getPosition().getY() && yPos < this.getPosition().getY() + this.getHeight() && xPos == this.getPosition().getX()){
                    xPos = this.getPosition().getX() + this.getWidth() - 1;
                }
            }
        }
        return false;
    }
    
    private void respawnBody(Level level){
        this.setVelocity(new Vector2D(0, 0));
        this.setX(level.getStartingPosition().getX());
        this.setY(level.getStartingPosition().getY());
    }
    
    public void applyAllAccelerations(){
        this.addInstantaniousAcceleration(new Vector2D(-this.getVel().getX() * dragForceStrength, 0));
        
        if(Math.abs(this.getVel().getX()) < 0.01){
            this.setVelocity(new Vector2D(0, this.getVel().getY()));
        }
        
    }
    
    //Stop the rigidbody from moving left or right
    public void resolveCollisionsX(Level level){
        this.setVelocity(new Vector2D(0, this.getVel().getY()));
    }
    
    //Stop the rigidbody from moving up or down
    public void resolveCollisionsY(Level level){
        this.setVelocity(new Vector2D(this.getVel().getX(), 0));
    }
    
    public void addInstantaniousAcceleration(Vector2D acceleration){
       this.addVelocity(acceleration);
    }
    
    @Override
    public void move(double time) {
      this.applyAllAccelerations();
      super.move(time);
    }
    
    public double getHorizontalVelocity(){
        if(this.isOnGround()){
            return horizontalVelocity;
        }
        else{
            return horizontalAirVelocity;
        }
    }
    
    public double getVerticalVelocity(){
        return verticalVelocity;
    }
    
    public AABB getBoundingBox(){
        return boundingBox;
    }
}
