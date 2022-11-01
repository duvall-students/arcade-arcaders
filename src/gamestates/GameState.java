package gamestates;
//package breakout2;

import gameElements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;

//Edited by Ethan Jeffries from GameState class in BrickBreakers
//Changed functionality to be a super class which will have children classes for each arcade game
public abstract class GameState {
	
	public List<GameElement> gameElements;
	
	protected List<Target> gameTargets;
	protected List<Projectile> gameProjectiles;
	protected PlayerMover playerMover;
	protected List<PowerUp> gamePowerUps;
	protected Group root;

	protected int screenWidth;
	protected int screenHeight;
	protected LifeCounter livesLeft;
	protected ScoreCard score;
	protected boolean gameLost;
	
	public GameState(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.root = new Group();
		this.gameTargets = new ArrayList<Target>();
		this.gameProjectiles = new ArrayList<Projectile>();
		this.gamePowerUps = new ArrayList<PowerUp>();
		
		spawnPlayerMover();
		createScoreCard();
		createLifeCounter();
		
		this.gameLost = false;

	}
	
	//Changing to be more general for gamestates
	public void step(double elapsedTime) {
		makeGameStep(elapsedTime);
	}
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public void makeGameStep(double elapsedTime) {
		handleProjectiles(elapsedTime);
	}
	
	//Method that spawns game targets created by the gamestate (will be called from the child constructors)
	public void spawnGameTargets(int rowsOfTargets, int targetOffset, int targetWidth, int targetHeight) {
		for (int i = 0; i < rowsOfTargets; i++) {
			for (int x = 0; x < (screenWidth / (targetWidth + targetOffset)); x++) {
				Target createdTarget = createTarget((targetWidth*x)+(targetOffset*x)+targetOffset,(targetHeight*i)+(targetOffset*i));
				gameTargets.add(createdTarget);
				root.getChildren().add(createdTarget.getNode());
			}
		}
	}
	
	//Method that creates the target objects for spawn game target function (will be overriden in children classes)
	public abstract Target createTarget(int targetXCoordinate, int targetYCoordinate);
	
	
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public void spawnGameProjectiles() {
		Projectile createdProjectile = createProjectile();
		gameProjectiles.add(createdProjectile);
		root.getChildren().add(createdProjectile.getNode());
	}
	
	public abstract Projectile createProjectile();
	
	
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public void spawnPlayerMover() {
		playerMover = createPlayerMover();
		root.getChildren().add(playerMover.getNode());
	}
	
	public abstract PlayerMover createPlayerMover();
	
	
	//Game functioning methods
	public void handleProjectiles(double elapsedTime) {
		for (int i = 0; i < gameProjectiles.size(); i++) {
			gameProjectiles.get(i).move(elapsedTime);
			handleProjectileTargetIntersection(gameProjectiles.get(i));
			handleProjectilePowerUpIntersection(gameProjectiles.get(i));
			makeProjectileChecks(gameProjectiles.get(i));
		}
	}

	public abstract void makeProjectileChecks(Projectile currentProjectile);
	
	public void handleProjectileTargetIntersection(Projectile currentProjectile) {
		for (int i = 0; i < gameTargets.size(); i++) {
			Target currentTarget = gameTargets.get(i);
			if(currentProjectile.getBounds().intersects(currentTarget.getBounds())) {
				
				//NEW SCORE SYSTEM NEEDED FOR TARGETS
//				score.incrementScore(currentTarget.getScore());
				
				projectileIntersectTarget(currentProjectile, currentTarget);
			}
		}
	}
	
	public void handleProjectilePowerUpIntersection(Projectile currentProjectile) {
		for(int i=0; i<gamePowerUps.size(); i++) {
			PowerUp currentPowerUp = gamePowerUps.get(i);
			if(currentProjectile.getBounds().intersects(currentPowerUp.getBounds())) {
				root.getChildren().remove(currentPowerUp.getNode());
				currentPowerUp.activatePowerUp();
				gamePowerUps.remove(currentPowerUp);
			}
		}
	}
	
	public abstract void projectileIntersectTarget(Projectile currentProjetile, Target currentTarget);
	//destroy for galaga
	//bounce for brickbreaker (DONE)
	
	private void createScoreCard() {
		this.score = new ScoreCard(screenWidth, screenHeight);
		root.getChildren().add(score.getNode());
	}
	
	private void createLifeCounter() {
		this.livesLeft = new LifeCounter(screenWidth, screenHeight, 3);
		root.getChildren().add(this.livesLeft.getNode());
	}
	
	//THESE TWO MOVEMENT FUNCTIONS WILL BE CHANGED TO BE GENERAL FOR THE MOVER CLASS
	public void moveLeft() {
		this.playerMover.moveLeft();
	}
	
	public void moveRight() {
		this.playerMover.moveRight();
	}
	
	public Group getRoot() {
		return this.root;
	}
	
	//Method checks for game over
	public boolean gameLost() {
		return this.gameLost;
	}

	//Checks to see if number of targets = 0 to see if player won this level
	public boolean isWon() {
		return this.gameTargets.size() == 0;
	}	
	
}
