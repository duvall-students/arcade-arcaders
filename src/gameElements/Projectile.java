package gameElements;
import gamestates.GameState;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public abstract class Projectile extends GameElement{
	 protected Circle circle;
	 protected Point2D myVelocity = new Point2D(100, 100);
	 protected GameState parentGameState;
	 
	 public Bounds getBounds() {
	    	return this.circle.getBoundsInParent();
	    }
	    
	    public Node getNode() {
	    	return this.circle;
	    }
	   
	public void move (double elapsedTime) {
        circle.setCenterX(circle.getCenterX() + myVelocity.getX() * elapsedTime);
        circle.setCenterY(circle.getCenterY() + myVelocity.getY() * elapsedTime);
    }

	public abstract void bounce(GameElement ge);

	public abstract void bouncePaddle(PlayerMover playerMover);

	public abstract void bounceOffWall(int screenWidth, int screenHeight);

}