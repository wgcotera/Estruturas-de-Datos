package list;

/**
 *
 * @author wgcotera
 * @param <E>
 */
public interface List<E> extends Iterable<E> {
    
    boolean addFirst(E e);
    
    boolean addLast(E e);
    
    void add(int index, E e);
    
    void clear();
    
    E get(int index);
    
    boolean isEmpty();
    
    E removeFirst();
    
    E removeLast();
    
    E remove(int index);
    
    E set(int index, E e);
    
    int size();

    @Override
    String toString();   
    
   boolean keepOnly(int from, int to); 
   
   List<E> copy();
   
   void reverse();
   
   void insertAt(int index, List<E> collection);
   
   
}
