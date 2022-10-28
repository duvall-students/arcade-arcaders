package gameElements;
//package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;




public class UnbreakableBrick extends Brick{
	private ImageView unbreakableView;

	public UnbreakableBrick(int brickXCoordinate, int brickYCoordinat, String brickImagePath) {
		super(brickXCoordinate, brickYCoordinat,brickImagePath);
	
	}

	@Override
	public String getBrickType() {
		return "unbreakable";
	}
	

	
	
}
