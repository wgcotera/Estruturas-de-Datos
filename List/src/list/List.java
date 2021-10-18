package list;

/**
 *
 * @author wgcotera
 */
public interface List<E> {
    
    public boolean addFirst(E e);
    
    public boolean addLast(E e);
    
    public void add(int index, E e);
    
    public void clear();
    
    public E get(int index);
    
    public boolean isEmpty();
    
    public E removeFirst();
    
    public E removeLast();
    
    public E remove(int index);
    
    public E set(int index, E e);
    
    public int size();

    @Override
    public String toString();   
    
}
