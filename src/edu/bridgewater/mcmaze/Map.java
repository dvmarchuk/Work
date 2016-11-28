package edu.bridgewater.mcmaze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class holds information from a map loaded from a file
 * 
 * @author Charles German
 *
 */
public class Map {
	private ArrayList<String> sqlStatements;
	private String relativeBGImagePath, mapName;

	/**
	 * constructor
	 * 
	 * @param relativeBGImagePath
	 *            the relative path (i.e., assets/) to the background image for
	 *            this map (probably won't be implemented)
	 * @param mapName
	 *            the name of the map (file)
	 */
	public Map(String relativeBGImagePath, String mapName) {
		this.relativeBGImagePath = relativeBGImagePath;
		this.mapName = mapName;
		sqlStatements = new ArrayList<>();
	}

	/**
	 * constructor
	 * 
	 * @param relativeBGImagePath
	 *            the relative path (i.e., assets/) to the background image for
	 *            this map (probably won't be implemented)
	 * @param mapName
	 *            the name of the map (file)
	 * @param sqlStatements
	 *            the SQL statements to use when creating this map in memory
	 */
	public Map(String relativeBGImagePath, String mapName, String... sqlStatements) {
		this.relativeBGImagePath = relativeBGImagePath;
		this.mapName = mapName;
		this.sqlStatements = new ArrayList<>();
		for (String statement : sqlStatements)
			this.sqlStatements.add(statement);
	}

	/**
	 * Add a single SQL statement to the list of statements required to build
	 * the map in memory
	 * 
	 * @param sqlStatement
	 *            the statement to add
	 */
	public void addStatement(String sqlStatement) {
		sqlStatements.add(sqlStatement);
	}

	/**
	 * Add multiple SQL statements to the list of statements required to build
	 * the map in memory
	 * 
	 * @param sqlStatements
	 *            the multiple statements to add
	 */
	public void addStatements(String[] sqlStatements) {
		for (String s : sqlStatements)
			addStatement(s);
	}

	/**
	 * Create a map with the SQL statements that have been
	 * 
	 * @param con
	 *            the connection to the database
	 * @throws SQLException
	 *             if there is a problem executing the SQL statements
	 */
	public void create(Connection con) throws SQLException {
		// execute each line of SQL code from the file to create the database
		for (String s : sqlStatements) {
			PreparedStatement ps = con.prepareStatement(s);
			ps.executeUpdate();
		}

		System.out.printf("Created map '%s' with %d lines of SQL\n", mapName, sqlStatements.size());
	}

	/**
	 * Drop the database for this map
	 * 
	 * @param con
	 *            the connection to the database
	 * @throws SQLException
	 *             if there is a problem executing the SQL statement
	 */
	public void drop(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("DROP DATABASE " + mapName + ";");
		ps.executeUpdate();
		System.out.println("Map '" + getName() + "' dropped.");
	}

	/**
	 * @return the map name
	 */
	public String getName() {
		return mapName;
	}

	/**
	 * @return the relative path (unix-style delimiters) to the background image
	 *         for this map
	 */
	public String getBGImagePath() {
		return relativeBGImagePath;
	}
}
