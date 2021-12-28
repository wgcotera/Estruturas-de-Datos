package Tree.BSTree;

import java.util.Comparator;

public class BSTree<E, K extends Comparable<K>> {

    /* *********************************************************************
     * Inner Classes
     ******************************************************************** */

    private class BSTNode<E, K extends Comparable<K>> {
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

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private BSTNode<E, K> root;
    private Comparator<K> comparator;


    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public BSTree(E content, K key) {
        this.root = new BSTNode<>(content, key);
        this.comparator = Comparator.naturalOrder();
    }

    /* *********************************************************************
     * Getters
     ******************************************************************** */

    public E getContent() {
        return this.root.getContent();
    }

    public BSTree<E, K> getLeft() {
        return this.root.getLeft();
    }

    public BSTree<E, K> getRight() {
        return this.root.getRight();
    }

    public K getKey() { return this.root.getKey(); }


    /* *********************************************************************
     * Setters
     ******************************************************************** */

    public void setLeft(E content, K key) {
        this.root.setLeft(content, key);
    }

    public void setLeft(BSTree<E, K> leftBinaryTree) {
        this.root.setLeft(leftBinaryTree);
    }

    public void setRight(E content, K key) {
        this.root.setRight(content, key);
    }

    public void setRight(BSTree<E, K> rightBinaryTree) {
        this.root.setRight(rightBinaryTree);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        if (!this.isEmpty()) {
            return this.getLeft() == null && this.getRight() == null;
        }
        return false;
    }

    public E getMin() {
        if (isEmpty()) {
            return null;
        }

        BSTree<E, K> tmp = this;
        while (tmp.getLeft() != null) {
            tmp = tmp.getLeft();
        }
        return tmp.getContent();
    }

    public E getMax() {
        if (isEmpty()) {
            return null;
        }

        BSTree<E, K> tmp = this;
        while (tmp.getRight() != null) {
            tmp = tmp.getRight();
        }
        return tmp.getContent();
    }

    public boolean insert(E content, K key) {
        if (this.isEmpty()) {
            this.root = new BSTNode<>(content, key);
            return true;
        }

        if (comparator.compare(key, this.getKey()) == 0) {
            return false;
        }

        if (comparator.compare(key, this.getKey()) < 0) {

            if (this.getLeft() == null) {
                this.setLeft(content, key);
            }
            else {
                this.getLeft().insert(content, key);
            }
        } else {
            if (this.getRight() == null) {
                this.setRight(content, key);
            }
            else {
                this.getRight().insert(content, key);
            }
        }
        return true;
    }



}
