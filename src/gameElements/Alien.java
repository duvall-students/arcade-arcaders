package gameElements;
import gamestates.GameState;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Alien extends Target{

	public final static int alienHeight = 25;
	public final static int alienWidth = 25;
	private final static String alienPath = "resources/alien.gif";

		//Constructor
	public Alien(int xCoordinate, int yCoordinate, GameState gs) {
		super(xCoordinate, yCoordinate, alienPath, alienWidth, alienHeight, gs);
	
	}
	
	public String getType() {
		return null;
	}

	
	//potentially add in a movement pattern class in here, but it probably would go somewhere like Galaga gamestate 

}
