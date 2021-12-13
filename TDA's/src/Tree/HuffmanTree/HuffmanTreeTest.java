package Tree.HuffmanTree;

import Tree.BinaryTree.BinaryTree;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeTest {
    public static void main(String[] args) {
        final var map = HuffmanTree.getFrequencies("ABACCDA");

        BinaryTree<HuffmanInfo> huffmanTree = HuffmanTree.buildHuffmanTree(map);
        Map<Character, String> map0 = new LinkedHashMap<>();
        Map<Character, String> map1 = new LinkedHashMap<>();
        Map<String, Character> map2 = new LinkedHashMap<>();

        List<Character> characters = Arrays.asList('A','B','C');
        System.out.println("\n \n \n");
        HuffmanTree.huffmanCodes(huffmanTree, map0,"");
//        System.out.println(map1);
        HuffmanTree.getHuffmanCodes(characters, huffmanTree,  map1, map2);
        System.out.println("Codigos Huffman Completos del Arbol: "+map0);
        System.out.println("Lista de caracteres"+characters);
        System.out.println(map1);
        System.out.println(map2);
        System.out.println("Codificando 'ABC': " + HuffmanTree.encode("ABC", map1));
        System.out.println("Decodificando '010011': " + HuffmanTree.decode("010011", map2));
        System.out.println();
        huffmanTree.printTree();
    }
}
