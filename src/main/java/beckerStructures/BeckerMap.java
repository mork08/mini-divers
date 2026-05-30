package beckerStructures;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;

public class BeckerMap<KT extends Comparable, RT> {
    private BinarySearchTree<MapNode> nodes;

    public BeckerMap(){
        nodes = new BinarySearchTree<>();
    }
    public RT get(KT key){
        if (key == null) return null;
        BinarySearchTree<MapNode> current = nodes;
        //System.out.println("Searching " + key);
        while (!current.isEmpty()){
            /* DEBUGGING
                System.out.println( " - Current: " + current.getContent().getKey());
                if (current.getLeftTree() !=  null && current.getLeftTree().getContent() != null){
                    System.out.println( " - Left: " + current.getLeftTree().getContent().getKey());
                } else {
                    System.out.println( " - Left: null");
                }
                if (current.getRightTree() !=  null && current.getRightTree().getContent() != null){
                    System.out.println( " - Right: " + current.getRightTree().getContent().getKey());
                } else {
                    System.out.println( " - Right: null");
                }
            */
            int cmp = current.getContent().key.compareTo(key);
            if (cmp > 0) {
                //System.out.println(current.getContent().getKey() + " > " + key + "; continue left");
                current = current.getLeftTree();
            }
            else if (cmp == 0) {
                //System.out.println(current.getContent().getKey() + " == " + key + "; TERMINATE");
                return current.getContent().getValue();
            }
            else if (cmp < 0) {
                //System.out.println(current.getContent().getKey() + " < " + key + "; continue right");
                current = current.getRightTree();
            }
        }
        //System.out.println( key + " not found; TERMINATE");
        return null;
    }
    public void add(KT key, RT value){
        //System.out.println("Adding key value pair: " + key + " -> " + value);
        nodes.insert(new MapNode(key, value));
    };

    public boolean contains(KT currentMode) {
        return get(currentMode) != null;
    }

    private class MapNode implements ComparableContent<MapNode> {
        KT key;
        RT value;
        public MapNode(KT key, RT value){
            this.value = value;
            this.key = key;
        }
        public boolean compareKey(KT key){
            return key.equals(this.key);
        }
        public RT getValue(){
            return value;
        }

        public KT getKey(){
            return key;
        }

        @Override
        public boolean isGreater(MapNode pContent) {
            return key.compareTo(pContent.key) > 0;
        }

        @Override
        public boolean isEqual(MapNode pContent) {
            return key.compareTo(pContent.key) == 0;
        }

        @Override
        public boolean isLess(MapNode pContent) {
            return key.compareTo(pContent.key) < 0;
        }
    }
}
