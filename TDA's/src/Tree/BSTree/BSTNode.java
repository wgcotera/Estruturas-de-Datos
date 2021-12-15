package Tree.BSTree;

public class BSTNode<E, K extends Comparable<K>> {
    private E content;
    private K key; // Elemento por el cual se va a comparar
    private BSTree<E, K> left;
    private BSTree<E, K> right;

    public BSTNode(E content , K key) {
        this.content = content;
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public E getContent() {
        return content;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BSTree<E, K> getLeft() {
        return left;
    }

    public void setLeft(BSTree<E, K> left) {
        this.left = left;
    }

    public void setLeft(E content, K key) {
        this.left = new BSTree<>(content, key);
    }

    public BSTree<E, K> getRight() {
        return right;
    }

    public void setRight(BSTree<E, K> right) {
        this.right = right;
    }

    public void setRight(E content, K key) {
        this.right = new BSTree<>(content, key);
    }
}
