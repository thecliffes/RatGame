package RatGame;

import java.util.ArrayList;

/**
 * This class is an implementation of the high scores table.
 * @author Vuyelwa N Mazingi
 * @version 1*/
public class HighScore {

	private int rank;
	private String playername;
	private int level;
	private int score;
	private ArrayList<PlayerProfile> topTenPlayerProfiles = new ArrayList<PlayerProfile>();
	
	/**
	 * The constructor takes a integer representing the level number and 
	 * generates a high score table based on the level number
	 * @param level the level to generate the high score
	 */
	public HighScore(int level) {
		this.level = level;
		displayTable(level);
	}

	/**
	 * This method adds a new profile to the table.
	 * @param score The current player's score.
	 * @param playername The current player's name.
	 * */
	public void addProfile(String playername, int score) {
		
	}

	/**
	 * For each level this method displays the high score table.
	 * */
	public void displayTable(int level) {
		ArrayList<Integer> highScoreTable = new ArrayList<Integer>();
		highScoreTable.add(1,2);
		for(Integer profile: highScoreTable) {
			System.out.println(highScoreTable);
		}
	}
	
}
