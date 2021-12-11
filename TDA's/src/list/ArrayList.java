package list;

import static java.lang.Math.floor;
import java.util.Iterator;

/**
 *
 * @author wgcotera
 */
public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int capacity = 100;
    private int effectiveSize;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacity]); //El unico casting que se va a realizar en todo el curso
        this.effectiveSize = 0;
    }
    
    //DONE
    private boolean isFull() {
        return this.effectiveSize == this.capacity;
    }

    //DONE
    private void addCapacity() {
        E[] tmp = (E[]) (new Object[this.capacity * 2]);
        for (int i = 0; i < this.effectiveSize; i++) {
            tmp[i] = this.elements[i];
        }
        this.elements = tmp;
        this.capacity = this.capacity * 2;
    }
    
    //DONE
    private void checkIndex(final int index) {
        if(index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException(index);
        }
    }
    
    //DONE
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            this.elements[this.effectiveSize++] = element;
            return true;
        } else if (isFull()) {
            addCapacity();
        }
        for (int i = this.effectiveSize - 1; i >= 0; i--) {
            this.elements[i + 1] = this.elements[i];
        }
        this.elements[0] = element;
        this.effectiveSize++;
        return true;
    }

    //DONE
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isFull()) {
            addCapacity();
        }
        this.elements[this.effectiveSize++] = element;
        return true;
    }

    //DONE
    @Override
    public void add(final int index, E element)  {
        checkIndex(index);
        if (isFull()) {
            addCapacity();
        }
        for (int i = this.effectiveSize - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
        this.elements[index] = element;
        this.effectiveSize++;
    }

    //DONE
    @Override
    public void clear() {
        for (int i = 0; i < this.effectiveSize; i++) {
            this.elements[i] = null;
        }
        this.effectiveSize = 0;
    }

    //DONE
    @Override
    public E get(final int index) {
        checkIndex(index);
        return this.elements[index];
    }

    //DONE
    @Override
    public boolean isEmpty() {
        return this.effectiveSize == 0;
    }
    
    //DONE
    @Override
    public E removeFirst() {
        checkIndex(0);
        E firstElement = this.elements[0];
        for (int i = 0 ; i < this.effectiveSize ; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.effectiveSize--;      
        this.elements[this.effectiveSize] = null;
        return firstElement;
    }
    
    //DONE
    @Override
    public E removeLast() {
        checkIndex(this.effectiveSize-1);
        E lastElement = this.elements[this.effectiveSize-1];
        this.effectiveSize--;
        this.elements[this.effectiveSize] = null;
        return lastElement;
    }

    //DONE
    @Override
    public E remove(final int index) {
        checkIndex(index);
        E removedElement = this.elements[index];
        for (int i = index ; i < this.effectiveSize - 1 ; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.effectiveSize--;
        this.elements[this.effectiveSize] = null;
        return removedElement;
    }

    //DONE
    @Override
    public E set(final int index, E element) {
        checkIndex(index);
        E oldElement = this.elements[index];
        this.elements[index] = element;
        return oldElement;
    }

    //DONE
    @Override
    public int size() {
        return this.effectiveSize;
    }

    //DONE
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.effectiveSize; i++) {
            s += this.elements[i].toString() + " ";
        }
        return s;
    }
    
    // DONE
    @Override
    public List<E> copy() {
        List<E> copy = new ArrayList<>();
        for (int i = 0 ; i < this.effectiveSize ; i++) {
            copy.addLast(this.elements[i]);
        }
        return copy;
    }
    
    //DONE
    @Override
    public void reverse() {
        for (int i = 0, j = this.effectiveSize - 1; i < j ; i++, j--) {
            E tmp = this.elements[j];
            this.elements[j] = this.elements[i];
            this.elements[i] = tmp;
        }
    }
    
    //DONE 
    @Override
    public boolean keepOnly(final int from, final int to) {
        checkIndex(from - 1);
        checkIndex(to-1);
        if ( from <= to ) {
            final int diff = to - from;
            for (int i = 0, p = from ; i <= diff ; i++, p++ ) {
                this.elements[i] = this.elements[p-1];
            }
            for (int i = diff + 1 ; i < this.effectiveSize ; i++) {
                this.elements[i] = null;
            }
            this.effectiveSize = diff + 1;
            return true;
        }
        return false;
    }
    
    //DONE
    @Override
     public void insertAt(int index, List<E> collection) {
         checkIndex(index);
         int sizeCollection = collection.size();
         for(int j = 0 ; j < sizeCollection; j++ ) {
             if (isFull()) {
                 addCapacity();
             }
             E element = collection.get(j);
             for (int k = this.effectiveSize - 1; k >= index; k--) {
                 this.elements[k + 1] = this.elements[k];
             }
             this.elements[index++] = element;
             this.effectiveSize++;
         }
     }

    @Override
    public Iterator<E> iterator() {
            Iterator<E> iterator = new Iterator<E>() {
                int cursor;
                @Override
                public boolean hasNext() {
                    return cursor < effectiveSize;
                }

                @Override
                public E next() {
                    E e =  elements[cursor];
                    cursor++;
                    return e;
                }
            };
            return iterator;
    }
}
