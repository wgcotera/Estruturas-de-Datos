package list;

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
    
    public ArrayList(int c) {
        this.elements = (E[]) (new Object[c]);
        this.effectiveSize = 0;
        this.capacity = c;
    }

    //DONE
    private boolean isFull() {
        return effectiveSize == capacity;
    }

    //DONE
    private void addCapacity() {
        E[] tmp = (E[]) (new Object[capacity * 2]);
        for (int i = 0; i < effectiveSize; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }
    
    //DONE
    private void checkIndex(int index) {
        if(index < 0 || index >= effectiveSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
    
    //DONE
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
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
        elements[effectiveSize++] = element;
        return true;
    }

    //DONE
    @Override
    public void add(final int index, E element)  {
        checkIndex(index);
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        effectiveSize++;
    }

    //DONE
    @Override
    public void clear() {
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = null;
        }
        effectiveSize = 0;
    }

    //DONE
    @Override
    public E get(final int index) {
        checkIndex(index);
        return elements[index];
    }

    //DONE
    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }
    
    //DONE
    @Override
    public E removeFirst() {
        checkIndex(0);
        E firstElement = elements[0];
        for (int i = 0 ; i < effectiveSize ; i++) {
            elements[i] = elements[i+1];
        }
        effectiveSize--;
        return firstElement;
    }
    
    //DONE
    @Override
    public E removeLast() {
        checkIndex(effectiveSize-1);
        E lastElement = elements[effectiveSize-1];
        elements[effectiveSize-1] = null;
        effectiveSize--;
        return lastElement;
    }

    //DONE
    @Override
    public E remove(final int index) {
        checkIndex(index);
        E removedElement = elements[index];
        for (int i = index ; i < effectiveSize - 1 ; i++) {
            elements[i] = elements[i + 1];
        }
        effectiveSize--;
        return removedElement;
    }

    //DONE
    @Override
    public E set(final int index, E element) {
        checkIndex(index);
        elements[index] = element;
        return element;
    }

    //DONE
    @Override
    public int size() {
        return effectiveSize;
    }

    //DONE
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < effectiveSize; i++) {
            s += elements[i].toString() + " ";
        }
        return s;
    }
    
        //HECHO SIN USAR CASTING
    public boolean keepOnly(final int from, final int to) {
        if (from > 0 && to > 0 && from <= to && to <= effectiveSize) {
            final int diff = to - from;
            for (int i = 0, p = from ; i <= diff ; i++, p++ ) {
                elements[i] = elements[p-1];
            }
            for (int i = diff + 1 ; i < effectiveSize ; i++) {
                elements[i] = null;
            }
            effectiveSize = diff + 1;
            return true;
        }
        return false;
    }

    //HECHO USANDO CASTING
//    public boolean keepOnly(int from, int to) {
//        E[] tmp = (E[]) (new Object[capacity]);
//        if (from <= effectiveSize && from > 0 && to <= effectiveSize && to > 0 && from <= to) {
//            int j = 0;
//            for (int i = (from - 1); i == to - 1; i--, j++) {
//                tmp[j++] = elements[i];
//            }
//            elements = tmp;
//            effectiveSize = j;
//            return true;
//        }
//        return false;
//    }
}
