package Tree.AVLTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class AVLTree <K extends Comparable<K>, V>{

    /* *********************************************************************
     * Inner Classes
     ******************************************************************** */
    private class Node<K extends Comparable<K>, V> {

        /* *********************************************************************
         * Parameters
         ******************************************************************** */
        K key;
        V value;
        int height;

        Node<K, V> left;
        Node<K, V> right;

        /* *********************************************************************
         * Constructors
         ******************************************************************** */

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        /* *********************************************************************
         * Methods
         ******************************************************************** */
        @Override
        public String toString() {
            return "{" + "key=" + key + "\t value=" + value + "\t height=" + height + "}";
        }
    }

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private Node<K, V> root;
    private final Comparator<K> cmp;


    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public AVLTree(Comparator<K> comparator) {
        this.root = null;
        this.cmp = comparator;
    }

    public AVLTree() {
        this.root = null;
        this.cmp = Comparator.naturalOrder();
    }


    /* *********************************************************************
     * Getters
     ******************************************************************** */

     private int height(Node<K,V> node) {
        return node == null ? 0 : node.height;
    }

    /* *********************************************************************
     * Public Methods
     ******************************************************************** */

    public boolean isEmpty() { return root == null;}

    public AVLTree<K, V> insert(K key, V value) {
        root = insert(root, key, value);
        return this;
    }

    public V search(K key) {
        return search(root, key);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    public void printBFS() {
        printBFS(root);
    }


    /* *********************************************************************
     * Private Methods
     ******************************************************************** */


    private int balance(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

//            y                                                   x
//           / \              Right Rotation            /  \
//         np   T3             - - - - - - - >            T1   np
//         / \                   < - - - - - - -                   / \
//       T1  cn             Left Rotation                cn  T3
// np = newParent      cn = centerNode

    private Node<K, V> rotateRight(Node<K,V> node) {
        Node<K, V> newParent = node.left;
        Node<K, V> centerNode = newParent.right;

        // Perform rotation
        newParent.right = node;
        node.left = centerNode;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        // Return new Parent
        return newParent;
    }

    private Node<K, V> rotateLeft(Node<K,V> node) {
        Node<K, V> newParent = node.right;
        Node<K, V> centerTree = newParent.left;

        // Perform rotation
        newParent.left = node;
        node.right = centerTree;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        // Return new Parent
        return newParent;
    }

    private Node<K, V> applyRotation(Node<K, V> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> insert(Node<K,V> node, K key, V value) {

        // BSTree Insertion
        if (node == null) {
            return (new Node<>(key, value));
        }

        int comparator = cmp.compare(key, node.key);

        if (comparator < 0) {
            node.left = insert(node.left, key, value);
        }
        else if (comparator > 0) {
            node.right = insert(node.right, key, value);
        }
        else {
            return node;
        }

        // Update height
        node.height = (1 + Math.max(height(node.left), height(node.right)));

        return applyRotation(node);
    }

    private V search(Node<K, V> node, K key) {
        int comparator = cmp.compare(key, node.key);
        if (comparator == 0) {
            return node.value;
        }
        if (comparator < 0 && node.left != null) {
            return search(node.left, key);
        }
        if (comparator > 0 && node.right != null) {
            return search(node.right, key);
        }
        return null;
    }

    private void printPreOrder(Node<K, V> node) {
        if (node != null) {
            System.out.println(node.toString());
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    private void printBFS(Node<K,V> node) {
        Queue<Node<K, V>> pq = new LinkedList<>();
        pq.offer(node);
        while(!pq.isEmpty()) {
            Node<K, V> tmp = pq.poll();
            if (tmp != null) {
                System.out.println(tmp.toString());
                if (tmp.left != null) {
                    pq.offer(tmp.left);
                }
                if (tmp.right != null) {
                    pq.offer(tmp.right);
                }
            }
        }
    }
}
