package gamestates.levels;
//package breakout2;

import gamestates.BrickBreakerGameState;

public class Level3 extends BrickBreakerGameState {
	
	private final static int oddsOfBrick = 85;
	private final static int oddsOfUnbreakableBrick = 20;
	private final static int numberOfBrickRows = 7;
	
	public Level3(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight, numberOfBrickRows, oddsOfBrick, oddsOfUnbreakableBrick);
	}
}
