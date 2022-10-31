package gameElements;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class PlayerMover extends GameElement{
	//some of these may need to be moved to subclasses 
	protected Rectangle userPlayerMover;
	private int moverXPosition = 0;
	private int moverYPositon = 0;
	private int moverSpeed = 12;
	//screenWidth required it to be initialized since screenWidth is a final variable 
	protected int screenWidth=0;
	protected int screenHeight=0;
	//screenHeight never used as of now so commented out
	//private final int screenHeight;
	private final int moverHeight = 10;
	private int moverWidth = 80;
	
	
	
	//potentially could use a constructor?
 
	
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
		return moverYPositon;	
	}
	
	public int getPaddleWidth() {
		return moverWidth;	
	}
	
	public int getPaddleSpeed() {
		return moverSpeed;
	}

}
