package Tree.BinaryTree;

public class HuffmanInfo implements Comparable<HuffmanInfo>{
    private String hContent;
    private int frequency;

    public HuffmanInfo(String content, int frequency) {
        this.hContent = content;
        this.frequency = frequency;
    }

    public String getHContent() {
        return hContent;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(HuffmanInfo other) {
        if(this.getFrequency() - other.getFrequency() != 0)
            return this.getFrequency() - other.getFrequency();
        else
            return this.getHContent().compareTo(other.getHContent());
    }
}
