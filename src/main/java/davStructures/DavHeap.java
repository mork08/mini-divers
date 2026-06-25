package davStructures;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;
import beckerStructures.BeckerList;

/**
 * Ein Heap ordnet Elemente in einem Binärbaum nach ihrer Größe.
 * Die Wurzel ist das kleinste (min heap) oder das größte Element (max heap). <br>
 * Gespeichert wird in einem dynamischen Array, weil der Baum lückenlos ist.
 * Referenzen auf Kinder bleiben so erspart.
 * @param <CT> Der Datentyp der gespeicherten Objekte, die vergleichbar sein müssen (implements ComparableContent)
 */
public class DavHeap <CT extends ComparableContent> {
    private boolean isMin;
    private BeckerList<CT> heap;

    public DavHeap(boolean isMin) {
        this.isMin = isMin;
        this.heap = new BeckerList<>();
    }

    /**
     * @return parents index of node behind index i
     */
    private int parent(int i){
        return (i - 1) / 2;
    }

    /**
     * @return left childs index of node behind index i
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @return right childs index of node behind index i
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Swaps the Objects of the nodes behind indices i and j
     */
    private void swap(int i, int j){
        CT temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Insterts an element according to the min or max heap property
     * @param element
     */
    public void add(CT element){
        // Insert element at the first unfilled position (at the end, as it's a complete tree)
        heap.append(element);
        int currentIndex = heap.getLength() - 1;

        // Bubble up (swap with parent as often as needed) to restore the heap property
        if (isMin){
            while (currentIndex >= 0 && (heap.get(currentIndex).isLess(heap.get(parent(currentIndex))) || heap.get(currentIndex) == null)){
                swap(currentIndex, parent(currentIndex));
                currentIndex = parent(currentIndex);
            }
        } else {
            while (currentIndex >= 0 && (heap.get(currentIndex).isGreater(heap.get(currentIndex))) || heap.get(currentIndex) == null){
                swap(currentIndex, parent(currentIndex));
                currentIndex = parent(currentIndex);
            }
        }
    }

    /**
     * @return the content with the min or max value
     */
    public CT getRoot(){
        if (heap.getLength() == 0) throw new RuntimeException("Heap is empty");
        return heap.get(0);
    }

    /**
     * @return the content with the min or max value and remove it
     */
    public CT extractRoot(){
        if (heap.getLength() == 0) throw new RuntimeException("Heap is empty");

        CT rootElement = heap.get(0);
        CT lastElement = heap.get(heap.getLength() - 1);
        heap.set(heap.getLength()-1, null);

        if (!isEmpty()){
            heap.set(0, lastElement);
            int currentIndex = 0;

            // Bubble down to restore heap property
            if (isMin){ // It's a min heap
                while (true){
                    int leftIndex =  leftChild(currentIndex);
                    int rightIndex =  rightChild(currentIndex);
                    int smallest = currentIndex;

                    // Find the smallest out of current and its right and left child
                    if (heap.get(leftIndex).isLess(heap.get(currentIndex))){
                        smallest = leftIndex;
                    }

                    if (heap.get(rightIndex).isLess(heap.get(currentIndex))){
                        smallest = rightIndex;
                    }

                    if (currentIndex == smallest){ break; } // Heap property is restored because current isn't bigger than its children

                    // If heap property is still unrestored, swap with smaller child
                    swap(smallest, currentIndex);
                    currentIndex = smallest;
                }

            } else { // It's a max heap
                while (true){
                    int leftIndex =  leftChild(currentIndex);
                    int rightIndex =  rightChild(currentIndex);
                    int biggest = currentIndex;

                    // Find the biggest out of current and its right and left child
                    if (heap.get(leftIndex).isGreater(heap.get(currentIndex))){
                        biggest = leftIndex;
                    }

                    if (heap.get(rightIndex).isGreater(heap.get(currentIndex))){
                        biggest = rightIndex;
                    }

                    if (currentIndex == biggest){ break; } // Heap property is restored because current isn't smaller than its children

                    // If heap property is still unrestored, swap with bigger child
                    swap(biggest, currentIndex);
                    currentIndex = biggest;
                }
            }
        }

        return rootElement;
    }

    /**
     * @return whether the heap is empty
     */
    public boolean isEmpty() {
        return heap.getLength() == 0;
    }

    public void updatePosition(CT pObject){
        // Find object inside the heap

        // beckerlist
    }

}
