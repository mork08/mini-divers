package davStructures;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import beckerStructures.BeckerList;
import my_project.model.AStarNode;

public class DavHeap <CT extends ComparableContent<CT>> {
    private boolean isMin;
    private BeckerList<AStarNode> heap;

    public DavHeap(boolean isMin) {
        this.isMin = isMin;
        this.heap = new BeckerList<>();
    }

    // For understanding a pissible implementation:
    // https://www.geeksforgeeks.org/java/heap-implementation-in-java/

}
