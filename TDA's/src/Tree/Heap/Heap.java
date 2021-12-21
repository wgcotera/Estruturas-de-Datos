package Tree.Heap;

import java.util.Comparator;

public class Heap<E extends Comparable<E>> {

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    public static int DEFAULT_CAPACITY = 10;

    private int capacity;

    private int size;

    private boolean isMax;

    private E[] queue;

    private Comparator<E> comparator;

    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public Heap(Comparator<E> comparator, int capacity, boolean isMax) {
        this.capacity = capacity;
        this.size = 0;
        this.isMax = isMax;
        this.queue = (E[]) new Comparable[this.capacity];
        this.comparator = isMax ? comparator : comparator.reversed();
    }

    public Heap(Comparator<E> comparator) {
        this(comparator, DEFAULT_CAPACITY, false);
    }

    public Heap(int capacity, boolean isMax) {
        this(Comparator.naturalOrder(), DEFAULT_CAPACITY, isMax);
    }

    public Heap(Comparator<E> comparator, boolean isMax) {
        this(comparator, DEFAULT_CAPACITY, isMax);
    }

    public Heap() {
        this(Comparator.naturalOrder(), DEFAULT_CAPACITY, false);
    }


    /* *********************************************************************
     * Public Methods
     ******************************************************************** */

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        if(this.isEmpty()) {
            return;
        }
        for(int i = 0; i < this.size ; i++) {
            queue[i] = null;
        }
        this.size = 0;
    }

    public Heap<E> offer(E element) {
        if(element != null) {
            if(this.isFull()) {
                this.grow();
            }
            queue[this.size++] = element;
            fixUpward();
        }
        return this;
    }

    public E peek() {
        if(this.isEmpty()) {
            return null;
        }
        return queue[0];
    }

    public E poll() {
        if(this.isEmpty()) {
            return null;
        }

        E topValue = queue[0];
        swap(0, --size);
        fixDownward(0);

        return topValue;
    }


    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

    private boolean isFull() {
        return this.capacity == this.size;
    }

    private boolean isLeaf(int index) {
        return index > (this.size >> 1) - 1;
    }

    private boolean checkRange(int index) {
        return index >= 0 && index < size;
    }

    private int getLeftIndex(int index) {
        return (index << 1) + 1;
    }

    private int getRightIndex(int index) {
        return (index << 1) +2;
    }

    private int getParentIndex(int index) {
        return (index - 1) >> 2;
    }

    private void grow() {
        int newCap = this.size + (this.size >> 1);
        E[] newQueue = (E[]) new Comparable[newCap];
        for(int i = 0 ; i < this.size ; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    private int compare(int index1, int index2) {
        return comparator.compare(queue[index1], queue[index2]);
    }

    private void swap(int index1, int index2) {
        E tmpElement = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = tmpElement;
    }

    private void fixDownward(int index) {

        if (isLeaf(index)) {
            return;
        }

        int topIndex = index;
        int leftIndex = getLeftIndex(index);
        int rightIndex = getRightIndex(index);

        if(checkRange(leftIndex) && this.compare(leftIndex, topIndex) > 0) {
            topIndex = leftIndex;
        }

        if(checkRange(rightIndex) && this.compare(rightIndex, topIndex) > 0) {
            topIndex = rightIndex;
        }

        if(topIndex != index) {
            swap(topIndex, index);
            fixDownward(topIndex);
        }
    }

    private void fixUpward() {
        for (int i = (this.size >> 1) - 1; i >= 0 ; --i) {
            fixDownward(i);
        }
    }
}
