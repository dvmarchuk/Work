package edu.bridgewater.mcmaze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class manages the connection with the MySQL database used to load map
 * information. It sends and receives queries and provides access to the results
 * to other classes.
 * 
 * @author Charles German
 *
 */
public class DBInterface {
	private static Connection con;
	// private static Object[][] queryResults;
	private static Map map;
	private static String adminUser, adminPass;

	/**
	 * Establish a connection to a database (each map is represented by a
	 * separate database)
	 * 
	 * @param adminUser
	 *            the username of the root user in MySQL
	 * @param adminPass
	 *            the password for {@code adminUser}
	 * @throws ClassNotFoundException
	 *             if {@link com.mysql.jdbc.Driver} is not found
	 * @throws SQLException
	 *             if an error occurs when connecting
	 */
	public static void establishConnection() throws ClassNotFoundException, SQLException {
		// ensure that all information is provided for db connection
		if (adminUser == null || adminPass == null)
			throw new NullPointerException();

		// connect to database
		String dbURL = "jdbc:mysql://localhost";
		Class.forName("com.mysql.jdbc.Driver");
		// TODO ensure that localhost/port information is correct
		// con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
		// dbName, mySqlUser, mySqlPass);
		con = DriverManager.getConnection(dbURL, adminUser, adminPass);

		System.out.printf("DB connection established:\n\tuser:%s,pass:%s\n\turl:%s\n", adminUser, adminPass, dbURL);
	}

	/**
	 * terminate the DB connection
	 * 
	 * @throws SQLException
	 *             if an error occurs while closing the connection
	 */
	public static void terminateConnection() throws SQLException {
		if (con == null)
			System.out.println("Cannot close a non-existent connection");
		else {
			map.drop(con);
			con.close();
			System.out.println("DB connection terminated");
		}
	}

	/**
	 * Load SQL code from a map file into memory and create the MySQL database
	 * 
	 * @param f
	 *            map file to load
	 * @throws IOException
	 *             If there is an issue reading the file
	 * @throws NumberFormatException
	 *             If there is an issue casting file header information to
	 *             {@code int} values
	 * @throws ClassNotFoundException
	 *             If there is an issue casting file header information and SQL
	 *             code to {@code String} values
	 * @throws SQLException
	 *             if there is a problem creating the MySQL database
	 */
	public static void loadMap(File f) throws IOException, ClassNotFoundException, SQLException {
		// set up resource readers
		FileInputStream fileIn = new FileInputStream(f);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);

		// load file header information
		String relativeBackgroundImagePath = (String) objectIn.readObject();
		// int numSQLStatements = Integer.parseInt((String)
		// objectIn.readObject());
		int numSQLStatements = objectIn.readInt();
		String mapName = (String) objectIn.readObject();
		Map m = new Map(relativeBackgroundImagePath, mapName);

		// load SQL statements into memory
		for (int i = 0; i < numSQLStatements; i++)
			m.addStatement((String) objectIn.readObject());
		// close resources
		objectIn.close();
		fileIn.close();

		map = m;
		map.create(con);
	}

	/**
	 * get a room with the given id
	 * 
	 * @param roomID
	 *            the id of the room to get
	 * @return the {@code Room} object for the room with that id or {@code null}
	 *         if no such room exists
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getRoom(final int roomID) throws SQLException {
		PreparedStatement ps = con
				.prepareStatement("SELECT RoomID, RoomName, RoomDesc, HasMcGregor, IsEndingRoom, IsStartingRoom FROM "
						+ map.getName() + ".Rooms WHERE RoomID='" + roomID + "';");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String roomName = rs.getString("RoomName");
			String roomDesc = rs.getString("RoomDesc");
			boolean isStartingRoom = rs.getBoolean("IsStartingRoom");
			boolean isEndingRoom = rs.getBoolean("IsEndingRoom");
			boolean hasMcGregor = rs.getBoolean("HasMcGregor");
			return new Room(roomName, roomDesc, isStartingRoom, isEndingRoom, hasMcGregor, roomID);
		} else
			return null;
	}

	/**
	 * get a room adjacent to the room with the given id
	 * 
	 * @param roomID
	 *            the id of the room adjacent to the target room
	 * @return an array of {@code Room} objects for the room adjacent to the
	 *         given room, or an empty(?) array if no such rooms can be found
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room[] getAdjacentRooms(final int roomID) throws SQLException {
		// get all edges which contain the given room
		PreparedStatement ps = con.prepareStatement("SELECT EdgeID, FirstNode, SecondNode, EdgeType FROM "
				+ map.getName() + ".Edges WHERE FirstNode='" + roomID + "' OR SecondNode='" + roomID + "';");
		ArrayList<Room> rooms = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		// get all rooms that are not the given room in the edges found
		while (rs.next()) {
			int firstNode = rs.getInt("FirstNode");
			int secondNode = rs.getInt("SecondNode");
			// get node that is NOT the given room id
			int targetNode = (firstNode == roomID) ? secondNode : firstNode;
			// TODO make sure this doesn't spam the connection into termination
			rooms.add(getRoom(targetNode));
		}
		// return an array full of the found rooms
		return rooms.toArray(new Room[rooms.size()]);
	}

	/**
	 * get the room adjacent to the room with the given room id
	 * 
	 * @param roomID
	 *            the known room id, the desired room DOES NOT have this room id
	 * @param edgeID
	 *            the edge id of the edge containing both the given room id and
	 *            the desired room id
	 * @return the room in the edge with the given id which DOES NOT have the
	 *         given room id or {@code null} if the room can't be found
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getAdjacentRoom(final int roomID, final int edgeID) throws SQLException {
		// TODO add handling for if the room can't be found
		PreparedStatement ps = con.prepareStatement("SELECT EdgeID, FirstNode, SecondNode, EdgeType FROM "
				+ map.getName() + ".Edges WHERE EdgeID='" + edgeID + "';");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			// get the 2 nodes in the edge
			int firstNode = rs.getInt("FirstNode");
			int secondNode = rs.getInt("SecondNode");

			// get the node that DOES NOT have the given room id
			int adjRoomID = (roomID == firstNode) ? secondNode : firstNode;
			return getRoom(adjRoomID);
		} else
			return null;
	}

	/**
	 * get the room adjacent to the room with the given id
	 * 
	 * @param roomID
	 *            the known room id, the desired room DOES NOT have this room id
	 * @param edgeType
	 *            the edge type containing both the given room id and the
	 *            desired room id
	 * @return the room in the edge with the given type which DOES NOT have the
	 *         given room id or {@code null} if the room or edge can't be found
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getAdjacentRoomByEdgeType(final int roomID, int edgeType) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT EdgeID, FirstNode, SecondNode, EdgeType FROM "
				+ map.getName() + ".Edges WHERE EdgeType='" + edgeType + "';");
		ResultSet rs = ps.executeQuery();
		// find the desired entry
		boolean found = false;
		int edgeID = -1;
		while (!found && rs.next()) {
			int firstNode = rs.getInt("FirstNode");
			int secondNode = rs.getInt("SecondNode");
			if (firstNode == roomID || secondNode == roomID) {
				found = true;
				edgeID = rs.getInt("EdgeID");
			}
		}

		// return null if the edge wasn't found
		if (edgeID == -1)
			return null;

		// if the edge was found, get the adjacent room
		return getAdjacentRoom(roomID, edgeID);
	}

	/**
	 * get all edges which contain the given room id
	 * 
	 * @param roomID
	 *            the id of the room which MUST be in the edges
	 * @return all edges which contain the given room id or {@code null} if no
	 *         edges can be found
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Edge[] getEdges(final int roomID) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT EdgeID, FirstNode, SecondNode, EdgeType FROM "
				+ map.getName() + ".Edges WHERE FirstNode='" + roomID + "' OR SecondNode='" + roomID + "';");
		ResultSet rs = ps.executeQuery();
		ArrayList<Edge> edges = new ArrayList<>();
		while (rs.next())
			edges.add(new Edge(rs.getInt("FirstNode"), rs.getInt("SecondNode"), rs.getInt("EdgeType"),
					rs.getInt("EdgeID")));
		if (edges.size() == 0)
			return null;
		else
			return edges.toArray(new Edge[edges.size()]);
	}

	/**
	 * get all valid exits (integer representation) for the room with the given
	 * id
	 * 
	 * @param roomID
	 *            the room to examine
	 * @return an array of integers, containing all of the valid exits (integer
	 *         representation)
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static int[] getExits(final int roomID) throws SQLException {
		ArrayList<Integer> exits = new ArrayList<>();
		Edge[] edges = getEdges(roomID);
		for (Edge e : edges)
			exits.add(e.getEdgeType());
		// convert to array
		int[] result = new int[exits.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = exits.get(i);
		return result;
	}

	/**
	 * @return the starting room
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getStartingRoom() throws SQLException {
		// query the database
		PreparedStatement ps = con
				.prepareStatement("SELECT RoomID, RoomName, RoomDesc, HasMcGregor, IsStartingRoom, IsEndingRoom FROM "
						+ map.getName() + ".Rooms WHERE IsStartingRoom='1';");
		ResultSet rs = ps.executeQuery();
		// get the starting room
		rs.next();
		return new Room(rs.getString("RoomName"), rs.getString("RoomDesc"), rs.getBoolean("IsStartingRoom"),
				rs.getBoolean("IsEndingRoom"), rs.getBoolean("HasMcGregor"), rs.getInt("RoomID"));
	}

	/**
	 * @return the easter egg room
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getEasterEggRoom() throws SQLException {
		// query the database
		PreparedStatement ps = con
				.prepareStatement("SELECT RoomID, RoomName, RoomDesc, HasMcGregor, IsStartingRoom, IsEndingRoom FROM "
						+ map.getName() + ".Rooms WHERE HasMcGregor='1';");
		ResultSet rs = ps.executeQuery();
		// get the easter egg room
		rs.next();
		return new Room(rs.getString("RoomName"), rs.getString("RoomDesc"), rs.getBoolean("IsStartingRoom"),
				rs.getBoolean("IsEndingRoom"), rs.getBoolean("HasMcGregor"), rs.getInt("RoomID"));
	}

	/**
	 * @return the ending room
	 * @throws SQLException
	 *             if there is a SQL problem
	 */
	public static Room getEndingRooms() throws SQLException {
		// query the database
		PreparedStatement ps = con
				.prepareStatement("SELECT RoomID, RoomName, RoomDesc, HasMcGregor, IsStartingRoom, IsEndingRoom FROM "
						+ map.getName() + ".Rooms WHERE IsEndingRoom='1';");
		ResultSet rs = ps.executeQuery();
		// get the starting room
		rs.next();
		return new Room(rs.getString("RoomName"), rs.getString("RoomDesc"), rs.getBoolean("IsStartingRoom"),
				rs.getBoolean("IsEndingRoom"), rs.getBoolean("HasMcGregor"), rs.getInt("RoomID"));
	}

	/**
	 * set MySQL admin credentials
	 * 
	 * @param user
	 *            the admin username
	 * @param pass
	 *            the admin password
	 */
	public static void setAdminCreds(String user, String pass) {
		adminUser = user;
		adminPass = pass;
	}

	/**
	 * write the map file
	 * 
	 * @param rooms
	 *            the rooms in the map
	 * @param edges
	 *            the edges in the map
	 * @param f
	 *            the file to write into
	 * @throws FileNotFoundException
	 *             if there is an I/O problem
	 * @throws IOException
	 *             if there is an I/O problem
	 */
	public static void writeMapFile(Room[] rooms, Edge[] edges, File f, String mapName)
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));

		// generate SQL statements
		ArrayList<String> sqlStatements = new ArrayList<>();
		// statements to create database & tables
		sqlStatements.add("CREATE DATABASE " + mapName + ";");
		sqlStatements.add("CREATE TABLE " + mapName
				+ ".Rooms (RoomID INT NOT NULL, RoomName VARCHAR(255) NOT NULL, RoomDesc TEXT, HasMcGregor BOOLEAN,"
				+ " IsEndingRoom BOOLEAN, IsStartingRoom BOOLEAN, PRIMARY KEY (RoomID));");
		sqlStatements.add("CREATE TABLE " + mapName
				+ ".Edges (EdgeID INT NOT NULL, FirstNode INT NOT NULL, SecondNode INT NOT NULL, EdgeType INT NOT NULL,"
				+ " PRIMARY KEY (EdgeID));");
		// statements to create rooms
		for (Room r : rooms)
			sqlStatements.add(r.toSQL(mapName));
		// statement to create edges
		for (Edge e : edges)
			sqlStatements.add(e.toSQL(mapName));

		// file header information
		out.writeObject("unused"); // relative bg image path
		out.writeInt(sqlStatements.size()); // num SQL statements
		out.writeObject(mapName); // map name

		// SQL statements
		for (String s : sqlStatements)
			out.writeObject(s);

		// just in case
		out.writeChar('\n');

		// close resources
		out.close();
	}
}
