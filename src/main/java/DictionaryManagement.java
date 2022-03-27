import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryManagement extends Dictionary{
    /**
     * Insert n words into the dictionary.
     * 1st line: number of words to be added.
     * 2nd line: target word. 3rd line: definition.
     * ...
     */
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numberOfWords = sc.nextInt();
        sc.nextLine(); //to clear the input for subsequent
        for (int i = 0; i < numberOfWords; i++) {
            String targetWord = sc.nextLine();
            String definition = sc.nextLine();
            wordList.add(new Word(targetWord, definition));
        }
    }

    /**
     * Insert words from src/main/java/Dictionary.txt into word list.
     */
    public static void insertFromFile() {
        File input = new File("src/main/java/Dictionary.txt");
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String infoFromFile = sc.nextLine();
                String[] word = infoFromFile.split("\\s+", 2); //IDK why this works. It just kinda does
                wordList.add(new Word(word[0], word[1]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Look up a word in the current database.
     * The input word must have the exact format.
     * The first letter of the word must be capitalized.
     * Print out the word and definition similar to Word.toString().
     */
    public static void dictionaryLookup() {
        System.out.print("Enter the word that you want to look up: ");
        Scanner sc = new Scanner(System.in);
        String searchWord = sc.next();
        for (int i = 0; i < wordList.size(); i++) {
            if (searchWord.equals(wordList.get(i).getTargetWord())) {
                System.out.println(wordList.get(i).toString());
            }
        }
        System.out.println(searchWord + " is not available in the database :(");
    }

    /**
     * Remove a word from the dictionary.
     */
    public static void dictionaryRemove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the word that you want to remove: ");
        String word = sc.next();
        for (int i = 0; i < wordList.size(); i++) {
            if (word.equals(wordList.get(i).getTargetWord())) {
                wordList.remove(i);
                System.out.println(word + " has been removed from dictionary");
                return;
            }
        }
        System.out.println("No instances of " + word + "has been found.");
    }

    /**
     * change the definition of a word
     */
    public static void appendDefinition() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the word that you want to change the definition: ");
        String word = sc.next();
        System.out.print("Enter the new definition: ");
        sc.nextLine();
        String newDef = sc.nextLine();
        for (int i = 0; i < wordList.size(); i++) {
            if (word.equals(wordList.get(i).getTargetWord())) {
                wordList.get(i).setDefinition(newDef);
                System.out.println("The definition of " + word + " has been updated.");
                return;
            }
        }
        System.out.println("No instances of " + word + "has been found.");
    }

    /**
     * Export to "src/main/java/Export.txt" if file exist.
     * Create it otherwise.
     * Output format:
     * No   |English            |Vietnamese
     * 1    |<targetWord1>      |<definition1>
     * 2    |<targetWord2>      |<definition2>
     * 3    |<targetWord3>      |<definition3>
     */
    public static void dictionaryExportToFile() {
        try {
            File output = new File("src/main/java/Export.txt");
            boolean createFile = output.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        String info = String.format("%-5s%-20s%-40s\n", "No", "|English", "|Vietnamese");
        for (int i = 0; i < wordList.size(); i++) {
            info += String.format("%-5s%-60s\n", "" + (i+1), wordList.get(i).toString());
        }
        try {
            FileWriter writer = new FileWriter("src/main/java/Export.txt");
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}