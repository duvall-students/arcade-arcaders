import java.util.ArrayList;
import java.util.List;

//Created by Ethan Jeffries
//Breaking apart original GameState class into children class of BreakoutGameState

public class BrickBreakerGameState extends GameState{

	private final int brickWidth = 50;
	private final int brickHeight = 15;
	private final int maxBrickOdds = 100;
	private final int maxUnbreakableBrickOdds = 100;
	private final int bricksPerRow = 8;
	public Paddle paddle;
	public List<Brick> bricks;
	public List<Ball> balls;
	private List<PowerUp> powerUps;
	private final int oddsToGeneratePowerUp = 1001;
	private int currentStep = 0;
	
	//Constructor that calls super constructor then adds in brickbreaker elements
	public BrickBreakerGameState(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);	
	}
	
	//Method that spawns in game items which will override the parent method
	@Override
	public void spawnGameItems() {
		//This method may be changed in order to be more generalized for spawning items
		//Bricks may now call a spawn target method while balls stays the same
		balls = new ArrayList<Ball>();
		bricks = new ArrayList<Brick>();
		powerUps = new ArrayList<PowerUp>();

		this.paddle = new Paddle(screenWidth, screenHeight);
		root.getChildren().add(this.paddle.getNode());
		
		//Changed the ball input here so it would be in the middle of the screen
		this.addBall();
	}
	
	//Inherited from GameState and makes basic frame checks for the brickbreaker game
	@Override
	public void makeGameChecks() {
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
	
	//Below are brickbreaker methods that have been moved from GameState
	public void bounceOffBricks() {
		for(Ball currentBall: balls) {
			for(int i = 0; i < bricks.size(); i ++) {
				Brick currentBrick = bricks.get(i);
				boolean intersects = currentBall.getBounds().intersects(currentBrick.getBounds());
				if(intersects) {
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
	
	public void addBall() {
		Ball newBall = new Ball(screenWidth, screenHeight);
		balls.add(newBall);
		root.getChildren().add(newBall.getNode());
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
