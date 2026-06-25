package beckerStructures;

import java.util.function.Consumer;

/**

 * @author Joshua Becker
 */
public class BeckerList<ContentType> {
    private ContentType[] array;
    private int currentIndex;
    int size = 0;
    public BeckerList(){
        currentIndex = 0;
        array = (ContentType[]) new Object[16];
    }
    public ContentType getContent(){
        return get(currentIndex);
    }
    public ContentType get(int i){
        currentIndex = i;
        return array[i];
    }
    public void set(int i, ContentType content){
        if (i >= 0 && i < array.length){
            currentIndex = i;
            array[i] = content;
        } else if (i>=0) {
            expand(i);
            set(i, content);
        }
    }
    private void expand(int newSize){
        ContentType[] newArray = (ContentType[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    public boolean hasAccess(){
        return array[currentIndex] != null;
    }
    public int getLength(){
        return size;
    }
    public void append(ContentType content){
        for (int i = 0; i < array.length; i++){
            if (array[i] == null){
                array[i] = content;
                size++;
                return;
            }
        }
        expand(array.length + 1);
        array[array.length-1] = content;
    }

    public boolean contains(ContentType content){
        for (int i = 0; i < getLength(); i++){
            if (array[i] == content) return true;
        }
        return false;
    }
    public int getIndex(ContentType content){
        for (int i = 0; i < getLength(); i++){
            if (array[i] == content) return i;
        }
        return -1;
    }

    public void delete(int index) {// Prüfen, ob der Index im gültigen Bereich liegt
        if (index >= 0 && index < array.length) {// Alle Elemente ab dem gelöschten Index um eins nach links verschieben
            for (int i = index; i < array.length - 1; i++) {
                array[i] = array[i + 1];}// Den nun doppelten letzten Eintrag auf null setzen
            array[array.length - 1] = null;
            // Optional: Den currentIndex anpassen, falls er aus dem Rahmen fällt
            if (currentIndex >= array.length || array[currentIndex] == null) {
                currentIndex = Math.max(0, index - 1);
            }
            size--;
        }
    }

    public void delete(ContentType content) {
        int index = getIndex(content);
        if (index != -1) {
            delete(index);
        }
    }
}
