package Tree.HuffmanTree;

import Tree.BinaryTree.BinaryTree;

import java.util.*;

public class HuffmanTree {


    /* *********************************************************************
     ****************** TAREA 2**********************************************
     ******************************************************************** */


    /* *********************************************************************
         * EJERCICIO 1 *
     ******************************************************************** */


    public static Map<Character, Integer> getFrequencies(String string) {
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();
        for(char character : string.toCharArray()) {
            frequencyMap.put(character, frequencyMap.containsKey(character) ? frequencyMap.get(character) + 1 : 1);
        }
        return frequencyMap;
    }



    /* *********************************************************************
     * EJERCICIO 2 *
     ******************************************************************** */


    public static BinaryTree<HuffmanInfo> buildHuffmanTree(Map<Character, Integer> frequencyMap) {

        Queue<BinaryTree<HuffmanInfo>> pq = new PriorityQueue<>(Comparator.comparing(BinaryTree::getContent));

        frequencyMap.forEach((character, frequency) -> pq.offer(new BinaryTree<>(new HuffmanInfo(character.toString(), frequency))));

        while (pq.size() > 1) {

            BinaryTree<HuffmanInfo> left = pq.poll();
            BinaryTree<HuffmanInfo> right = pq.poll();

            String parentContent = left.getContent().getCharacters() + right.getContent().getCharacters();
            int parentFrequency = left.getContent().getFrequency() + right.getContent().getFrequency();

            BinaryTree<HuffmanInfo> parent = new BinaryTree<>(new HuffmanInfo(parentContent, parentFrequency));
            parent.setLeft(left);
            parent.setRight(right);

            pq.offer(parent);
        }
        return pq.poll();
    }



    /* *********************************************************************
     * EJERCICIO  *
     ******************************************************************** */


    private static void huffmanCodes(BinaryTree<HuffmanInfo> huffmanTree, Map<Character, String> map, String binaryCode) {

        if (huffmanTree.isLeaf()) {
            map.put(huffmanTree.getContent().getCharacters().charAt(0), binaryCode);
            return;
        }

        huffmanCodes(huffmanTree.getLeft(), map, binaryCode + "0");
        huffmanCodes(huffmanTree.getRight(), map, binaryCode + "1");
    }

    public static void getHuffmanCodes(Set<Character> characterSet, BinaryTree<HuffmanInfo> huffmanTree, Map<Character, String> map1, Map<String, Character> map2) {
        Map<Character, String> map = new LinkedHashMap<>();
        huffmanCodes(huffmanTree, map, "");
        for (char character : characterSet) {
            String bCode = map.get(character);
            map1.put(character, bCode);
            map2.put(bCode, character);
        }
    }



    /* *********************************************************************
     * EJERCICIO 4 *
     ******************************************************************** */


    public static String encode(String source, Map<Character, String> map) {
        StringBuilder strB = new StringBuilder();
        for (char character : source.toCharArray()) {
            strB.append(map.get(character));
        }
        return strB.toString();
    }



    /* *********************************************************************
     * EJERCICIO 5 *
     ******************************************************************** */


    public static String decode(String source, Map< String, Character> map) {
        StringBuilder result = new StringBuilder();
        StringBuilder bCode = new StringBuilder();

        for (char character : source.toCharArray()) {
            bCode.append(character);
            if (map.containsKey(bCode.toString())) {
                result.append(map.get(bCode.toString()));
                bCode.setLength(0);
            }
        }
        return result.toString();
    }
}
