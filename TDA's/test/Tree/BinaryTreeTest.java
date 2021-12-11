/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Tree;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BinaryTreeTest {
    
    public BinaryTreeTest() {
        
    }
    
    private BinaryTree<Integer> binaryTree0; /* BT vacio */
    private BinaryTree<Integer> binaryTree1; /* BT no-vacio */
    private BinaryTree<Integer> binaryTree2; /* BT no-vacio */
    private BinaryTree<Integer> binaryTree3; /* BT no-vacio */
    private BinaryTree<String>  binaryTree4; /* BT no-vacio */

    @BeforeEach
    void setUp() {
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
        binaryTree2 = new BinaryTree(0);
        binaryTree2.setLeft(1);
        binaryTree2.setRight(2);

        /* *********************************************************************
         * BinaryTree #3
         * ****************************************************************** */
        // root
        binaryTree3 = new BinaryTree<>(0);
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
    }

    @Test
    void isEmpty() {
        assertTrue(binaryTree0.isEmpty());
        assertFalse(binaryTree1.isEmpty());
        assertFalse(binaryTree2.isEmpty());
        assertFalse(binaryTree3.isEmpty());
        assertFalse(binaryTree4.isEmpty());
    }

//    @Test
    void isLeaf() {
        /* BinaryTree #0 */
        assertFalse(binaryTree0.isLeaf());

        /* BinaryTree #1 */
        assertTrue(binaryTree1.isLeaf());

        /* BinaryTree #2 */
        assertTrue(binaryTree2.getLeft().isLeaf());
        assertTrue(binaryTree2.getRight().isLeaf());

        /* BinaryTree #3 */
        assertFalse(binaryTree3.getLeft().isLeaf());
        assertTrue(binaryTree3.getLeft().getLeft().isLeaf());
        assertTrue(binaryTree3.getRight().getRight().getRight().isLeaf());

        /* BinaryTree #4 */
        assertFalse(binaryTree4.getLeft().isLeaf());
        assertTrue(binaryTree4.getLeft().getLeft().isLeaf());
    }
//
//    @Test
//    void recorrerPreOrdenRecursive() {
//        System.out.println("BinaryTree #1:");
//        binaryTree1.recorrerPreOrdenRecursive();
//
//        System.out.println("BinaryTree #2:");
//        binaryTree2.recorrerPreOrdenRecursive();
//
//        System.out.println("BinaryTree #3:");
//        binaryTree3.recorrerPreOrdenRecursive();
//
//        System.out.println("BinaryTree #4:");
//        binaryTree4.recorrerPreOrdenRecursive();
//    }
//
//    @Test
//    void recorrerEnOrdenRecursive() {
//        System.out.println("BinaryTree #1:");
//        binaryTree1.recorrerEnOrdenRecursive();
//
//        System.out.println("BinaryTree #2:");
//        binaryTree2.recorrerEnOrdenRecursive();
//
//        System.out.println("BinaryTree #3:");
//        binaryTree3.recorrerEnOrdenRecursive();
//
//        System.out.println("BinaryTree #4:");
//        binaryTree4.recorrerEnOrdenRecursive();
//    }
//
//    @Test
//    void recorrerPostOrdenRecursive() {
//        System.out.println("BinaryTree #1:");
//        binaryTree1.recorrerPostOrdenRecursive();
//
//        System.out.println("BinaryTree #2:");
//        binaryTree2.recorrerPostOrdenRecursive();
//
//        System.out.println("BinaryTree #3:");
//        binaryTree3.recorrerPostOrdenRecursive();
//
//        System.out.println("BinaryTree #4:");
//        binaryTree4.recorrerPostOrdenRecursive();
//    }
//
//    @Test
//    void countLeavesRecursive() {
//        /* BinaryTree #0 */
//        assertEquals(0, binaryTree0.countLeavesRecursive());
//
//        /* BinaryTree #1 */
//        assertEquals(1, binaryTree1.countLeavesRecursive());
//
//        /* BinaryTree #2 */
//        assertEquals(2, binaryTree2.countLeavesRecursive());
//
//        /* BinaryTree #3 */
//        assertEquals(4, binaryTree3.countLeavesRecursive());
//
//        /* BinaryTree #4 */
//        assertEquals(3, binaryTree4.countLeavesRecursive());
//    }
//
//    @Test
//    void countLeavesIterative() {
//        /* BinaryTree #0 */
//        assertEquals(0, binaryTree0.countLeavesIterative());
//
//        /* BinaryTree #1 */
//        assertEquals(1, binaryTree1.countLeavesIterative());
//
//        /* BinaryTree #2 */
//        assertEquals(2, binaryTree2.countLeavesIterative());
//
//        /* BinaryTree #3 */
//        assertEquals(4, binaryTree3.countLeavesIterative());
//
//        /* BinaryTree #4 */
//        assertEquals(3, binaryTree4.countLeavesIterative());
//    }
//
//    @Test
//    void searchRecursive() {
//        /* BinaryTree #0 */
//        assertNull(binaryTree0.searchRecursive(0, Integer::compareTo));
//
//        /* BinaryTree #1 */
//        assertNotNull(binaryTree1.searchRecursive(0, Integer::compareTo));
//
//        /* BinaryTree #2 */
//        assertNotNull(binaryTree2.searchRecursive(0, Integer::compareTo));
//        assertNull(binaryTree2.searchRecursive(3, Integer::compareTo));
//
//        /* BinaryTree #3 */
//        assertNotNull(binaryTree3.searchRecursive(7, Integer::compareTo));
//        assertNull(binaryTree3.searchRecursive(8, Integer::compareTo));
//
//        /* BinaryTree #4 */
//        assertNotNull(binaryTree4.searchRecursive("One",
//                                                  Comparator.comparingInt(String::length)));
//        assertNull(binaryTree4.searchRecursive("TooLong",
//                                               Comparator.comparingInt(String::length)));
//    }
//
//    @Test
//    void searchIterative() {
//        /* BinaryTree #0 */
//        assertNull(binaryTree0.searchIterative(0, Integer::compareTo));
//
//        /* BinaryTree #1 */
//        assertNotNull(binaryTree1.searchIterative(0, Integer::compareTo));
//
//        /* BinaryTree #2 */
//        assertNotNull(binaryTree2.searchIterative(0, Integer::compareTo));
//        assertNull(binaryTree2.searchIterative(3, Integer::compareTo));
//
//        /* BinaryTree #3 */
//        assertNotNull(binaryTree3.searchIterative(7, Integer::compareTo));
//        assertNull(binaryTree3.searchIterative(8, Integer::compareTo));
//
//        /* BinaryTree #4 */
//        assertNotNull(binaryTree4.searchIterative("One",
//                                                  Comparator.comparingInt(String::length)));
//        assertNull(binaryTree4.searchIterative("TooLong",
//                                               Comparator.comparingInt(String::length)));
//    }
//
//    @Test
//    void getMinRecursive() {
//        assertEquals(0, binaryTree3.getMinRecursive(Integer::compareTo));
//    }
//
//    @Test
//    void getMinIterative() {
//        assertEquals(0, binaryTree3.getMinIterative(Integer::compareTo));
//    }


}
