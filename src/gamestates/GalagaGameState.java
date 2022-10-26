package gamestates;

//Created by Ethan Jeffries
//This class is a child class of GameState and will handle anything that is specific to the Galaga game

public class GalagaGameState extends GameState{

	public GalagaGameState(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
	}

	//The rest of the class is fairly empty and will be soon implemented once we have arcade structure properly set up
	//Method that spawns in game items which will override the parent method
	@Override
	public void spawnGameItems() {

	}

	//Inherited from GameState and makes basic frame checks for the brickbreaker game
	@Override
	public void makeGameChecks() {

	}
}