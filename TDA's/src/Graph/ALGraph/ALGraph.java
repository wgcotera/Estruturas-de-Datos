package Graph.ALGraph;


import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class ALGraph<V extends Comparable<V>, E> {

    /* *********************************************************************
     * Inner Classes
     ******************************************************************** */

    private class Vertex<V extends Comparable<V>, E> {
        private final V content;
        private final List<Edge<E, V>> edges;
        private boolean  isVisited;

        private int distance;
        private Vertex<V, E> predecesor;

        private Vertex(V content) {
            this.content = content;
            this.edges = new LinkedList<>();
            this.isVisited = false;
        }

        @Override
        public String toString() {
            return content.toString();
        }

        private void printVertex() {
            System.out.println("{ source = " + this + " }");
        }
    }

    private class Edge<E, V extends Comparable<V>> {
        private final Vertex<V, E> source;
        private final Vertex<V, E> target;
        private final int weight;
        private final E data;

        private Edge(Vertex<V, E> source, Vertex<V, E> target, int weight, E data) {
            this.source = source;
            this.target = target;
            this.weight = weight;
            this.data = data;
        }

        private Edge(Vertex<V, E> source, Vertex<V, E> target) {
            this(source, target,1, null);
        }

        private void printEdge() {
            System.out.printf("{ source = %s \t target = %s \t weight = %d \t data = %s }\n",
                                        source.toString(), target.toString(), weight, data.toString());
        }
    }

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private int vertices;
    private final LinkedList<Vertex<V, E>> nodes;
    private final boolean isDirected;
    private final Comparator<V> comparator;

    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public ALGraph(boolean isDirected,  Comparator<V> comparator) {
        this.vertices = 0;
        this.isDirected = isDirected;
        this.nodes = new LinkedList<>();
        this.comparator = comparator;
    }

    public ALGraph(boolean isDirected) {
        this(isDirected, Comparator.naturalOrder());
    }
    public ALGraph(Comparator<V> comparator) {this(false, comparator);}
    public  ALGraph() {
        this(false, Comparator.naturalOrder());
    }

    /* *********************************************************************
     * Public Methods
     ******************************************************************** */

    public int countVertices() {
        return this.vertices;
    }

    public ALGraph<V, E> addVertex(V vertex) {
        if (vertex != null && !this.containVertex(vertex)) {
            this.nodes.add(new Vertex<>(vertex));
            vertices++;
        }
        return this;
    }

    // Agrega una conneccion entre dos vertices vertex1 y vertex2
    public void addEdge(V source, V target, int weight, E data) {

        Vertex<V, E> sourceV = this.getVertex(source);
        Vertex<V, E> targetv = this.getVertex(target);

        if (sourceV != null && targetv != null && !this.containEdge(sourceV, targetv, weight, data)) {
            sourceV.edges.add(new Edge<>(sourceV, targetv, weight, data));
            if (!this.isDirected) {
                targetv.edges.add(new Edge<>(sourceV, targetv, weight, data));
            }
        }
    }

    public void addEdge(V source, V target) {
        this.addEdge(source, target, 1, null);
    }

    // Remueve una coneccion especifica entre los vertices vertex1 y vertex2
    public void removeEdge(V vertex1, V vertex2, int weight, E data) {

        final Vertex<V, E> source = getVertex(vertex1);
        final Vertex<V, E> target = getVertex(vertex2);

        if (source != null && target != null) {

            Edge<E, V> tmpEdge1 = new Edge<>(source, target, weight, data);
            source.edges.removeIf(e -> equalEdges(e, tmpEdge1));

            if(!isDirected) {
                Edge<E, V> tmpEdge2 = new Edge<>(target, source, weight, data);
                target.edges.removeIf(e -> equalEdges(e,tmpEdge2));
            }
        }
    }

    // remueve todas las conecciones existentes entre los vertices vertex1 y vertex2
    public void disconnect(V vertex1, V vertex2) {

        final Vertex<V, E> source = getVertex(vertex1);
        final Vertex<V, E> target = getVertex(vertex2);

        if (source != null && target != null) {
            source.edges.removeIf(e -> vertexEquals(e.target.content, vertex2));

            if(!isDirected) {
                target.edges.removeIf(e -> vertexEquals(e.target.content, vertex1));
            }
        }
    }

    // Remueve un vertice y todas las conexiones asociadas a el del grafo
    public ALGraph<V, E> removeVertex(V vertex) {
        Vertex<V, E> vert = this.getVertex(vertex);
        if (vert != null) {
            this.nodes.forEach(v -> v.edges.removeIf(e -> vertexEquals(vertex, e.target.content)));
            this.nodes.remove(vert);
        }
        return this;
    }

    // Recorre el grafo en anchura y devuelve una lista con los elementos ordenados segun el recorrido.
    public List<V> BFS(V vertex) {
        List<V> result = this.bsfPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    // Recorre el grafo en profundidad y devuelve una lista con los elementos ordenados segun el recorrido.
    public List<V> DFS(V vertex) {
        List<V> result = this.dsfPrivate(vertex);
        this.resetIsVisited();
        return result;
    }
    //PARA GRAFOS NO DIRIGIDOS
    // Devuelve una lista con todas las componentes conexas del grafo.
    public List<List<V>> connectedComponents() {
        List<List<V>> paths = new LinkedList<>();
        for (Vertex<V, E> vertex : this.nodes) {
            if (!vertex.isVisited) {
                paths.add(this.bsfPrivate(vertex.content));
            }
        }
        this.resetIsVisited();
        return paths;
    }
    //PARA GRAFOS NO DIRIGIDOS
    // Devuelve verdadero si es grafo es conexo
    public boolean isConnected() {
        return this.connectedComponents().size() == 1;
    }

    //PARA GRAFOS DIRIGIDOS
    public List<Set<V>> stronglyConnectedComponents() {
        List<Set<V>> result = new LinkedList<>();
        ALGraph<V, E> reverseGraph = this.reverse();

        for(Vertex<V, E> v : this.nodes) {
            if (!v.isVisited) {
                Set<V> A = this.getTargetsOf(v.content);
                Set<V> D = reverseGraph.getTargetsOf(v.content);

                System.out.println("\n"+ "N = " + A);
                System.out.println("R = " +D);
                if (D != null && A != null) {
                    D.retainAll(A);
                    result.add(D);
                    this.nodes.stream().filter(vertex -> D.contains(vertex.content)).forEach(vertex -> vertex.isVisited = true);
                }
                System.out.println("strongly Connected Component " +D);
            }
        }
        this.resetIsVisited();
        return result;
    }

    public  boolean isStronglyConnected() {
        return this.stronglyConnectedComponents().size() == 1;
    }

    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

    private Set<V> getTargetsOf(V vertex) {
        Vertex<V, E> source = this.getVertex(vertex);
        if(source == null) {
            return null;
        }
        Set<V> result = new LinkedHashSet<>(this.DFS(vertex));
        return result;
    }
    private ALGraph<V, E> reverse() {
        ALGraph<V, E> result = new ALGraph<>(true);

        this.nodes.forEach(v -> result.addVertex(v.content));

        this.nodes.forEach(v -> v.edges
                        .forEach(e -> result.addEdge(e.target.content, e.source.content, e.weight, e.data)));

        return result;
    }

    private boolean vertexEquals(V vertex1, V vertex2) {
        return vertex1 != null && vertex2 != null && this.comparator.compare(vertex1, vertex2) == 0;
    }

    private boolean equalEdges(Edge<E, V> edge1, Edge<E, V> edge2) {
        boolean source = vertexEquals(edge1.source.content, edge2.source.content);
        boolean target = vertexEquals(edge1.target.content, edge2.target.content);
        return source && target && edge1.data == edge2.data && edge1.weight == edge2.weight;
    }

    private Vertex<V, E> getVertex(V vertex) {
        if (this.vertices != 0 && vertex != null) {
            for (Vertex<V, E> node : nodes) {
                if (vertexEquals(vertex, node.content)) {
                    return node;
                }
            }
        }
        return null;
    }

    private boolean containVertex(V vertex) {
        if (this.vertices != 0 && vertex != null) {
            for (Vertex<V, E> v : nodes) {
                if (vertexEquals(vertex, v.content)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containEdge(Vertex<V,E> source, Vertex<V,E> target, int weight, E data) {

        if(source != null && target != null) {

            Edge<E, V> tmpEdge = new Edge<>(source, target, weight, data);

            for (Edge<E, V> edge : source.edges) {
                if (equalEdges(tmpEdge, edge)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void resetIsVisited() {
        for(Vertex<V, E> vertex : nodes) {
            vertex.isVisited = false;
        }
    }

    private  List<V> bsfPrivate(V vertex) {

        List<V> result = new LinkedList<>();
        Vertex<V, E> source = this.getVertex(vertex);

        if(source != null) {

            Queue<Vertex<V, E>> queue = new LinkedList<>();
            queue.offer(source);

            while(!queue.isEmpty()) {

                Vertex<V, E> tmpVertex = queue.poll();

                if(!tmpVertex.isVisited){
                    result.add(tmpVertex.content);
                    tmpVertex.isVisited = true;

                    for(Edge<E, V> edge : tmpVertex.edges) {
                        if (!edge.target.isVisited) {
                            queue.offer(edge.target);
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<V> dsfPrivate(V vertex) {

        List<V> result = new LinkedList<>();
        Vertex<V, E> source = this.getVertex(vertex);

        if (source != null) {

            Deque<Vertex<V, E>> stack = new ArrayDeque<>();
            stack.push(source);

            while(!stack.isEmpty()) {

                Vertex<V, E> tmpVertex = stack.pop();

                if (!tmpVertex.isVisited) {
                    result.add(tmpVertex.content);
                    tmpVertex.isVisited = true;

                    for (Edge<E, V> edge : tmpVertex.edges) {
                        stack.push(edge.target);
                    }
                }
            }
        }
        return result;
    }
}
