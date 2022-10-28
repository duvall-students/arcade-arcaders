package gamestates.levels;

import gamestates.BrickBreakerGameState;

public class Level1 extends BrickBreakerGameState {

	private final int oddsOfBrick = 70;
	private final int oddsOfUnbreakableBrick = 10;
	private final int numberOfBrickRows = 3;
	

	public Level1(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		spawnGameTargets(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);
	}
}
