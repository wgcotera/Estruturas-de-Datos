package Tree.HuffmanTree;

public class HuffmanInfo implements Comparable<HuffmanInfo>{
    private String characters;
    private int frequency;

    public HuffmanInfo(String content, int frequency) {
        this.characters = content;
        this.frequency = frequency;
    }

    public String getCharacters() {
        return characters;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(HuffmanInfo other) {
        if(this.getFrequency() - other.getFrequency() != 0)
            return this.getFrequency() - other.getFrequency();
        else
            return this.getCharacters().compareTo(other.getCharacters());
    }

    @Override
    public String toString() {
        return "{" +characters +": "+  frequency +"}" ;
    }
}
