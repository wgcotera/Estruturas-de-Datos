package list;

/**
 *
 * @author wgcotera
 */
public interface List<E> {
    
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
}
