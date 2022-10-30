package gamestates.levels;
//package breakout2;

import gamestates.BrickBreakerGameState;

public class Level2 extends BrickBreakerGameState {
	
	private final int oddsOfBrick = 80;
	private final int oddsOfUnbreakableBrick = 15;
	private final int numberOfBrickRows = 5;
	

	public Level2(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		spawnGameTargets(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);
	}
}
