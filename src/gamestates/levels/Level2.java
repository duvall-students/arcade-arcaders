package gamestates.levels;

import gamestates.BrickBreakerGameState;

public class Level2 extends BrickBreakerGameState {
	
	private final static int oddsOfBrick = 80;
	private final static int oddsOfUnbreakableBrick = 15;
	private final static int numberOfBrickRows = 5;
	
	public Level2(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight, numberOfBrickRows, oddsOfBrick, oddsOfUnbreakableBrick);
	}
}
