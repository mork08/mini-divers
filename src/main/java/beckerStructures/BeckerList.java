package beckerStructures;

import java.util.function.Consumer;

/**

 * @author Joshua Becker
 */
public class BeckerList<ContentType> {
    private ContentType[] array;
    private int currentIndex;
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
        return array.length;
    }
    public void append(ContentType content){
        for (int i = 0; i < array.length; i++){
            if (array[i] == null){
                array[i] = content;
                return;
            }
        }
        expand(array.length + 1);
        array[array.length-1] = content;
    }

    public boolean isInside(ContentType content){
        for (int i = 0; i < getLength(); i++){
            if (array[i] == content) return true;
        }
        return false;
    }
}
