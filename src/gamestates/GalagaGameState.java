package gamestates;


import gameElements.*;
//Created by Ethan Jeffries
//This class is a child class of GameState and will handle anything that is specific to the Galaga game

public abstract class GalagaGameState extends GameState{
	
	protected final int numOfAlienRows;
	private final int TARGETOFFSET = 10;
	//POSSIBLY CHANGE HOW THESE ARE FOUND
	private final int TARGETHEIGHT = 25;
	private final int TARGETWIDTH = 25;

	
	public GalagaGameState(int screenWidth, int screenHeight, int numOfAlienRows) {
		super(screenWidth, screenHeight);
		this.numOfAlienRows = numOfAlienRows;
		super.spawnGameTargets(numOfAlienRows, TARGETOFFSET, TARGETWIDTH, TARGETHEIGHT);
	}
	
	//Make step method that is called every frame
	@Override
	public void makeGameStep(double elapsedTime) {
		super.makeGameStep(elapsedTime);
		//Aliens move down
		
	}
	//check for out of bounds
	@Override
	public void makeProjectileChecks(Projectile currentProjectile) {
		checkBulletOutOfBounds(currentProjectile);
	}

	//The rest of the class is fairly empty and will be soon implemented once we have arcade structure properly set up
	//Method that spawns in game items which will override the parent method
	@Override
	public Target createTarget(int targetXCoordinate, int targetYCoordinate) {
		return new Alien(targetXCoordinate, targetYCoordinate, this);
	}
	
	@Override
	public Projectile createProjectile() {
		return new Bullet(screenWidth, screenHeight);
	}
	
	@Override
	public PlayerMover createPlayerMover() {
		return new Spaceship(screenWidth, screenHeight);
	}
	
	private void checkBulletOutOfBounds(Projectile currentProjectile) {
		if (currentProjectile.getBounds().getMaxY() < 0) {
			root.getChildren().remove(currentProjectile.getNode());
			gameProjectiles.remove(gameProjectiles.indexOf(currentProjectile));
		}
	}
	


}