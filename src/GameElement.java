//package breakout2;

import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public abstract class GameElement {
	
	public abstract Node getNode();

	public int getRandomInRange(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	 
	public abstract Bounds getBounds();
 
}
