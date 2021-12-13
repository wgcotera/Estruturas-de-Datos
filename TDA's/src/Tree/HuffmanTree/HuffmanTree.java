package Tree.HuffmanTree;

import Tree.BinaryTree.BinaryTree;

import java.util.*;

public class HuffmanTree {


    /* *********************************************************************
     * Tarea 2
     ******************************************************************** */

    /* EJERCICIO 1 */

    public static Map<Character, Integer> getFrequencies(String string) {
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();
        for(char character : string.toCharArray()) {
            frequencyMap.put(character, frequencyMap.containsKey(character) ? frequencyMap.get(character) + 1 : 1);
        }
        return frequencyMap;
    }

    /* EJERCICIO 2 */

    public static BinaryTree<HuffmanInfo> buildHuffmanTree(Map<Character, Integer> frequencyMap) {

        Queue<BinaryTree<HuffmanInfo>> pq = new PriorityQueue<>(Comparator.comparing(BinaryTree::getContent));

        frequencyMap.forEach((character, frequency) -> pq.offer(new BinaryTree<>(new HuffmanInfo(character.toString(), frequency))));

        while (pq.size() > 1) {

            BinaryTree<HuffmanInfo> left = pq.poll();
            BinaryTree<HuffmanInfo> right = pq.poll();

            String parentContent = left.getContent().getHContent() + right.getContent().getHContent();
            int parentFrequency = left.getContent().getFrequency() + right.getContent().getFrequency();
            BinaryTree<HuffmanInfo> parent = new BinaryTree<>(new HuffmanInfo(parentContent, parentFrequency));
            parent.setLeft(left);
            parent.setRight(right);

            pq.offer(parent);
        }
        return pq.poll();
    }

    /* EJERCICIO 3 */

    public static void huffmanCodes(BinaryTree<HuffmanInfo> huffmanTree, Map<Character, String> map, String bCode) {

        if (huffmanTree.getLeft() == null && huffmanTree.getRight() == null) {
            map.put(huffmanTree.getContent().getHContent().charAt(0), bCode);
            return;
        }

        huffmanCodes(huffmanTree.getLeft(), map, bCode + "0");
        huffmanCodes(huffmanTree.getRight(), map, bCode + "1");
    }

    public static void getHuffmanCodes(List<Character> charactersList, BinaryTree<HuffmanInfo> huffmanTree, Map<Character, String> map1, Map<String, Character> map2) {
        Map<Character, String> map = new LinkedHashMap<>();
        huffmanCodes(huffmanTree, map, "");
        for (char character : charactersList) {
            String bCode = map.get(character);
            map1.put(character, bCode);
            map2.put(bCode, character);
        }
    }

    /* EJERCICIO 4 */

    public static String encode(String string, Map<Character, String> map) {
        String bCode = "";
        for (char character : string.toCharArray()) {
            bCode += map.get(character);
        }
        return bCode;
    }

    /* EJERCICIO 5 */

    public static String decode(String string, Map< String, Character> map) {
        StringBuilder result = new StringBuilder();
        String bCode = "";

        for (char character : string.toCharArray()) {
            bCode += character;
            if (map.containsKey(bCode)) {
                result.append(map.get(bCode));
                bCode = "";
            }
        }
        return result.toString();
    }
}
