package Exams;

import Tree.BinaryTree.BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Arbol {
    Nodo raiz;

    public class Nodo {
        String evento;
        double probabilidad;
        Nodo izq , der;

        public Nodo(String evento, double probabilidad) {
            this.evento = evento;
            this.probabilidad = probabilidad;
        }

        public boolean isLeaf() {
            return der == null && izq == null;
        }
        public String getContent() {
            return evento;
        }

        public Nodo getLeft() {
            return izq;
        }

        public Nodo getRight() {
            return der;
        }

        public void setLeft(String data, double probabilidad) {
            this.izq = new Nodo(data, probabilidad);
        }
        public void setRight(String data, double probabilidad) {
            this.der = new Nodo(data, probabilidad);
        }
    }

    public void setLeft(String data, double probabilidad) {
        raiz.setLeft(data,probabilidad);
    }
    public void setRight(String data, double probabilidad) {
        raiz.setRight(data,probabilidad);
    }

    public Arbol(String data, double probabilidad) {
        this.raiz = new Nodo(data,probabilidad);
    }

    public Nodo getLeft() {
        return this.raiz.izq;
    }

    public Nodo getRight() {
        return this.raiz.der;
    }

    public double estimarProbabilidad(String evento) {

        List<Nodo> nodos = new LinkedList<>();
        this.getProbabilidades(raiz.probabilidad, nodos, raiz);

        return nodos.stream()
                .filter(nodo -> nodo.getContent().equals(evento))
                .mapToDouble(n -> n.probabilidad)
                .sum();
    }

    public void getProbabilidades(double acum, List<Nodo> nodos, Nodo nodo) {

        if (nodo.isLeaf()) {
            Nodo tmp = new Nodo(nodo.getContent(), acum);
            nodos.add(tmp);
            return;
        }
        getProbabilidades(acum*nodo.izq.probabilidad, nodos, nodo.izq);
        getProbabilidades(acum*nodo.der.probabilidad, nodos, nodo.der);
    }

    public static void main(String[] args) {
        Arbol tree = new Arbol("raiz", 1);

        tree.setLeft("principal", 0.8);
        tree.getLeft().setLeft("entrenamiento", 0.6);
        tree.getLeft().setRight("amistoso", 0.4);
        tree.getLeft().getRight().setLeft("arquero", 0.6);
        tree.getLeft().getRight().setRight("no arquero", 0.4);

        tree.setRight("suplente", 0.2);
        tree.getRight().setLeft("entrenamiento", 0.5);
        tree.getRight().setRight("amistoso", 0.5);
        tree.getRight().getRight().setLeft("arquero", 0.6);
        tree.getRight().getRight().setRight("no arquero", 0.4);

        System.out.println(tree.estimarProbabilidad("arquero"));
    }
}
