/**
 * Checks the spelling and provides spelling correction suggestions based on a given dictionary
 */
public class SpellingChecker {
    private Dictionary dictionary;

    /**
     * Constructs a SpellingChecker object with a specified dictionary
     *
     * @param dictionary The dictionary used for spelling correction
     */
    public SpellingChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Gets a spelling correction suggestion for a given word
     *
     * @param word The word to find a suggestion for
     * @return The suggested correction for the word
     */
    public String getSuggestion(String word) {
        int minDistance = Integer.MAX_VALUE;
        String closestMatch = "";

        for (String dictWord : dictionary.getWords()) {
            int distance = calculateLevenshteinDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = dictWord;
            }
        }

        return closestMatch;
    }

    /**
     * Calculates the Levenshtein distance between two strings
     *
     * @param a The first string
     * @param b The second string
     * @return The Levenshtein distance between the two stringa
     */
    public static int calculateLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    /**
     * Calculates the cost of substituting one character with another
     *
     * @param a The first character
     * @param b The second character
     * @return The cost of substitution
     */
    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     * Returns the minimum value among the given numbers
     *
     * @param numbers The numbers to find the minimum value from
     * @return The minimum value among the given numbers
     */
    public static int min(int... numbers) {
        return java.util.Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}
