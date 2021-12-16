import java.util.Scanner;

public class Main {
    public static Scanner userInput = new Scanner(System.in);
    public static int from = 1;
    public static int to = 20;

    public static void main(String[] args) {

        String name;
        boolean play;

        try {
            System.out.println("Hi! What's your name?");
            name = userInput.nextLine();
            do {
                guessTheNumber(name,from,to);
                play = playAgain();
            } while (play);

        } catch (InterruptedException e) {
            userInput.nextLine();
        }
        System.out.println("Good-bye!");
    }

    public static void guessTheNumber(String name,int from,int to) throws InterruptedException {
        int attemptsAllowed = 5;
        int attempted = 0;
        int guess;
        boolean win = false;
        int number = randInt(from,to);
        int delay=1500;
        String winMessage;
        String[] messages = {"Well, %s, I am thinking of a number between %d and %d.","Can you guess my number?","Take a guess."};
        for (String message : messages) {
            System.out.printf(message+"\n",name,from,to);
            Thread.sleep(delay);
            delay -=500;
        }
        while(!win && attemptsAllowed != attempted) {
            try {
                guess = userInput.nextInt();
                win = guess == number;
                if (guess > number) {
                    System.out.println("You're too high!");
                } else if (guess < number) {
                    System.out.println("You're too low!");
                }
                attempted++;
                if (attempted != attemptsAllowed && !win){
                    System.out.printf(messages[2] + " %d %s left.\n",Math.abs(attempted-attemptsAllowed), Math.abs(attempted-attemptsAllowed) > 1 ? "guesses": "guess");
                }
            } catch (Exception e) {
                String wrongInput = userInput.nextLine();
                System.err.printf("Wrong input type. '%s' is not a number.\n",wrongInput);
            }
        }

        if (win) {
            winMessage = attempted == 1 ? "And with your FIRST GUESS!":"You guessed my number in %d guesses!";
            System.out.printf("%s, You Won! " + winMessage +"\n",name,attempted);
        } else {
            System.out.println("You lose. You ran out of guesses.");
        }
    }

    public static int randInt(int start, int stop){
        double randNum = Math.random();
        if (start < 0 ) {
            return (int) Math.round(randNum * (Math.abs(start - stop)) + start);
        }
        return (int) Math.round(randNum * stop + (start - (start*randNum)));
    }

    public static boolean playAgain(){
        String ans ="";
        while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n")) {
            try {
                System.out.println("Would you like to play again? (Y or N)");
                ans = userInput.next();
            } catch (Exception e){
                System.err.println("Input Error");
                userInput.nextLine();
            }
        }
        return ans.equalsIgnoreCase("y");
    }
}
