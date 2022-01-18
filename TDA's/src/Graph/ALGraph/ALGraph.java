package Graph.ALGraph;


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

        @Override
        public String toString(){
            return String.format("{ %s,  %s, %d, %s }", source, target, weight, data);
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

    public ALGraph(boolean isDirected, Comparator<V> comparator) {
        this.vertices = 0;
        this.isDirected = isDirected;
        this.nodes = new LinkedList<>();
        this.comparator = comparator;
    }

    public ALGraph(boolean isDirected) {
        this(isDirected, Comparator.naturalOrder());
    }
    public ALGraph(Comparator<V> comparator) {this(false, comparator);}
    public ALGraph() {
        this(false, Comparator.naturalOrder());
    }

    /* *********************************************************************
     * Public Methods
     ******************************************************************** */

    private void dijkstra(V vertex) {
        Vertex<V, E> start = this.getVertex(vertex);

        if (start == null) return; //early exit

        Queue<Vertex<V, E>> vertices = new PriorityQueue<>(Comparator.comparing((Vertex<V, E> v) -> v.distance));

        start.distance = 0;
        vertices.offer(start);

        while(!vertices.isEmpty()) {
            Vertex<V, E> current = vertices.poll();
            current.isVisited = true;

            for(Edge<E, V> e : current.edges) {
                Vertex<V, E> target = e.target;
                if (!target.isVisited) {
                    int dist = e.weight + current.distance;
                    if (dist < target.distance) {
                        vertices.offer(target);
                        target.predecesor = current;
                        target.distance = dist;
                    }
                }
            }
        }
    }

    public Map<V, Integer> shortestPath(V source, V target) {

        Vertex<V, E> vSource = this.getVertex(source);
        Vertex<V, E> vTarget = this.getVertex(target);
        if (vSource == null || vTarget == null) return null;

        this.dijkstra(source);

        Map<V, Integer> result = new LinkedHashMap<>();
        Vertex<V, E> pre = vTarget;

        while (pre.predecesor != null) {
            result.put(pre.content, pre.distance);
            pre = pre.predecesor;
        }

        this.resetIsVisited();

        return result;
    }

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
        Vertex<V, E> targetV = this.getVertex(target);

        if (sourceV != null && targetV != null && !this.containEdge(sourceV, targetV, weight, data)) {
            sourceV.edges.add(new Edge<>(sourceV, targetV, weight, data));
            if (!this.isDirected) {
                targetV.edges.add(new Edge<>(targetV, sourceV, weight, data));
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
        List<V> result = this.bfsPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    // Recorre el grafo en profundidad y devuelve una lista con los elementos ordenados segun el recorrido.
    public List<V> DFS(V vertex) {
        List<V> result = this.dfsPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    // Imprime los vertices del grafo (partiendo del primer vertice add) por anchura
    public void printBFS() {
        System.out.println(this.BFS(this.nodes.get(0).content));
    }



    // Imprime los vertices del grafo (partiendo del primer vertice add) por anchura
    public void printDFS() {
        System.out.println(this.DFS(this.nodes.get(0).content));
    }

    //PARA GRAFOS NO DIRIGIDOS
    // Devuelve una lista con todas las componentes conexas del grafo.
    public List<List<V>> connectedComponents() {
        List<List<V>> paths = new LinkedList<>();
        for (Vertex<V, E> vertex : this.nodes) {
            if (!vertex.isVisited) {
                paths.add(this.bfsPrivate(vertex.content));
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
    // Devuelve una lista con todas las componentes fuertemente conexas del grafo
    public List<Set<V>> stronglyConnectedComponents() {
        List<Set<V>> result = new LinkedList<>();
        ALGraph<V, E> reverseGraph = this.reverse();

        for(Vertex<V, E> v : this.nodes) {
            if (!v.isVisited) {
                Set<V> A = new LinkedHashSet<>(this.BFS(v.content));
                Set<V> D = new LinkedHashSet<>(reverseGraph.BFS(v.content));

                System.out.println("\n"+ "N = " + A);
                System.out.println("R = " +D);

                D.retainAll(A);
                result.add(D);
                result.forEach(s -> s.forEach(c -> this.getVertex(c).isVisited = true));

                System.out.println("strongly Connected Component " +D);
            }
        }
        this.resetIsVisited();
        return result;
    }
    //PARA GRAFOS DIRIGIDOS
    // Devuelve verdadero si es grafo es fuertemente conexo
    public  boolean isStronglyConnected() {
        return this.stronglyConnectedComponents().size() == 1;
    }

    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

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

    //Regresa el estado de los vertices "isVisited" a falso
    private void resetIsVisited() {
        for(Vertex<V, E> vertex : nodes) {
            vertex.isVisited = false;
        }
    }

    private  List<V> bfsPrivate(V vertex) {

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

    public List<V> dfsPrivate(V vertex) {

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
