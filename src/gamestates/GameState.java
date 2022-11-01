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
	protected int currentStep = 0;

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
		 

		this.score = new ScoreCard(screenWidth, screenHeight);
		root.getChildren().add(score.getNode());
		this.livesLeft = new LifeCounter(screenWidth, screenHeight, 3);
		root.getChildren().add(this.livesLeft.getNode());
		
		this.gameLost = false;
	}
	
	//Changing to be more general for gamestates
	public void step(double elapsedTime) {
		makeGameStep(elapsedTime);
	}
	
	public void incrementScore() {
		this.score.incrementScore();
	}
	
	public void removeTarget(Target t) {
		this.score.incrementScore();
		this.root.getChildren().remove(t.getNode());
		gameTargets.remove(t);
	}
	
	public void removeProjectile(Projectile p) {
		this.root.getChildren().remove(p.getNode());
		gameProjectiles.remove(p);
	}
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public abstract void spawnGameTargets();
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public abstract void spawnGameProjectiles();
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public abstract void spawnPlayerMover();
	
	//Empty method that is inherited and overridden by children classes for creating game items
	public void makeGameStep(double elapsedTime) {
		currentStep += 1;
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
	
	public void handleAllIntersects() {
		for(Projectile currentProjectile: gameProjectiles) {
			for(int i = 0; i < gameTargets.size(); i ++) {
				Target currentTarget = gameTargets.get(i);
				currentTarget.handleIntersects(currentProjectile);
			}
		}
	}
	
}
