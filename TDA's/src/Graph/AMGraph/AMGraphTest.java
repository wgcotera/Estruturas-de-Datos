package Graph.AMGraph;

public class AMGraphTest {
    public static void main(String[] args) {
            AMGraph<Integer> g = new AMGraph<>();
        g.print();
        g.addVertex(1).addVertex(2).addVertex(3);
        g.print();

        g.removeVertex(1);
        g.print();
    }
}
