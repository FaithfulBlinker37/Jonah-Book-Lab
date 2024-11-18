import java.lang.*;

public class PigLatinTranslator
{
  public static Book translate(Book input)
  {
    Book translatedBook = new Book();

    // Add code here to populate translatedBook with a translation of the input book.
    // Curent do-nothing code will return an empty book.
    for (int i = 0; i < input.getLineCount(); i++) {
        String translatedLine = translate(input.getLine(i)); // Translate the line
        translatedBook.appendLine(translatedLine); // Add the translated line
    }
    return translatedBook;
  }

  public static String translate(String input)
  {
    // System.out.println("Translate String: '" + input + "'");
    String[] parts = input.split("(?<=\\s)|(?=\\s)");
        StringBuilder translated = new StringBuilder();

        for (String part : parts) {
            if (part.trim().isEmpty()) {
                translated.append(part); // Preserve whitespace
            } else {
                translated.append(translateWord(part)); // Translate word
            }
        }

        return translated.toString();
    }
    // Replace this code to translate a string input.
    // The input to this function could be any English string. 
    // A sentence, paragraph, or a single word. 
    // It should call translateWord at least once.

  private static String translateWord(String input) {
    // Handle empty strings
    if (input.isEmpty()) {
        return input;
    }

    // Check if the word starts with a vowel (a, e, i, o, u)
    boolean startsWithUpperCase = Character.isUpperCase(input.charAt(0));  // Check if the word starts with a capital letter

    // Handle punctuation at the end of the word
    String punctuation = "";
    if (!Character.isLetterOrDigit(input.charAt(input.length() - 1))) {
        punctuation = input.substring(input.length() - 1); // Store punctuation
        input = input.substring(0, input.length() - 1);  // Remove punctuation
    }

    if (input.contains("-")) {
        String[] parts = input.split("-");
        StringBuilder translatedParts = new StringBuilder();
        for (String part : parts) {
            translatedParts.append(translateWord(part)).append("-");
        }
        return translatedParts.substring(0, translatedParts.length() - 1) + punctuation;
    }

    // If the word starts with a vowel, just add "ay" at the end
    if ("aeiouAEIOU".indexOf(input.charAt(0)) != -1) {
        String result = input + "ay";
        return startsWithUpperCase ? capitalizeFirstLetter(result) + punctuation : result + punctuation;
    }

    // Otherwise, find the first vowel and move the consonant cluster to the end
    int firstVowelIndex = -1;
    for (int i = 0; i < input.length(); i++) {
        if ("aeiouAEIOU".indexOf(input.charAt(i)) != -1) {
            firstVowelIndex = i;
            break;
        }
    }

    // If no vowels are found (e.g., "rhythm"), treat it as a consonant-heavy word
    if (firstVowelIndex == -1) {
        String result = input + "ay";
        return startsWithUpperCase ? capitalizeFirstLetter(result) + punctuation : result + punctuation;
    }

    // Move the consonant cluster to the end and add "ay"
    String consonantCluster = input.substring(0, firstVowelIndex);
    String restOfWord = input.substring(firstVowelIndex);
    String result = restOfWord + consonantCluster + "ay";

    // Capitalize the first letter if needed
    return startsWithUpperCase ? capitalizeFirstLetter(result) + punctuation : result + punctuation;
}

private static String matchCase(String original, String result) {
    if (original.equals(original.toUpperCase())) {
        return result.toUpperCase();
    } else if (original.equals(original.toLowerCase())) {
        return result.toLowerCase();
    } else {
        return preserveMixedCase(original, result);
    }
}

private static String preserveMixedCase(String original, String result) {
        StringBuilder adjusted = new StringBuilder();
        int minLength = Math.min(original.length(), result.length());

        for (int i = 0; i < minLength; i++) {
            char originalChar = original.charAt(i);
            char resultChar = result.charAt(i);

            if (Character.isUpperCase(originalChar)) {
                adjusted.append(Character.toUpperCase(resultChar));
            } else {
                adjusted.append(Character.toLowerCase(resultChar));
            }
        }

// Helper method to capitalize the first letter of a word
private static String capitalizeFirstLetter(String word) {
    if (word == null || word.isEmpty()) {
        return word;
    }
    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
}

  // Add additonal private methods here.
  // For example, I had one like this:
  // private static String capitalizeFirstLetter(String input)

}