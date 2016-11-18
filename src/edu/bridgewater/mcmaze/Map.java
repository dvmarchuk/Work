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
	 * @param numSqlStatements
	 *            the number of SQL statements required to make the map
	 * @param relativeBGImagePath
	 *            the relative path to the background image file for the map
	 */
	public Map(String relativeBGImagePath, String mapName) {
		this.relativeBGImagePath = relativeBGImagePath;
		this.mapName = mapName;
		sqlStatements = new ArrayList<>();
	}

	/**
	 * constructor
	 * 
	 * @param numSqlStatements
	 *            the number of SQL statements required to make the map
	 * @param relativeBGImagePath
	 *            the relative path to the background image file for the map
	 * @param sqlStatements
	 *            the SQL statements required to make this database
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
		for (int i = 0, len = sqlStatements.size(); i < len; i++) {
			StringBuilder statement = new StringBuilder();
			// build a complete SQL statement
			while (!statement.toString().contains(";"))
				statement.append(sqlStatements.get(i++));
			// execute the complete SQL statement
			PreparedStatement ps = con.prepareStatement(statement.toString());
			ps.executeUpdate();
		}

		System.out.printf("Created map %s with %d lines of SQL\n", mapName, sqlStatements.size());
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
		PreparedStatement ps = con.prepareStatement("DROP DATABASE " + mapName + " IF EXISTS;");
		ps.executeUpdate();
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
