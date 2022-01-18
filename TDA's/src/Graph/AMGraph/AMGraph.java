package Graph.AMGraph;

import java.util.Comparator;
import java.util.stream.IntStream;

public class AMGraph<V extends Comparable<V>> {

    /* *********************************************************************
     * Parameters
     ******************************************************************** */
    private  static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_MATRIX_VALUE = Integer.MAX_VALUE;

    private     V[] vertices;
    private     final boolean isDirected;
    private     int capacity;
    private     int size;
    private     int[][] matrix;
    private     final Comparator<V> cmpVertex;

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
            vertices[this.size++] = vertex;
        }
        return this;
    }

    public void addEdge(V v1, V v2, int weight) {
        int v1Index = this.indexOf(v1);
        int v2Index = this.indexOf(v2);

        if (v1Index != -1 && v2Index != -1) {
            this.matrix[v1Index][v2Index] = weight;
            if (!this.isDirected) {
                this.matrix[v2Index][v1Index] = weight;
            }
        }
    }

    public void addEdge(V v1, V v2) {
        this.addEdge(v1, v2, 1);
    }

    public void removeEdge(V v1, V v2) {
        this.addEdge(v1, v2, DEFAULT_MATRIX_VALUE);
    }

    public boolean isAdyacent(V v1, V v2) {

        int v1Index = this.indexOf(v1);
        int v2Index = this.indexOf(v2);

        if (v1Index != -1 && v2Index != -1) {
            return this.matrix[v1Index][v2Index] != DEFAULT_MATRIX_VALUE;
        }

        return false;
    }

    private <T> void  shiftLeft(int index, T[] array) {
        if (this.size - index >= 0) System.arraycopy(array, index + 1, array, index, this.size - index);
        array[this.size - 1] = null;
    }

    private void  shiftLeft(int index, int[] array) {
        if (this.size - index >= 0) System.arraycopy(array, index + 1, array, index, this.size - index);
        array[this.size - 1] = DEFAULT_MATRIX_VALUE;
    }

    public V removeVertex(V vertex) {

        int vertexIdx = this.indexOf(vertex);
        if (vertexIdx == -1) {return null; }
        V removedVertex = this.vertices[vertexIdx];

        this.shiftLeft(vertexIdx, this.vertices);

        this.shiftLeft(vertexIdx, this.matrix);

        for (int i = 0 ; i < this.size - 1  ; i++) {
            this.shiftLeft(vertexIdx, this.matrix[i]);
        }

        this.size--;


        return removedVertex;
    }

    public int outDegree(V vertex) {

        int vertexIdx = this.indexOf(vertex);
        if (vertexIdx == -1) {return -1; }

        int count = 0;
        for (int i : this.matrix[vertexIdx]) {
            if (i != DEFAULT_MATRIX_VALUE) {
                count++;
            }
        }
        return count;
    }

    public int inDegree(V vertex) {

        int vertexIdx = this.indexOf(vertex);
        if (vertexIdx == -1) {return -1; }

        int counter = 0;

        for (int i = 0 ; i < this.size ; i++) {
            if (this.matrix[i][vertexIdx] != DEFAULT_MATRIX_VALUE) {
                counter++;
            }
        }
        return counter;
    }

    public void print() {
        for (V vertex : vertices) {
            if (vertex != null) {
                System.out.println(vertex);
            }
        }
    }

    public int[][] floydWarshall() {
        int[][] A = this.matrix.clone();
        for (int i = 0 ; i < size ; i++) { //Recorro cada vertice
            for (int m = 0 ; m < size ; m++) { //Recorro cada fila
                for (int n = 0 ; n < size ; n++) { // Recorro cada columna de la fila.
                    A[m][n] = Math.min(A[m][n], A[m][i] + A[i][n]);
                }
            }
        }
        return A;
    }

    public boolean[][] warshall() {
        boolean[][] A =  new boolean[this.size][this.size];
        for (int x = 0 ; x < this.size ; x++) {
            for (int y = 0 ; y < this.size ; y++) {
                A[x][y] = this.matrix[x][y] != DEFAULT_MATRIX_VALUE;
            }
        }
        for (int i = 0 ; i < size ; i++) { //Recorro cada vertice
            for (int m = 0 ; m < size ; m++) { //Recorro cada fila
                for (int n = 0 ; n < size ; n++) { // Recorro cada columna de la fila.
                    A[m][n] = A[m][n] || (A[m][i] && A[i][n]);
                }
            }
        }
        return A;
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

    private boolean vertexEquals(V vertex1, V vertex2) {
        if (vertex1 == null ^ vertex2 == null) {
            return false;
        }
        return this.cmpVertex.compare(vertex1, vertex2) == 0;
    }

    private boolean containVertex(V vertex) {
        if (vertex != null) {
            for (V v : this.vertices) {
                if (vertexEquals(v, vertex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int indexOf(V vertex) {
        return IntStream.range(0, size)
                .filter(i -> this.vertexEquals(vertices[i], vertex) )
                .findFirst()
                .orElse(-1);
    }

    private boolean isFull() {
        return size == capacity;
    }

    private void grow() {

        this.capacity = this.capacity + (this.capacity >> 1);

        // grow array of vertices
        V[] tmp = (V[]) (new Comparable[this.capacity]);
        if (this.size >= 0) System.arraycopy(this.vertices, 0, tmp, 0, this.size);
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
