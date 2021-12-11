package list;

import java.util.Comparator;

/**
 *
 * @author wgcotera
 */
public class Main {

    public static void main(String[] args) {
        Comparator<Integer> cmp = (x, y) -> Integer.compare(x,y);
        Comparator<String> cmp2 = (x, y) ->x.compareTo(y);


        
    }
}
