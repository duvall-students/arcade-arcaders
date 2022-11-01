package gameElements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spaceship extends PlayerMover{
	private final int spaceShipHeight = 30;
	private int spaceShipWidth = 40;
	private ImageView spaceShipView;
	
	private final static String spaceShipPath = "resources/spaceShip.gif";
	
	public Spaceship(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		
		try {
	        Image image = new Image(new FileInputStream(spaceShipPath));
	        spaceShipView = new ImageView(image);
	        
	    } catch (FileNotFoundException e) {}
		spaceShipView.setFitWidth(spaceShipWidth);
		spaceShipView.setFitHeight(spaceShipHeight);
		//these two don't seem necesary since initialized in playerMover but not entirely sure
//		spaceShipView.setX(screenWidth/2);
//		spaceShipView.setY(screenHeight-screenHeight/10);
	}
	
	  
	
	

}
