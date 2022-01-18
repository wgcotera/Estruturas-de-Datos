package Graph.ALGraph;

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



        ALGraph<String, Integer> grafo = new ALGraph<>(true);
        grafo.addVertex("V1");
        grafo.addVertex("V2");
        grafo.addVertex("V3");
        grafo.addVertex("V4");
        grafo.addVertex("V5");
        grafo.addVertex("V6");

        grafo.addEdge("V1", "V2", 3, null);
        grafo.addEdge("V1", "V3", 4, null);
        grafo.addEdge("V1", "V5", 8, null);
        grafo.addEdge("V2", "V5", 5, null);
        grafo.addEdge("V3", "V5", 3, null);
        grafo.addEdge("V5", "V4", 7, null);
        grafo.addEdge("V5", "V6", 3, null);
        grafo.addEdge("V6", "V4", 2, null);

        System.out.println("*****************GRAFO IMPRESO EN ANCHURA*****************");
        grafo.printBFS();
        System.out.println("\n\n*****************GRAFO IMPRESO EN PROFUNDIDAD*****************");
        grafo.printDFS();
        System.out.println("\n\n*****************COMPONENTES CONEXAS*****************");
        System.out.println(grafo.connectedComponents());
    }
}
