package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import beckerStructures.BeckerList;
import davStructures.DavHeap;

/**
 * The AStar algorithm finds the shortest path in a Graph from the startNode to the endNode <br>
 * For reasons of efficiency and clarity the algorithm uses the vertices from the graph for information storage. <br><br>
 * WARNING: The given graph needs to explicitly store Objects of the type AStarVertex and no other type of Vertex.
 *
 * @param <ContentType> the contentType of the Objects inside the graph
 */
public class AStar <ContentType extends GraphicalObject>{
    private final Graph<ContentType, AStarVertex<ContentType>> graph;
    private final AStarVertex<ContentType> startNode;
    private final AStarVertex<ContentType> endNode;

    public AStar(Graph<ContentType, AStarVertex<ContentType>> graph, AStarVertex<ContentType> start, AStarVertex<ContentType> end){
        this.graph = graph;

        this.startNode = start;
        startNode.setDistance(0);
        startNode.setHeuristic(start.getContent().getDistanceTo(end.getContent()));
        startNode.setParent(null);

        this.endNode = end;
        endNode.setDistance(Double.MAX_VALUE);
        endNode.setHeuristic(0);
        endNode.setParent(null);
    }

    /**
     * Method for path finding on the given graph. <br><br>
     *
     * An open node is a discovered node which is still to be evaluated regarding the path.<br>
     * A closed node is fully evaluated.<br>
     * An unknown node is a vertex from the graph which is newly discovered during the algorithms runtime and becomes an open one.
     * @return the shortest path from the start vertex to the end vertex as a KAGO-List with AStarVertices.
     */
    public List<AStarVertex> findPath(){


        DavHeap<AStarVertex<ContentType>> openNodes = new DavHeap<>(true);
        openNodes.add(startNode);
        BeckerList<AStarVertex> closedNodes = new BeckerList<>();

        while (!openNodes.isEmpty()){
            AStarVertex current = openNodes.extractRoot(); // Node gets deleted out of openNodes as soon as it gets examined.
            if (current == this.endNode) return reconstructPathFrom(current);

            closedNodes.append(current);
            current.setMark(false);

            List<AStarVertex<ContentType>> neighbours = graph.getNeighbours(current);
            neighbours.toFirst();
            while (neighbours.hasAccess()){
                AStarVertex<ContentType> nbr = neighbours.getContent();

                // check if nbr is unknown or closed
                ContentType currentObject = nbr.getContent();
                ContentType nbrObject = endNode.getContent();
                double weight = graph.getEdge(current,nbr).getWeight();

                if (!nbr.isMarked()){ // TODO lieber isMarked für closed, um nicht immer durchsuchen zu müssen?
                    if (closedNodes.contains(nbr)) continue; // Skips closed neighbour

                    // if this is reached, nbr is unknown
                    openNodes.add(nbr);
                    nbr.setDistance(current.getDistance() + weight);
                    nbr.setHeuristic(currentObject.getDistanceTo(nbrObject));
                    nbr.setParent(current);
                    nbr.setMark(true);

                } else { // Node to this neighbour vertex is in openNodes

                    double tentativeCost = current.getDistance() + weight;
                    if (tentativeCost < nbr.getDistance()){
                        nbr.setParent(current);
                        nbr.setDistance(current.getDistance() + weight);
                        nbr.setHeuristic(nbr.getContent().getDistanceTo(endNode.getContent()));
                        openNodes.updatePosition(nbr, true);
                    }
                }

                neighbours.next();
            }
        }

        return reconstructPathFrom(null); // return empty if path not found
    }

    /**
     * Changes the path data from a líst into an array.
     * @param list The list, e.g. returned by findPath()
     * @return the data now in an array
     */
    public AStarVertex[] listToArray(List<AStarVertex> list){
        if (list == null || list.isEmpty()) return new AStarVertex[0];

        int counter = 0;
        list.toFirst();
        while (list.hasAccess()){
            counter++;
            list.next();
        }

        AStarVertex[] path = new AStarVertex[counter];
        list.toFirst();
        for (int i = 0; i < counter; i++){
            path[i] = list.getContent();
            list.next();
        }

        return path;
    }

    /**
     * Recunstructs the path beginning from the end node.  If the endNode wasn't found, an empty list is returned.
     * @param current the node te reconstruction is initialized from
     * @return the path as a KAGO-List
     */
    private List<AStarVertex> reconstructPathFrom(AStarVertex current){
        if (current == null ) return new List<>();
        if (current != endNode) throw new IllegalArgumentException("Path reconstruction needs to start with endNode");

        List<AStarVertex> list = new List<>();

        while (current != null){
            list.append(current);
            current = current.getParent();
        }

        Stack<AStarVertex> stack = new Stack<>();
        list.toFirst();
        while (list.hasAccess()){
            stack.push(list.getContent());
            list.next();
        }

        list = new List<>();
        while (!stack.isEmpty()){
            list.append(stack.top());
            stack.pop();
        }

        return list;
    }


    public void resetVertices(){
        List<AStarVertex<ContentType>> vertices = graph.getVertices();

    }

}

/*
        while (!openList.isEmpty()){
            // Node with least cost is going to be examined, is the new current
            openList.toFirst();
            PathNode current = openList.getContent();

            // If node is goalNode, return found path
            if (current.getTile() == goal) {
                System.out.println("Path updated");
                return reconstructPath(current);
            }

            closedList.append(current);
            openList.remove();

      private Stack<Tile> findPath(){
        List<PathNode> openList = new List<>();
        List<PathNode> closedList = new List<>();

        Tile start = control.getDungeon().getTileFromCoordinates(x,y);
        Tile goal = control.getDungeon().getTileFromCoordinates(control.getDungeonPlayer().getX(), control.getDungeonPlayer().getY());

        PathNode startNode = new PathNode(start, goal);
        openList.append(startNode);
        startNode.setDistance(0);
        startNode.calculateCost();
        startNode.setParent(null);

            Tile[] neighbors = current.getNotSolidNeighboringTiles();
            outer:
            for (Tile neighbor : neighbors){

                // Check if pathNode is already closed, meaning that its evaluated (and its neighbors in openList)
                closedList.toFirst();
                while (closedList.hasAccess()){
                    if (closedList.getContent().getTile() == neighbor) continue outer; // Jumps to next tile bcs this one already in closedList
                    closedList.next();
                }

                double tentativeDistance = current.getDistance() + current.getTile().getDistanceTo(neighbor);

                // Check if pathNode already visited, meaning if inside openList
                openList.toFirst();
                boolean insideOpenList = false;
                while (openList.hasAccess()){
                    if (openList.getContent().getTile() == neighbor) insideOpenList = true;
                    openList.next();
                }

                PathNode neighborNode = new PathNode(neighbor, goal);
                if (!insideOpenList){
                    insertByCost(openList, neighborNode);
                } else if (tentativeDistance >= neighborNode.getDistance()) continue; // This path is not shorter

                // Calculate neighboring pathTiles values or update them, if its parent is set to current bcs then path to this pathTile is shorter
                neighborNode.setDistance(tentativeDistance);
                neighborNode.setParent(current);
                neighborNode.calculateCost();
            }
        }
        return null; // No path found
    }

    */
