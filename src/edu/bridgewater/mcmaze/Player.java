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
	private Enemy mcgregor;

	/**
	 * constructor
	 * 
	 * @param r
	 *            the starting room for the player
	 */
	public Player(Room r) {
		try {
			setLocation(r.getRoomID());
			if (DBInterface.getEasterEggRoom() == null)
				mcgregor = null;
			else
				mcgregor = new Enemy();
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
		// if (DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction)
		// == null) {
		// // TODO adjust sql to only search by firstNode
		// // TODO add code to duplicate edges (but modified)
		// // print error message and return to direction choosing
		// GUIScreens.print("You cannot go that direction");
		// } else {
		// setLocation(DBInterface.getAdjacentRoomByEdgeType(getLocationID(),
		// direction).getRoomID());
		// }
		movesMade++;
		if (DBInterface.getExits(getLocationID()).contains(direction)) {
			setLocation(DBInterface.getAdjacentRoomByEdgeType(getLocationID(), direction).getRoomID());
		} else
			GUIScreens.print("You cannot go " + GUIScreens.translateEdgeType(direction));
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

	private void setLocation(int ID) throws SQLException {
		this.location = DBInterface.getRoom(ID);
		// TODO handle on-entering effects
		// display room name and description
		GUIScreens.print(location.getRoomName());
		GUIScreens.print(location.getRoomDesc());
		if (location.isEndingRoom()) {
			GUIScreens.print("YOU WIN!");
			GUIScreens.print("Score (lower is better): " + calculateScore());
			// prevent player from moving after winning
			this.location = null;
		}
		if (location.hasMcGregor()) {
			mcgregor.setPlayerSpotted(true);
			GUIScreens.print("McGregor has spotted you. It won't be long before he catches you...");
			// TODO start enemy chase
		}
		if (mcgregor != null) {
			mcgregor.incrementMoves();
			GUIScreens.print("You can hear McGregor creeping closer...");
			if (mcgregor.hasCaughtPlayer(location.getRoomID())) {
				GUIScreens.print("McGregor has caught you! YOU LOSE!");
				// prevent player from moving after losing
				this.location = null;
			}
		}
	}

	private int calculateScore() {
		if (mcgregor == null)
			return movesMade;
		else {
			return movesMade - mcgregor.getMovesMade();
		}
	}
}
