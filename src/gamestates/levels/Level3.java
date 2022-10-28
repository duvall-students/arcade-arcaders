package gamestates.levels;
//package breakout2;

import gamestates.BrickBreakerGameState;

public class Level3 extends BrickBreakerGameState {
	
	private int oddsOfBrick = 85;
	private int oddsOfUnbreakableBrick = 20;
	private int numberOfBrickRows = 7;
	

	public Level3(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		spawnGameTargets(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);	}
}
