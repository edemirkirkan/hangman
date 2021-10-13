public class Hangman {
    
    private String[] wordPool = new String[] {"levis", "lacoste", "tommy", "nike", "adidas", "timberland", "lumberjack", "armani", "guess", "gucci", "moschino"};
    private final int maxAllowedIncorrectTries = 6;
    private StringBuffer secretWord;
    private StringBuffer usedLetters;
    private StringBuffer knownSoFar;
    private int numOfIncorrectTries;

    public Hangman() {
        usedLetters = new StringBuffer("");
        knownSoFar = new StringBuffer("");
        chooseSecretWord();
    }

    public String getSecretWord() {
        return this.secretWord.toString();
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
 
    public void tryThis(char letter) {
        this.usedLetters.append(letter);
        Boolean found = false;
        for (int i = 0; i < this.secretWord.length(); i++) {
            char character = this.secretWord.charAt(i);
            if (character == letter) {
                found = true;
                this.knownSoFar.append(letter);
                secretWord.deleteCharAt(i--);
            }
        }
        if (!found) 
            this.numOfIncorrectTries++;
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