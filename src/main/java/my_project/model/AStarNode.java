package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;

/**
 * A node for handling the path finding inside the A*-Algorithm.
 */
public class AStarNode<ContentType> implements ComparableContent<AStarNode> {
    private double distance;
    private double heuristic;
    private double cost;
    private AStarNode parent;
    private Vertex graphNode;

    public AStarNode(double distance, double heuristic, AStarNode parent, Vertex<ContentType> pGraphNode){
        this.distance = distance;
        this.heuristic = heuristic;
        this.parent = parent;
        this.cost = this.distance + this.heuristic;
        this.graphNode = pGraphNode;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        this.cost = this.distance + this.heuristic;
    }

    public void setParent(AStarNode parent) {
        this.parent = parent;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
        this.cost = this.distance + this.heuristic;
    }

    public double getCost() {
        return cost;
    }
    public Vertex getVertex(){return graphNode;}
    public double getDistance(){return distance;}
    public AStarNode getParent(){return parent;}

    @Override
    public boolean isGreater(AStarNode otherNode){return cost > otherNode.cost;}

    @Override
    public boolean isLess(AStarNode otherNode){return cost < otherNode.cost;}

    @Override
    public boolean isEqual(AStarNode otherNode){return cost == otherNode.cost;}
}
