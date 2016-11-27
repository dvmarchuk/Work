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
		setFirstNode(firstNode);
		setSecondNode(secondNode);
		setEdgeType(edgeType);
		setEdgeID(-1); // TODO change this
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
		setFirstNode(firstNode);
		setSecondNode(secondNode);
		setEdgeType(edgeType);
		setEdgeID(edgeID);
	}

	/**
	 * constructor (if using map maker)
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @param edgeType
	 */
	public Edge(Room firstNode, Room secondNode, int edgeType) {
		setFirstNode(firstNode.getRoomID());
		setSecondNode(secondNode.getRoomID());
		setEdgeType(edgeType);
		setEdgeID(-1); // TODO change this
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
		this.edgeID = edgeID;
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
}
