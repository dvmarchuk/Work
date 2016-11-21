package edu.bridgewater.mcmaze;

/**
 * Player Class
 * 
 * @author Alan Bowman
 */

public class Player {
	private String name;
	private int movesMade; // useful for scoring
	private Room location;

	// Constructor for invalid name, part of easter egg?
	public Player(Room location) {
		this.name = "Ralph";
		this.location = location;
		movesMade = 0;
	}

	public Player(String name, Room location) {
		this.name = name;
		this.location = location;
		movesMade = 0;
	}

	public void movePlayer(int direction) {
		
		movesMade++;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getMovesMade() {
		return movesMade;
	}

	public Room getLocation() {
		return location;
	}
}
