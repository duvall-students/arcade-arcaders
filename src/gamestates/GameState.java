package gamestates;
//package breakout2;

import gameElements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;

//Created by Ethan Jeffries
//This is an abstract super class that provides a base structure for the children classes which will perform the specific game functions
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
	
	//Constructor
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
	
	//Called every frame and calls makeGameStep function which is overridden by children classes
	public void step(double elapsedTime) {
		makeGameStep(elapsedTime);
	}
	
	//Increments score by 100 every time a target is destroyed
	public void incrementScore() {
		this.score.incrementScore();
	}
	
	//Removes target from game scene
	public void removeTarget(Target t) {
		this.score.incrementScore();
		this.root.getChildren().remove(t.getNode());
		gameTargets.remove(t);
	}
	
	//Removes projectile from game scene
	public void removeProjectile(Projectile p) {
		this.root.getChildren().remove(p.getNode());
		gameProjectiles.remove(p);
	}
	
	//Empty method that is inherited and overridden by children classes for creating game targets
	public abstract void spawnGameTargets();
	
	//Empty method that is inherited and overridden by children classes for creating game projectiles
	public void spawnGameProjectiles() {
		
	}
	
	//Empty method that is inherited and overridden by children classes for creating player mover
	public abstract void spawnPlayerMover();
	
	//similar to step method and is called by step but is overridden by children classes
	public void makeGameStep(double elapsedTime) {
		currentStep += 1;
	}
	
	//PlayerMover movement function that is called on proper input
	public void moveLeft() {
		this.playerMover.moveLeft();
	}
	
	//PlayerMover movement function that is called on proper input
	public void moveRight() {
		this.playerMover.moveRight();
	}
	
	//Gets current gamestate root group (used for adding and removing game elements)
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
	
	//Method that handles intersects between all projectiles and targets and makes method call to target method if there is intersect
	public void handleAllIntersects() {
		for(int x = 0; x < gameProjectiles.size(); x++) {
			Projectile currentProjectile = gameProjectiles.get(x);
			for(int i = 0; i < gameTargets.size(); i ++) {
				Target currentTarget = gameTargets.get(i);
				currentTarget.handleIntersects(currentProjectile);
			}
		}
	}
	
}
