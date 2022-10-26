//package breakout2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;

//Edited by Ethan Jeffries from GameState class in BrickBreakers
//Changed functionality to be a super class which will have children classes for each arcade game
public abstract class GameState {
	
	public List<GameElement> gameElements;
	private int screenWidth;
	private int screenHeight;
	private Group root;
	private LifeCounter livesLeft;
	private ScoreCard score;
	private boolean gameLost;
	
	public GameState(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		this.root = new Group();
		spawnGameItems();
		
		this.score = new ScoreCard(screenWidth, screenHeight);
		root.getChildren().add(score.getNode());
		this.livesLeft = new LifeCounter(screenWidth, screenHeight, 3);
		root.getChildren().add(this.livesLeft.getNode());
		
		this.gameLost = false;
		
		
	}
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public abstract void spawnGameItems
	
	//This method WILL SOON BE CHANGED TO SPAWN TARGET CHARACTERS REGARDLESS OF GAMESTATE
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
	
	//THESE TWO MOVEMENT FUNCTIONS WILL BE CHANGED TO BE GENERAL FOR THE MOVER CLASS
	public void moveLeft() {
		this.paddle.moveLeft();
	}
	
	public void moveRight() {
		this.paddle.moveRight();
	}
	
	//Changing to be more general for gamestates
	public void step(double elapsedTime) {
		makeGameChecks();
	}
	
	//Method that is inherited and overridden in child classes to make frame checks for objects
	public abstract void makeGameChecks
	
	public Group getRoot() {
		return this.root;
	}
	
	//Method checks for game over
	public boolean gameLost() {
		return this.gameLost;
	}
	
	//Short method to check if the player beat the level by seeing if all bricks are gone
	public boolean checkBeatLevel() {
		return (bricks.size()==0);
	}
	

	//Will soon be changed to if the number of targets = 0
	public boolean isWon() {
		return this.bricks.size() == 0;
	}
	
	
	
}
