package gamestates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AddBallPowerUp;
import Ball;
import Brick;
import GameOverMessage;
import IncreasePaddleLength;
import Paddle;
import PowerUp;
import UnbreakableBrick;

//Created by Ethan Jeffries
//Breaking apart original GameState class into children class of BreakoutGameState

public class BrickBreakerGameState extends GameState{

	//These variables may be moved to level classes
	private final int brickWidth = 50;
	private final int brickHeight = 15;
	private final int maxBrickOdds = 100;
	private final int maxUnbreakableBrickOdds = 100;
	private final int bricksPerRow = 8;
	
	private final int oddsToGeneratePowerUp = 1001;
	private int currentStep = 0;
	
	//Constructor that calls super constructor then adds in brickbreaker elements
	public BrickBreakerGameState(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);	
	}
	
	//Inherited from GameState and makes basic frame checks for the brickbreaker game
	@Override
	public void makeGameStep(double elapsedTime) {
		generatePowerUp();
		handleBallMovement(elapsedTime);
		checkForPowerUps();
		checkBallIsOut();
		checkForBalls();
	}
	
	@Override
	public void spawnGameTargets() {
		//creating the bricks 
		Random randomVal = new Random();
		for(int x = 1; x<=numberOfBrickRows;x++) {
			for(int i = 0; i < bricksPerRow; i ++) {
				if (randomVal.nextInt(maxBrickOdds) < oddsOfBrick) {
					Brick brickCreated = new Brick(i*brickWidth, brickHeight*x, "resources/brick6.gif" );
					gameTargets.add(brickCreated);
					root.getChildren().add(brickCreated.getNode());
				}
				else if (randomVal.nextInt(maxUnbreakableBrickOdds) < oddsOfUnbreakableBrick ) {
					System.out.println(randomVal.nextInt(maxUnbreakableBrickOdds));
					UnbreakableBrick brickCreated = new UnbreakableBrick(i*brickWidth, brickHeight*x, "resources/brick3.gif" );
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
	public PlayerMover spawnPlayerMover() {
		Paddle userPaddle = new Paddle(screenWidth, screenHeight);
		root.getChildren().add(userPaddle.getNode());
		return userPaddle;
	}
	
	public void handleBallMovement(double elapsedTime) {
		for (int i = 0; i < gameProjectiles.size(); i++) {
			gameProjectiles.get(i).move(elapsedTime);
			gameProjectiles.get(i).bounceOffWall(screenWidth, screenHeight);
			gameProjectiles.get(i).bouncePaddle(playerMover);
		}
		bounceOffBricks();
	}
	//Below are brickbreaker methods that have been moved from GameState
	
	//Possibly restructure to allow for a uniform look in handleBallMovement
	public void bounceOffBricks() {
		for(Ball currentBall: gameProjectiles) {
			for(int i = 0; i < gameTargets.size(); i ++) {
				Brick currentBrick = gameTargets.get(i);
				boolean intersects = currentBall.getBounds().intersects(currentBrick.getBounds());
				if(intersects) {
					currentBall.bounce(currentBrick);
					if(currentBrick.getBrickType().equals("brick")) {
						System.out.println("hit and removed a brick");
						score.incrementScore(100);
						root.getChildren().remove(currentBrick.getNode());
						gameTargets.remove(currentBrick);
					}
					else {
						System.out.println("unbreakable");
					}
					
				}
			}
		}
	}

	public void checkForPowerUps() {
		for(int j = 0; j < gameProjectiles.size(); ++j) {
			Ball currentBall = gameProjectiles.get(j);
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
			Ball currentBall = gameProjectiles.get(i);
			double ballY = currentBall.getBounds().getCenterY();
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
		this.paddle.increaseX();
	}
	
	//Possibly move to spawnGameProjectiles method?
	public void addBall() {
		Ball newBall = new Ball(screenWidth, screenHeight);
		gameProjectiles.add(newBall);
		root.getChildren().add(newBall.getNode());
	}
	
	public void generatePowerUp() {
		if (++ currentStep % oddsToGeneratePowerUp == 1) {
			int rand = userMover.getRandomInRange(0, 100);
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
