package Tree.HuffmanTree;

import Tree.BinaryTree.BinaryTree;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DecodeFile {
    public static void main(String[] args) {
        try {

            /* *********************************************************************
             * EJERCICIO 7 *
             ******************************************************************** */

            // Direccion donde se guardara el texto decodificado
            String decodedFilePath = "src/Tree/HuffmanTree/TextFiles/DecodeText.txt";

            // Leer todo el contenido del archivo como una sola cadena de caracteres
            String normalText = Util.geTextFromFile("src/Tree/HuffmanTree/TextFiles/OriginalNormal.txt");
            String enCodedText = Util.geTextFromFile("src/Tree/HuffmanTree/TextFiles/OriginalEncoded.txt");

            // Generar la tabla de frecuencias.
            Map<Character, Integer> frequencyMap = HuffmanTree.getFrequencies(normalText);

            //Construir el arbol de Huffman
            BinaryTree<HuffmanInfo> huffmanTree = HuffmanTree.buildHuffmanTree(frequencyMap);

            // Genarar los codigos correspondientes utilizando el arbol construido.
            Map<Character, String> encodeMap = new LinkedHashMap<>();
            Map<String, Character> decodeMap = new LinkedHashMap<>();

            Set<Character> characterSet = frequencyMap.keySet();

            HuffmanTree.getHuffmanCodes(characterSet, huffmanTree, encodeMap, decodeMap);

             // Decodificar el contenido del archivo
            System.out.println("Decodificando archivo: HuffmanTree/TextFiles/OriginalEncoded.txt");
            String decodedText = HuffmanTree.decode(enCodedText, decodeMap);

            // Guardar el texto decodificado en un archivo de texto
            System.out.printf("Guardando archivo decodificado en: %s \n", decodedFilePath);                    
            Util.createAndWriteFile(decodedText, decodedFilePath);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
