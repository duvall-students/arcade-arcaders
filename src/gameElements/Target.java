package gameElements;
import java.io.FileInputStream;
import java.lang.Double;
import java.io.FileNotFoundException;

import gamestates.GameState;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Target extends GameElement implements Comparable<Target>{

	protected ImageView targetView;
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
	
	@Override
	  public int compareTo(Target t){
	     int compareX = Double.compare(this.targetView.getX(), t.targetView.getX());
	     return compareX == 0 ? Double.compare(this.targetView.getY(), t.targetView.getY()) : compareX;
	  }
	
	
	public abstract void handleIntersects(Projectile p);
 
}
 