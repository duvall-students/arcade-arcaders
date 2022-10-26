//package breakout2;

public class Level3 extends GameState {
	
	private int oddsOfBrick = 85;
	private int oddsOfUnbreakableBrick = 20;
	private int numberOfBrickRows = 7;
	

	public Level3(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		makeBricks(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);
	}
}
