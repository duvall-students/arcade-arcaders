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

	//The rest of the class is fairly empty and will be soon implemented once we have arcade structure properly set up
	//Method that spawns in game items which will override the parent method
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
	
	public void checkAlienDirection() {
		Target lowestTarget = Collections.min(gameTargets);
		Target highestTarget = Collections.max(gameTargets);
		if(moveToLeft) {
			moveToLeft = 5 < lowestTarget.getBounds().getMinX();
		} else {
			moveToLeft = screenWidth - 5 < highestTarget.getBounds().getMaxX();
		}
	}
	
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
	
	@Override
	public void spawnGameProjectiles() {
		Bullet createdBullet = new Bullet(playerMover.getX(),playerMover.getY());
		gameProjectiles.add(createdBullet);
		root.getChildren().add(createdBullet.getNode());
	}
	
	@Override
	public void spawnPlayerMover() {
		playerMover = new Spaceship(screenWidth, screenHeight);
		root.getChildren().add(playerMover.getNode());
	}
	
	public void handleProjectileMovement(double elapsedTime) {
		for (int i = 0; i < gameProjectiles.size(); i++) {
			gameProjectiles.get(i).move(elapsedTime);
		}
	}
	
	//Make step method that is called every frame
	public void makeGameStep(double elapsedTime) {
		super.makeGameStep(elapsedTime);
		invadeWithAllALiens();
		handleProjectileMovement(elapsedTime);
	}

}