/* create the database for the map if it doesn't exist, this requires MySQL admin credentials */
CREATE DATABASE /* map name */;

/* create the tables in the database */
USE /* map name */

CREATE TABLE Rooms
(
RoomID INT NOT NULL,
RoomName VARCHAR(255) NOT NULL,
RoomDesc TEXT,
HasMcGregor BOOLEAN,
IsEndingRoom BOOLEAN,
IsStartingRoom BOOLEAN,
PRIMARY KEY (RoomID)
);

CREATE TABLE Edges
(
EdgeID INT NOT NULL,
FirstNode INT NOT NULL,
SecondNode INT NOT NULL,
EdgeType INT NOT NULL,
PRIMARY KEY (EdgeID)
);

/* delete a map, this requires MySQL admin permissions */
DROP DATABASE /* map name */;
