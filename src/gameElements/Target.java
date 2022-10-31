package gameElements;
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

public abstract class Target extends GameElement{

	private ImageView targetView;
	protected GameState parentGameState;
	
	//Constructor
	public Target(int xCoordinate, int yCoordinate, String imagePath, int targetWidth, int targetHeight, GameState gs) {
		this.parentGameState = gs;
	    try {
	        Image image = new Image(new FileInputStream(imagePath));
	        targetView = new ImageView(image);
	        
	    } catch (FileNotFoundException e) {}
        targetView.setFitWidth(targetWidth);
        targetView.setFitHeight(targetHeight);
        targetView.setX(xCoordinate);
        targetView.setY(yCoordinate);
	}
	
	@Override
	public Node getNode() {
		return targetView;
	}

	@Override
	public Bounds getBounds() {
		return targetView.getBoundsInParent();
	}
	
	public abstract String getType();
 
}
 