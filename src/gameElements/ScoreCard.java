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
	private final static int xOffset = -150;
	private final static int yOffset = -20;
	private final int scoreIncrement = 100;
	
	public ScoreCard(int screenWidth, int screenHeight, String pathToHighScore) {
		this.currentScore = 0;
		this.scoreManager = new ScoreManager(pathToHighScore);
		this.highScore = scoreManager.getHighScore();
		textToDisplay = new Text(screenWidth + xOffset, screenHeight + yOffset,
				String.format("Score: %d\nHighScore: %d", currentScore, highScore));
	}
	
	public void incrementScore() {
		this.currentScore += scoreIncrement;
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
		highScore = Math.max(this.scoreManager.getHighScore(), highScore);
	}
	
//	public void setHighScore(int currentScore) {
//		this.scoreManager.setHighScore(currentScore);
//	}
	
	public void setCurrentScore(int score) {
		this.currentScore = score;
		this.highScore = Math.max(this.currentScore,  this.highScore);
		this.textToDisplay.setText(String.format("Score: %d\nHighScore: %d", currentScore, highScore));
	}
	
	public int getHighScore() {
		return scoreManager.getHighScore();
	}
	
	@Override
	public Bounds getBounds() {
		return this.textToDisplay.getBoundsInParent();
	}

}
