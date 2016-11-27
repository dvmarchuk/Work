/* create the database for the map if it doesn't exist, this requires MySQL admin credentials */
CREATE DATABASE /* map name */ IF NOT EXISTS;

/* create the tables in the database */
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
FOREIGN KEY (FirstNode) REFERENCES Rooms(RoomID) ON DELETE CASCADE,
FOREIGN KEY (SecondNode) REFERENCES Rooms(RoomID) ON DELETE CASCADE,
PRIMARY KEY (EdgeID)
);

/* delete a map, this requires MySQL admin permissions */
DROP DATABASE /* map name */ IF EXISTS;
