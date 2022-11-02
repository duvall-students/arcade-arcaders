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
	private int paddleSizeIncrement = 20;
//	private Rectangle userPaddle;
	
	public Paddle(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		this.userPlayerMover = new Rectangle(moverXPosition, moverYPosition, paddleWidth, paddleHeight);
		this.userPlayerMover.setFill(paddleColor);
	}
	  
	public void increaseX() {
		this.userPlayerMover.setWidth(userPlayerMover.getWidth() + paddleSizeIncrement);
		this.paddleWidth += paddleSizeIncrement;
	}
	 
//	@Override
//	public Node getNode() {
//		return this.userPlayerMover;
//	}
	



	
	
}
