package gamestates;


import gameElements.*;
//Created by Ethan Jeffries
//This class is a child class of GameState and will handle anything that is specific to the Galaga game

public class GalagaGameState extends GameState{
	
	protected final int numOfAlienRows;
	private final int AlienOffset = 10;

	public GalagaGameState(int screenWidth, int screenHeight, int numOfAlienRows) {
		super(screenWidth, screenHeight);
		this.numOfAlienRows = numOfAlienRows;
		this.spawnGameTargets();
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
	
	@Override
	public void spawnGameProjectiles() {
		
	}
	
	@Override
	public void spawnPlayerMover() {
		
	}
	
	//Make step method that is called every frame
	public void makeGameStep(double elapsedTime) {
		
	}

}