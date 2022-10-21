package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class PowerUp extends GameElement{
	
	private final int size = 20;
	private ImageView powerUpView;
	protected GameState gameState;
	
	protected PowerUp(String powerUpImagePath, int screenWidth, int screenHeight, GameState gameState) {
		
		this.gameState = gameState;
		
        try {
	        Image image = new Image(new FileInputStream(powerUpImagePath));
	        powerUpView = new ImageView(image);
        } catch (FileNotFoundException e) {}
        
        powerUpView.setFitWidth(size);
        powerUpView.setFitHeight(size);
        powerUpView.setX(getRandomInRange(size,  screenWidth - size));
        powerUpView.setY(getRandomInRange(size + screenHeight / 10, screenHeight - size - screenHeight / 10));
	}
	
	public Node getNode() {
		return powerUpView;
	}
	
	public Bounds getBounds() {
		return powerUpView.getBoundsInParent();
	}
	
	public abstract void activatePowerUp();

}
