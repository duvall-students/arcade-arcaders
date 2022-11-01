package gamestates.levels;

import gamestates.GalagaGameState;

public class GalagaLevel2 extends GalagaGameState{
	
	private final static int numOfAlienRows = 4;

	public GalagaLevel2(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight, numOfAlienRows);
	}

}
