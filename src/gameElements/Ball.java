package gameElements;
//package breakout2;

import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.lang.Math;


public class Ball extends Projectile{
    private final int BOUNCER_SIZE = 10;

    private Random dice = new Random();
    private final static int xOffSet = 50;
    private double velocityScaler = 100.0;

    public Ball (int screenWidth, int screenHeight) {
        circle = new Circle(BOUNCER_SIZE);
        circle.setCenterX(screenWidth/2);
        circle.setCenterY(screenHeight/2-xOffSet);
    }
     

     
    //moved into projectile
//    public void move (double elapsedTime) {
//        circle.setCenterX(circle.getCenterX() + myVelocity.getX() * elapsedTime);
//        circle.setCenterY(circle.getCenterY() + myVelocity.getY() * elapsedTime);
//    }

    
    //ball no longer bounces off floor
    @Override
    public void bounceOffWall (int screenWidth, int screenHeight) {
        if (circle.getCenterX() - BOUNCER_SIZE < 0) {
            myVelocity = new Point2D(Math.abs(myVelocity.getX()) * this.getRandomInRange(80, 120) /100.0, myVelocity.getY() * this.getRandomInRange(80, 120) /100.0);
        } else if (circle.getCenterX() > screenWidth - BOUNCER_SIZE) {
        	myVelocity = new Point2D(-Math.abs(myVelocity.getX()) * this.getRandomInRange(80, 120) /100.0, myVelocity.getY() * this.getRandomInRange(80, 120) /100.0);
        }
        if (circle.getCenterY() + BOUNCER_SIZE < 0 ) {
            myVelocity = new Point2D(myVelocity.getX() * this.getRandomInRange(80, 120) /100.0, Math.abs(myVelocity.getY()) * this.getRandomInRange(80, 120) /100.0);
        }
    }
    
    public void bounce(GameElement ge) {
    	Bounds geBounds = ge.getBounds();
    	if (this.getBounds().intersects(geBounds) && this.getBounds().getCenterX() > geBounds.getMinX() && this.getBounds().getCenterX() < geBounds.getMaxX()) {
    		myVelocity = new Point2D(myVelocity.getX(), myVelocity.getY() * -1);
    	} else if (this.getBounds().intersects(geBounds)) {
    		myVelocity = new Point2D(myVelocity.getX() * -1, myVelocity.getY());
    	}
    	
    	
        //myVelocity = new Point2D(myVelocity.getX(), -1 * myVelocity.getY());
    }
    
    //Adding sideBounce method to clear up bug from bouncing off side of brick
//    public void sideBounce() {
//    	myVelocity = new Point2D(-1 * myVelocity.getX(), myVelocity.getY());
//    }
    
    @Override
    public void bouncePaddle(PlayerMover playerMover) {
    	boolean intersects = this.getBounds().intersects(playerMover.getBounds());
		double ballX = this.getBounds().getCenterX();
		double ballY = this.getBounds().getCenterY();
		double paddleMiddleX = playerMover.getBounds().getCenterX();
		if (intersects && ballX < paddleMiddleX && ballY < playerMover.getBounds().getMaxY()) {
			myVelocity = new Point2D(-1 * Math.abs(myVelocity.getX()), -1 * myVelocity.getY());
		}
		else if (intersects && ballX >= paddleMiddleX && ballY < playerMover.getBounds().getMaxY()) {
			myVelocity = new Point2D(Math.abs(myVelocity.getX()), -1 * myVelocity.getY());
		}
    }
    
//    public Bounds getBounds() {
//    	return this.circle.getBoundsInParent();
//    }
//    
//    @Override
//    public Node getNode() {
//    	return this.circle;
//    }
     
    
    private Point2D normalizeVelocity(Point2D velocity) {
    	double unitXVelocity = velocity.getX() / Math.sqrt(velocity.getX() * velocity.getX() + velocity.getY() * velocity.getY());
    	double unitYVelocity = velocity.getY() / Math.sqrt(velocity.getX() * velocity.getX() + velocity.getY() * velocity.getY());
    	return new Point2D(unitXVelocity, unitYVelocity);
    }

}
