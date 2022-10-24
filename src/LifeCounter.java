//package breakout2;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class LifeCounter extends GameElement {

	private int livesLeft;
	private Text textToDisplay;
	private final static int xOffset = -350;
	private final static int yOffset = -15;
	
	public LifeCounter(int screenWidth, int screenHeight, int initialLives) {
		this.livesLeft = initialLives;
		textToDisplay = new Text(screenWidth + xOffset, screenHeight + yOffset,
				String.format("Lives Left: %d", this.livesLeft));
	}
	
	public void changeLives(int increment) {
		this.livesLeft += increment;
		this.textToDisplay.setText(String.format("Lives Left: %d", this.livesLeft));
	}

	@Override
	public Node getNode() {
		return textToDisplay;
	}
	
	public int getLivesLeft() {
		return this.livesLeft;
	}
	
	@Override
	public Bounds getBounds() {
		return this.textToDisplay.getBoundsInParent();
	}

}
