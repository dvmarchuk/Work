package edu.bridgewater.mcmaze;

import edu.bridgewater.mcmaze.Room;

import java.sql.SQLException;

import edu.bridgewater.mcmaze.Player;

public class Navigation{
	// Contains the actual "map" of the game, created from Edge and Vertex
	public int getRoom(int ID) throws SQLException{
		Room room = DBInterface.getRoom(ID);
		if(room.isEndingRoom()){
			//print("Congratulations- you win!!");
		}
		return room.getRoomID();
	}
//Hashtable ht = new Hashtable();
	
//database is an adjancency list 
//

//MIGHT NOT NEED THIS CLASS AT ALL-LET ME KNOW WHAT YOU GUYS THINK THOUGH!	
}
