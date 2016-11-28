package edu.bridgewater.mcmaze;

import edu.bridgewater.mcmaze.Room;

public class Navigation{
	// Contains the actual "map" of the game, created from Edge and Vertex
	public String getRoom(int ID){
		Room room = DBInterface.getRoom(ID);
		if(room.IsEndingRoom){
			System.out.print("Congratulations- you win!!");
		}
		return room.get
	}
//Hashtable ht = new Hashtable();
	
//database is an adjancency list 
//


}
