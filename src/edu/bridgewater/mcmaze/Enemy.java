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
	Room location;
	int previousDir;
	Random rand;
	final int CHASE_DISTANCE = 2;

	public Enemy(Room location) {
		this.location = location;
		rand = new Random();
	}

	public void performEnemyTurn() {
		move(enemyLogic());
		// if enemy location is adjacent to player location alert the player to
		// hearing footsteps
		// if enemy location == player location kill the player
	}

	// Determines the direction in which the enemy moves (Enemy AI)
	public int enemyLogic() {
		int direction;
		while (true) {
			direction = rand.nextInt(9);
			if (direction == previousDir) {
				continue;
			}
			break;
		}
		previousDir = direction;
		return direction;
	}

	public void move(int direction) {
		// TODO implement this
	}
}
