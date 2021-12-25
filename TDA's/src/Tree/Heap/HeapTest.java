package Tree.Heap;

public class HeapTest {
    public static void main(String[] args) {
        Heap<Integer> hp1 = new Heap();
        Heap<Integer> hp2 = new Heap(true);

        hp1.offer(1).offer(15).offer(-2).offer(40).offer(25);
        hp2.offer(1).offer(15).offer(-2).offer(40).offer(25);

        System.out.println(hp1.peek());
        System.out.println(hp2.peek());
        System.out.println(" - ".repeat(100));

        while (!hp1.isEmpty()) {
            System.out.println(hp1.poll());
        }

        System.out.println(" - ".repeat(100));

        while(!hp2.isEmpty()) {
            System.out.println(hp2.poll());
        }
    }
}
