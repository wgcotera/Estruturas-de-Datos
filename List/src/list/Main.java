package list;

/**
 *
 * @author wgcotera
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> numbers = new ArrayList<>();
        List<String> colores = new ArrayList<>();
        numbers.addLast(0);
        numbers.addLast(1);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);
        numbers.addLast(6);
        numbers.addFirst(-1);
        
        System.out.println(numbers.toString());        
        System.out.println(numbers.size());
        
        numbers.add(3, 2);
        numbers.add(0,-2);
//        numbers.clear();
//        numbers.add(9, 7);
        
        System.out.println(numbers.toString());        
        System.out.println(numbers.size());
        
        numbers.removeFirst();
        
        System.out.println(numbers.toString());        
        System.out.println(numbers.size());
        
        numbers.keepOnly(3, 7);
        System.out.println(numbers.toString());   
        System.out.println(numbers.size());
    }
    
}
