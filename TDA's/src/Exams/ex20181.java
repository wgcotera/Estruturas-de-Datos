package Exams;

import Graph.ALGraph.ALGraph;
import Tree.BinaryTree.BinaryTree;

import java.lang.management.BufferPoolMXBean;
import java.util.*;

public class ex20181 {

    /* *********************************************************************
     * Examen 2018 - Primer Termino - Segunda Evaluacion
     ******************************************************************** */



    //TEMA 3



    public static List<String> getTeamsByPhase(BinaryTree<String> teamsTree, String fase, HashMap<String, Integer> fases) {

        Integer level = fases.get(fase);
        List<String> result = new LinkedList<>();

        if (level != null ) {

            Queue<BinaryTree<String>> q = new LinkedList<>();
            q.offer(teamsTree);

            while(q.size() < Math.pow(2, level)) {
                BinaryTree<String> tmp = q.poll();
                q.offer(tmp.getLeft());
                q.offer(tmp.getRight());
            }

            while(!q.isEmpty()) {
                result.add(q.poll().getContent());
            }

        }
        return result;
    }

    // 1

    public static List<String> equiposDerrotadosFases(BinaryTree<String> teamsTree, String fase, HashMap<String, Integer> fases) {

//        List<String> nivelPedido = getTeamsByPhase(teamsTree, fase, fases);
//        List<String> ultimoNivel = getTeamsByPhase(teamsTree, "octavos", fases);
//
//        ultimoNivel.removeIf(nivelPedido::contains);

        Set<String> nivelPedido = new LinkedHashSet<>(getTeamsByPhase(teamsTree, fase, fases));
        Set<String> ultimoNivel = new LinkedHashSet<>(getTeamsByPhase(teamsTree, "octavos", fases));

        ultimoNivel.removeAll(nivelPedido);

        return new LinkedList<>(ultimoNivel);
    }

    // 2

    public static List<BinaryTree<String>> getPartidosSeleccion(BinaryTree<String> teamsTree, String seleccion) {
        List<BinaryTree<String>> result = new LinkedList<>();

        Queue<BinaryTree<String>> q = new LinkedList<>();
        q.offer(teamsTree);

        while(!q.isEmpty()) {
            BinaryTree<String> tmp = q.poll();
            if (tmp.getContent().equals(seleccion)) {
                result.add(tmp);
            }

            BinaryTree<String> left = tmp.getLeft();
            BinaryTree<String> right = tmp.getRight();

            if (left != null) {
                q.offer(left);
            }
            if (right != null) {
                q.offer(right);
            }
        }
        return result;
    }

    public static List<String> equiposEliminados(BinaryTree<String> teamsTree, String seleccion) {
        List<BinaryTree<String>> partidos = getPartidosSeleccion(teamsTree, seleccion);
        List<String> result = new LinkedList<>();

        for(BinaryTree<String> bt : partidos) {
            BinaryTree<String> right = bt.getRight();
            if (right != null) {
                result.add(bt.getRight().getContent());
            }
        }
        return result;
    }



    // TEMA 4



    // Asumiendo que estan implementados los siguientes metodos.
    // 1. Recorrido por anchura y profundidad (BFS(V data) y DFS(V data))
    // 2. Resetear los vertices a estado de "NO VISITADO" (resetVisited())

    // Asumiendo que estamos trabajando dentro del TDA ALGraph (para tener acceso a la lista de vertices y los metodos privados que contiene)

//    public int objetos() {
//        List<List<V>> list = new LinkedList<>();
//
//        for(ALGraph.Vertex<V,E> v : this.nodes) {
//
//            if (!v.isVisited) {
//                List<V> bfsTmp = this.bfsPrivate(v.content);
//
//                if(bfsTmp.size() > 1) {
//                    list.add(bfsTmp);
//                }
//            }
//        }
//        this.resetIsVisited();
//        return list.size();
//    }

    // Asumiendo que tenemos el metodo connectedComponents() en la clase ALGraph
    // retorna una lista con todas las componentes conexas

    // FORMA 1 DE HACERLO

//    public List<List<V>> connectedComponents() {
//        List<List<V>> paths = new LinkedList<>();
//        for (ALGraph.Vertex<V, E> vertex : this.nodes) {
//            if (!vertex.isVisited) {
//                paths.add(this.bfsPrivate(vertex.content));
//            }
//        }
//        this.resetIsVisited();
//        return paths;
//    }

    // FORMA 2 DE HACERLO

    public int objetos(ALGraph<Integer, Integer> graph) {
        List<List<Integer>> paths = graph.connectedComponents();
        int result = 0;
        for(List<Integer> list : paths) {
            result = list.size() > 1 ? result + 1: result;
        }
        
        return result;
    }

    // TEMA 5

//    private void dijkstra(V vertex) {
//        ALGraph.Vertex<V, E> start = this.getVertex(vertex);
//
//        if (start == null) return; //early exit
//
//        Queue<ALGraph.Vertex<V, E>> vertices = new PriorityQueue<>(Comparator.comparing((ALGraph.Vertex<V, E> v) -> v.distance));
//
//        start.distance = 0;
//        vertices.offer(start);
//
//        while(!vertices.isEmpty()) {
//            ALGraph.Vertex<V, E> current = vertices.poll();
//            current.isVisited = true;
//
//            for(ALGraph.Edge<E, V> e : current.edges) {
//                ALGraph.Vertex<V, E> target = e.target;
//                if (!target.isVisited) {
//                    int dist = e.weight + current.distance;
//                    if (dist < target.distance) {
//                        vertices.offer(target);
//                        target.predecesor = current;
//                        target.distance = dist;
//                    }
//                }
//            }
//        }
//    }
//
//    private List<Integer> getDistances(ALGraph.Vertex<V, E> source) {
//
//        this.dijkstra(source.content);
//
//        List<Integer> distancias = new ArrayList<>();
//
//        for (ALGraph.Vertex<V, E> target : this.nodes) {
//            distancias.add(target.distance);
//        }
//        this.resetIsVisited();
//        return distancias;
//    }
//
//    public Map<V, Double> centralidad() {
//
//        Map<V, Double> result = new HashMap<>();
//
//        for(ALGraph.Vertex<V, E> source : this.nodes) {
//
//
//            List<Integer> distancias = this.getDistances(source);
//
//            double sum = distancias.stream().reduce(0, Integer::sum);
//            double centrality = (this.nodes.size() - 1) / sum;
//
//            result.put(source.content, centrality);
//        }
//
//        return result;
//    }
}

