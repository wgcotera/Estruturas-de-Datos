package Tree;

public class BinaryTreeNode<T> {
    private T content;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    
    public BinaryTreeNode(T content) {
        this.content = content;
        this.left = null;
        this.right = null;
    }
    
    public BinaryTreeNode() {
        this.content = null;
        this.left = null;
        this.right = null;
    }

    public T getContent() {
        return content;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setLeft(T content) { this.left = new BinaryTree<>(content); }

    public void setRight(T content) {
        this.right = new BinaryTree<>(content);
    }

    public void setLeft(BinaryTree<T> leftBinaryTree) { this.left = leftBinaryTree; }

    public void setRight(BinaryTree<T> rightBinaryTree) {
        this.right = rightBinaryTree;
    }
}
