import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    protected static List <Word> wordList = new ArrayList<>();

    protected Dictionary () {}

    public static Dictionary createDictionary() {
        Dictionary dictionary = new Dictionary();
        return dictionary;
    }
}