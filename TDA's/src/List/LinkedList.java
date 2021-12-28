package List;

import java.util.Iterator;

/**
 *
 * @author wgcotera
 */
public class LinkedList<E> implements List<E> {
    private NodeList<E> header;
    private NodeList<E> last;

    public LinkedList() {
        this.header = null;
        this.last = null;
    }
   
    //DONE
    private void checkIndex(final int index) {
        if(index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(index);
        }
    }
    
    //DONE
    private NodeList<E> getPrevious(NodeList<E> node) {
        NodeList<E> previous = null;
        NodeList<E> n; //PUNTERO VIAJERO
        
        for (n = this.header ; n != node && n != null ; n = n.getNext()) {
            previous = n;
        }
        return previous;
    }
    
    //DONE
    private NodeList<E> getPrevious(int index) {
        this.checkIndex(index);
        NodeList<E> previous = null;
        NodeList<E> n = this.header; //PUNTERO VIAJERO
        
        for (int i = 0 ; i < index ; i++) {
            previous = n;
            n = n.getNext();         
        }
        return previous;
    }
    
    //DONE
    @Override
    public boolean addFirst(E e) {
        if (e != null) {
            NodeList<E> newNode = new NodeList(e);
            newNode.setNext(this.header);
            this.header = newNode;
            return true;           
        } 
        else {
            return false;
        }
    }

    //DONE
    @Override
    public boolean addLast(E e) {
        NodeList<E> newNode = new NodeList(e);
        if (e != null) {
            if (this.isEmpty()) {
                this.header = this.last = newNode;
                return true;
            } 
            else {
                this.last.setNext(newNode);
                this.last = newNode;
                return true;
            }
        }
        else {
            return false;
        }
    }
    
    //DONE
    @Override
    public void add(final int index, E e) {
        if (e != null) {
            NodeList<E> previous = this.getPrevious(index);
            NodeList<E> newNode = new NodeList(e);        
            newNode.setNext(previous.getNext());
            previous.setNext(newNode);            
        }
    }
        
        //SIN EL METODO getPrevious(index)
//        @Override
//        public void add(final int index, E e) {
//        checkIndex(index);
//        if (e != null) {
//            
//            if (index == 0) {
//                this.addFirst(e);
//            }
//            
//            else {
//                int counter = 0;
//                NodeList<E> newNode = new NodeList(e);
//                NodeList<E> previous = null;
//                NodeList<E> n; //PUNTERO VIAJERO
//                
//                for (n = this.header ; n != null ; n = n.getNext()) {
//                    if (counter == index) {
//                        previous.setNext(newNode);
//                        newNode.setNext(n.getNext());
//                        n.setContent(null);
//                        n.setNext(null);
//                    }
//                    previous = n;
//                }                
//            }
//        }
//    }

    //DONE
    @Override
    public void clear() {
        this.header = null;
        this.last = null;
    }

    //DONE
    @Override
    public E get(int index) {
        this.checkIndex(index);
        
        NodeList<E> n; //PUNTERO VIAJERO
        int counter = 0;
        
        for (n = this.header ; n != null ; n = n.getNext()) {
            if (counter == index) {
                return n.getContent();
            }
        }
        return null;
    }

    //DONE
    @Override
    public boolean isEmpty() {
        return this.header == null && this.last == null;
    }

    //DONE
    @Override
    public E removeFirst() {
        NodeList<E> firstNode = this.header;
        this.header = this.header.getNext();
        firstNode.setNext(null);
        return firstNode.getContent();
    }

    //DONE
    @Override
    public E removeLast() {
        NodeList<E> lastNode = this.last; 
        NodeList<E> previous = this.getPrevious(this.last);
        this.last = previous;
        return lastNode.getContent();
    }       

    //DONE
    @Override
    public E remove(int index) {
        NodeList<E> previous = this.getPrevious(index);
        NodeList<E> removedElement = previous.getNext();
        previous.setNext(removedElement.getNext());
        
        removedElement.setNext(null);
        return removedElement.getContent();
    }
    
    //DONE
    @Override
    public E set(int index, E e) {
        NodeList<E> node = this.getPrevious(index).getNext();
        E oldContent = node.getContent();
        node.setContent(e);
        return oldContent;       
    }

    // DONE
    @Override
    public int size() {
        int count = 0;
        NodeList<E> n; //PUNTERO VIAJERO
        for ( n = header  ; n != null ; n = n.getNext() ) {
            count++;
        }
        return count;
    }
    
    //DONE
    @Override
    public String toString() {
        String s = "";
        NodeList<E> n;        
        for (n = this.header ; n != null ; n = n.getNext()) {
            E e = n.getContent();
            s += e.toString() + " ";
        }             
        return s;
    }

    //DONE
    @Override
    public boolean keepOnly(int from, int to) {
        this.checkIndex(from-1);
        this.checkIndex(to-1);

        if (from <= to) {
            
            int count = 0;
            NodeList<E> n; //PUNTERO VIAJERO
            NodeList<E> previous = null;
            for (n = this.header ; n != null ; n = n.getNext()) {            
                count++;
                if (count == from) {
                    this.header = n;
                    previous.setNext(null);
                }
                if (count == to) {
                    this.last = n;
                }
                previous = n;
            }            
            return true;
        }
        else {
            return false;            
        }
    }

    //DONE
    @Override
    public List<E> copy() {
        List<E> copy = new LinkedList<E>();
        NodeList<E> n;
        for (n = this.header ; n != null ; n = n.getNext()) {
            copy.addLast(n.getContent());
        }
        return copy;
    }

    //DONE
    @Override
    public void reverse() {
        LinkedList<E> newList = new LinkedList<E>();
        NodeList<E> n;
        for (n = this.header ; n != null ; n = n.getNext()) {
            newList.addFirst(n.getContent());
        }
        this.header = newList.header;
        this.last = newList.last;
        newList.clear();
    }

    //DONE
    @Override
    public void insertAt(int index, List<E> collection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //DONE
    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E> () {
            NodeList<E> cursor = header;
            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                return e;
            }            
        };
        return iterator;
    }    
    
    public Iterator<E> iteratorStep(int start, int step) {
        int size = this.size();
        
        Iterator<E> it = new Iterator<E>() {
            
            int idx = start;
            NodeList<E> cursor = header;
            

            @Override
            public boolean hasNext() {
               return idx < size;
            }
                        

            @Override
            public E next() {
                
                if (cursor == header) {
                    for (int i = 0 ; i < start ; i++) {
                        cursor = cursor.getNext();
                    }
                }                
                
                E oldValue = cursor.getContent();
                
                for (int i = 0; i < step; i++) {
                    if (cursor != null) {
                        cursor = cursor.getNext();                        
                    }
                }
                
                idx += step;                
                return oldValue;
            }

        };
        return it;
    }

    public static <E> boolean isReverse(List<E> l1, List<E> l2) {

        if (l1.size() != l2.size()) {
            return false;
        } else if (l1.isEmpty()) {
            return true;
        } else if (l1.removeFirst().equals(l2.removeLast())) {
            return isReverse(l1, l2);
        }
        return false;
    }

    public static int factorial(int number) {
        return number < 2 ? 1 : number*factorial(--number);
    }

    public static int fibonnacci(int number) {
        if (number == 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        } else {
            return fibonnacci(number - 2) + fibonnacci(number - 1);
        }
    }

    public static List<String> getStringsShorterThan1(List<String> inputList, int k) {
        List<String> result = new LinkedList<String>();

        for(String s : inputList) {
            if (s.length() < k) {
                result.addLast(s);
            }
        }
        return result;
    }

//    public static List<String> getStringsShorterThan(List<String> inputList, int k) {
//        List<String> result = new LinkedList<>();
//
//        if (inputList.size() == 0) {
//            return result;
//        }
//        String s = inputList.removeFirst();
//        if (s.length() < k) {
//            result.addLast(s);
//        }
//        return result.addAll(getStringsShorterThan(inputList, k));
//        }
//    }
}
