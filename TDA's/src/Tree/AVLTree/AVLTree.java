package Tree.AVLTree;

import java.util.Comparator;

public class AVLTree <K extends Comparable<K>, V>{


    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private AVLTreeNode<K, V> root;
    private Comparator<K> cmp;


    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public AVLTree(K key, V value) {
        this.root = new AVLTreeNode<>(key, value);
        this.cmp = Comparator.naturalOrder();
    }

//    public AVLTree() {
//        this.root = null;
//        this.cmp = Comparator.naturalOrder();
//    }
//
//    public AVLTree(AVLTreeNode<K, V> nodo) {
//        this.root = nodo;
//        this.cmp = Comparator.naturalOrder();
//    }

    /* *********************************************************************
     * Getters
     ******************************************************************** */

     private int height(AVLTreeNode<K,V> node) {
        return node == null ? 0 : node.height;
    }

    /* *********************************************************************
     * Setters
     ******************************************************************** */
//    public void setRoot(AVLTreeNode<K, V> node, K key, V value) {
//        if (node != null) {
//            node.key = key;
//            node.value = value;
//        }
//    }

    /* *********************************************************************
     * Methods
     ******************************************************************** */

//    public boolean isEmpty() { return this.root == null;}

    private int balance(AVLTreeNode<K, V> node) {
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

    private AVLTreeNode<K, V> rotateRight(AVLTreeNode<K,V> node) {
        AVLTreeNode<K, V> newParent = node.left;
        AVLTreeNode<K, V> centerNode = newParent.right;

        // Perform rotation
        newParent.right = node;
        node = centerNode;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        // Return new Parent
        return newParent;
    }

    private AVLTreeNode<K, V> rotateLeft(AVLTreeNode<K,V> node) {
        AVLTreeNode<K, V> newParent = node.right;
        AVLTreeNode<K, V> centerTree = newParent.left;

        // Perform rotation
        newParent.left = node;
        node.right = centerTree;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        // Return new Parent
        return newParent;
    }

    private int getBalance(AVLTreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public AVLTreeNode<K, V> insert(AVLTreeNode<K,V> node, K key, V value) {

        // BSTree Insertion
        if (node == null) {
            return (new AVLTreeNode<>(key, value));
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

        // Get Balance
        int balance = balance(node);

        // left-left
        if (balance > 1 && cmp.compare(key, node.left.key) < 0) {
            return rotateRight(node);
        }

        // right-right
        if (balance < -1 && cmp.compare(key, node.right.key) > 0) {
            return rotateLeft(node);
        }

        // left-right
        if (balance > 1 && cmp.compare(key, node.right.key) > 0) {
            node.left = (rotateLeft(node.left));
            return rotateRight(node);
        }

        // right-left
        if (balance < -1 && cmp.compare(key, node.right.key) < 0) {
            node.right = (rotateRight(node.right));
            return rotateLeft(node);
        }
        return node;
    }

    public void preOrder (AVLTreeNode<K, V> node) {
        if (node != null) {
            System.out.println(node.toString());
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main (String[]args){

        AVLTree<Integer, Integer> tree = new AVLTree<>(10,10);

        tree.root = tree.insert(tree.root, 20, 20);
        tree.root = tree.insert(tree.root, 25, 25);
        tree.root = tree.insert(tree.root, 30, 30);
        tree.root = tree.insert(tree.root, 40, 40);
        tree.root = tree.insert(tree.root, 50, 50);



 /* The constructed AVL Tree would be
             30
            /  \
         20   40
        /  \     \
     10  25    50
*/
        tree.preOrder(tree.root);
        System.out.println(tree.root.toString());
        System.out.println(tree.root.left.toString());
        System.out.println(tree.root.right.toString());
    }
}
