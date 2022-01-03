package Graph.AMGraph;

import java.util.Comparator;

public class AMGraph<V extends Comparable<V>> {

    /* *********************************************************************
     * Parameters
     ******************************************************************** */
    public static final int DEFAULT_CAPACITY = 10;
    public static final int DEFAULT_MATRIX_VALUE = Integer.MAX_VALUE;

    private     V[]                     vertices;
    private     boolean            isDirected;
    private     int                     capacity;
    private     int                     size;
    private     int[][]                 matrix;
    private Comparator<V>   cmpVertex;

    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public AMGraph(boolean isDirected, int capacity, Comparator<V> cmpVertex) {
        this.vertices = (V[]) new Comparable[capacity];
        this.isDirected = isDirected;
        this.capacity = capacity;
        this.size = 0;
        this.matrix = new int[capacity][capacity];
        this.initMatrix(this.matrix);
        this.cmpVertex = cmpVertex;
    }

    public AMGraph(boolean isDirected, Comparator<V> cmpVertex) {
        this(isDirected, DEFAULT_CAPACITY, cmpVertex);
    }

    public AMGraph(boolean isDirected) {
        this(isDirected, DEFAULT_CAPACITY, Comparator.naturalOrder());
    }

    public AMGraph() {
        this(false, DEFAULT_CAPACITY, Comparator.naturalOrder());
    }

    /* *********************************************************************
     * Public Methods
     ******************************************************************** */


    public AMGraph<V> addVertex(V vertex) {
        if (!this.containVertex(vertex)) {
            if (this.isFull()) {
                this.grow();
            }
            vertices[size++] = vertex;
        }
        return this;
    }

    public void addEdge(V v1, V v2) {
        this.addEdge(v1, v2, 1);
    }

    public void addEdge(V v1, V v2, int weight) {
        int v1Index = this.indexOf(v1);
        int v2Index = this.indexOf(v2);

        if (v1Index != 1 && v2Index != -1) {
            this.matrix[v1Index][v2Index] = weight;
            if (!this.isDirected) {
                this.matrix[v2Index][v1Index] = weight;
            }
        }
    }


    /* *********************************************************************
     * Private Methods
     ******************************************************************** */

    private void initMatrix(int[][] matrix) {
        for (int i = 0 ; i < matrix.length; i++) {
            for ( int j = 0 ; j < matrix.length ; j++) {
                matrix[i][j] = DEFAULT_MATRIX_VALUE;
            }
        }
    }

    private int vertexCompare(V vertex1, V vertex2) {
        return this.cmpVertex.compare(vertex1, vertex2);
    }

    private boolean containVertex(V vertex) {
        if (vertex != null) {
            for (V v : this.vertices) {
                if (vertexCompare(v, vertex) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int indexOf(V vertex) {
        int i = -1;
        if (this.containVertex(vertex)) {
            for (V v : this.vertices) {
                if (vertexCompare(v, vertex) == 0) {
                    break;
                }
                i++;
            }
        }
        return i;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private void grow() {

        this.capacity = this.capacity + (this.capacity >> 1);

        // grow array of vertices
        V[] tmp = (V[]) (new Comparable[this.capacity]);
        for (int i = 0; i < this.size; i++) {
            tmp[i] = this.vertices[i];
        }
        this.vertices = tmp;

        // grow matrix
        this.growMatrix(this.capacity);
    }

    private int[] growArray(int capacity, int[] array) {

        int[] tmp = new int[capacity];
        System.arraycopy(array, 0, tmp, 0, array.length);
        return tmp;
    }

    private void growMatrix(int capacity) {

        int[][] tmp = new int[capacity][capacity];
        this.initMatrix(tmp);

        for (int i = 0 ; i < this.matrix.length ; i ++ ) {
            tmp[i] = this.growArray(capacity, this.matrix[i]);
        }

        this.matrix = tmp;
    }
}
