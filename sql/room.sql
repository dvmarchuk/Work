/* add a room to the database */
INSERT INTO /* map name */.Rooms (RoomName, RoomDesc, HasMcGregor, IsEndingRoom, IsStartingRoom)
VALUES (/* room name */, /* room description */, /* 0/1 (true/false) if mcgregor is there */, /* 0/1 (true/false) if it is the last room */, /* 0/1 (true/false) if it is the starting room*/);

/* delete a room from the database */
DELETE FROM /* map name */.Rooms WHERE RoomID='/* the room id */';

/* update a room with new information */
UPDATE /* map name */.Rooms SET
RoomName='/* new name */',
RoomDesc='/* new desc */',
HasMcGregor='/* new mcg status */',
IsEndingRoom='/* new ending room status */',
IsStartingRoom='/* new starting room status */'
WHERE RoomID='/* the room id */';

/* search for a room */
SELECT RoomID, RoomName, RoomDesc, HasMcGregor, IsEndingRoom, IsStartingRoom FROM /* map name */.Rooms
WHERE RoomID='/* the room id */';
