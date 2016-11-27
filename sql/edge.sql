/* add an edge to the database */
INSERT INTO /* map name */.Edges (EdgeID, FirstNode, SecondNode, EdgeType)
VALUES (/* the edge id */, /* the first room ID */, /* the second room ID */, /* 0...9 */);

/* delete an edge from the database */
DELETE FROM /* map name */.Edges WHERE EdgeID='/* the edge id */';

/* update an edge with new information */
UPDATE /* map name */.Edges SET
FirstNode='/* new id */',
SecondNode='/* new id */',
EdgeType='/* new type */',
WHERE EdgeID='/* the edge id */';

/* search for an edge */
SELECT EdgeID, FirstNode, SecondNode, EdgeType FROM /* map name */.Edges
WHERE EdgeID='/* the edge id */';
