package Tree.BSTree;

public class BSTreeTest {

    public static void main(String[] args) {
        BSTree<String, Integer> root = new BSTree<>("casa1", 5);
        root.insert("casa2", 3);
        root.insert("casa3", 7);//                            /5\
        root.insert("casa4", 2);//              /3\                        / 7\
        root.insert("casa5", 4);//      2               4              6           8
        root.insert("casa6", 6);
        root.insert("casa7",8);

        System.out.println(root.getContent()+root.getKey());
        System.out.println(root.getLeft().getContent()+root.getLeft().getKey());
        System.out.println(root.getRight().getContent()+ root.getRight().getKey());

    }
}
