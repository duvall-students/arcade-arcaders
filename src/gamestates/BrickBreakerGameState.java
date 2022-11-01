package gamestates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

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
	private final int TARGETOFFSET = 0;
	//POSSIBLY CHANGE HOW THESE ARE FOUND
	private final int TARGETWIDTH = 50;
	private final int TARGETHEIGHT = 15;
	
	//Constructor that calls super constructor then adds in brickbreaker elements
	public BrickBreakerGameState(int screenWidth, int screenHeight, int numberOfBrickRows, int brickOdds, int oddsOfUnbreakableBrick) {
		super(screenWidth, screenHeight);	
		this.numberOfBrickRows = numberOfBrickRows;
		this.brickOdds = brickOdds;
		this.oddsOfUnbreakableBrick = oddsOfUnbreakableBrick;
		super.spawnGameTargets(numberOfBrickRows, TARGETOFFSET, TARGETWIDTH, TARGETHEIGHT);
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
	public Target createTarget(int targetXCoordinate, int targetYCoordinate) {
		//creating the bricks 
		TreeMap<Integer, Target> possibleBricks = new TreeMap<Integer, Target>();
		possibleBricks.put(brickOdds, new Brick(targetXCoordinate, targetYCoordinate, this));
		possibleBricks.put(maxOddsOfBrick, new UnbreakableBrick(targetXCoordinate, targetYCoordinate, this));
		Random randomVal = new Random();
		return possibleBricks.higherEntry(randomVal.nextInt(maxOddsOfBrick)).getValue();		
	}
	
	@Override
	public Projectile createProjectile() {
		if (gameProjectiles.size() == 0) {
			return new Ball(screenWidth, screenHeight);
		}
		else {
			return new Ball(0,0);
		}
	}
	
	@Override
	public PlayerMover createPlayerMover() {
		return new Paddle(screenWidth, screenHeight);
	}
	
	@Override
	public void makeProjectileChecks(Projectile currentProjectile) {
		currentProjectile.bounceOffWall(screenWidth, screenHeight);
		currentProjectile.bouncePaddle(playerMover);
		checkBallIsOut(currentProjectile);
	}

	//Below are brickbreaker methods that have been moved from GameState
	
	//Possibly restructure to allow for a uniform look in handleBallMovement
	@Override
	public void projectileIntersectTarget(Projectile currentProjectile, Target currentTarget) {
		currentProjectile.bounce(currentTarget);
		if(currentTarget.getType().equals("brick")) {		
			root.getChildren().remove(currentTarget.getNode());
			gameTargets.remove(gameTargets.indexOf(currentTarget));
		}
	}
	
	public void checkBallIsOut(Projectile currentProjectile) {
		double ballY = currentProjectile.getBounds().getCenterY();
		if (ballY>screenHeight){
			gameProjectiles.remove(gameProjectiles.indexOf(currentProjectile));
			root.getChildren().remove(currentProjectile.getNode());


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
