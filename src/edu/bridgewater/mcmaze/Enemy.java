package edu.bridgewater.mcmaze;

import java.util.Random;

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
		Random r = new Random();
		int upperBound = 7;
		int lowerBound = 3;
		movesUntilCapture = r.nextInt(upperBound - lowerBound + 1) + lowerBound;
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

	public boolean spottedPlayer() {
		return playerSeen;
	}

	public void setPlayerSpotted(boolean b) {
		playerSeen = b;
	}
}
