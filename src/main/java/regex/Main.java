package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println("Password Valid: " + checkForPassword(userInput, 6));
        System.out.println("Emails Found: " + extractEmails(userInput));
        System.out.println("Double Capitals: " + checkForDoubles(userInput));
    }

    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum length.
     */
    public static boolean checkForPassword(String str, int minLength) {
        // Fix: Check for null to prevent NullPointerException
        if (str == null) {
            return false;
        }

        // Regex explanation:
        // (?=.*[a-z]) -> Positive Lookahead: at least one lowercase
        // (?=.*[A-Z]) -> Positive Lookahead: at least one uppercase
        // (?=.*[0-9]) -> Positive Lookahead: at least one digit
        // .{minLength,} -> Match any char for minLength or more times
        return Pattern.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{" + minLength + ",}", str);
    }

    /**
     * Returns a list of email addresses that occur in a given string.
     */
    public static List<String> extractEmails(String str) {
        // Fix: Check for null to prevent NullPointerException
        if (str == null) {
            return new ArrayList<>(); // Return empty list for null input
        }

        // Regex explanation:
        // [^\\s@]+       -> username (non-whitespace, non-@ chars)
        // @              -> literal @
        // (mail\\.)?     -> optional "mail."
        // utoronto\\.ca  -> literal "utoronto.ca"
        final Pattern pattern = Pattern.compile("[^\\s@]+@(mail\\.)?utoronto\\.ca");
        final Matcher matcher = pattern.matcher(str);
        final List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Checks whether a given string contains the same capital letter twice.
     */
    public static boolean checkForDoubles(String str) {
        // Fix: Check for null to prevent NullPointerException
        if (str == null) {
            return false;
        }

        // Regex explanation:
        // .* -> any chars
        // ([A-Z])  -> Capture Group 1 (a capital letter)
        // .* -> any chars
        // \\1      -> Match whatever was captured in Group 1
        // .* -> any chars
        return str.matches(".*([A-Z]).*\\1.*");
    }
}