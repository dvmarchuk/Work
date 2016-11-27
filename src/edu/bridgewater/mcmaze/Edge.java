package edu.bridgewater.mcmaze;

/**
 * This class represents an edge from the adjacency list in the database
 * 
 * @author Charles German
 *
 */
public class Edge {
	private static int edgeCount; // TODO generate edgeIDs
	private int firstNode, secondNode, edgeID;
	private int edgeType;

	/**
	 * constructor
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
	 */
	public Edge(int firstNode, int secondNode, int edgeType) {
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.edgeType = edgeType;
		this.edgeID = -1;
	}

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
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.edgeType = edgeType;
		this.edgeID = edgeID;
	}

	public Edge(Room firstNode, Room secondNode, int edgeType) {
		// TODO fill out constructor
	}

	/**
	 * @return the secondNode
	 */
	public int getSecondNode() {
		return secondNode;
	}

	/**
	 * @return the firstNode
	 */
	public int getFirstNode() {
		return firstNode;
	}

	/**
	 * @return the edgeType
	 */
	public int getEdgeType() {
		return edgeType;
	}
}
