package Tree.HuffmanTree;

import Tree.BinaryTree.BinaryTree;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EncodeFile {
    public static void main(String[] args) {

        try {

            /* *********************************************************************
             * EJERCICIO 6 *
             ******************************************************************** */


            // Direccion donde se guardara el texto codificado
            String encodedFilePath = "src/Tree/HuffmanTree/TextFiles/EncodedText.txt";

            // Leer todo el contenido del archivo como una sola cadena de caracteres
            String normalText = Util.geTextFromFile("src/Tree/HuffmanTree/TextFiles/OriginalNormal.txt");

            // Generar la tabla de frecuencias.
            Map<Character, Integer> frequencyMap = HuffmanTree.getFrequencies(normalText);

            //Construir el arbol de Huffman
            BinaryTree<HuffmanInfo> huffmanTree = HuffmanTree.buildHuffmanTree(frequencyMap);

            // Genarar los codigos correspondientes utilizando el arbol construido.
            Map<Character, String> encodeMap = new LinkedHashMap<>();
            Map<String, Character> decodeMap = new LinkedHashMap<>();

            Set<Character> characterSet = frequencyMap.keySet();

            HuffmanTree.getHuffmanCodes(characterSet, huffmanTree, encodeMap, decodeMap);

            // Codificar el contenido del archivo
            System.out.println("Codificando archivo: HuffmanTree/TextFiles/OriginalNormal.txt");
            String encodeText = HuffmanTree.encode(normalText, encodeMap);

            // Guardar el texto codificado en un archivo de texto
            System.out.printf("Guardando archivo codificado en: %s \n", encodedFilePath);   
            Util.createAndWriteFile(encodeText, encodedFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
