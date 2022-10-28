package backendStuff;
//package breakout2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class ScoreManager {
	
	private final static String pathToScoreFile = "resources/scores";
	private int highScore;
	
	public ScoreManager() {
        try {
            FileReader reader = new FileReader(pathToScoreFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            this.highScore = Integer.valueOf(bufferedReader.readLine());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public int getHighScore() {
		return this.highScore;
	}
	
	public void setHighScore(int newHighScore) {
        try {
        	this.highScore = Math.max(newHighScore, highScore);
            FileWriter writer = new FileWriter(pathToScoreFile, false);
            writer.write(String.valueOf(this.highScore));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	

}
