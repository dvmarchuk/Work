package edu.bridgewater.mcmaze;

import java.sql.SQLException;

/**
 * Player Class
 * 
 * @author Alan Bowman, Zach Squires, Charles German
 */
public class Player {
	private int movesMade; // useful for scoring
	private Room location;

	/**
	 * constructor
	 * 
	 * @param r
	 *            the starting room for the player
	 */
	public Player(Room r) {
		try {
			setLocation(r.getRoomID());
		} catch (SQLException e) {
			System.err.println("=== PROBLEM CREATING PLAYER ===");
			e.printStackTrace();
			System.err.println("=== END PLAYER PROBLEM ===");
		}
		movesMade = 0;
	}

	/**
	 * move the player
	 * 
	 * @param direction
	 *            <ul>
	 *            <li>0 - north</li>
	 *            <li>1 - north-east</li>
	 *            <li>2 - east</li>
	 *            <li>3 - south-east</li>
	 *            <li>4 - south</li>
	 *            <li>5 - south-west</li>
	 *            <li>6 - west</li>
	 *            <li>7 - north-west</li>
	 *            <li>8 - up</li>
	 *            <li>9 - down</li>
	 *            </ul>
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public void move(int direction) throws SQLException {
		if (DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction) == null) {
			// print error message and return to direction choosing
			GUIScreens.print("You cannot go that direction");
		} else {
			setLocation(DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction).getRoomID());
		}
		movesMade++;
	}

	public int getMovesMade() {
		return movesMade;
	}

	public Room getLocation() {
		return location;
	}

	public int getLocationID() {
		return location.getRoomID();
	}

	public void setLocation(int ID) throws SQLException {
		this.location = DBInterface.getRoom(ID);
		// TODO handle on-entering effects
		// display room name and description
		GUIScreens.print(location.getRoomName());
		GUIScreens.print(location.getRoomDesc());
		if (location.isEndingRoom()) {
			GUIScreens.print("YOU WIN!");
			GUIScreens.print("Score (lower is better): " + movesMade);
			// prevent player from moving after winning
			this.location = null;
		}
		if (location.hasMcGregor()) {
			GUIScreens.print("McGregor mode activated - prepare yourself");
			// TODO start enemy chase
		}
	}
}
