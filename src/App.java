package breakout2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application{
	
    private static final int windowSizeX = 400;
    private static final int windowSizeY = 400;
    private static final int framesPerSecond = 60;
    public static final int millisecondDelay = 1000 / framesPerSecond;
    private final Paint backgroundColor = Color.CORNSILK;
    private GameState[] levels;
    
    private int currentLevel;
    private Scene gameScene;
    private GameState game;

	@Override
	public void start(Stage primaryStage) throws Exception {
        gameScene = initializeGameScene(windowSizeX, windowSizeY, this.backgroundColor);
        primaryStage.setScene(gameScene);
        primaryStage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(millisecondDelay), e -> step(millisecondDelay/1000.0));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
	
    private Scene initializeGameScene (int width, int height, Paint background) {
    	//Changed the gamestate that is created
    	levels = new GameState[] {new Level1(width, height), new Level2(width, height), new Level3(width,height)};
        this.currentLevel = 0;
    	game = levels[currentLevel];
        Scene scene = new Scene(game.getRoot(), width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }
    
    private void step (double elapsedTime) {
    	if (!game.gameLost() && !game.isWon()) {
    		game.step(elapsedTime);
    	}
    	if (game.isWon()) {
    		this.nextLevel();
    	}
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            game.moveRight();
        }
        else if (code == KeyCode.LEFT) {
            game.moveLeft();
        }
        else if (code == KeyCode.SPACE){
        	this.nextLevel();
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
    
    public void nextLevel() {
    	if (currentLevel < levels.length - 1) {
    		game = levels[++ currentLevel];
    		gameScene.setRoot(game.getRoot());
    	}
    }
		
}