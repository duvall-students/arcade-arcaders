package gameElements;
//package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gamestates.BrickBreakerGameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IncreasePaddleLength extends PowerUp{
	
	private final static String powerUpImagePath = "resources/laserpower.gif";
	private Image image;

	public IncreasePaddleLength(int screenWidth, int screenHeight, BrickBreakerGameState gameState) {
        super(powerUpImagePath, screenHeight, screenHeight, gameState);
	}
	
	@Override
	public void activatePowerUp() {
		this.gameState.increasePaddleSize();
		
	}

}
