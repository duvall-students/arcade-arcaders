//package breakout2;

public class Level1 extends GameState {

	private int oddsOfBrick = 70;
	private int oddsOfUnbreakableBrick = 10;
	private int numberOfBrickRows = 3;
	

	public Level1(int screenWidth, int screenHeight) {
		super(screenWidth,screenHeight);
		makeBricks(numberOfBrickRows,oddsOfBrick,oddsOfUnbreakableBrick);
	}
}
