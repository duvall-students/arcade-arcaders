package gameElements;
//package breakout2;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends PlayerMover{

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
	  
	public void increaseX() {
		this.userPaddle.setWidth(userPaddle.getWidth() + paddleSizeIncrement);
		this.paddleWidth += paddleSizeIncrement;
	}
	



	
	
}
