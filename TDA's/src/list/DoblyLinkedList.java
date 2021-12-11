package list;

import java.util.Iterator;

/**
 *
 * @author wgcotera
 */
public class DoblyLinkedList<E> implements List<E> {
    
    private DoblyNodeList<E> header;
    private DoblyNodeList<E> last;
    
    public DoblyLinkedList() {
        this.header = null;
        this.last = null;      
    }    
    
    private void checkIndex(final int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    //DONE
    @Override
    public boolean addFirst(E e) {
        if (e != null) {
            DoblyNodeList<E> newNode = new DoblyNodeList(e);
            newNode.setNext(this.header);
            this.header.setPrevious(newNode);
            this.header = newNode;
            return true;
        }
        return false;
    }

    @Override
    public boolean addLast(E e) {
        if(e != null) {
            DoblyNodeList<E> newNode = new DoblyNodeList(e);
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.last = newNode;
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, E e) {
        this.checkIndex(index);
        if (e != null) {
            DoblyNodeList<E> newNode = new DoblyNodeList(e);
            DoblyNodeList<E> n = this.header;
            
            for (int i = 0 ; i != index ; i++) {
                n = n.getNext();
            }
            newNode.setNext(n.getNext());
            newNode.setPrevious(n);
            n.getNext().setPrevious(newNode);
            n.setNext(newNode);           
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return this.header == null && this.last == null;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int index, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keepOnly(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reverse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertAt(int index, List<E> collection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean isReverse(DoblyLinkedList<E> list) {

        DoblyNodeList<E> ptrStart = this.header;
        DoblyNodeList<E> ptrEnd = this.last;

        E firstElem;
        E lastElem;

        if(this.size() != this.size()) {
            return false;
        }

        while(!list.isEmpty()) {
            firstElem = list.removeFirst();
            lastElem = list.isEmpty() ? firstElem : list.removeLast();

            if (ptrStart.getContent().equals(lastElem) && ptrEnd.getContent().equals(firstElem)) {
                ptrStart = ptrStart.getNext();
                ptrEnd = ptrEnd.getPrevious();
            } else {
                return false;
            }
        }
        return true;
    }


}
