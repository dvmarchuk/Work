package edu.bridgewater.mcmaze;

import java.util.ArrayList;

/**
 * This class represents an edge from the adjacency list in the database
 * 
 * @author Charles German
 *
 */
public class Edge {
	private static ArrayList<Integer> usedIDs;
	private int firstNode, secondNode, edgeID;
	private int edgeType;

	/**
	 * constructor (if loading from DB)
	 * 
	 * @param firstNode
	 *            the room id of a room in this edge
	 * @param secondNode
	 *            the room id of the other room in this edge. these ids CAN be
	 *            the same, though this might be confusing
	 * @param edgeType
	 *            the relationship between the two rooms
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
	 * @param edgeID
	 *            the id for this edge
	 */
	public Edge(int firstNode, int secondNode, int edgeType, int edgeID) {
		setFirstNode(firstNode);
		setSecondNode(secondNode);
		setEdgeType(edgeType);
		setEdgeID(edgeID);
	}

	/**
	 * constructor (if using map maker)
	 * 
	 * @param firstNode
	 *            the room object for the first node
	 * @param secondNode
	 *            the room object for the second node. these objects CAN be the
	 *            same, though this might be confusing
	 * @param edgeType
	 *            the relationship between the two rooms
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
	 */
	public Edge(Room firstNode, Room secondNode, int edgeType) {
		setFirstNode(firstNode.getRoomID());
		setSecondNode(secondNode.getRoomID());
		setEdgeType(edgeType);
		setEdgeID(generateEdgeID());
	}

	/**
	 * @return the edgeType
	 */
	public int getEdgeType() {
		return edgeType;
	}

	/**
	 * @param edgeType
	 *            the edgeType to set
	 */
	public void setEdgeType(int edgeType) {
		this.edgeType = edgeType;
	}

	/**
	 * @return the edgeID
	 */
	public int getEdgeID() {
		return edgeID;
	}

	/**
	 * @param edgeID
	 *            the edgeID to set
	 */
	public void setEdgeID(int edgeID) {
		// ensure list is not null
		if (usedIDs == null)
			usedIDs = new ArrayList<>();
		// add ID to list of used IDs
		usedIDs.add(edgeID);

		this.edgeID = edgeID;
	}

	/**
	 * @return a unique, unused edgeID
	 */
	public int generateEdgeID() {
		// ensure list is not null
		if (usedIDs == null)
			usedIDs = new ArrayList<>();
		// generate unique, unused edgeID
		int id = 0;
		do {
			id++;
		} while (usedIDs.contains(id));
		return id;
	}

	/**
	 * @return the secondNode
	 */
	public int getSecondNode() {
		return secondNode;
	}

	/**
	 * @param secondNode
	 *            the secondNode to set
	 */
	public void setSecondNode(int secondNode) {
		this.secondNode = secondNode;
	}

	/**
	 * @return the firstNode
	 */
	public int getFirstNode() {
		return firstNode;
	}

	/**
	 * @param firstNode
	 *            the firstNode to set
	 */
	public void setFirstNode(int firstNode) {
		this.firstNode = firstNode;
	}

	/**
	 * @param mapName
	 *            the name of the database the edge table is in
	 * @return the SQL instruction required to create this Edge
	 */
	public String toSQL(String mapName) {
		return String.format("INSERT INTO %s.Edges (EdgeID, FirstNode, SecondNode, EdgeType) VALUES (%d, %d, %d, %d);",
				mapName, getEdgeID(), getFirstNode(), getSecondNode(), getEdgeType());
	}

	/**
	 * get the correspondingly opposite edge to this one. also, PARKOUR!
	 * 
	 * @param mapName
	 *            the name of the database the edge table is in
	 * @return the SQL instruction required to create the Edge opposite this one
	 */
	public String getMirrorsEdge(String mapName) {
		int firstNode = this.secondNode;
		int secondNode = this.firstNode;
		int edgeID = generateEdgeID();
		int edgeType = getOppositeEdge(this.edgeType);
		return new Edge(firstNode, secondNode, edgeType, edgeID).toSQL(mapName);
	}

	private int getOppositeEdge(int edgeType) {
		int i;
		switch (edgeType) {
		default:
		case 0:
			i = 4;
			break;
		case 1:
			i = 5;
			break;
		case 2:
			i = 6;
			break;
		case 3:
			i = 7;
			break;
		case 4:
			i = 0;
			break;
		case 5:
			i = 1;
			break;
		case 6:
			i = 2;
			break;
		case 7:
			i = 3;
			break;
		case 8:
			i = 9;
			break;
		case 9:
			i = 8;
			break;
		}
		return i;
	}

	/**
	 * mark the given id as free
	 * 
	 * @param id
	 *            the id to free up
	 */
	public static void freeID(int id) {
		if (usedIDs.contains(id)) {
			int index = usedIDs.indexOf(id);
			usedIDs.remove(index);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// example: 1 : 56 : 4 : 8
		return getEdgeID() + " : " + getFirstNode() + " : " + getSecondNode() + " : " + getEdgeType();
	}
}
