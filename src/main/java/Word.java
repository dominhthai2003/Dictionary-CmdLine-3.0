public class Word {
    private String targetWord;
    private String definition;

    public Word(String targetWord, String definition) {
        this.targetWord = targetWord;
        this.definition = definition;
    }

    public Word(Word word) {
        this.targetWord = word.getTargetWord();
        this.definition = word.getDefinition();
    }

    /**
     * Return formatted target word & definition
     * Ex: |<targetWord>  |<definition>
     * @return formatted target word & definition
     */
    @Override
    public String toString() {
        String str = String.format("%-20s%-40s", "|" + targetWord, "|" + definition);
        return str;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
