package Graph.ALGraph;

import java.util.LinkedList;
import java.util.List;

public class ALGraphTest {
    public static void main(String[] args) {
        ALGraph<String, String> test = new ALGraph<>();
        test.addVertex("Alexander").addVertex("Alina").addVertex("Juan").addVertex("Genaro").addVertex("Steven").addVertex("Eliana").addVertex("Gia").addVertex("Maria").addVertex("Anabel");
        test.addEdge("Alexander", "Alina");
        test.addEdge("Alina", "Genaro");
        test.addEdge("Alexander", "Juan");
        test.addEdge("Juan", "Steven");
        test.addEdge("Alina", "Eliana");
        test.addEdge("Alexander", "Gia");
        test.addEdge("Maria", "Anabel");
        List<String> result = test.bfs("Alexander");
        System.out.println(result.toString());

        List<String> result1 = test.dfs("Alexander");
        System.out.println(result1.toString());

        System.out.println(test.isRelated());

    }
}
