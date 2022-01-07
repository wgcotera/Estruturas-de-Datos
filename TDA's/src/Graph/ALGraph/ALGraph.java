package Graph.ALGraph;


import java.util.*;

public class ALGraph<V extends Comparable<V>, E> {

    /* *********************************************************************
     * Inner Classes
     ******************************************************************** */

    private class Vertex<V extends Comparable<V>, E> {
        private final V content;
        private final LinkedList<Edge<E, V>> edges;
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

    public void addEdge(V vertex1, V vertex2, int weight, E data) {

        Vertex<V, E> source = this.getVertex(vertex1);
        Vertex<V, E> target = this.getVertex(vertex2);

        if (source != null && target != null && !this.containEdge(source, target, weight, data)) {
            source.edges.add(new Edge<>(source, target, weight, data));
            if (!this.isDirected) {
                target.edges.add(new Edge<>(source, target, weight, data));
            }
        }
    }

    public LinkedList<V> BSF(V vertex) {
        LinkedList<V> result = this.bsfPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    public LinkedList<V> DSF(V vertex) {
        LinkedList<V> result = this.dsfPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    public LinkedList<List<V>> paths() {
        LinkedList<List<V>> paths = new LinkedList<>();
        for (Vertex<V, E> vertex : this.nodes) {
            if (!vertex.isVisited) {
                paths.add(this.bsfPrivate(vertex.content));
            }
        }
        this.resetIsVisited();
        return paths;
    }

    public boolean isRelated() {
        LinkedList<List<V>> paths = this.paths();
        return paths.size() == 1;
    }

    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

    private boolean vertexEquals(V vertex1, V vertex2) {
        return this.comparator.compare(vertex1, vertex2) == 0;
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

    private  LinkedList<V> bsfPrivate(V vertex) {

        LinkedList<V> result = new LinkedList<>();
        Vertex<V, E> source = this.getVertex(vertex);

        if(source != null) {

            Queue<Vertex<V, E>> queue = new LinkedList<>();
            queue.offer(source);

            while(!queue.isEmpty()) {

                Vertex<V, E> tmpVertex = queue.poll();

                if(!tmpVertex.isVisited){
                    result.add(tmpVertex.content);
                    tmpVertex.isVisited = true;

                    if(!tmpVertex.edges.isEmpty()) {
                        for(Edge<E, V> edge : tmpVertex.edges) {
                            queue.offer(edge.target);
                        }
                    }
                }
            }
        }
        return result;
    }

    public LinkedList<V> dsfPrivate(V vertex) {

        LinkedList<V> result = new LinkedList<>();
        Vertex<V, E> source = this.getVertex(vertex);

        if (source != null) {

            Deque<Vertex<V, E>> stack = new ArrayDeque<>();
            stack.push(source);

            while(!stack.isEmpty()) {

                Vertex<V, E> tmpVertex = stack.pop();

                if (!tmpVertex.isVisited) {
                    result.add(tmpVertex.content);
                    tmpVertex.isVisited = true;

                    if (!tmpVertex.edges.isEmpty()) {
                        for (Edge<E, V> edge : tmpVertex.edges) {
                            stack.push(edge.target);
                        }
                    }
                }
            }
        }
        return result;
    }
}
