package Tree.BSTree;

import Tree.BinaryTree.BinaryTree;

import java.util.Comparator;

public class BSTree<E, K extends Comparable<K>> {

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private BSTNode<E, K> root;
    private Comparator<K> cmp;


    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public BSTree(E content, K key) {
        this.root = new BSTNode<>(content, key);
        this.cmp = Comparator.naturalOrder();
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

        if (cmp.compare(key, this.getKey()) == 0) {
            return false;
        }

        if (cmp.compare(key, this.getKey()) < 0) {

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
