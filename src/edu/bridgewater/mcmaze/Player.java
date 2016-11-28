package edu.bridgewater.mcmaze;

import java.sql.SQLException;

/**
 * Player Class
 * 
 * @author Alan Bowman, Zach Squires
 */

public class Player {
	// private String name;
	private int movesMade; // useful for scoring
	private Room location;

	// Constructor for invalid name, part of easter egg?
	public Player(Room location) {
		// this.name = "Ralph";
		this.location = location;
		movesMade = 0;
	}

	// public Player(String name, Room location) {
	// this.name = name;
	// this.location = location;
	// movesMade = 0;
	// }

	public void movePlayer(int direction) throws SQLException {
		if (DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction) == null) {
			// print error message and return to direction choosing
		} else {
			setLocation(DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction).getRoomID());
			// display room name and description
			GUIScreens.print(location.getRoomName());
			GUIScreens.print(location.getRoomDesc());
			if (location.isEndingRoom()) {
				GUIScreens.print("YOU WIN!");
				// TODO exit
			}
			if (location.hasMcGregor()) {
				GUIScreens.print("McGregor mode activated- prepare yourself");
				// TODO switch to the eater egg mode
			}

		}
		movesMade++;
	}

	// Getters
	// public String getName() {
	// return name;
	// }

	public int getMovesMade() {
		return movesMade;
	}

	public Room getLocation() {
		return location;
	}

	public int getLocationID() {
		return location.getRoomID();
	}

	// Setters
	public void setLocation(int ID) throws SQLException {
		this.location = DBInterface.getRoom(ID);

	}
}
