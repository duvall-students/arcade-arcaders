package gamestates.levels;

import gamestates.GalagaGameState;

public class GalagaLevel3 extends GalagaGameState{
	
	private final static int numOfAlienRows = 5;

	public GalagaLevel3(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight, numOfAlienRows);
	}

}
