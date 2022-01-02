package Graph.ALGraph;


import java.util.*;

public class ALGraph<V extends Comparable<V>, E> {

    /* *********************************************************************
     * Inner Classes
     ******************************************************************** */

    private class Node<V extends Comparable<V>, E> {
        private V content;
        private LinkedList<Edge<E, V>> edges;
        private boolean isVisited;

        private int distance;
        private Node<V, E> predecesor;

        public Node(V content) {
            this.content = content;
            this.edges = new LinkedList<>();
            this.isVisited = false;
        }
    }

    private class Edge<E, V extends Comparable<V>> {
        private E data;
        private Node<V, E> source;
        private Node<V, E> target;
        private int weight;

        public Edge(E data, Node<V, E> source, Node<V, E> target, int weight) {
            this.data = data;
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public Edge(Node<V, E> source, Node<V, E> target) {
            this(null, source, target,1);
        }

    }

    /* *********************************************************************
     * Parameters
     ******************************************************************** */

    private int vertices;
    private LinkedList<Node<V, E>> nodes;
    private boolean isDirected;
    private Comparator<V> comparator;

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

    public ALGraph<V, E> addVertex(V vertex) {
        if (vertex != null && !this.containVertex(vertex)) {
            this.nodes.add(new Node<>(vertex));
            vertices++;
        }
        return this;
    }

    public void addEdge(V vertex1, V vertex2) {

        Node<V, E> source = this.getNode(vertex1);
        Node<V, E> target = this.getNode(vertex2);

        if (source != null && target != null && !this.containEdge(vertex1, vertex2)) {
            source.edges.add(new Edge<>(source, target));
            if (!this.isDirected) {
                target.edges.add(new Edge<>(target, source));
            }
        }
    }

    public List<V> bfs(V vertex) {
        List<V> result = this.bfsPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    public List<V> dfs(V vertex) {
        List<V> result = this.dfsPrivate(vertex);
        this.resetIsVisited();
        return result;
    }

    public boolean isRelated() {
        List<List<V>> paths = new LinkedList<>();
        for (Node<V, E> node : this.nodes) {
            if (!node.isVisited) {
                paths.add(this.bfsPrivate(node.content));
            }
        }
        this.resetIsVisited();
        return paths.size() == 1;
    }

    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

    private int vertexCompare(V vertex1, V vertex2) {
        return this.comparator.compare(vertex1, vertex2);
    }

    private boolean equalEdges(Edge<E, V> edge1, Edge<E, V> edge2) {
        int sourceCmp = vertexCompare(edge1.source.content, edge2.source.content);
        int targetCmp = vertexCompare(edge1.target.content, edge2.target.content);
        return sourceCmp == 0 && targetCmp == 0;
    }

    private Node<V, E> getNode(V vertex) {
        if (this.vertices != 0 && vertex != null) {
            for (Node<V, E> node : nodes) {
                if (vertexCompare(vertex, node.content) == 0) {
                    return node;
                }
            }
        }
        return null;
    }

    private boolean containVertex(V vertex) {
        if (this.vertices != 0 && vertex != null) {
            for (Node<V, E> node : nodes) {
                if (vertexCompare(vertex, node.content) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containEdge(V vertex1, V vertex2) {

        Node<V, E> source = this.getNode(vertex1);
        Node<V, E> target = this.getNode(vertex2);

        if(source != null && target != null) {

            Edge<E, V> tmpEdge = new Edge<>(source, target);

            for (Edge<E, V> edge : source.edges) {
                if (equalEdges(tmpEdge, edge)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void resetIsVisited() {
        for(Node<V, E> node : nodes) {
            node.isVisited = false;
        }
    }

    private  List<V> bfsPrivate(V vertex) {

        List<V> result = new LinkedList<>();
        Node<V, E> source = this.getNode(vertex);

        if(source != null) {

            Queue<Node<V, E>> queue = new LinkedList<>();
            queue.offer(source);

            while(!queue.isEmpty()) {

                Node<V, E> tmpNode = queue.poll();

                if(!tmpNode.isVisited){
                    result.add(tmpNode.content);
                    tmpNode.isVisited = true;

                    if(!tmpNode.edges.isEmpty()) {
                        for(Edge<E, V> edge : tmpNode.edges) {
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
        Node<V, E> source = this.getNode(vertex);

        if (source != null) {

            Deque<Node<V, E>> stack = new ArrayDeque<>();
            stack.push(source);

            while(!stack.isEmpty()) {

                Node<V, E> tmpNode = stack.pop();

                if (!tmpNode.isVisited) {
                    result.add(tmpNode.content);
                    tmpNode.isVisited = true;

                    if (!tmpNode.edges.isEmpty()) {
                        for (Edge<E, V> edge : tmpNode.edges) {
                            stack.push(edge.target);
                        }
                    }
                }
            }
        }
        return result;
    }
}
