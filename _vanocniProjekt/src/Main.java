import java.util.Scanner;

/**
 * The main method to run the spelling checker
 */
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("F:\\Документы\\Документы\\dictionary.txt");
        SpellingChecker checker = new SpellingChecker(dictionary);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a word to check for spelling errors: ");
            String inputText = scanner.nextLine();

            if (inputText.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the spelling checker. Goodbye!");
                break;
            }

            if (!isValidInput(inputText)) {
                System.out.println("Invalid input. Please enter only letters without spaces");
                continue;
            }

            String[] words = inputText.split("\\s+");

            for (String word : words) {
                if (dictionary.contains(word.toLowerCase())) {
                    System.out.println("You spelled the word correctly");
                } else {
                        String suggestion = checker.getSuggestion(word);
                        System.out.println("Possible spelling error: " + word + ". Perhaps you meant: " + suggestion);
                }
            }
        }
    }

    /**
     * Checks whether the input contains only letters without spaces
     *
     * @param input The input string to be validated
     * @return {@code true} if the input is valid; {@code false} otherwise
     */
    public static boolean isValidInput(String input) {
        return input.matches("^[a-zA-Z]*$");
    }
}