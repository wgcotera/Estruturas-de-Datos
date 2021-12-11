package Tree;

import static java.lang.Integer.max;
import java.util.*;

public class BinaryTree<T> {


    /* *********************************************************************
         * Parameters
         ******************************************************************** */

    private BinaryTreeNode<T> root;



    /* *********************************************************************
         * Constructors
         ******************************************************************** */

    public BinaryTree(T content) { this.root = new BinaryTreeNode<T>(content); }

    public BinaryTree() {
        this.root = null;
    }



    /* *********************************************************************
         * Getters
         ******************************************************************** */

    public T getContent() {
        return this.root.getContent();
    }

    public  BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }


    /* *********************************************************************
         * Setters
         ******************************************************************** */

    public void setContent(T content) {
        this.root.setContent(content);
    }

    public void setLeft(T content) {
        this.root.setLeft(content);
    }

    public void setLeft(BinaryTree<T> leftBinaryTree) {
        this.root.setLeft(leftBinaryTree);
    }

    public void setRight(T content) {
        this.root.setRight(content);
    }

    public void setRight(BinaryTree<T> rightBinaryTree) {
        this.root.setRight(rightBinaryTree);
    }

    /* *********************************************************************
         *
         ******************************************************************** */

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        if (!this.isEmpty()) {
            return this.getLeft() == null && this.getRight() == null;
        }
        return false;
    }


    /* *********************************************************************
         * Recursive Methods
         ******************************************************************** */

    public void recorrerPreOrdenRecursive() {

        if (this.isEmpty()) {
            return;
        }
        System.out.println(this.getContent());
        if (this.getLeft() != null) {
            this.getLeft().recorrerPreOrdenRecursive();
        }
        if (this.getRight() != null) {
            this.getRight().recorrerPreOrdenRecursive();
        }
    }

    public void recorrerEnOrdenRecursive() {

        if (this.isEmpty()) {
            return;
        }

        if (this.getLeft() != null) {
            this.getLeft().recorrerEnOrdenRecursive();
        }

        System.out.println(this.getContent());

        if (this.getRight() != null) {
            this.getRight().recorrerEnOrdenRecursive();
        }
    }

    public void recorrerPostOrdenRecursive() {

        if (this.isEmpty()) {
            return;
        }

        if (this.getLeft() != null) {
            this.getLeft().recorrerPostOrdenRecursive();
        }

        if (this.getRight() != null) {
            this.getRight().recorrerPostOrdenRecursive();
        }

        System.out.println(this.getContent());
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        }
        else if (this.isLeaf()) {
            return 1;
        }
        else {
            int leavesLeft = 0;
            int leavesRight = 0;

            if (this.getLeft() != null) {
                leavesLeft = this.getLeft().countLeavesRecursive();
            }

            if(this.getRight() != null) {
                leavesRight = this.getRight().countLeavesRecursive();
            }

            return leavesLeft + leavesRight;
        }
    }

    public boolean recursiveSearch(T content, Comparator<T> cmp) {

        boolean result =false;

        if (!this.isEmpty()) {
            if (cmp.compare(content, this.getContent()) == 0) {
                return true;
            } else {

                if (this.getLeft() != null) {
                    result = result || this.getLeft().recursiveSearch(content, cmp);
                }

                if (this.getRight() != null) {
                    result = result || this.getRight().recursiveSearch(content, cmp);
                }
            }
        }
        return result;
    }

    /* *********************************************************************
         * Iterative Methods
         ******************************************************************** */

    public int countLeavesIterative() {

        Deque<BinaryTree<T>> stack = new ArrayDeque<>();
        int totalLeaves = 0;

        if (this.isEmpty()) {
            return totalLeaves;
        }
        else {
            stack.push(this);

            while(!stack.isEmpty()) {
                BinaryTree<T> subTree = stack.pop();
                if (subTree.isLeaf()) {
                    totalLeaves++;
                }
                else if(subTree.getLeft() != null) {
                    stack.push(subTree.getLeft());
                }
                else if(subTree.getRight() != null){
                    stack.push(subTree.getRight());
                }
            }
        }
        return totalLeaves;
    }


    /* *********************************************************************
         * Taller
         ******************************************************************** */

//    public boolean IterativeSearch(T content, Comparator<T> cmp) {
//        
//        boolean result = false;
//        
//        Deque<BinaryTree<T>> stack = new ArrayDeque();     
//        stack.push(this);
//        
//        if(!this.isEmpty()) {
//            
//            while(!stack.isEmpty()) {
//                
//                BinaryTree<T> tmp = stack.pop();
//                
//                if(cmp.compare(content, tmp.getContent()) == 0) {
//                    return result = true;
//                }
//                if(!tmp.getLeft())
//                
//            }
//        }
//        return result;
//    }

    public int countDescendantsRecursive() {

        if(this.isEmpty() || this.isLeaf()) {
            return 0;
        }

        int descendants = 0;

        if (this.getLeft() != null) {
            descendants += 1 + this.getLeft().countDescendantsRecursive();
        }

        if (this.getRight() != null) {
            descendants += 1 +this.getRight().countDescendantsRecursive();
        }

        return descendants;
    }

    /* *********************************************************************
     * Static Methods
     ******************************************************************** */

    public static BinaryTree<String> getExpressionTree(String posfixExpression) {

        String[] elements = posfixExpression.split("\s+");

        Deque<BinaryTree<String>> operands = new ArrayDeque<>();

        for (String element : elements) {
            BinaryTree<String> tree = new BinaryTree<>(element);
            if(!isOperand(element)) {
                tree.setRight(operands.pop());
                tree.setLeft(operands.pop());
            }
            operands.push(tree);

        }
        return operands.pop();
    }

    public static double evaluateExpressionTree(BinaryTree<String> expressionTree) {
        if ( expressionTree.isLeaf()) {
            return Double.parseDouble(expressionTree.getContent());
        }
        else {
            double totalLeft = evaluateExpressionTree(expressionTree.getLeft());
            double totalRight = evaluateExpressionTree(expressionTree.getRight());
            return operate(totalLeft, totalRight, expressionTree.getContent());
        }
    }

    private static boolean isOperator(String operator) {
        return "/".equals(operator) || "*".equals(operator) || "+".equals(operator) || "-".equals(operator) || "^".equals(operator);
    }

    private static boolean isOperand(String operand) {
        return !isOperator(operand) ;
    }

    private static double operate(double operand1, double operand2, String operator) {
        switch (operator) {
            case "/":
                return operand1/operand2;
            case "*":
                return operand1*operand2;
            case "+":
                return operand1+operand2;
            case "-" :
                return operand1-operand2;
            default:
                return Math.pow(operand1, operand2);
        }
    }


    /* *********************************************************************
         * Tarea
         ******************************************************************** */

     /* EJERCICIO 1 */

    public T findParentRecursive(T nodeContent, Comparator<T> cmp) {
        T parent = null;
        if (!this.isEmpty() && !this.isLeaf()) {
            if (this.getLeft() != null) {
                if (cmp.compare(nodeContent, this.getLeft().getContent()) == 0) {
                    return this.getContent();
                } else {
                    parent = this.getLeft().findParentRecursive(nodeContent, cmp);
                }
            }

            if (this.getRight() != null) {
                if (cmp.compare(nodeContent, this.getRight().getContent()) == 0) {
                    return this.getContent();
                } else {
                    parent = this.getRight().findParentRecursive(nodeContent, cmp);
                }
            }
        }
        return parent;
    }

    public T findParentIterative(T nodeContent, Comparator<T> cmp) {

        Deque<BinaryTree<T>> stack = new ArrayDeque();
        stack.push(this);

        if (!this.isEmpty() && !this.isLeaf()) {

            while (!stack.isEmpty()) {

                BinaryTree<T> tmp = stack.pop();

                if (tmp.getLeft() != null) {
                    if (cmp.compare(nodeContent, tmp.getLeft().getContent()) == 0) {
                        return tmp.getContent();
                    } else {
                        stack.push(tmp.getLeft());
                    }
                }

                if (tmp.getRight() != null) {
                    if (cmp.compare(nodeContent, tmp.getRight().getContent()) == 0) {
                        return tmp.getContent();
                    } else {
                        stack.push(tmp.getRight());
                    }
                }
            }
        }
        return null;
    }

    /* EJERCICIO 2 */

    public int countLevelsRecursive() {

        if (this.isEmpty()) {
            return 0;
        }

        if (this.isLeaf()) {
            return 1;
        }

        int leftLevelCounter = 0;
        if (this.getLeft() != null) {
            leftLevelCounter += this.getLeft().countLevelsRecursive();
        }

        int rightLevelCounter = 0;
        if (this.getRight() != null) {
            rightLevelCounter += this.getRight().countLevelsRecursive();
        }
        return 1 + max(leftLevelCounter, rightLevelCounter);
    }

    public int countLevelsIterative() {
        Deque<BinaryTree<T>> stack = new ArrayDeque();
        stack.push(this);

        int levelCounter = 0;

        if(!this.isEmpty()) {

            while(!stack.isEmpty()) {
                levelCounter++;
                LinkedList<BinaryTree<T>> subTrees = new LinkedList();

                while(!stack.isEmpty()) {
                    subTrees.add(stack.pop());
                }

                for(BinaryTree<T> subTree : subTrees) {
                    if (subTree.getLeft() != null) {
                        stack.push(subTree.getLeft());
                    }
                    if (subTree.getRight() != null) {
                        stack.push(subTree.getRight());
                    }
                }
            }
        }
        return levelCounter;
    }


    /* EJERCICIO 3 */

    public boolean isLeftyRecursive() {

        if (this.isEmpty() || this.isLeaf()) {
            return true;
        }

        if  (this.getLeft() != null && !this.getLeft().isLeftyRecursive()) {
            return false;
        }
        if (this.getRight() != null && !this.getRight().isLeftyRecursive()) {
            return false;
        }
        if (this.getLeft() == null || this.countDescendantsRecursive() / 2 >= this.getLeft().countDescendantsRecursive() +1) {
            return false;
        }
        return true;
    }

    public boolean isLeftyIterative() {

        if (this.isEmpty() || this.isLeaf()) {
            return true;
        }
        Deque<BinaryTree<T>> stack = new ArrayDeque();
        stack.push(this);

        while (!stack.isEmpty()) {

            BinaryTree<T> tmp = stack.pop();

            int descendants = tmp.countDescendantsRecursive();
            int leftDescendants = 0;

            if (tmp.getLeft() != null) {
                leftDescendants = tmp.getLeft().countDescendantsRecursive();
                if (descendants/2 >= leftDescendants +1) {
                    return false;
                }

                stack.push(tmp.getLeft());

                if (tmp.getRight()!= null) {
                    stack.push(tmp.getRight());
                }
            }
        }
        return true;
    }


    /* EJERCICIO 4 */

    public boolean isIdenticalRecursive(BinaryTree<T> tree, Comparator<T> cmp) {

        if (this.isEmpty() ^ tree.isEmpty()) {
            return false;
        }
        if (cmp.compare(this.getContent(), tree.getContent()) != 0) {
            return false;
        }
        if (this.getLeft() != null && tree.getLeft() != null && !this.getLeft().isIdenticalRecursive(tree.getLeft(), cmp)) {
            return false;
        }
        if (this.getRight() != null && tree.getRight() != null && !this.getRight().isIdenticalRecursive(tree.getRight(),cmp)) {
            return  false;
        }
        return true;
    }

    public boolean isIdenticalIterative(BinaryTree<T> tree, Comparator<T> cmp) {

        Deque<BinaryTree<T>> stackThis = new ArrayDeque<>();
        Deque<BinaryTree<T>> stackOther = new ArrayDeque<>();
        stackThis.push(this);
        stackOther.push(tree);
        while(!stackThis.isEmpty() && !stackOther.isEmpty()) {
            BinaryTree<T> thisTree = stackThis.pop();
            BinaryTree<T> otherTree = stackOther.pop();
            if(thisTree.isEmpty() ^ otherTree.isEmpty())
                return false;
            if(!thisTree.isEmpty() && !otherTree.isEmpty() && cmp.compare(thisTree.getContent(), otherTree.getContent()) != 0)
                return false;
            if(thisTree.getLeft() != null) {
                stackThis.push(thisTree.getLeft());
            }
            if(otherTree.getLeft() != null) {
                stackOther.push(otherTree.getLeft());
            }

            if(thisTree.getRight() != null) {
                stackThis.push(thisTree.getRight());
            }
            if(otherTree.getRight() != null) {
                stackOther.push(otherTree.getRight());
            }
        }
        return stackThis.isEmpty() && stackOther.isEmpty();
    }

    /* EJERCICIO 5 */

    // Metodo para encontrar el maximo valor de un nivel
    private static List<Integer> largestValueOfLevels(BinaryTree<Integer> tree, int level, List<Integer> result) {
        if (tree.isEmpty()) {
            return result;
        }
        if ((level + 1)  > result.size()) {
            result.add(level, tree.getContent());
        }
        else {
            result.set(level, Math.max(result.get(level), tree.getContent()));
        }
        if (tree.getLeft() != null) {
            largestValueOfLevels(tree.getLeft(), level + 1, result);
        }
       if(tree.getRight() != null) {
           largestValueOfLevels(tree.getRight(), level + 1, result);
       }

        return result;
    }

    public static void largestValueOfEachLevelRecursive(BinaryTree<Integer> tree) {
        if (tree.isEmpty()) {
            return;
        }
        List<Integer> result = new ArrayList<>();
        largestValueOfLevels(tree, 0, result);

        for (int i = 1; i <= tree.countLevelsRecursive(); i++) {
            System.out.printf("Largest value of level %d is %d.\n", i, result.get(i-1));
        }

    }

    // Funciona para cualquier tipo de valores, no solo enteros. Solo hay que pasarle un comparator
    public void largestValueOfEachLevelIterative(Comparator<BinaryTree<T>> cmpDescendant) {

        Queue<BinaryTree<T>> tmpQueue = new PriorityQueue<>(cmpDescendant);
        tmpQueue.offer(this);

        if (!this.isEmpty()) {

            while (!tmpQueue.isEmpty()) {

                System.out.println(tmpQueue.peek().getContent());
                LinkedList<BinaryTree<T>> subTrees = new LinkedList<>();

                while (!tmpQueue.isEmpty()) {
                    subTrees.add(tmpQueue.poll());
                }

                for (BinaryTree<T> subTree : subTrees) {
                    if (subTree.getLeft() != null) {
                        tmpQueue.offer(subTree.getLeft());
                    }
                    if (subTree.getRight() != null) {
                        tmpQueue.offer(subTree.getRight());
                    }
                }
            }
        }

    }

    /* EJERCICIO 6 */

    public int countNodesWithOnlyChildRecursive() {
        if (isEmpty()) {
            return 0;
        }

        int result = this.getLeft() != null ^ this.getRight() != null ? 1 : 0;
        if (this.getLeft() != null) {
            result += this.getLeft().countNodesWithOnlyChildRecursive();
        }
        if (this.getRight() != null) {
            result += this.getRight().countNodesWithOnlyChildRecursive();
        }

        return result;
    }

    public int countNodesWithOnlyChildIterative() {
        if (this.isEmpty() || this.isLeaf()) {
            return 0;
        }

        int result = 0;
        final Deque<BinaryTree<T>> stack = new ArrayDeque<>();
        stack.push(this);
        while (true) {
            final boolean isStackEmpty = stack.isEmpty();
            if (isStackEmpty) {
                break;
            }

            final BinaryTree<T> subBT = stack.pop();

            result = subBT.getLeft() != null ^ subBT.getRight() != null
                    ? result + 1
                    : result;

            if (subBT.getLeft() != null) {
                stack.push(subBT.getLeft());
            }
            if (subBT.getRight() != null) {
                stack.push(subBT.getRight());
            }
        }

        return result;
    }

    /* EJERCICIO 7 */

    public int getHeight() {
        return this.countLevelsRecursive() - 1;
    }

    public boolean isHeightBalanceRecursive() {
        if (this.isEmpty()) {
            return true;
        }
        if ((this.getLeft() != null && !this.getLeft().isHeightBalanceRecursive()) || (this.getRight() != null && !this.getRight().isHeightBalanceRecursive())) {
            return false;
        }
        if (this.getLeft() != null && this.getRight() != null) {
            return Math.abs(this.getLeft().getHeight() - this.getRight().getHeight()) <= 1;
        }
        return true;
    }

    public boolean isHeightBalanceIterative() {
        if(this.isEmpty()) {
            return true;
        }
        Deque<BinaryTree<T>> stack = new ArrayDeque<>();
        stack.push(this);

        while(!stack.isEmpty()) {
            BinaryTree<T> subTree = stack.pop();

            if(subTree.getLeft() != null && subTree.getRight() != null && !(Math.abs(subTree.getLeft().getHeight() - subTree.getRight().getHeight()) <= 1)) {
                return false;
            }
            if(subTree.getLeft() != null) {
                stack.push(subTree.getLeft());
            }
            if(subTree.getRight() != null) {
                stack.push(subTree.getRight());
            }
        }
        return true;
    }
}
