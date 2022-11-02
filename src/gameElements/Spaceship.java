package gameElements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Spaceship extends PlayerMover{
	
	private final int spaceShipHeight = 30;
	private int spaceShipWidth = 30;
	private int spaceShipOffset = 15;
	private ImageView spaceShipView;
	private Image image;
	
	private final static String spaceShipPath = "resources/spaceShip.gif";
	
	public Spaceship(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		
		try {
	        image = new Image(new FileInputStream(spaceShipPath));
	        
	    } catch (FileNotFoundException e) {}
//		spaceShipView.setFitWidth(spaceShipWidth);
//		spaceShipView.setFitHeight(spaceShipHeight);
		this.moverWidth = spaceShipWidth;
		this.userPlayerMover = new Rectangle(moverXPosition, moverYPosition - spaceShipOffset, spaceShipWidth, spaceShipHeight);
		this.userPlayerMover.setFill(new ImagePattern(image));
		//these two don't seem necesary since initialized in playerMover but not entirely sure
//		spaceShipView.setX(screenWidth/2);
//		spaceShipView.setY(screenHeight-screenHeight/10);
	}
}
