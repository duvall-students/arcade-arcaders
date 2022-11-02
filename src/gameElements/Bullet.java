package gameElements;
import gamestates.GameState;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Bullet extends Projectile{

	private final int BULLET_SPEED = 50;
	private final int BULLET_SIZE = 5;
	
	

	public Bullet (int moverXPosition, int moverYPosition) {
        circle = new Circle(BULLET_SIZE);
        circle.setCenterX(moverXPosition);
        circle.setCenterY(moverYPosition);
	}

	@Override
	public void move (double elapsedTime) {
        circle.setCenterY(circle.getCenterY() - myVelocity.getY() * elapsedTime);
    }


	@Override
	public void bounce(GameElement ge) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void bouncePaddle(PlayerMover playerMover) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void bounceOffWall(int screenWidth, int screenHeight) {
		// TODO Auto-generated method stub
		
	}

}
