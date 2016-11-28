package edu.bridgewater.mcmaze;

/**
 * A class for the AI enemy present in the game's maze. The Enemy will randomly
 * search the maze until he is within a certain distance of the player at which
 * point he will chase the player.
 * 
 * @author Alan Bowman, Charles German
 */

public class Enemy {
	private int movesUntilCapture;
	private boolean playerSeen;
	private int movesMade;

	public Enemy() {
		// range 1-4 (1-3??)
		movesUntilCapture = (int) (Math.random() * 3 + 1);
		playerSeen = false;
	}

	/**
	 * @param playerRoomID
	 *            the roomID for the room the player is in
	 * @return {@code true} if the player has run out of time after seeing the
	 *         enemy
	 */
	public boolean hasCaughtPlayer(int playerRoomID) {
		return movesMade == movesUntilCapture && playerSeen;
	}

	public int getMovesMade() {
		return movesMade;
	}

	public void incrementMoves() {
		movesMade++;
	}

	public boolean playerSpotted() {
		return playerSeen;
	}

	public void setPlayerSpotted(boolean b) {
		playerSeen = b;
	}
}
