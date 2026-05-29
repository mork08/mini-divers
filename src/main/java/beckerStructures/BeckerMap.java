package beckerStructures;

import KAGO_framework.model.abitur.datenstrukturen.BinarySearchTree;
import KAGO_framework.model.abitur.datenstrukturen.ComparableContent;

public class BeckerMap<KT extends Comparable, RT> {
    private BinarySearchTree<MapNode> nodes;

    public BeckerMap(){
        nodes = new BinarySearchTree<>();
    }
    public RT get(KT key){
        BinarySearchTree<MapNode> current = nodes;
        boolean hasEnded = false;
        while (!current.isEmpty()){
            switch (current.getContent().key.compareTo(key)) {
                case -1: current = current.getLeftTree();
                case 0: return current.getContent().getValue();
                case 1: current = current.getRightTree();
            }
        }
        return null;
    }
    public void add(KT key, RT value){
        nodes.insert(new MapNode(key, value));
    };

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

        @Override
        public boolean isGreater(MapNode pContent) {
            return key.compareTo(pContent.key) == 1;
        }

        @Override
        public boolean isEqual(MapNode pContent) {
            return key.compareTo(pContent.key) == 0;
        }

        @Override
        public boolean isLess(MapNode pContent) {
            return key.compareTo(pContent.key) == -1;
        }
    }
}
