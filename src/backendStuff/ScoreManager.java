package backendStuff;
//package breakout2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class ScoreManager {
	
	private int highScore;
	private String pathToHighScore;
	
	public ScoreManager(String pathToHighScore) {
        try {
            FileReader reader = new FileReader(pathToHighScore);
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
        	System.out.println(String.format("SETTING HIGHSCORE %d", newHighScore));
        	this.highScore = Math.max(newHighScore, highScore);
            FileWriter writer = new FileWriter(pathToHighScore, false);
            writer.write(String.valueOf(this.highScore));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	

}
