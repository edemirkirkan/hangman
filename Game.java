import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;
        
        do {
            System.out.println();
            Hangman hangman = new Hangman();
            play(hangman, scanner);
            displayResult(hangman);
            
            System.out.println("To play again, press 'p' and press any key to finish the game.");
            playAgain = scanner.nextLine();
        }  while(playAgain.equals("p"));
        
        scanner.close();;
    }

    public static void play(Hangman hangman, Scanner scanner) {
        ArrayList<String> previouslyGuessedWords = new ArrayList<>();
        String chosenWord = hangman.getSecretWord();
        StringBuffer displayedWord = new StringBuffer("");
        do {
            for (char ch : chosenWord.toCharArray()) {
                if (hangman.getKnownSoFar().indexOf(ch) == -1) {
                    displayedWord.append("_ ");
                }
                else {
                    displayedWord.append(ch + " ");
                }
            }

            System.out.println(displayedWord + "\n");
            System.out.println("Number of remaining tries are " + 
            (hangman.getMaxAllowedIncorrectTries() - hangman.getNumOfIncorrectTries()) + ".");  
            System.out.print("Guess a letter or directly a word: ");
            displayedWord.delete(0, displayedWord.length());

            String input = scanner.nextLine();
            int inputLength = input.length();
            if (inputLength == 0) {
                System.out.println("Please enter a valid letter or word.\n");
                continue;
            }

            if (inputLength > 1) {
                if (input.equals(chosenWord)) {
                    break;
                }
                else {
                    if (previouslyGuessedWords.contains(input)) {
                        System.out.println("You already guessed the word '" + input + "'. Try another one.\n");
                    }
                    else {
                        previouslyGuessedWords.add(input);
                        hangman.setNumOfIncorrectTries(hangman.getNumOfIncorrectTries() + 1);
                    }   
                    continue;
                }   
            }
               
            char character = input.charAt(0);
            if (!Character.isAlphabetic(character)) {
                System.out.println("Please enter a valid alphabetic letter.\n");
                continue;
            }
                
            if (hangman.getUsedLetters().indexOf(character) != -1) {
                System.out.println("You already used the letter '" + character + "'. Try another one.\n");
                continue;
            }
            
            hangman.tryThis(character);   
            System.out.println();
        } while(!hangman.isGameOver());
        System.out.println("\nSecret word was '" + chosenWord + "'.\n");
    }

    public static void displayResult(Hangman hangman) {
        if (hangman.hasLost()) 
            System.out.println("You lost! Better luck next time!\n");
        else 
            System.out.println("You won! Congratulations!\n");
    }
}
