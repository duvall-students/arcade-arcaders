

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameElements.LifeCounter;
import gameElements.PowerUp;
import gameElements.Projectile;
import gameElements.ScoreCard;
import gameElements.Target;
import gamestates.*;
import gamestates.levels.*;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application{
	
    private static final int windowSizeX = 400;
    private static final int windowSizeY = 400;
    private static final int framesPerSecond = 60;
    public static final int millisecondDelay = 1000 / framesPerSecond;
    private final Paint backgroundColor = Color.CORNSILK;
    private Map<String, GameState[]> mapGameSelectedToLevels;
    
    private int currentLevel = -1;
    private Scene gameScene;
    private GameState game;
    private String gameSelection = "No Game Selected";

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
    	this.initializeLevelMap(width, height);
        Scene scene = new Scene(this.startScreen(width, height), width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }
     
    private void step (double elapsedTime) {
    	if (gameSelection != "No Game Selected" && !game.gameLost() && !game.isWon()) {
    		game.step(elapsedTime);
    	}
    	if (gameSelection != "No Game Selected" && game.isWon()) {
    		System.out.println("LEVEL WON");
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
        else if (code == KeyCode.SPACE) {
        	game.spawnGameProjectiles();
        }
        else if (code == KeyCode.DOWN){
        	this.nextLevel();
        }
        else if (code == KeyCode.B){
        	gameSelection = "BrickBreaker";
        	this.nextLevel();
        }
        else if (code == KeyCode.G){
        	gameSelection = "Galaga";
        	this.nextLevel();
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
    
    public void initializeLevelMap(int width, int height) {
    	GameState[] GalagaLevels = new GameState[] {new GalagaLevel1(width, height), new GalagaLevel2(width, height), new GalagaLevel3(width, height)};
    	GameState[] BrickBreakerLevels = new GameState[] {new Level1(width, height), new Level2(width, height), new Level3(width, height)};
    	mapGameSelectedToLevels = new HashMap<String, GameState[]>();
    	mapGameSelectedToLevels.put("Galaga", GalagaLevels);
    	mapGameSelectedToLevels.put("BrickBreaker", BrickBreakerLevels);
    }
    
    public void nextLevel() {
    	if (currentLevel < mapGameSelectedToLevels.get(gameSelection).length - 1) {
    		game = mapGameSelectedToLevels.get(gameSelection)[++ currentLevel];
    		gameScene.setRoot(game.getRoot());
    	}
    }
    
    public Group startScreen(int width, int height) {
		Group root = new Group();
		Text textToDisplay = new Text(width / 4, height / 2,
				"Welcome!\nPress \"B\" to play Brick Breakers\nPress\"G\" to playe Galaga");
		root.getChildren().add(textToDisplay);
		return root;
    }
		
}