package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;

/**
 * A node for handling the path finding inside the A*-Algorithm.
 */
public class AStarNode<ContentType> implements ComparableContent<AStarNode> {
    private double distance;
    private double heuristic;
    private double value;
    private AStarNode prev;
    private Vertex graphNode;

    public AStarNode(double distance, double heuristic, AStarNode prev, Vertex<ContentType> pGraphNode){
        this.distance = distance;
        this.heuristic = heuristic;
        this.prev = prev;
        this.value = this.distance + this.heuristic;
        this.graphNode = pGraphNode;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        this.value = this.distance + this.heuristic;
    }

    public void setPrev(AStarNode prev) {
        this.prev = prev;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
        this.value = this.distance + this.heuristic;
    }

    @Override
    public boolean isGreater(AStarNode otherNode){return value > otherNode.value;}

    @Override
    public boolean isLess(AStarNode otherNode){return value < otherNode.value;}

    @Override
    public boolean isEqual(AStarNode otherNode){return value == otherNode.value;}
}
