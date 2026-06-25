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

        bubbleUp(heap.getLength() - 1);
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
        heap.delete(heap.getLength()-1);

        if (!isEmpty()){
            heap.set(0, lastElement);
            bubbleDown(0);
        }

        return rootElement;
    }

    private void bubbleDown(int index){
        int size = heap.getLength();
        if (isMin){ // Min Heap
            while (true){
                int leftIndex =  leftChild(index);
                int rightIndex =  rightChild(index);
                int smallest = index;
                //if(heap.get(smallest) == null) return;
                System.out.println("SMALLEST: " + smallest);
                // NEU: Prüfen ob Kind existiert (Index < size) BEVOR isLess aufgerufen wird!
                if (leftIndex < size && heap.get(leftIndex) != null && heap.get(leftIndex).isLess(heap.get(smallest))){
                    smallest = leftIndex;
                }

                if (rightIndex < size && heap.get(rightIndex) != null && heap.get(rightIndex).isLess(heap.get(smallest))){
                    smallest = rightIndex;
                }

                if (index == smallest){ break; } // Heap property restored

                swap(smallest, index);
                index = smallest;
            }

        } else { // Max Heap
            while (true){
                int leftIndex =  leftChild(index);
                int rightIndex =  rightChild(index);
                int biggest = index;
                System.out.println("BIGGEST: " + biggest);
                // NEU: Prüfen ob Kind existiert
                if (leftIndex < size && heap.get(leftIndex) != null && heap.get(leftIndex).isGreater(heap.get(biggest))){
                    biggest = leftIndex;
                }

                if (rightIndex < size && heap.get(rightIndex) != null && heap.get(rightIndex).isGreater(heap.get(biggest))){
                    biggest = rightIndex;
                }

                if (index == biggest){ break; } // Heap property restored

                swap(biggest, index);
                index = biggest;
            }
        }
    }

/*    private void bubbleDown(int index){
        // Bubble down to restore heap property
        if (isMin){ // It's a min heap
            while (true){
                int leftIndex =  leftChild(index);
                int rightIndex =  rightChild(index);
                int smallest = index;
                if (leftIndex >= heap.getLength()) break;
                if (rightIndex <= heap.getLength()) break;
                //if(heap.get(index) == null) return;
                // Find the smallest out of current and its right and left child
                if (heap.get(leftIndex).isLess(heap.get(index))){
                    smallest = leftIndex;
                }

                if (heap.get(rightIndex).isLess(heap.get(index))){
                    smallest = rightIndex;
                }

                if (index == smallest){ break; } // Heap property is restored because current isn't bigger than its children

                // If heap property is still unrestored, swap with smaller child
                swap(smallest, index);
                index = smallest;
            }

        } else { // It's a max heap
            while (true){
                int leftIndex =  leftChild(index);
                int rightIndex =  rightChild(index);
                int biggest = index;

                // Find the biggest out of current and its right and left child
                if (heap.get(leftIndex).isGreater(heap.get(index))){
                    biggest = leftIndex;
                }

                if (heap.get(rightIndex).isGreater(heap.get(index))){
                    biggest = rightIndex;
                }

                if (index == biggest){ break; } // Heap property is restored because current isn't smaller than its children

                // If heap property is still unrestored, swap with bigger child
                swap(biggest, index);
                index = biggest;
            }
        }
    }*/

    private void bubbleUp(int index){
        // Bubble up (swap with parent as often as needed) to restore the heap property
        if (isMin){
            while (index > 0 && (heap.get(index) == null || heap.get(index).isLess(heap.get(parent(index))))){
                swap(index, parent(index));
                index = parent(index);
            }
        } else {
            while (index > 0 && (heap.get(index) == null || heap.get(index).isGreater(heap.get(index)))){
                swap(index, parent(index));
                index = parent(index);
            }
        }
    }

    /**
     * @return whether the heap is empty
     */
    public boolean isEmpty() {
        return heap.getLength() == 0;
    }

    /**
     * Updates the position of the given object if it gets a new value inside the heap property. If it is not inside the heap nothing happens.
     * @param pObject the object to be updated
     * @param gotSmaller whether the comparable value of the object got smaller or bigger
     */
    public void updatePosition(CT pObject, boolean gotSmaller){
        int index = heap.getIndex(pObject);
        if (index == -1) return;
        if (isMin && gotSmaller || !isMin && !gotSmaller) bubbleUp(index);
        if (isMin && !gotSmaller || !isMin && gotSmaller) bubbleDown(index);
    }

}
