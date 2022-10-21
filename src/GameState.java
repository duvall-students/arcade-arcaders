package breakout2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class GameState {
	
	
	private final int brickWidth = 50;
	private final int brickHeight = 15;
	private final int maxBrickOdds = 100;
	private final int maxUnbreakableBrickOdds = 100;
	private final int bricksPerRow = 8;
	public Paddle paddle;
	public List<Brick> bricks;
	public List<Ball> balls;
	public List<GameElement> gameElements;
	private int screenWidth;
	private int screenHeight;
	private Group root;
	private List<PowerUp> powerUps;
	private LifeCounter livesLeft;
	private ScoreCard score;
	private boolean gameLost;
	private final int oddsToGeneratePowerUp = 1001;
	private int currentStep = 0;
	
	
	public GameState(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		this.root = new Group();
		
		balls = new ArrayList<Ball>();
		bricks = new ArrayList<Brick>();
		powerUps = new ArrayList<PowerUp>();

		this.paddle = new Paddle(screenWidth, screenHeight);
		root.getChildren().add(this.paddle.getNode());
		
		//Changed the ball input here so it would be in the middle of the screen
		this.addBall();
		
		
		this.score = new ScoreCard(screenWidth, screenHeight);
		root.getChildren().add(score.getNode());
		this.livesLeft = new LifeCounter(screenWidth, screenHeight, 3);
		root.getChildren().add(this.livesLeft.getNode());
		
		this.gameLost = false;
		
		
	}
	
	//created class to construct bricks 
	//takes in values for number of rows and bricks per row as well as the odds of a brick which will be unique to each level
	public void makeBricks(int numberOfBrickRows, int oddsOfBrick, int oddsOfUnbreakableBrick) {
		//creating the bricks 
		Random randomVal = new Random();
		for(int x = 1; x<=numberOfBrickRows;x++) {
			for(int i = 0; i < bricksPerRow; i ++) {
				if (randomVal.nextInt(maxBrickOdds) < oddsOfBrick) {
					Brick brickCreated = new Brick(i*brickWidth, brickHeight*x, "resources/brick6.gif" );
					bricks.add(brickCreated);
					root.getChildren().add(brickCreated.getNode());
				}
				else if (randomVal.nextInt(maxUnbreakableBrickOdds) < oddsOfUnbreakableBrick ) {
					System.out.println(randomVal.nextInt(maxUnbreakableBrickOdds));
					UnbreakableBrick brickCreated = new UnbreakableBrick(i*brickWidth, brickHeight*x, "resources/brick3.gif" );
					bricks.add(brickCreated);
					root.getChildren().add(brickCreated.getNode());
				}
				//add another if statement to add in unbreakable bricks
			}
		}
	}
	
	public void moveLeft() {
		this.paddle.moveLeft();
	}
	
	public void moveRight() {
		this.paddle.moveRight();
	}
	
	public void step(double elapsedTime) {
		if (++ currentStep % oddsToGeneratePowerUp == 1) this.generatePowerUp();
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).move(elapsedTime);
			balls.get(i).bounceOffWall(screenWidth, screenHeight);
		}
		this.bounceOffBricks();
		this.bounceOffPaddle();
		this.checkForPowerUps();
		this.checkBallIsOut();
		this.checkForBalls();
	}
	
	public Group getRoot() {
		return this.root;
	}
	
	public void bounceOffBricks() {
		for(Ball currentBall: balls) {
			for(int i = 0; i < bricks.size(); i ++) {
				Brick currentBrick = bricks.get(i);
				boolean intersects = currentBall.getBounds().intersects(currentBrick.getBounds());
				if(intersects) {
					//Change logic so it checks the position of the ball relative to the brick and bounces if the middle is outside of the brick
//					if (currentBall.getBounds().getCenterX()>currentBrick.getBounds().getMinX() && currentBall.getBounds().getCenterX()<currentBrick.getBounds().getMaxX()) {
//						currentBall.bounce();
//					}
//					else {
//						currentBall.sideBounce();
//					}
					currentBall.bounce(currentBrick);
					if(currentBrick.getBrickType().equals("brick")) {
						System.out.println("hit and removed a brick");
						score.incrementScore(100);
						this.root.getChildren().remove(currentBrick.getNode());
						this.bricks.remove(currentBrick);
					}
					else {
						System.out.println("unbreakable");
					}
					
				}
			}
		}
	}
	

	public void bounceOffPaddle() {
		for (Ball currentBall : balls) {
			currentBall.bouncePaddle(this.paddle);
		}
	}

	public void checkForPowerUps() {
		for(int j = 0; j < balls.size(); ++j) {
			Ball currentBall = balls.get(j);
			for(int i = 0; i < powerUps.size(); i ++) {
				PowerUp currentPowerUp = powerUps.get(i);
				boolean intersects = currentBall.getBounds().intersects(currentPowerUp.getBounds());
				if(intersects) {
					System.out.println("HIT POWERUP");
					this.root.getChildren().remove(currentPowerUp.getNode());
					currentPowerUp.activatePowerUp();
					this.powerUps.remove(currentPowerUp);
				}
			}
		}
	}
	
	public void checkBallIsOut() {
		ArrayList<Ball> badBalls = new ArrayList<Ball>();
		for (int i = 0; i < balls.size(); i ++) {
			Ball currentBall = balls.get(i);
			double ballY = currentBall.getBounds().getCenterY();
			if (ballY>screenHeight){
				badBalls.add(currentBall);
				balls.remove(i);
				this.root.getChildren().remove(currentBall.getNode());
			}
		}
	}
	
	public void checkForBalls() {
		if (balls.size() == 0 && this.livesLeft.getLivesLeft() > 0) {
			this.livesLeft.changeLives(-1);
			if (this.livesLeft.getLivesLeft() > 0) {
				System.out.println(String.format("You have %d lives left, good luck.", this.livesLeft.getLivesLeft()));
				this.addBall();
			}
		} else if (balls.size() == 0) {
			gameLost = true;
			this.root.getChildren().add(new GameOverMessage(screenWidth, screenHeight, this.score.getCurrentScore(),
					this.score.getHightScore()).getNode());
		}
	}

	public void increasePaddleSize() {
		this.paddle.increaseX();
	}
	
	public boolean gameLost() {
		return this.gameLost;
	}
	
	//Short method to check if the player beat the level by seeing if all bricks are gone
	public boolean checkBeatLevel() {
		return (bricks.size()==0);
	}
	
	public void addBall() {
		Ball newBall = new Ball(screenWidth, screenHeight);
		balls.add(newBall);
		root.getChildren().add(newBall.getNode());
	}
	
	public boolean isWon() {
		return this.bricks.size() == 0;
	}
	
	
	public void generatePowerUp() {
		int rand = this.paddle.getRandomInRange(0, 100);
		if (rand < 15) {
			AddBallPowerUp powerUpToAdd = new AddBallPowerUp(screenWidth, screenHeight, this);
			root.getChildren().add(powerUpToAdd.getNode());
			powerUps.add(powerUpToAdd);
		} else if (rand < 30) {
			IncreasePaddleLength powerUpToAdd = new IncreasePaddleLength(screenWidth, screenHeight, this);
			root.getChildren().add(powerUpToAdd.getNode());
			powerUps.add(powerUpToAdd);
		}
	}
	
}
