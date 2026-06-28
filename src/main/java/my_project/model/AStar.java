package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import beckerStructures.BeckerList;
import davStructures.DavHeap;
import my_project.model.modes.galaxyMap.GalaxyMapPlanet;

import javax.net.ssl.SSLEngineResult;

/**
 * The AStar algorithm finds the shortest path in a Graph from the startNode to the endNode <br>
 * For reasons of efficiency and clarity the algorithm uses the vertices from the graph for information storage. <br><br>
 * WARNING: The given graph needs to explicitly store Objects of the type AStarVertex and no other type of Vertex.
 *
 * @param <CT> the content type of the objects inside the graph
 * @author David Glusmann
 */
public class AStar <CT extends GraphicalObject>{
    private final Graph<CT, AStarVertex<CT>> graph;
    private final AStarVertex<CT> startNode;
    private final AStarVertex<CT> endNode;

    public AStar(Graph<CT, AStarVertex<CT>> graph, AStarVertex<CT> start, AStarVertex<CT> end){
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
    public List<AStarVertex<CT>> findPath(){
        resetVertices();
        DavHeap<AStarVertex<CT>> openNodes = new DavHeap<>(true);
        startNode.setStatus(AStarVertex.Status.OPEN);
        openNodes.add(startNode);
        //BeckerList<AStarVertex> closedNodes = new BeckerList<>();

        while (!openNodes.isEmpty()){
            AStarVertex current = openNodes.extractRoot(); // Node gets deleted out of openNodes as soon as it gets examined.
            if (current == this.endNode) return reconstructPathFrom(current);

            //closedNodes.append(current);
            current.setStatus(AStarVertex.Status.CLOSED);

            List<AStarVertex<CT>> neighbours = graph.getNeighbours(current);
            neighbours.toFirst();
            while (neighbours.hasAccess()){
                AStarVertex<CT> nbr = neighbours.getContent();
                double weight = graph.getEdge(current,nbr).getWeight();

                // if (closedNodes.contains(nbr)) continue; // Skips closed neighbour
                // if (nbr.getStatus() == AStarVertex.Status.CLOSED) continue; // Skips closed neighbour

                if (!(nbr.getStatus() == AStarVertex.Status.CLOSED)){ // check status & skip node if closed
                    if (nbr.getContent() instanceof GalaxyMapPlanet){
                        if (((GalaxyMapPlanet)nbr.getContent()).getOccupation().equals("MiniEarth") || (nbr == endNode && ((GalaxyMapPlanet)current.getContent()).getOccupation().equals("MiniEarth"))){

                            if (nbr.getStatus() == AStarVertex.Status.OPEN) {
                                double tentativeCost = current.getDistance() + weight;
                                if (tentativeCost < nbr.getDistance()) {
                                    nbr.setDistance(current.getDistance() + weight);
                                    nbr.setParent(current);
                                    openNodes.updatePosition(neighbours.getContent(), true);
                                }
                            } else { // if this is reached, nbr is unknown
                                nbr.setDistance(current.getDistance() + weight);
                                nbr.setHeuristic(nbr.getContent().getDistanceTo(endNode.getContent()));
                                nbr.setParent(current);
                                nbr.setStatus(AStarVertex.Status.OPEN);
                                openNodes.add(nbr);
                            }
                        }
                    }
                }
                neighbours.next();
            }
        }

        return reconstructPathFrom(null); // return empty if path not found
    }

    public void resetVertices(){
        List<AStarVertex<CT>> vertices = graph.getVertices();
        vertices.toFirst();
        while (vertices.hasAccess()){
            vertices.getContent().resetVertex();
            vertices.next();
        }
    }

    /**
     * Recunstructs the path beginning from the end node.  If the endNode wasn't found, an empty list is returned.
     * @param current the node te reconstruction is initialized from
     * @return the path as a KAGO-List
     */
    private List<AStarVertex<CT>> reconstructPathFrom(AStarVertex<CT> current){
        if (current == null || current != endNode) return new List<>();

        List<AStarVertex<CT>> list = new List<>();

        Stack<AStarVertex> stack = new Stack<>();
        while (current != null){
            stack.push(current);
            current = current.getParent();
        }

        list = new List<>();
        while (!stack.isEmpty()){
            list.append(stack.top());
            stack.pop();
        }

        return list;
    }

    /**
     * Method for path finding on the given graph.
     * @return the shortest path from the start vertex to the end vertex as an Array of AStarVertices.
     */
    public AStarVertex<CT>[] findPathAsArray(){
        return (listToArray(findPath()));
    }

    /**
     * Changes the path data from a líst into an array.
     * @param list The list, e.g. returned by findPath()
     * @return the data now in an array
     */
    public AStarVertex<CT>[] listToArray(List<AStarVertex<CT>> list){
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
}
