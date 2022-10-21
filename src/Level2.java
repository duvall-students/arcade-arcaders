package breakout2;

public class Level2 extends GameState {
	
	private int oddsOfBrick = 80;
	private int oddsOfUnbreakableBrick = 15;
	private int numberOfBrickRows = 5;
	

	public Level2(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		makeBricks(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);
	}
}
