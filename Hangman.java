import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String currentWord = wordChoice();
        int lives = 6,
            wordLength = 0,
            count = 0;
        char[] lettersUsed = new char[27];

        for (int i = 0; i < currentWord.length(); i++) {
            wordLength++;
        }
        char[] word = wordArray(currentWord, wordLength);
        String[] blanks = new String[wordLength];

        for (int i = 0; i < wordLength; i++) {
            blanks[i] = "_";
        }
        
        System.out.println("|---------------------|");
        System.out.println("| Welcome to hangman! |");
        System.out.println("|---------------------|");


        while (lives > 0) {
            System.out.print("\nPick a letter (a-z): ");
            String strGuess = in.nextLine().toLowerCase();
            char guess = strGuess.charAt(0);

            if (checkIfUsed(word, guess, wordLength, lettersUsed) > 0){
                System.out.print("\033[H\033[2J"); // CLEAR SCREEN
                System.out.println("\nYou've already guessed that.");
                System.out.println("\nGuess again.");
            }
            else {
                System.out.print("\033[H\033[2J"); // Clear Screen
                guessInWord(word, guess, strGuess, wordLength, blanks);
                if (notInWord(word, guess, wordLength) == true) {
                    lives--;
                    if (lives >= 1) {
                        System.out.println("\nIncorrect, try again.");
                        lettersUsed[count] = guess;
                        hangHim(lives);
                        count++;
                    }
                    else {
                        System.out.println();
                        hangHim(lives);
                        System.out.println("The word was '" + currentWord + "'.");
                        break;
                    }
                }
            }
            if (winCondition(blanks, currentWord, wordLength) == true) {
                System.out.println("Congratulations! You won!");
                break;
            }
            
            System.out.println("\nLetters used so far:");
            for(int i = 0; i < lettersUsed.length; i++) {
                System.out.print(lettersUsed[i]);
            }
            System.out.println();
            System.out.print("\nThe word: ");
            for (int i = 0; i < wordLength; i++) {
                System.out.print(blanks[i]);
            }
            System.out.println();
        }
    } // End main()

    // Checks to see if the character guessed has already been
    // used. If so the method returns 1, if not it returns 0.
    public static int checkIfUsed(char[] word, char guess, int wordLength, char[] lettersUsed) {
        for (int i = 0; i < wordLength; i++) {
            if (guess == lettersUsed[i]) {
                return 1;
            }
        }
        return -1;
    } // End of checkIfUsed()

    // Checks if the character is in the word. If it is then
    // it replaces index location in blank that has the same
    // location of the letters in the word to be guessed
    public static String[] guessInWord(char[] word, char guess, String strGuess, int wordLength, String[] blanks) {
        for (int i = 0; i < wordLength; i++) {
            if (guess == word [i]) {
                blanks[i] = strGuess;
            }
        }
        return blanks;
    } // End of guessInWord()

    // Checks if a character is in the word to be guessed. If not
    // then returns true
    public static boolean notInWord(char[] word, char guess, int wordLength) {
        int count = 0;
        for (int i = 0; i < wordLength; i++) {
            if (guess != word[i]) {
                count++;
            }
        }
        if (count == wordLength) {
            return true;
        }
        else {
            return false;
        }
    }

    // Create an array of the letters from the word chosen.
    public static char[] wordArray(String currentWord, int wordLength) {
        char[] wordArray = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            wordArray[i] = currentWord.charAt(i);
        }
        return wordArray;
    } // End of wordArray()

    public static boolean winCondition(String[] blanks, String currentWord, int wordLength) {
        String word = "",
               blank = "";

        // Convert the arrays into proper strings to check if they're equal
        for (int i = 0; i < wordLength; i++) {
            String letter = "" + currentWord.charAt(i);
            word += letter;
        }
        for (int i = 0; i < wordLength; i++) {
            String correctGuesses = "" + blanks[i];
            blank += correctGuesses;
        }

        if (word.equals(blank)) {
            return true;
        }
        else {
            return false;
        }
    }   // End of winCondition()

    //  Draws the hanging man with each incorrect guess.
    public static void hangHim(int lives) {
        if (lives == 5) {
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :. .:");
            System.out.println("  '...'");
            System.out.println("   -:-");
        } else if (lives == 4) {
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :' ':");
            System.out.println("  '...'");
            System.out.println("   -:-");
            System.out.println("    :");
            System.out.println("    :");
            System.out.println("    :");
        } else if (lives == 3) {
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :o o:");
            System.out.println("  '...'");
            System.out.println("   -:-");
            System.out.println("   ,:");
            System.out.println("  ; :");
            System.out.println("    :");
        } else if (lives == 2) {
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :O O:");
            System.out.println("  '...'");
            System.out.println("   -:-");
            System.out.println("   ,:.");
            System.out.println("  ; : :");
            System.out.println("    :  ");
        } else if (lives == 1) {
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :0 0:");
            System.out.println("  '...'");
            System.out.println("   -:-");
            System.out.println("   ,:.");
            System.out.println("  ; : :");
            System.out.println("    :  ");
            System.out.println("   .");
            System.out.println("  .");
            System.out.println("  :");
        } else if (lives == 0) {
            System.out.println("You lose!");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ...");
            System.out.println("  :x x:");
            System.out.println("  '...'");
            System.out.println("   -:-");
            System.out.println("   ,:.");
            System.out.println("  ; : :");
            System.out.println("    :  ");
            System.out.println("   . .");
            System.out.println("  .   .");
            System.out.println("  :   :");
        }
    } // End of hangHim()

    public static String wordChoice() {
        String currentWord;
        int wordChosen;
        Random random = new Random();

        String[] words = {
            "cider",
            "hippopotamus",
            "graphical",
            "ornament",
            "speaker",
            "pedantic",
            "orange",
            "empiracle",
            "opalescent",
            "abruptly",
            "abyss",
            "funny",
            "askew",
            "matrix",
            "gizmo",
            "mystify",
            "subway",
            "bandwagon",
            "banjo",
            "avoid",
            "jukebox",
            "wristwatch",
            "rhythm",
            "kayak",
            "ivy",
            "icebox",
            "balloon",
            "buzzing",
            "stronghold",
            "zombie",
            "quizzes",
            "keyhole",
            "pumpkin"
        };

        wordChosen = random.nextInt(words.length);
        currentWord = words[wordChosen];
        return currentWord;
    } // End of wordChoice()
}
