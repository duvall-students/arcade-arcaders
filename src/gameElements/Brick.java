package gameElements;
//package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Target{

	//Setting variable values for now these are just place holders
	//will change when we find better values
	//Might want to include location variables? 
	//but those are also handled by the constructor that will create the level
	private final int brickHeight = 15;
	private int brickWidth = 50;
	private Paint brickColor = Color.RED;
	private Rectangle brickRectangle;
	private String brickPath = "resources/brick6.gif";
	private ImageView brickView;
		
		//Constructor
	public Brick(int brickXCoordinate, int brickYCoordinate, String brickImagePath) {
		brickRectangle = new Rectangle(brickXCoordinate, brickYCoordinate, brickWidth, brickHeight);
		brickRectangle.setFill(brickColor);
	  	
        try {
	        Image image = new Image(new FileInputStream(brickImagePath));
	        brickView = new ImageView(image);
	        
        } catch (FileNotFoundException e) {}
        
        brickView.setFitWidth(brickWidth);
        brickView.setFitHeight(brickHeight);
        brickView.setX(brickXCoordinate);
        brickView.setY(brickYCoordinate);
	}
	
	public void setBrickPath(String fileName) {
		brickPath = fileName;
	}
	
	public String getBrickType() {
		return "brick";
	}
		
	public Node getNode() {
		return brickView;
	}

	public Bounds getBounds() {
		return brickView.getBoundsInParent();
	}
	
	
		
}
