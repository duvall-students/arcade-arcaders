package gamestates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import gameElements.*;
//Created by Ethan Jeffries 
//This class is a child class of GameState and will handle anything that is specific to the Galaga game

public class GalagaGameState extends GameState{
	
	protected final int numOfAlienRows;
	private final int AlienOffset = 10;
	private boolean moveToLeft = true;
	//private List<Alien> aliens;

	public GalagaGameState(int screenWidth, int screenHeight, int numOfAlienRows) {
		super(screenWidth, screenHeight);
		this.numOfAlienRows = numOfAlienRows;
		this.spawnGameTargets();
		this.spawnPlayerMover();
	}

	//Method that spawns in game targets which will override the parent method
	@Override
	public void spawnGameTargets() {
		for (int j = 0; j < numOfAlienRows; j ++) {
			for (int i = 0; i < screenWidth / (Alien.alienWidth + AlienOffset); i ++) {
				Alien alienCreated = new Alien(AlienOffset * i + Alien.alienWidth * i + AlienOffset, AlienOffset + j * Alien.alienHeight, this);
				gameTargets.add(alienCreated);
				root.getChildren().add(alienCreated.getNode());
			}
		}
	}
	
	//Checks the direction that the aliens will move in this frame
	public void checkAlienDirection() {
		Target lowestTarget = Collections.min(gameTargets);
		Target highestTarget = Collections.max(gameTargets);
		if(moveToLeft) {
			moveToLeft = 5 < lowestTarget.getBounds().getMinX();
		} else {
			moveToLeft = screenWidth - 5 < highestTarget.getBounds().getMaxX();
		}
	}
	
	//Moves the aliens downwards by calling alien invade functions
	public void invadeWithAllALiens() {
		checkAlienDirection();
		for (Target a: gameTargets) {
			if(moveToLeft) {
				((Alien) a).invadeLeft();
			} else {
				((Alien) a).invadeRight();
			}
		}
	}
	
	//Overridden method that spawns projectiles when desired
	@Override
	public void spawnGameProjectiles() {
		Bullet createdBullet = new Bullet((int)playerMover.getBounds().getCenterX(),(int)playerMover.getBounds().getMinY());
		gameProjectiles.add(createdBullet);
		root.getChildren().add(createdBullet.getNode());
	}
	
	//Overridden method that creates the playermover at in the constructor
	@Override
	public void spawnPlayerMover() {
		playerMover = new Spaceship(screenWidth, screenHeight);
		root.getChildren().add(playerMover.getNode());
	}
	
	//Handles all projectile movement for a specific frame
	public void handleProjectileMovement(double elapsedTime) {
		for (int i = 0; i < gameProjectiles.size(); i++) {
			gameProjectiles.get(i).move(elapsedTime);
		}
	}
	
	//Checks if the aliens have gone below the playermover and if so spawns them again at the top
	public void checkForTooLowAliens() {
		double lowest = 0;
		for (Target t: gameTargets) {
			lowest = Math.max(lowest, t.getBounds().getMaxY());
		}
		if(lowest > this.playerMover.getBounds().getMinY()) {
			this.gameTargets = new ArrayList<Target>();
			this.spawnGameTargets();
		}
	}
	
	//Make step method that is called every frame
	public void makeGameStep(double elapsedTime) {
		super.makeGameStep(elapsedTime);
		invadeWithAllALiens();
		handleProjectileMovement(elapsedTime);
		handleAllIntersects();
		checkForTooLowAliens();
	}

}