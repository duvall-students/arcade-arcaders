package gameElements;
//package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gamestates.GameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UnbreakableBrick extends Target{
	
	//will change when we find better values
	//Might want to include location variables? 
	//but those are also handled by the constructor that will create the level
	private final static int brickHeight = 15;
	private final static int brickWidth = 50;
	private final static String brickPath = "resources/brick3.gif";
	private ImageView brickView;
		 
		//Constructor
	public UnbreakableBrick(int brickXCoordinate, int brickYCoordinate, GameState gs) {
		super(brickXCoordinate, brickYCoordinate, brickPath, brickWidth, brickHeight, gs);

	}
	
	@Override
	public void handleIntersects(Projectile p) {
		if(p.getBounds().intersects(this.getBounds())) {
			p.bounce(this);
		}
	}
	
}