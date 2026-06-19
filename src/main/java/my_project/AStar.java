package my_project;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import beckerStructures.BeckerList;
import davStructures.DavHeap;
import my_project.model.AStarNode;
import my_project.model.modes.map.Planet;

/**
 * The AStar algorithm finds the shortest path in a Graph from the startNode to the endNode;
 * @param <ContentType>
 */
public class AStar <ContentType extends GraphicalObject>{
    private Graph<ContentType> graph;
    private AStarNode<ContentType> startNode;
    private AStarNode<ContentType> endNode;

    public AStar(Graph<ContentType> graph, Vertex<ContentType> start, Vertex<ContentType> end){
        this.graph = graph;
        double heuristic = start.getContent().getDistanceTo(end.getContent());
        this.startNode = new AStarNode(0, heuristic, null, start);
        this.endNode = new AStarNode(Double.MAX_VALUE, 0, null, end);
    }

    private Vertex[] findPath(){
        DavHeap<AStarNode<ContentType>> openNodes = new DavHeap(true);
        openNodes.add(startNode);
        BeckerList<AStarNode> closedNodes = new BeckerList<>();

        while (!openNodes.isEmpty()){
            AStarNode current = openNodes.extractRoot(); // Mit Bearbeitung des Nodes wird es automatisch aus openNodes entfernt

            if (current == this.endNode) return reconstructPathFrom(current);

            closedNodes.append(current);

        }

        return reconstructPathFrom(null); // return leer?
    }

    public Vertex[] reconstructPathFrom(AStarNode current){
        return new Vertex[0];
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

    */

    /*
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

}
