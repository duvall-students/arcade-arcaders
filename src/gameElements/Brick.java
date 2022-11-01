package gameElements;
//package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gamestates.GameState;
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
	public final static int brickHeight = 15;
	public final static int brickWidth = 50;
	private final static String brickPath = "resources/brick6.gif";
	private ImageView brickView;
		 
		//Constructor
	public Brick(int brickXCoordinate, int brickYCoordinate, GameState gs) {
		super(brickXCoordinate, brickYCoordinate, brickPath, brickWidth, brickHeight, gs);

	}
	
	public String getType() {
		return "brick";
	}
	
}
