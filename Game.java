import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String again;
        
        do {
            System.out.println();
            Hangman hangman = new Hangman();
            String chosenWord = hangman.getAllLetters();
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
                if (input.length() == 0) {
                    System.out.println("Please enter a valid letter or word.\n");
                    continue;
                }

                if (input.length() != 1) {
                    if (input.equals(chosenWord)) {
                        break;
                    }
                    else {
                        hangman.setNumOfIncorrectTries(hangman.getNumOfIncorrectTries() + 1);
                        continue;
                    }   
                }
                   
                char character = input.charAt(0);
                if (!Character.isAlphabetic(character)) {
                    System.out.println("Please enter a valid alphabetic letter.\n");
                    continue;
                }
                    
                if (hangman.getUsedLetters().indexOf(character) != -1)
                {
                    System.out.println("You already use the letter '" + character + "'. Try another one.\n");
                    continue;
                }
                hangman.tryThis(character);   
                System.out.println();
            } while(!hangman.isGameOver());
            System.out.println("\nSecret word was '" + chosenWord + "'.\n");
            if (hangman.hasLost()) 
                System.out.println("You lost! Better luck next time!\n");
            else 
                System.out.println("You won! Congratulations!\n");
            
            System.out.println("To play again, press 'p' and press any key to finish the game.");
            again = scanner.nextLine();
        }  while(again.equals("p"));
        
        scanner.close();;
    }
}
