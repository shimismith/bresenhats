package bresenhats;

public class Camera {
            
    private Vector2D pos;
    
    private double horizontalJuice = 0.1;
    private double verticalJuice = 0.3;
    
    public Camera(int x, int y){
        pos = new Vector2D(x, y);
    }
    
    //Adjust the camera's position (with cool smoothing effects of course)
    public void setCameraPosition(Vector2D position){
        //Smoothly transition camera positions
        pos.setX(pos.getX() + (position.getX() - pos.getX()) * horizontalJuice);
        pos.setY(pos.getY() + (position.getY() - pos.getY()) * verticalJuice);
        
    }
    
    //Adjusts the camera to follow the player, and make player the centre of the screen
    public void adjustToPlayerPosition(RigidBody body){
        this.setCameraPosition(new Vector2D(-body.getPosition().getX() + 300, -body.getPosition().getY() + 100));
    }
    
    public Vector2D getPosition(){
        return pos;
    }
    
}
