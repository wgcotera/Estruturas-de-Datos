package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BinaryTreeTest {
    public static void main(String[] args) {


        BinaryTree<Integer> binaryTree0; /* BT vacio */
        BinaryTree<Integer> binaryTree1; /* BT no-vacio */
        BinaryTree<Integer> binaryTree2; /* BT no-vacio */
        BinaryTree<Integer> binaryTree3; /* BT no-vacio */
        BinaryTree<String>  binaryTree4; /* BT no-vacio */
        BinaryTree<Integer>  binaryTree5; /* BT no-vacio copia de binaryTree3*/
        BinaryTree<Integer>  binaryTree6;
        BinaryTree<String>  binaryTree7;/* BT no-vacio copia de binaryTree4*/

        /* *********************************************************************
         * BinaryTree #0
         ******************************************************************** */

        binaryTree0 = new BinaryTree();

        /* *********************************************************************
         * BinaryTree #1
         * ****************************************************************** */

        binaryTree1 = new BinaryTree(0);

        /* *********************************************************************
         * BinaryTree #2
         * ****************************************************************** */

        binaryTree2 = new BinaryTree(1);
        binaryTree2.setLeft(2);                     // 1
        binaryTree2.setRight(3);                //1      2

        /* *********************************************************************
         * BinaryTree #3
         * ****************************************************************** */

        // root
        binaryTree3 = new BinaryTree(0);
        // root-izq
        binaryTree3.setLeft(1);
        // root-der
        binaryTree3.setRight(2);
        // root-izq-izq
        binaryTree3.getLeft().setLeft(3);
        // root-izq-der
        binaryTree3.getLeft().setRight(4);
        // root-der-izq
        binaryTree3.getRight().setLeft(5);
        // root-der-der
        binaryTree3.getRight().setRight(6);
        // root-der-der-der
        binaryTree3.getRight().getRight().setRight(7);

        /* *********************************************************************
         * BinaryTree #4
         * ****************************************************************** */

        // root
        binaryTree4 = new BinaryTree<>("Zero");
        // root-izq
        binaryTree4.setLeft("One");
        // root-der
        binaryTree4.setRight("Two");
        // root-izq-izq
        binaryTree4.getLeft().setLeft("Three");
        // root-izq-der
        binaryTree4.getLeft().setRight("Four");
        // root-der-der
        binaryTree4.getRight().setRight("Five");

        /* *********************************************************************
         * BinaryTree #5
         * ****************************************************************** */

        // root
        binaryTree5 = new BinaryTree<>(0);
        // root-izq
        binaryTree5.setLeft(1);
        // root-der
        binaryTree5.setRight(2);
        // root-izq-izq
        binaryTree5.getLeft().setLeft(3);
        // root-izq-der
        binaryTree5.getLeft().setRight(4);
        // root-der-izq
        binaryTree5.getRight().setLeft(5);
        // root-der-der
        binaryTree5.getRight().setRight(6);
        // root-der-der-der
        binaryTree5.getRight().getRight().setRight(7);

        /* *********************************************************************
         * BinaryTree #6
         * ****************************************************************** */
        // root
        binaryTree6 = new BinaryTree(11);
        // root-izq
        binaryTree6.setLeft(21);
        // root-der
        binaryTree6.setRight(2);
        // root-izq-izq
        binaryTree6.getLeft().setLeft(10);
        // root-izq-izq-izq
        binaryTree6.getLeft().getLeft().setLeft(14);

        /* *********************************************************************
         * BinaryTree #7
         * ****************************************************************** */

        // root
        binaryTree7 = new BinaryTree<>("Zero");
        // root-izq
        binaryTree7.setLeft("One");
        // root-der
        binaryTree7.setRight("Two");
        // root-izq-izq
        binaryTree7.getLeft().setLeft("Three");
        // root-izq-der
        binaryTree7.getLeft().setRight("Four");
        // root-der-der
        binaryTree7.getRight().setRight("Six");

        /* *********************************************************************
         * isEmpty()
         * ****************************************************************** */

        System.out.println("\n***isEmpty()***\n");
        System.out.println("TRUE:  \t"+binaryTree0.isEmpty());
        System.out.println("FALSE:\t"+binaryTree1.isEmpty());
        System.out.println("FALSE:\t"+binaryTree2.isEmpty());
        System.out.println("FALSE:\t"+binaryTree3.isEmpty());
        System.out.println("FALSE:\t"+binaryTree4.isEmpty());

        /* *********************************************************************
         * isLeaf()
         * ****************************************************************** */

        System.out.println("\n***isLeaf()***\n");
        /* BinaryTree #0 */
        System.out.println("FALSE:\t"+binaryTree0.isLeaf());

        /* BinaryTree #1 */
        System.out.println("TRUE:  \t"+binaryTree1.isLeaf());

        /* BinaryTree #2 */
        System.out.println("TRUE:  \t"+binaryTree2.getLeft().isLeaf());
        System.out.println("TRUE:  \t"+binaryTree2.getRight().isLeaf());

        /* BinaryTree #3 */
        System.out.println("FALSE:\t"+binaryTree3.getLeft().isLeaf());
        System.out.println("TRUE:  \t"+binaryTree3.getLeft().getLeft().isLeaf());
        System.out.println("TRUE:  \t"+binaryTree3.getRight().getRight().getRight().isLeaf());

        /* BinaryTree #4 */
        System.out.println("FALSE:\t"+binaryTree4.getLeft().isLeaf());
        System.out.println("TRUE:  \t"+binaryTree4.getLeft().getLeft().isLeaf());

        /* *********************************************************************
         * recursiveSearch()
         * ****************************************************************** */

        System.out.println("\n***recursiveSearch()***\n");
        /* BinaryTree #0 */
        System.out.println("FALSE:\t"+binaryTree0.recursiveSearch(0, Integer::compareTo));

        /* BinaryTree #1 */
        System.out.println("TRUE:  \t"+binaryTree1.recursiveSearch(0, Integer::compareTo));

        /* BinaryTree #2 */
        System.out.println("TRUE:  \t"+binaryTree2.recursiveSearch(1, Integer::compareTo));
        System.out.println("FALSE:\t"+binaryTree2.recursiveSearch(4, Integer::compareTo));

        /* BinaryTree #3 */
        System.out.println("TRUE:  \t"+binaryTree3.recursiveSearch(5, Integer::compareTo));
        System.out.println("FALSE:\t"+binaryTree3.recursiveSearch(8, Integer::compareTo));

        /* BinaryTree #4 */
        System.out.println("TRUE:  \t"+binaryTree4.recursiveSearch("One", Comparator.comparingInt(String::length)));
        System.out.println("FALSE:\t"+binaryTree4.recursiveSearch("TooLong", Comparator.comparingInt(String::length)));

        /* *********************************************************************
         * countDescendantsRecursive()
         * ****************************************************************** */

        System.out.println("\n***countDescendantsRecursive()***\n");
        /* BinaryTree #0 */
        System.out.println("**0**: \t"+binaryTree0.countDescendantsRecursive());

        /* BinaryTree #1 */
        System.out.println("**0**: \t"+binaryTree1.countDescendantsRecursive());

        /* BinaryTree #2 */
        System.out.println("**2**: \t"+binaryTree2.countDescendantsRecursive());

        /* BinaryTree #3 */
        System.out.println("**7**: \t"+binaryTree3.countDescendantsRecursive());

        /* BinaryTree #4 */
        System.out.println("**5**: \t"+binaryTree4.countDescendantsRecursive());

        /* *********************************************************************
         * countDescendantsIterative()
         * ****************************************************************** */

//        System.out.println("\n***countDescendantsIterative()***\n");
//        /* BinaryTree #0 */
//        System.out.println("**0**: \t"+binaryTree0.countDescendantsIterative());
//
//        /* BinaryTree #1 */
//        System.out.println("**0**: \t"+binaryTree1.countDescendantsIterative());
//
//        /* BinaryTree #2 */
//        System.out.println("**2**: \t"+binaryTree2.countDescendantsIterative());
//
//        /* BinaryTree #3 */
//        System.out.println("**7**: \t"+binaryTree3.countDescendantsIterative());
//
//        /* BinaryTree #4 */
//        System.out.println("**5**: \t"+binaryTree4.countDescendantsIterative());

        /* *********************************************************************
         * findParentRecursive()
         * ****************************************************************** */
        System.out.println("\n***************************************\n");
        System.out.println("******************TAREA******************");
        System.out.println("\n***************************************\n");
        System.out.println("\n***findParentRecursive()***\n");
        /* BinaryTree #0 */
        System.out.println("NULL: \t"+binaryTree0.findParentRecursive(0, Integer::compareTo));

        /* BinaryTree #1 */
        System.out.println("NULL: \t"+binaryTree1.findParentRecursive(0, Integer::compareTo));

        /* BinaryTree #2 */
        System.out.println("NULL: \t"+binaryTree2.findParentRecursive(0, Integer::compareTo));
        System.out.println("**1**: \t"+binaryTree2.findParentRecursive(3, Integer::compareTo));

        /* BinaryTree #3 */
        System.out.println("**6**: \t"+binaryTree3.findParentRecursive(7, Integer::compareTo));
        System.out.println("NULL: \t"+binaryTree3.findParentRecursive(8, Integer::compareTo));

        /* BinaryTree #4 */
        System.out.println("ZERO: \t"+binaryTree4.findParentRecursive("One", Comparator.comparingInt(String::length)));
        System.out.println("NULL: \t"+binaryTree4.findParentRecursive("TooLong", Comparator.comparingInt(String::length)));

        /* *********************************************************************
         * findParentIterative()
         * ****************************************************************** */

        System.out.println("\n***findParentIterative()***\n");
        /* BinaryTree #0 */
        System.out.println("NULL: \t"+binaryTree0.findParentIterative(0, Integer::compareTo));

        /* BinaryTree #1 */
        System.out.println("NULL: \t"+binaryTree1.findParentIterative(0, Integer::compareTo));

        /* BinaryTree #2 */
        System.out.println("NULL: \t"+binaryTree2.findParentIterative(0, Integer::compareTo));
        System.out.println("**1**: \t"+binaryTree2.findParentIterative(3, Integer::compareTo));

        /* BinaryTree #3 */
        System.out.println("**6**: \t"+binaryTree3.findParentIterative(7, Integer::compareTo));
        System.out.println("NULL: \t"+binaryTree3.findParentIterative(8, Integer::compareTo));

        /* BinaryTree #4 */
        System.out.println("ZERO: \t"+binaryTree4.findParentIterative("One", String::compareTo));
        System.out.println("NULL: \t"+binaryTree4.findParentIterative("TooLong", String::compareTo));

        /* *********************************************************************
         * countLevelsRecursive()
         * ****************************************************************** */

        System.out.println("\n***countLevelsRecursive()***\n");
        /* BinaryTree #0 */
        System.out.println("**0**: \t"+binaryTree0.countLevelsRecursive());

        /* BinaryTree #1 */
        System.out.println("**1**: \t"+binaryTree1.countLevelsRecursive());

        /* BinaryTree #2 */
        System.out.println("**2**: \t"+binaryTree2.countLevelsRecursive());

        /* BinaryTree #3 */
        System.out.println("**4**: \t"+binaryTree3.countLevelsRecursive());

        /* BinaryTree #4 */
        System.out.println("**3**: \t"+binaryTree4.countLevelsRecursive());


        /* *********************************************************************
         * countLevelsIterative()
         * ****************************************************************** */

        System.out.println("\n***countLevelsIterative()***\n");
        /* BinaryTree #0 */
        System.out.println("**0**: \t"+binaryTree0.countLevelsIterative());

        /* BinaryTree #1 */
        System.out.println("**1**: \t"+binaryTree1.countLevelsIterative());

        /* BinaryTree #2 */
        System.out.println("**2**: \t"+binaryTree2.countLevelsIterative());

        /* BinaryTree #3 */
        System.out.println("**4**: \t"+binaryTree3.countLevelsIterative());

        /* BinaryTree #4 */
        System.out.println("**3**: \t"+binaryTree4.countLevelsIterative());


        /* *********************************************************************
         * isLeftyRecursive()
         * ****************************************************************** */

        System.out.println("\n***isLeftyRecursive()***\n");
        /* BinaryTree #0 */
        System.out.println("TRUE:  \t"+binaryTree0.isLeftyRecursive());

        /* BinaryTree #1 */
        System.out.println("TRUE:  \t"+binaryTree1.isLeftyRecursive());

        /* BinaryTree #2 */
        System.out.println("FALSE:\t"+binaryTree2.isLeftyRecursive());

        /* BinaryTree #3 */
        System.out.println("FALSE:\t"+binaryTree3.isLeftyRecursive());

        /* BinaryTree #4 */
        System.out.println("FALSE:\t"+binaryTree4.isLeftyRecursive());


        /* *********************************************************************
         * isLeftyIterative()
         * ****************************************************************** */

        System.out.println("\n***isLeftyRecursive()***\n");
        /* BinaryTree #0 */
        System.out.println("TRUE:  \t"+binaryTree0.isLeftyIterative());

        /* BinaryTree #1 */
        System.out.println("TRUE:  \t"+binaryTree1.isLeftyIterative());

        /* BinaryTree #2 */
        System.out.println("FALSE:\t"+binaryTree2.isLeftyIterative());

        /* BinaryTree #3 */
        System.out.println("FALSE:\t"+binaryTree3.isLeftyIterative());

        /* BinaryTree #4 */
        System.out.println("FALSE:\t"+binaryTree4.isLeftyIterative());

        /* *********************************************************************
         * isIdenticalRecursive()
         * ****************************************************************** */

        System.out.println("\n***isIdenticalRecursive()***\n");

        /* BinaryTree #3  and BinaryTree #5 */
        System.out.println("TRUE:  \t"+binaryTree3.isIdenticalRecursive(binaryTree5, Integer::compareTo));

        /* BinaryTree #4  and BinaryTree #7 */
        System.out.println("FALSE:\t"+binaryTree4.isIdenticalRecursive(binaryTree7, String::compareTo));

        /* *********************************************************************
         * isIdenticalIterative()
         * ****************************************************************** */

        System.out.println("\n***isIdenticalIterative()***\n");

        /* BinaryTree #3 and BinaryTree #5 */
        System.out.println("TRUE:  \t"+binaryTree3.isIdenticalIterative(binaryTree5, Integer::compareTo));

        /* BinaryTree #4  and BinaryTree #7 */
        System.out.println("FALSE:\t"+binaryTree4.isIdenticalRecursive(binaryTree7, String::compareTo));

        /* *********************************************************************
         * largestValueOfEachLevelRecursive()
         * ****************************************************************** */

        System.out.println("\n***largestValueOfEachLevelRecursive()***\n");
        System.out.println("\n***FUNCIONA SOLO CON ENTEROS***\n");

        /* BinaryTree #0 */
        System.out.println("BinaryTree #0");
        BinaryTree.largestValueOfEachLevelRecursive(binaryTree0);

        /* BinaryTree #1 */
        System.out.println("\nBinaryTree #1");
        BinaryTree.largestValueOfEachLevelRecursive(binaryTree1);

        /* BinaryTree #2 */
        System.out.println("\nBinaryTree #2");
        BinaryTree.largestValueOfEachLevelRecursive(binaryTree2);

        /* BinaryTree #3 */
        System.out.println("\nBinaryTree #3");
        BinaryTree.largestValueOfEachLevelRecursive(binaryTree3);

        /* BinaryTree #4 */
        System.out.println("\nBinaryTree #6");
        BinaryTree.largestValueOfEachLevelRecursive(binaryTree6);


        /* *********************************************************************
         * largestValueOfEachLevelIterative()
         * ****************************************************************** */

        System.out.println("\n***largestValueOfEachLevelIterative()***\n");
        System.out.println("\n***FUNCIONA CON CUALQUIER TIPO DE VALORES***\n");
        /* BinaryTree #0 */
        System.out.println("BinaryTree #0");
        binaryTree0.largestValueOfEachLevelIterative((o1, o2) -> o2.getContent()-o1.getContent());

        /* BinaryTree #1 */
        System.out.println("\nBinaryTree #1");
        binaryTree1.largestValueOfEachLevelIterative((o1, o2) -> o2.getContent()-o1.getContent());

        /* BinaryTree #2 */
        System.out.println("\nBinaryTree #2");
        binaryTree2.largestValueOfEachLevelIterative((o1, o2) -> o2.getContent()-o1.getContent());

        /* BinaryTree #3 */
        System.out.println("\nBinaryTree #3");
        binaryTree3.largestValueOfEachLevelIterative((o1, o2) -> o2.getContent()-o1.getContent());

        /* BinaryTree #4 */
        System.out.println("\nBinaryTree #4");
        binaryTree4.largestValueOfEachLevelIterative((a1, a2) -> a1.getContent().compareTo(a2.getContent()));

        /* BinaryTree #6 */
        System.out.println("\nBinaryTree #6");
        binaryTree6.largestValueOfEachLevelIterative((o1, o2) -> o2.getContent()-o1.getContent());

        /* *********************************************************************
         * countNodesWithOnlyChildRecursive()
         * ****************************************************************** */

        System.out.println("\n***countNodesWithOnlyChildRecursive()***\n");
        System.out.println("BinaryTree #3");
        System.out.println(binaryTree3.countNodesWithOnlyChildRecursive());
        System.out.println("\nBinaryTree #4");
        System.out.println(binaryTree4.countNodesWithOnlyChildRecursive());

        /* *********************************************************************
         * countNodesWithOnlyChildIterative()
         * ****************************************************************** */

        System.out.println("\n***countNodesWithOnlyChildIterative()***\n");
        System.out.println("BinaryTree #3");
        System.out.println(binaryTree3.countNodesWithOnlyChildIterative());
        System.out.println("\nBinaryTree #4");
        System.out.println(binaryTree4.countNodesWithOnlyChildIterative());

        /* *********************************************************************
         * isHeigthBalanceIterative()
         * ****************************************************************** */

        System.out.println("\n***isHeigthBalanceIterative()***\n");

        System.out.println("BinaryTree #0");
        System.out.println(binaryTree0.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #1");
        System.out.println(binaryTree1.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #2");
        System.out.println(binaryTree2.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #3");
        System.out.println(binaryTree3.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #4");
        System.out.println(binaryTree4.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #5");
        System.out.println(binaryTree5.isHeightBalanceRecursive());
        System.out.println("\nBinaryTree #6");
        System.out.println(binaryTree6.isHeightBalanceRecursive());

        /* *********************************************************************
         * isHeigthBalanceIterative()
         * ****************************************************************** */

        System.out.println("\n***isHeigthBalanceIterative()***\n");
        System.out.println("BinaryTree #0");
        System.out.println(binaryTree0.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #1");
        System.out.println(binaryTree1.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #2");
        System.out.println(binaryTree2.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #3");
        System.out.println(binaryTree3.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #4");
        System.out.println(binaryTree4.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #5");
        System.out.println(binaryTree5.isHeightBalanceIterative());
        System.out.println("\nBinaryTree #6");
        System.out.println(binaryTree6.isHeightBalanceIterative());



        /* *********************************************************************
         * PRUEBAS
         * ****************************************************************** */

        String string = "Hola a   todos      por     hoy        pas";
        String[] ls = string.split("\s+");
        System.out.println(Arrays.toString(ls));

        BinaryTree<String> exp = BinaryTree.getExpressionTree("A B * C D * - H +");
        System.out.println(exp.getContent());
        System.out.println(exp.getLeft().getContent() + " " + exp.getRight().getContent());
        System.out.println(exp.getLeft().getLeft().getContent()+" "+ exp.getLeft().getRight().getContent());
        System.out.println(exp.getLeft().getLeft().getLeft().getContent()+" "+ exp.getLeft().getLeft().getRight().getContent()+" "+exp.getLeft().getRight().getLeft().getContent()+" "+exp.getLeft().getRight().getRight().getContent());

        BinaryTree<String> expT = BinaryTree.getExpressionTree("30 3 +");
        System.out.println(expT.getContent());
        System.out.println(expT.getLeft().getContent() + " " + expT.getRight().getContent());

        System.out.println(BinaryTree.evaluateExpressionTree(expT));
    }

}
