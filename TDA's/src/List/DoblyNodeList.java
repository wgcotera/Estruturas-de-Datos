package List;

/**
 *
 * @author wgcotera
 */
public class DoblyNodeList <E> {
    private E content;
    private DoblyNodeList<E> next;
    private DoblyNodeList<E> previous;
    
    public DoblyNodeList() {
        this.content = null;
        this.next = null;
        this.previous = null;
    }
    
    public DoblyNodeList(E content) {
        this.content = content;
        this.next = null;
        this.previous = null;
    }    

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DoblyNodeList<E> getNext() {
        return next;
    }

    public void setNext(DoblyNodeList<E> next) {
        this.next = next;
    }

    public DoblyNodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoblyNodeList<E> previous) {
        this.previous = previous;
    }
    
    
}
