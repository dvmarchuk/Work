# McMaze
This is definitely not a Zork clone. No, really, it's not.
Instead, it is a text-based maze game.

# Game
## Functional Requirements
- Directional buttons that allow the player to navigate the maze
  - up
  - down
  - north
  - south
  - east
  - west
  - northeast
  - southeast
  - southwest
  - northwest
- A text log to display room descriptions and a list of previously entered commands and the corresponding output
- A command prompt that will process commands
  - up (u)
  - down (d)
  - north (n)
  - south (s)
  - east (e)
  - west (w)
  - northeast (ne)
  - southeast (se)
  - southwest (sw)
  - northwest (nw)
  - help (h)
  - debug
  - quit (q)
  - restart (r)
- A button to select a map to load

## Non-Functional Requirements
- Load map information from a file (the file will consist of information specifying a background image for the maze, as well as the `SQL` necessary to create the map database in MySQL server
- Construct an adjacency list for the map (represented as a Graph in memory)
- Process commands from the command prompt
  - When processing an invalid command, instruct the user to enter the `help` command
- Load room descriptions on-the-fly from the database
- Determine whether or not the player has reached the end of the maze on-the-fly from information in the database
- When done using a map, drop the database in MySQL server (the map information is stored in a map file, so it can be easily re-created at any time)

# Map-Maker
## Non-Functional Requirements
- Generate a map file from scratch or load an existing file
- When done creating or modifying the map, save the map file (a map file consists of information that specifies a background image for the maze, as well as containing all of the SQL code necessary to create the database
