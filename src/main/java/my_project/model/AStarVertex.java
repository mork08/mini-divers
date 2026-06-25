package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;

/**
 * A node for handling the path finding inside the A*-Algorithm.
 */
public class AStarVertex<ContentType> extends Vertex<ContentType> implements ComparableContent<AStarVertex> {
    private double distance;
    private double heuristic;
    private double cost;
    private AStarVertex parent;
    private AStarVertex<ContentType> prev;

    public AStarVertex(String pID){
        super(pID);
    }

    public void setDistance(double distance) {
        this.distance = distance;
        this.cost = this.distance + this.heuristic;
    }

    public void setParent(AStarVertex parent) {
        this.parent = parent;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
        this.cost = this.distance + this.heuristic;
    }

    public double getDistance(){return distance;}
    public AStarVertex getParent(){return parent;}

    public void resetVertex(){
        distance = 0.00;
        heuristic = 0.00;
        cost = 0.00;
        parent = null;
    }

    public void setPrev(AStarVertex<ContentType> pPrev){prev = pPrev;}

    public AStarVertex<ContentType> getPrev(){return prev;}

    @Override
    public boolean isGreater(AStarVertex otherNode){return cost > otherNode.cost;}

    @Override
    public boolean isLess(AStarVertex otherNode){return cost < otherNode.cost;}

    @Override
    public boolean isEqual(AStarVertex otherNode){return cost == otherNode.cost;}
}
