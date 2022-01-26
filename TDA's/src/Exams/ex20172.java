package Exams;

import Tree.BinaryTree.BinaryTree;

import java.util.*;

public class ex20172 {

    /* *********************************************************************
     * Examen 2017 - Segundo Termino - Segunda Evaluacion
     ******************************************************************** */



    //TEMA 3



    public static BinaryTree<String> crearArbolMorse(HashMap<String, List<String>> mapa) {
        BinaryTree<String> result = new BinaryTree<>();
        crearArbolMorse(reverseMap(mapa), "", result);
        return result;
    }

    public static Map<List<String>, String> reverseMap(Map<String, List<String>> mapa) {
        Map<List<String>, String> treeMap = new TreeMap<>((l1, l2) -> l2.size() - l1.size());
        for (String s : mapa.keySet()) {
            treeMap.put(mapa.get(s), s);
        }
        return treeMap;
    }

    public static void crearArbolMorse(Map<List<String>, String> mapa, String str, BinaryTree<String> result) {

        if (str.length() > 4) {
            return;
        }

        String sleft = str + "_";
        String sRight = str + ".";

        List<String> lLeft = new LinkedList<>();
        List<String> lRight = new LinkedList<>();

        for (char c : sleft.toCharArray()) {
            lLeft.add(Character.toString(c));
        }
        for (char c : sRight.toCharArray()) {
            lRight.add(Character.toString(c));
        }

        result.setLeft(mapa.get(lLeft) != null ? mapa.get(lLeft) : "_");
        result.setLeft(mapa.get(lRight) != null ? mapa.get(lRight) : ".");

        crearArbolMorse(mapa, sleft, result.getLeft());
        crearArbolMorse(mapa, sRight, result.getRight());
    }


    //Asumiendo que la representacion de una pausa para cambio de letra esta representada por un string vacio
    //Asumiendo que la representacion de una pausa para cambio de palabra esta representada por un espacio
    //Entonces podria usar esa separacion para extraer las distintas letras codificadas.

    public static String codificarMorse(List<String> codigos, BinaryTree<String> tree) {

        StringBuilder result = new StringBuilder();
        StringBuilder str = new StringBuilder();
        BinaryTree<String> tmp = tree;

        for(String s : codigos) {
            if(s.equals("")) {
                result.append(tmp.getContent());
                str = new StringBuilder();
            }

            if(s.equals(" ")) {
                result.append(s);
            } else {
                str.append(s);
            }

        }
        return result.toString();
    }




    // TEMA 4




    //Mas detallado en la calse Arbol con una prueba

//    public double estimarProbabilidad(String evento) {
//
//        List<Arbol.Nodo> nodos = new LinkedList<>();
//        this.getProbabilidades(raiz.probabilidad, nodos, raiz);
//
//        return nodos.stream()
//                .filter(nodo -> nodo.getContent().equals(evento))
//                .mapToDouble(n -> n.probabilidad)
//                .sum();
//    }
//
//    public void getProbabilidades(double acum, List<Arbol.Nodo> nodos, Arbol.Nodo nodo) {
//
//        if (nodo.isLeaf()) {
//            Arbol.Nodo tmp = new Arbol.Nodo(nodo.getContent(), acum);
//            nodos.add(tmp);
//            return;
//        }
//        getProbabilidades(acum*nodo.izq.probabilidad, nodos, nodo.izq);
//        getProbabilidades(acum*nodo.der.probabilidad, nodos, nodo.der);
//    }




    // TEMA 5










}
