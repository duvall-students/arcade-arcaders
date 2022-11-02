package gameElements;
//package breakout2;

import backendStuff.ScoreManager;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class GameOverMessage extends GameElement{

	private Text textToDisplay;
	
	public GameOverMessage(int screenWidth, int screenHeight, int currentScore, int highScore, boolean gameWon) {
		String gameStatus = gameWon ? "Won": "Lost";
		textToDisplay = new Text(screenWidth/2, screenHeight/2,
				String.format("You %s!\nYour Score: %d\nHighScore: %d", gameStatus, currentScore, highScore));
		//new ScoreManager().setHighScore(highScore);
	}

	@Override
	public Node getNode() {
		return textToDisplay;
	}

	@Override
	public Bounds getBounds() {
		return this.textToDisplay.getBoundsInParent();
	}
	
	public Group gameOverGroup(){
		Group root = new Group();
		root.getChildren().add(this.textToDisplay);
		return root;
	}
	
	

}
