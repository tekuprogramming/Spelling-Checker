import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a dictionary of valid words used by the spelling checker
 */
public class Dictionary {
    private Set<String> words;

    /**
     * Constructs a Dictionary object and loads words from a specified file
     *
     * @param fileName The name of the file containing valid words
     */
    public Dictionary(String fileName) {
        this.words = loadDictionary(fileName);
    }

    /**
     * Loads words from a specified file into a set
     *
     * @param fileName The name of the file containing valid words
     * @return A set of valid words loaded from the file
     */
    public Set<String> loadDictionary(String fileName) {
        Set<String> dictionary = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    /**
     * Checks whether the dictionary contains a specific word
     *
     * @param word The word to check for in the dictionary
     * @return {@code true} if the word is found; {@code false} otherwise
     */
    public boolean contains(String word) {
        return words.contains(word);
    }

    /**
     * Gets the set of valid words in the dictionary
     *
     * @return The set of valid words
     */
    public Set<String> getWords() {
        return words;
    }
}
