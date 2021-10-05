/**
 * Hangman
 */
public class Hangman {
    private String[] wordPool = new String[] {"inheritance", "polymorphism", "abstraction", "linux", 
                                            "kernel", "ubuntu", "windows", "python", "java", "database", "algorithm"};
    private StringBuffer secretWord;
    private StringBuffer allLetters;
    private StringBuffer usedLetters;
    private StringBuffer knownSoFar;
    private int numOfIncorrectTries;
    private int maxAllowedIncorrectTries;

    public Hangman() {
        this.maxAllowedIncorrectTries = 6;
        usedLetters = new StringBuffer("");
        knownSoFar = new StringBuffer("");
        chooseSecretWord();
        allLetters = this.secretWord;
    }

    public String getAllLetters() {
        return this.allLetters.toString();
    }

    public String getUsedLetters() {
        return this.usedLetters.toString();
    }

    public int getNumOfIncorrectTries() {
        return this.numOfIncorrectTries;
    }

    public void setNumOfIncorrectTries(int numOfIncorrectTries) {
        this.numOfIncorrectTries = numOfIncorrectTries;
    }

    public int getMaxAllowedIncorrectTries() {
        return this.maxAllowedIncorrectTries;
    }

    public String getKnownSoFar() {
        return this.knownSoFar.toString();
    }
 
    public int tryThis(char letter) {
        int numberOfOccurence = 0;
        this.usedLetters.append(letter);
        Boolean found = false;
        for (int i = 0; i < this.secretWord.length(); i++) {
            char character = this.secretWord.charAt(i);
            if (character == letter) {
                found = true;
                this.knownSoFar.append(letter);
                numberOfOccurence++;
                secretWord.deleteCharAt(i);
            }
        }
        if (!found) 
            this.numOfIncorrectTries++;
        return numberOfOccurence;
    }

    public boolean isGameOver() {
        if (hasLost())
            return true;
        if (secretWord.length() == 0) 
            return true;
        return false;
    }

    public boolean hasLost() {
        return this.numOfIncorrectTries >= this.maxAllowedIncorrectTries;
    }

    private void chooseSecretWord() {
        int randomNumber = (int)(Math.random() * wordPool.length);
        this.secretWord = new StringBuffer(this.wordPool[randomNumber]);
    }
}