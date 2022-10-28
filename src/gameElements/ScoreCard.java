package gameElements;
//package breakout2;

import backendStuff.ScoreManager;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class ScoreCard extends GameElement {
	
	private int currentScore;
	private int highScore;
	private Text textToDisplay;
	private ScoreManager scoreManager;
	private final static int xOffset = -100;
	private final static int yOffset = -20;
	
	public ScoreCard(int screenWidth, int screenHeight) {
		this.currentScore = 0;
		this.scoreManager = new ScoreManager();
		this.highScore = scoreManager.getHighScore();
		textToDisplay = new Text(screenWidth + xOffset, screenHeight + yOffset,
				String.format("Score: %d\nHighScore: %d", currentScore, highScore));
	}
	
	public void incrementScore(int increment) {
		this.currentScore += increment;
		this.highScore = Math.max(this.currentScore,  this.highScore);
		this.textToDisplay.setText(String.format("Score: %d\nHighScore: %d", currentScore, highScore));
	}

	@Override
	public Node getNode() {
		return textToDisplay;
	}
	
	public int getCurrentScore() {
		return this.currentScore;
	}
	
	public void setHighScore() {
		this.scoreManager.setHighScore(this.highScore);
	}
	
	public int getHightScore() {
		return this.highScore;
	}
	
	@Override
	public Bounds getBounds() {
		return this.textToDisplay.getBoundsInParent();
	}

}
