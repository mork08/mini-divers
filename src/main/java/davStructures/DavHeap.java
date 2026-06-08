package davStructures;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import beckerStructures.BeckerList;

public class DavHeap <CT extends ComparableContent<CT>> {
    private boolean isMin;
    // private BeckerList<HeapNode> nodes;

    public DavHeap(boolean isMin) {
        this.isMin = isMin;
    }

    /*private class AStarNode<CT extends ComparableContent<CT>>{
        private double distance;
        private double heuristic;
        private double value;
        private HeapNode prev;
        private CT content;

        public HeapNode(double distance, double heuristic, HeapNode prev, CT pContent){
            this.distance = distance;
            this.heuristic = heuristic;
            this.prev = prev;
            this.value = this.distance + this.heuristic;
            this.content = pContent;
        }
    }*/





}
