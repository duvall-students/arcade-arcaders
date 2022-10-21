package breakout2;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends GameElement{

	//Setting variable values for now these are just place holders
	//will change when we find better values
	private final int paddleHeight = 10;
	private int paddleWidth = 80;
	private final Paint paddleColor = Color.BLUE;
	private int paddleSpeed = 12;
	private int paddleXPosition = 0;
	private int paddleYPosition = 0;
	private int paddleSizeIncrement = 20;
	private final int screenWidth;
	private final int screenHeight;
	private Rectangle userPaddle;
	
	public Paddle(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.paddleXPosition = screenWidth/2;
		this.paddleYPosition = screenHeight - screenHeight/10;
		this.userPaddle = new Rectangle(paddleXPosition, paddleYPosition, paddleWidth, paddleHeight);
		this.userPaddle.setFill(paddleColor);
	}
	
	public void moveLeft() {
		userPaddle.setX(Math.max(this.userPaddle.getX() - paddleSpeed, 0));
		paddleXPosition = (int) Math.max(this.userPaddle.getX() - paddleSpeed, 0);
	}
	
	public void moveRight() {
		userPaddle.setX(Math.min(this.userPaddle.getX() + paddleSpeed, screenWidth - paddleWidth));
		paddleXPosition = (int) Math.min(this.userPaddle.getX() + paddleSpeed, screenWidth - paddleWidth);
	}
	
	public void increaseX() {
		this.userPaddle.setWidth(userPaddle.getWidth() + paddleSizeIncrement);
		this.paddleWidth += paddleSizeIncrement;
	}
	
	public Bounds getBounds() {
		return userPaddle.getBoundsInParent();
	}

	public Node getNode() {
		return this.userPaddle;
	}

	public int getX() {
		return paddleXPosition;	
	}
	
	public int getY() {
		return paddleYPosition;	
	}
	
	public int getPaddleWidth() {
		return paddleWidth;	
	}
	
	public int getPaddleSpeed() {
		return paddleSpeed;
	}
	
//	public void moveLeftEdge() {
//		paddleXPosition = 0;
//		userPaddle.setX(0);
//		System.out.println("Hit the edge");
//	}
	
//	public void moveRightEdge(int screenWidth) {
//		paddleXPosition = screenWidth-paddleWidth;
//		userPaddle.setX(screenWidth-paddleWidth);
//		System.out.println("Hit the edge");
//	}


	
	
}
