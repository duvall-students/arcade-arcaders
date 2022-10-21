package breakout2;

import javafx.scene.image.Image;

public class AddBallPowerUp extends PowerUp {

	private final static String powerUpImagePath = "resources/extraballpower.gif";
	private Image image;

	public AddBallPowerUp(int screenWidth, int screenHeight, GameState gameState) {
        super(powerUpImagePath, screenHeight, screenHeight, gameState);
	}
	
	@Override
	public void activatePowerUp() {
		this.gameState.addBall();
		
	}

}
