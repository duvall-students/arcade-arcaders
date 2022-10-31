package gamestates.levels;

import gamestates.BrickBreakerGameState;

public class Level1 extends BrickBreakerGameState {

	private final static int oddsOfBrick = 70;
	private final static int oddsOfUnbreakableBrick = 10;
	private final static int numberOfBrickRows = 3;
	
	public Level1(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight, numberOfBrickRows, oddsOfBrick, oddsOfUnbreakableBrick);
	}
}
