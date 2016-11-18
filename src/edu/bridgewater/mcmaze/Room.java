package edu.bridgewater.mcmaze;

/**
 * This class represents a room in memory
 * 
 * @author Charles German
 *
 */
public class Room {
	private String roomName, roomDesc;
	private boolean isStartingRoom, isEndingRoom, hasMcGregor;
	private final int roomID;

	/**
	 * constructor
	 * 
	 * @param roomName
	 *            the name of the room
	 * @param roomDesc
	 *            the description for the room
	 * @param isStartingRoom
	 *            whether or not this room is the first room in the maze
	 * @param isEndingRoom
	 *            whether or not this room is an exit for the maze
	 * @param hasMcGregor
	 *            whether or not this room contains the easter egg
	 * @param roomID
	 *            the ID of this room
	 */
	public Room(String roomName, String roomDesc, boolean isStartingRoom, boolean isEndingRoom, boolean hasMcGregor,
			final int roomID) {
		this.setRoomName(roomName);
		this.setRoomDesc(roomDesc);
		this.setStartingRoom(isStartingRoom);
		this.setEndingRoom(isEndingRoom);
		this.setHasMcGregor(hasMcGregor);
		this.roomID = roomID;
	}

	/**
	 * @return the roomDesc
	 */
	public String getRoomDesc() {
		return roomDesc;
	}

	/**
	 * @param roomDesc
	 *            the roomDesc to set
	 */
	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @param roomName
	 *            the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @return the isStartingRoom
	 */
	public boolean isStartingRoom() {
		return isStartingRoom;
	}

	/**
	 * @param isStartingRoom
	 *            the isStartingRoom to set
	 */
	public void setStartingRoom(boolean isStartingRoom) {
		this.isStartingRoom = isStartingRoom;
	}

	/**
	 * @return the isEndingRoom
	 */
	public boolean isEndingRoom() {
		return isEndingRoom;
	}

	/**
	 * @param isEndingRoom
	 *            the isEndingRoom to set
	 */
	public void setEndingRoom(boolean isEndingRoom) {
		this.isEndingRoom = isEndingRoom;
	}

	/**
	 * @return the hasMcGregor
	 */
	public boolean isHasMcGregor() {
		return hasMcGregor;
	}

	/**
	 * @param hasMcGregor
	 *            the hasMcGregor to set
	 */
	public void setHasMcGregor(boolean hasMcGregor) {
		this.hasMcGregor = hasMcGregor;
	}

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}
}
