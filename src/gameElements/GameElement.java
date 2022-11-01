package gameElements;
//package breakout2;

import java.util.Random;

import gamestates.GameState;
import javafx.geometry.Bounds;
import javafx.scene.Node;

public abstract class GameElement {
	
	public abstract Node getNode();
//	protected GameState parentGameState;
//	
//	public GameElement(GameState gs) {
//		parentGameState = gs;
//	}

	public int getRandomInRange(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	 
	public abstract Bounds getBounds();
 
}
