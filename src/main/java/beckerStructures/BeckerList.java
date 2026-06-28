package beckerStructures;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**

 * @author Joshua Becker
 */
public class BeckerList<ContentType> {
    private ContentType[] array;
    private int currentIndex;
    private int length;

    public BeckerList(){
        this(16);
    }

    public BeckerList(int capacity) {
        this.currentIndex = 0;
        this.length = 0;
        this.array = (ContentType[]) new Object[capacity];
    }

    public ContentType getContent(){
        return get(this.currentIndex);
    }

    public ContentType get(int i){
        this.currentIndex = i;
        return this.array[i];
    }

    public ContentType get(Predicate<ContentType> predicate){
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && predicate.test(this.array[i])) {
                this.currentIndex = i;
                return this.array[i];
            }
        }
        return this.array[this.currentIndex];
    }

    public BeckerList<ContentType> getMany(Predicate<ContentType> predicate){
        BeckerList<ContentType> result = new BeckerList();
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && predicate.test(this.array[i])) {
                // currentIndex wird nicht verändert
                result.append(this.array[i]);
            }
        }
        return result;
    }

    public void set(int i, ContentType content){
        if (i >= 0 && i < this.array.length){
            this.currentIndex = i;

            if (this.array[i] == null && content != null) {
                this.length++;
            } else if (this.array[i] != null && content == null) {
                this.length--;
            }

            this.array[i] = content;

        } else if (i >= 0) {
            expand(i + 1);
            set(i, content);
        }
    }

    private void expand(int newSize){
        if (newSize <= this.array.length) return;
        ContentType[] newArray = (ContentType[]) new Object[newSize];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }

    public boolean hasAccess(){
        return this.array[this.currentIndex] != null;
    }

    public int getCapacity() {
        return this.array.length;
    }

    public int getLength() {
        return this.length;
    }

    public void append(ContentType content){
        for (int i = 0; i < this.array.length; i++){
            if (this.array[i] == null){
                this.array[i] = content;
                this.length++;
                return;
            }
        }
        expand(this.array.length + 1);
        this.array[this.array.length-1] = content;
        this.length++;
    }

    public ContentType remove() {
        return this.remove(this.currentIndex);
    }

    public ContentType remove(int index) {
        ContentType value = this.array[index];
        this.array[index] = null;
        this.length--;
        return value;
    }

    public ContentType remove(ContentType value) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && this.array[i].equals(value)) {
                ContentType v = this.array[i];
                this.array[i] = null;
                this.length--;
                return v;
            }
        }
        return null;
    }

    public ContentType remove(Predicate<ContentType> predicate) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && predicate.test(this.array[i])) {
                ContentType value = this.array[i];
                this.array[i] = null;
                this.length--;
                return value;
            }
        }
        return null;
    }

    public BeckerList<ContentType> removeAll(Predicate<ContentType> predicate) {
        BeckerList<ContentType> result = new BeckerList();
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null && predicate.test(this.array[i])) {
                ContentType value = this.array[i];
                this.array[i] = null;
                this.length--;
                result.append(value);
            }
        }
        return result;
    }

    public void forEach(BiConsumer<ContentType, Integer> consumer) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                consumer.accept(this.array[i], i);
            }
        }
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = null;
        }
        this.currentIndex = 0;
        this.length = 0;
    }

    public BeckerList<ContentType> reverse() {
        BeckerList<ContentType> result = new BeckerList<>(array.length);

        for (int i = 0; i < array.length; i++) {
            result.set(i, array[array.length - 1 - i]);
        }
        return result;
    }

    public BeckerList<ContentType> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > this.array.length || fromIndex > toIndex) {
            System.err.println(new IndexOutOfBoundsException(
                    "fromIndex: " + fromIndex + ", toIndex: " + toIndex
            ));
            return new BeckerList<>(toIndex - fromIndex);
        }

        BeckerList<ContentType> result = new BeckerList<>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            result.set(i - fromIndex, this.array[i]);
        }

        return result;
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
}
