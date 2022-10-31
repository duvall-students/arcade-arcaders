package gamestates.levels;

import gamestates.GalagaGameState;

public class GalagaLevel1 extends GalagaGameState{
	
	private final static int numOfAlienRows = 3;

	public GalagaLevel1(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight, numOfAlienRows);
	}

}
