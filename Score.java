package RatGame;

/**
 * This class handles a player's score in the game.
 * @author Mathew Philip Peedikayil
 * @version 1
 * */
public class Score {
	private static int points;
	private int bonusPoints;
	private int totalPoints;

	/**
	 * Creates an empty score object.
	 * */
	public Score() {
		
	}

	/**
	 * Creates a new score object.
	 * @param points The player's score.
	 * @param bonusPoints Any additional score the player might get*/
	public Score(int points, int bonusPoints) {
		this.points = points;
		this.bonusPoints = bonusPoints;
	}

	/**
	 * This method increases a player's score.
	 * @param p the amount of points to add to the score.
	 * */
	public static void addPoints(int p) {
		points += p;
	}

	/**
	 * This method computes how much bonus points a player is going to get.
	 * If a player finishes a level before the expected time, for each unused second
	 * the player is awarded 1 point.
	 * @param timeLimitOfLevel the time limit of the level.
	 * @param timeOfCompletion time the level takes to complete.
	 * */
	public void addBonusPoints(int timeLimitOfLevel, int timeOfCompletion) {
		int timeDifference = 0;
		if(timeOfCompletion < timeLimitOfLevel) {
			timeDifference = timeLimitOfLevel - timeOfCompletion;
			setBonusPoints(timeDifference);
		} else {
			timeDifference = 0;
		}
	}

	/**
	 * This method gets the current player's points.
	 * @return The player's points.
	 * */
	public int getPoints() {
		return points;
	}

	/**
	 * This method sets the player's points.
	 * @param p Current player's points.
	 * */
	public void setPoints(int p) {
		this.points = p;
	}

	/**
	 * This method gets the bonus points.
	 * @return The player's bonus points.
	 * */
	public int getBonusPoints() {
		return this.bonusPoints;
	}

	/**
	 * This method sets the bonus points.
	 * @param bonusPoints The player's bonus points.
	 * */
	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	/**
	 * This method gets the total score. The total score is calculated by
	 * adding the bonus point to the player's points.
	 * @return The total score.
	 * */
	public int getTotalPoints() {
		totalPoints = points + bonusPoints;
		return this.totalPoints;
	}

	/**
	 * This method sets the total score.
	 * @param totalPoints The total points.
	 * */
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}