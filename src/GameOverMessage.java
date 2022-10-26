//package breakout2;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class GameOverMessage extends GameElement{

	private Text textToDisplay;
	
	public GameOverMessage(int screenWidth, int screenHeight, int currentScore, int highScore) {
		textToDisplay = new Text(screenWidth/2, screenHeight/2,
				String.format("You Lost!\nYour Score: %d\nHighScore: %d", currentScore, highScore));
		new ScoreManager().setHighScore(highScore);
	}

	@Override
	public Node getNode() {
		return textToDisplay;
	}

	@Override
	public Bounds getBounds() {
		return this.textToDisplay.getBoundsInParent();
	}
	
	

}
