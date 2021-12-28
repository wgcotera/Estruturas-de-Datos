package Tree.AVLTree;

public class AVLTreeTest {
    public static void main (String[]args){

        AVLTree<Integer, Character> tree = new AVLTree<>();
        tree.insert(10, 'A');
        tree.insert(20, 'B');
        tree.insert(30, 'C');
        tree.insert(40, 'D');
        tree.insert(50, 'E');
        tree.insert(25, 'F');
        tree.insert(15, 'G');
        tree.insert(27, 'H');
        tree.insert(44, 'I');
        tree.insert(32, 'J');

        tree.printPreOrder();
        System.out.println("\n");
        tree.printBFS();
        System.out.println(tree.search(50));
    }
}
