package Exams;

import Graph.ALGraph.ALGraph;
import Graph.AMGraph.AMGraph;
import Tree.BinaryTree.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ex20171 {
    public static void main(String[] args) {

    }

    /* *********************************************************************
     * Examen 2017 - Primer Termino - Segunda Evaluacion
     ******************************************************************** */



    // TEMA 3



    public BinaryTree<Integer> crearArbolHeap(int inicio, int levels) {
        BinaryTree<Integer> result = new BinaryTree<>(inicio);
        LinkedList<BinaryTree<Integer>> q= new LinkedList<>();
        q.add(result);

        while (q.size() < Math.pow(2, levels-1)) {
            LinkedList<BinaryTree<Integer>> tmpList = new LinkedList<>();

            while (!q.isEmpty()) {
                BinaryTree<Integer> tmp = q.removeFirst();
                tmp.setLeft(tmp.getContent()*2+1);
                tmp.setRight(tmp.getContent()*2+2);
                tmpList.add(tmp.getLeft());
                tmpList.add(tmp.getRight());
            }
            q.addAll(tmpList);
        }
        return result;
    }



    // TEMA 4




    // Asumimos que las respuesta "NO" y "YES" corresponden a los hijos izquierdo y derecho respectivamente.

    public String decision(BinaryTree<String> bt, List<String> respuestas) {

        BinaryTree<String> tmp = bt;

        if(!tmp.isLeaf()) {

            for (String rst : respuestas) {

                tmp = rst.equals("YES") ? tmp.getRight() : tmp.getLeft();

                if (tmp.isLeaf()) {
                    return tmp.getContent();
                }
            }
        }
        return "INCERTIDUMBRE";
    }



    // TEMA 5



    public boolean escalera(ALGraph<String, String> A, String start, String end, int pasos) {
        // Este mapa contiene el recorrido desde start hasta end donde las claves son las palabras y el value corresponde a la distancia que se va acumulando.
        // El metodo shortestPath se implemeto usando internamente el algoritmo de Dijkstra.
        Map<String, Integer> shortestPath = A.shortestPath(start, end);
        // El ultimo elemento agregado al mapa corresponde a key = end y tiene asociado el valor de la distancia total reccorrida.
        // Se retorna si la distancia cumple con lo especificado.
        return shortestPath.get(end) <= pasos;
    }

    // TEMA 6
    public <S extends Comparable<S>> AMGraph<S> algoritmoMenorCableado(AMGraph<S> grafo) {
        // Generar todos los posibles caminos y retornar el de menor valor
        /*
        FALTA
         */
        return grafo;
    }
}
