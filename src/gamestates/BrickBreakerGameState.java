package gamestates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import gameElements.*;

//Created by Ethan Jeffries
//Breaking apart original GameState class into children class of BreakoutGameState

public abstract class BrickBreakerGameState extends GameState{

	private final int maxOddsOfBrick = 100;
	protected final int numberOfBrickRows;
	protected final int brickOdds;
	protected final int oddsOfUnbreakableBrick;
	private final int oddsToGeneratePowerUp = 1001;
	private int currentStep = 0;
	
	//Constructor that calls super constructor then adds in brickbreaker elements
	public BrickBreakerGameState(int screenWidth, int screenHeight, int numberOfBrickRows, int brickOdds, int oddsOfUnbreakableBrick) {
		super(screenWidth, screenHeight);	
		this.numberOfBrickRows = numberOfBrickRows;
		this.brickOdds = brickOdds;
		this.oddsOfUnbreakableBrick = oddsOfUnbreakableBrick;
		spawnGameTargets();
		spawnPlayerMover();
		spawnGameProjectiles();
	}
	
	//Inherited from GameState and makes basic frame checks for the brickbreaker game
	@Override
	public void makeGameStep(double elapsedTime) {
		super.makeGameStep(elapsedTime);
		generatePowerUp();
		handleBallMovement(elapsedTime);
		handleAllIntersects();
		checkForPowerUps();
		checkBallIsOut();
		checkForBalls();
	}
	
	@Override
	public void spawnGameTargets() {
		this.spawnGameTargets(this.numberOfBrickRows, this.brickOdds, this.oddsOfUnbreakableBrick);
	}
	
	public void spawnGameTargets(int numberOfBrickRows, int brickOdds, int oddsOfUnbreakableBrick) {
		//creating the bricks 
		Random randomVal = new Random();
		for(int x = 1; x<=numberOfBrickRows;x++) {
			for(int i = 0; i < this.screenWidth / Brick.brickWidth; i ++) {
				if (randomVal.nextInt(maxOddsOfBrick) < brickOdds) {
					Brick brickCreated = new Brick(i*Brick.brickWidth, Brick.brickHeight*x, this);
					gameTargets.add(brickCreated);
					root.getChildren().add(brickCreated.getNode());
				}
				else if (randomVal.nextInt(maxOddsOfBrick) < oddsOfUnbreakableBrick ) {
					UnbreakableBrick brickCreated = new UnbreakableBrick(i*Brick.brickWidth, Brick.brickHeight*x, this);
					gameTargets.add(brickCreated);
					root.getChildren().add(brickCreated.getNode());
				}
			}
		}
	}
	
	@Override
	public void spawnGameProjectiles() {
		this.addBall();
	}
	
	@Override
	public void spawnPlayerMover() {
		playerMover = new Paddle(screenWidth, screenHeight);
		root.getChildren().add(playerMover.getNode());
	}
	
	public void handleBallMovement(double elapsedTime) {
		for (int i = 0; i < gameProjectiles.size(); i++) {
			gameProjectiles.get(i).move(elapsedTime);
			gameProjectiles.get(i).bounceOffWall(screenWidth, screenHeight);
			gameProjectiles.get(i).bouncePaddle(playerMover);
		}
	}

	public void checkForPowerUps() {
		for(int j = 0; j < gameProjectiles.size(); ++j) {
			Ball currentBall = (Ball) gameProjectiles.get(j);
			for(int i = 0; i < gamePowerUps.size(); i ++) {
				PowerUp currentPowerUp = gamePowerUps.get(i);
				boolean intersects = currentBall.getBounds().intersects(currentPowerUp.getBounds());
				if(intersects) {
					System.out.println("HIT POWERUP");
					root.getChildren().remove(currentPowerUp.getNode());
					currentPowerUp.activatePowerUp();
					gamePowerUps.remove(currentPowerUp);
				}
			}
		}
	}
	
	public void checkBallIsOut() {
		for (int i = 0; i < gameProjectiles.size(); i ++) {
			Ball currentBall = (Ball) gameProjectiles.get(i);
			double ballY = currentBall.getBounds().getMinY();
			if (ballY>screenHeight){
				gameProjectiles.remove(i);
				root.getChildren().remove(currentBall.getNode());
			}
		}
	}
	
	public void checkForBalls() {
		if (gameProjectiles.size() == 0 && livesLeft.getLivesLeft() > 0) {
			livesLeft.changeLives(-1);
			if (livesLeft.getLivesLeft() > 0) {
				System.out.println(String.format("You have %d lives left, good luck.", livesLeft.getLivesLeft()));
				addBall();
			}
		} else if (gameProjectiles.size() == 0) {
			gameLost = true;
			root.getChildren().add(new GameOverMessage(screenWidth, screenHeight, score.getCurrentScore(),score.getHightScore()).getNode());
		}
	}

	//May move to paddle class
	public void increasePaddleSize() {
		Paddle p = (Paddle) this.playerMover;
		p.increaseX();
	}
	
	//Possibly move to spawnGameProjectiles method?
	public void addBall() {
		Projectile newBall = new Ball(screenWidth, screenHeight);
		gameProjectiles.add(newBall);
		root.getChildren().add(newBall.getNode());
	}
	
	public void generatePowerUp() {
		if (++ currentStep % oddsToGeneratePowerUp == 1) {
			int rand = playerMover.getRandomInRange(0, 100);
			if (rand < 15) {
				AddBallPowerUp powerUpToAdd = new AddBallPowerUp(screenWidth, screenHeight, this);
				root.getChildren().add(powerUpToAdd.getNode());
				gamePowerUps.add(powerUpToAdd);
			} else if (rand < 30) {
				IncreasePaddleLength powerUpToAdd = new IncreasePaddleLength(screenWidth, screenHeight, this);
				root.getChildren().add(powerUpToAdd.getNode());
				gamePowerUps.add(powerUpToAdd);
			}
		}
	}

}
