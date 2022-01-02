package Graph.AMGraph;


import java.util.ArrayList;
import java.util.Comparator;

public class AMGraph<V extends Comparable<V>> {

    /* *********************************************************************
     * Parameters
     ******************************************************************** */
    public static int DEFAULT_CAPACITY = 10;
    public static int DEFAULT_MATRIX_VALUE = Integer.MAX_VALUE;

    private ArrayList<V> vertices;
    private boolean isDirected;
    private int capacity;
    private int[][] matrix;
    private Comparator<V> cmpVertex;

    /* *********************************************************************
     * Constructors
     ******************************************************************** */

    public AMGraph(boolean isDirected, int capacity, Comparator<V> cmpVertex) {
        this.vertices = new ArrayList<>();
        this.isDirected = isDirected;
        this.capacity = capacity;
        this.matrix = new int[capacity][capacity];

        for (int i = 0 ; i < this.matrix.length; i++) {
            for ( int j = 0 ; j < this.matrix.length ; j++) {
                this.matrix[i][j] = DEFAULT_MATRIX_VALUE;
            }
        }

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
            this.vertices.add(vertex);
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
}
