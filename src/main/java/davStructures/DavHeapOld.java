package davStructures;

import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;

/**
 * This class is either a min heap or a max heap created with an Abitur-BST.
 * Use it for fancy Priority Queues.
 * David Glusmann
 */
public class DavHeapOld<CT extends ComparableContent<CT>> {
    private final boolean isMin;
    private HeapNode<CT> node;

    private class HeapNode<CT extends ComparableContent<CT>>{
        private CT content;
        private DavHeapOld<CT> left, right;

        public HeapNode(boolean isMin, CT pContent){
            this.content = pContent;
            left = new DavHeapOld<CT>(isMin);
            right = new DavHeapOld<CT>(isMin);
        }
    }

    /**
     * Creates an empty heap.
     * @param isMin true if it's a min heap, otherwise it's a max heap
     */
    public DavHeapOld(boolean isMin){
        this.isMin = isMin;
        node = null;
    }

    /**
     * Creates a heap with a CT-content and no children.
     * @param isMin true if it's a min heap, otherwise it's a max heap
     * @param pContent the content
     */
    public DavHeapOld(boolean isMin, CT pContent){
        this.isMin = isMin;
        node = new HeapNode<>(isMin, pContent);
    }

    public boolean isEmpty() {
        return this.node == null;
    }

    public CT getFirst(){
        return node.content;
    }

    public void add(CT c){
        if (c == null) return;
        if (isEmpty()) node = new HeapNode<>(isMin, c);

        if (isMin){

        } else {

        }
    }

    /*public void insert(ContentType pContent) {
        if (pContent != null) {
            if (isEmpty()) {
                this.node = new BinarySearchTree.BSTNode<ContentType>(pContent);
            } else if (pContent.isLess(this.node.content)) {
                this.node.left.insert(pContent);
            } else if(pContent.isGreater(this.node.content)) {
                this.node.right.insert(pContent);
            }
        }
    }*/

    /*
    2. Wie findet man den nächsten freien Platz?

    Das hängt komplett davon ab, wie du deinen Baum im Speicher aufbaust:

    Variante A:
    Verwendung eines Arrays / einer Liste (Der Standardweg)
    Wenn du den Heap in einem Array speicherst, ist das Finden des nächsten freien Platzes extrem einfach:
    Es ist immer der nächste freie Index am Ende des Arrays (array.length bzw. list.size()).
    Durch die mathematische Struktur des Heaps kannst du die Positionen im Array ohne Zeiger (Pointer) berechnen.
    Für einen Knoten am Index i gilt:
    Linkes Kind: 2 * i + 1
    Rechtes Kind: 2 * i + 2
    Elternknoten: (i - 1) / 2 (Integer-Division)
    Das Array füllt sich automatisch perfekt von links nach rechts.

    Variante B:
    Verwendung von echten Knoten-Objekten (mit left und right Pointern)
    Wenn du den Baum mit echten Objekten und Zeigern programmierst, ist es schwieriger, die nächste freie Lücke von oben nach unten zu finden.
    Es gibt dafür zwei gängige mathematische Tricks:

    Trick 1: Der Binär-Pfad (Über die Elementanzahl)
    Die Gesamtzahl der Elemente nach dem Einfügen verrät dir den exakten Weg von der Wurzel zur neuen Lücke.
    Nimm die neue Anzahl der Elemente (z. B. 6 Elemente im Baum).
    Wandle diese Zahl in Binärcode um: \(6 = 110_2\).
    Ignoriere die erste 1 (sie steht für die Wurzel).
    Lies die restlichen Bits von links nach rechts:
    1 bedeutet Gehe nach rechts, 0 bedeutet Gehe nach links.
    Pfad für das 6. Element: Erst rechts, dann links.
    Dort ist dein freier Platz.

    Trick 2: Level-Order-Traversal (Breitensuche)
    Du startest an der Wurzel und nutzt eine Warteschlange (Queue), um den Baum Ebene für Ebene von links nach rechts zu prüfen.
    Der erste Knoten, der kein linkes oder kein rechtes Kind hat, ist der Elternknoten für deinen neuen Platz.
    Dieser Weg ist allerdings langsamer (\(O(n)\)).
     */


}
