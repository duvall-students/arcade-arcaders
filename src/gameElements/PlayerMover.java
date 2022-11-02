package gameElements;
import gamestates.GameState;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class PlayerMover extends GameElement{
	


	//some of these may need to be moved to subclasses 
	protected Rectangle userPlayerMover;
	protected int moverXPosition = 0;
	protected int moverYPosition = 0;
	private int moverSpeed = 12;
	//screenWidth required it to be initialized since screenWidth is a final variable 
	protected int screenWidth=0;
	protected int screenHeight=0;
	//screenHeight never used as of now so commented out
	//private final int screenHeight;
	private final int moverHeight = 10;
	protected int moverWidth = 80;
	
	
	
	//potentially could use a constructor?
	public PlayerMover(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.moverXPosition = screenWidth/2;
		this.moverYPosition = screenHeight - screenHeight/10;
	}
	
	public void moveLeft() {
		userPlayerMover.setX(Math.max(this.userPlayerMover.getX() - moverSpeed, 0));
		moverXPosition = (int) Math.max(this.userPlayerMover.getX() - moverSpeed, 0);
	}
	
	public void moveRight() {
		//Problem is second half of min statement (screenwidth - moverwidth)
		userPlayerMover.setX(Math.min(this.userPlayerMover.getX() + moverSpeed, screenWidth - moverWidth));
		moverXPosition = (int) Math.min(this.userPlayerMover.getX() + moverSpeed, screenWidth - moverWidth);
	}
	
	  
	
	public Bounds getBounds() {
		return userPlayerMover.getBoundsInParent();
	}

	public Node getNode() {
		return this.userPlayerMover;
	}

	public int getX() {
		return moverXPosition;	
	}
	
	public int getY() {
		return moverYPosition;	
	}
	
	public int getPaddleWidth() {
		return moverWidth;	
	}
	
	public int getPaddleSpeed() {
		return moverSpeed;
	}

}
