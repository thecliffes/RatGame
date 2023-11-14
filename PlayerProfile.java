package RatGame;

/**
 * This class models a Player Profile. Player profiles can be created and deleted via appropriate
 * menu items or buttons within the application.
 * @author Mathew Philip Peedikayil
 * @version 1
 */
public class PlayerProfile implements Comparable<PlayerProfile> {
	private String playerName;
	private int levelUnlocked;
	private int playerScore;

	/**
	 * Creates a new player profile.
	 * @param playerName The player's name.
	 * @param levelUnlocked The most recent level the player has unlocked.
	 * @param playerScore The player's score.
	 */
	public PlayerProfile(String playerName, int levelUnlocked, int playerScore) {
		this.playerName = playerName;
		this.levelUnlocked = levelUnlocked;
		this.playerScore = playerScore;
	}

	/**
	 * This method gets the player's name.
	 * @return The player's name.
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * This method sets the player's name.
	 * @param playerName The player's name.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * This method gets the most recent level the player has unlocked.
	 * @return The most recent level the player has unlocked.
	 */
	public int getLevelUnlocked() {
		return this.levelUnlocked;
	}

	/**
	 * This method sets the most recent level the player has unlocked.
	 * @param levelUnlocked The most recent level the player has unlocked.
	 */
	public void setLevelUnlocked(int levelUnlocked) {
		this.levelUnlocked = levelUnlocked;
	}

	/**
	 * This method gets the player's score.
	 * @return The player's score.
	 */
	public int getPlayerScore() {
		return this.playerScore;
	}

	/**
	 * This method sets the player's score.
	 * @param playerScore The player's score.
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	/**
	 * This method compares players' scores.
	 * @param otherPlayer A different player profile.
	 * @return 1 if the score is lower and -1 if the score is higher.
	 */
	public int compareTo(PlayerProfile otherPlayer) {
		if (this.getPlayerScore() < otherPlayer.getPlayerScore()) {
			return +1;
		} else if(this.getPlayerScore() == otherPlayer.getPlayerScore()) {
			return 0;
		} else {
			return -1;
		}
	}
	
}