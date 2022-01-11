package Graph.ALGraph;

import java.util.List;

public class ALGraphTest {
    public static void main(String[] args) {
//        ALGraph<String, String> test = new ALGraph<>(true);
//        test.addVertex("Alex").addVertex("Alina").addVertex("Juan A").addVertex("Genaro").addVertex("Steven").addVertex("Eliana").addVertex("Gia");
//        test.addEdge("Alex", "Alina", 9, "novios");
//        test.addEdge("Alina", "Genaro",8, "amigos");
//        test.addEdge("Alex", "Juan A", 7, "amigos");
//        test.addEdge("Juan A", "Steven", 6, "compañeros");
//        test.addEdge("Alina", "Eliana", 5, "amigas");
//        test.addEdge("Juan", "Genaro", 4, "amigos");
////        test.addEdge("Maria", "Anabel", 3, "familia");
//        List<String> result = test.BFS("Alex");
//        System.out.println(result.toString());
//        List<String> result1 = test.DFS("Alex");
//        System.out.println(result1.toString());
//        System.out.println(test.isStronglyConnected());



        ALGraph<String, Integer> test1 = new ALGraph<>(true);
        test1.addVertex("B").addVertex("S").addVertex("C").addVertex("F").addVertex("H");
        test1.addEdge("B", "S");
        test1.addEdge("C", "B");
        test1.addEdge("F", "C");
        test1.addEdge("F", "S");
        test1.addEdge("S", "C");
        test1.addEdge("H", "B");
        test1.addEdge("H", "F");

        System.out.println(test1.isStronglyConnected());




    }
}
