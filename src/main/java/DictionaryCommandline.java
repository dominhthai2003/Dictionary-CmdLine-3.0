import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement{
    private DictionaryCommandline() {}

    public static DictionaryCommandline createDictionaryCommandLine() {
        DictionaryCommandline dcl = new DictionaryCommandline();
        return dcl;
    }

    /**
     * Display all the words currently in the dictionary in this format
     * No   |English            |Vietnamese
     * 1    |<targetWord1>      |<definition1>
     * 2    |<targetWord2>      |<definition2>
     * 3    |<targetWord3>      |<definition3>
     */
    public void showAllWords() {
        String header = String.format("%-5s%-20s%-40s", "No", "|English", "|Vietnamese");
        System.out.println(header);
        for (int i = 0; i < wordList.size(); i++) {
            String word = String.format("%-5s%-60s", "" + (i+1), wordList.get(i).toString());
            System.out.println(word);
        }
    }


    /**
     * Allow user to input a number of words from terminal (check insertFromCommandline from DictionaryManagement)
     * Print all the words on terminal.
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    /**
     * Read information from "Dictionary.txt".
     * Print out all the words on terminal.
     * Allow user to search for a word.
     * Input format must be precise (The first letter must be capitalized).
     */
    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        //dictionaryLookup();
        dictionarySearcher();
    }

    /**
     * Search for words that start with the input phrase.
     * If such words exist, print it onto the terminal.
     * If no words fit the description, announce it on terminal.
     * Input: first letter must be capitalized.
     */
    public void dictionarySearcher() {
        System.out.println("Enter the phrase you want to search: ");
        Scanner sc = new Scanner(System.in);
        String search = sc.next();
        List <Word> result = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getTargetWord().startsWith(search)) {
                result.add(new Word(wordList.get(i)));
            }
        }
        if (result.size() == 0) {
            System.out.println("No instances of '" + search + "' was found.");
        } else {
            String header = String.format("%-5s%-20s%-40s", "No", "|English", "|Vietnamese");
            System.out.println(header);
            for (int i = 0; i < result.size(); i++) {
                String word = String.format("%-5s%-60s", "" + (i + 1), result.get(i).toString());
                System.out.println(word);
            }
        }
    }
}
