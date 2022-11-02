package gameElements;
import gamestates.GameState;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Alien extends Target{

	public final static int alienHeight = 25;
	public final static int alienWidth = 25;
	private final static String alienPath = "resources/alien.gif";
	private final double downwardVelocity = 0.05;
	private final double sidewardsVelocity = 0.05;

		//Constructor
	public Alien(int xCoordinate, int yCoordinate, GameState gs) {
		super(xCoordinate, yCoordinate, alienPath, alienWidth, alienHeight, gs);
	
	}
	
	@Override
	public void handleIntersects(Projectile p) {
		if(p.getBounds().intersects(this.getBounds())) {
			this.parentGameState.removeTarget(this);
			this.parentGameState.removeProjectile(p);
		}
	}
	
	public void invadeRight() {
		this.targetView.setY(this.targetView.getY() + downwardVelocity);
		this.targetView.setX(this.targetView.getX() + sidewardsVelocity);
	}
	
	public void invadeLeft() {
		this.targetView.setY(this.targetView.getY() + downwardVelocity);
		this.targetView.setX(this.targetView.getX() - sidewardsVelocity);
	}

	
	//potentially add in a movement pattern class in here, but it probably would go somewhere like Galaga gamestate 

}
