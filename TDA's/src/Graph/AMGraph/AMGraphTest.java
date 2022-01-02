package Graph.AMGraph;

public class AMGraphTest {
    public static void main(String[] args) {
        int [][] test = new int [3][3];
        for (int[] i : test) {
            for(int j : i) {
                System.out.println(j);
            }
        }
        System.out.println("\n\n");

        for (int[] i : test) {
            for(int j : i) {
                if (j == Integer.MAX_VALUE) {
                    System.out.println("null");
                }
            }
        }
    }
}
