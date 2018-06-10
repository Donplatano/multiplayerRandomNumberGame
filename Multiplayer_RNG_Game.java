import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Multiplayer_RNG_Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Variables

		boolean gameOver = true;

		// Do-while loop controls the entire game -- if this loop cannot run, the game will not run.
		do {
			int players = 999;
			boolean numberError = true;
			String answer = "";
			int number = 0;


			int[] randomNumbers = new int[players];
			int[] numberOfGuesses = new int[players];
			int[][] numberArray = new int[players][205];


			Scanner keyboard = new Scanner(System.in);
			boolean playAgain = false;
			System.out.println("Welcome to the Multi-Player Random Number Game\nHow many players do we have?");

			do {
				if (players <= 0) {
					System.out.println("Please select at least one player.");

				}
				
				//Validates the number of players
				try {
					players = parsePlayerInput();

					if (players > 0) {
						numberError = false;
						
						//Creates the game
						for (int i = 0; i < players; i++) {
							 randomNumbers[i] = RNG();
							// *Remove the two slashes to the left and everything within these stars to see random number values per player* System.out.println(randomNumbers[i]);
						}

						for (int i = 0; i < players; i++) {
							numberOfGuesses[i] = 0;
						}
					}
				}
				catch (Exception e) {
					System.out.println("Your input was not an integer; try again");
					numberError = true;
				}
			} while (numberError == true);
			System.out.println("Okay, let's get started\n\nEach player has been assigned a random number.\nTo win, correctly guess your random number before the other players do the same.\nThe numbers are between 1 and 10.");
		
			//Game condition set by While loop
			while (playersFinished(players, randomNumbers, numberOfGuesses, numberArray ) == false) {

				for (int i = 0; i < players; i++) {
					try {
						int guessedNumber = numberOfGuesses[i];
						if (guessedNumber == 0 || numberArray[i][guessedNumber - 1] != randomNumbers[i]) {
							System.out.println("\nPlease enter a number Player " + (i + 1));
							number = parseUserInput();
							guessComparator(number, randomNumbers[i]);
							numberArray[i][guessedNumber] = number;
							numberOfGuesses[i] = guessedNumber + 1;
						}
					} catch (Exception e) {
						System.out.println("Your input was not an integer OR it was out of range; try again");
						i = i - 1;
					}
				}			
			}

			printValues(players, numberOfGuesses, numberArray);
			rankingGenerator(players, numberOfGuesses);

			System.out.println("\nWould you like to play again?: yes? or no?\ntype 1 for yes \nor \ntype 2 for no");
			answer = keyboard.next();
			while  (playAgain == false){
				try {
					if (Integer.parseInt(answer.trim()) == 1){
						System.out.print("\n\n\n");
						playAgain = true;
					}
					else if (Integer.parseInt(answer.trim()) == 2) {
						gameOver = false;
						playAgain = true;
						System.out.println("Thanks for playing. Ciao!"); 
					}
					else {
						System.out.print("\nPlease input 1 or 2 to make a descision:\n");
						answer = keyboard.next();

					}
				}
				catch (Exception e){
					System.out.print("Your input was not an integer\nPlease input 1 or 2 to make a descision:\n");
					answer = keyboard.next();


				}
			} 
			//Game End
		} while (gameOver);
	}




	//METHODS
	static int RNG() {
		Random r = new Random();
		int randNum =  r.nextInt(10) + 1;
		return randNum;
	}
	
	static int parsePlayerInput() {
		Scanner keyboard = new Scanner(System.in);
				
		String enteredString = keyboard.next();
		
		int players = Integer.parseInt(enteredString.trim());
	
		return players;
	}
	
	static int parseUserInput() {
		Scanner keyboard = new Scanner(System.in);
		String rougeString = "This Breaks the Method";
		
		String enteredString = keyboard.next();
		
		int players = Integer.parseInt(enteredString.trim());
		
		if (players < 1 || players > 10) {
			int Breaker = Integer.parseInt(rougeString.trim());
		}

		
		return players;
	}

	
	static boolean guessComparator(int theGuess, int randomNumber) {

		boolean isNumberCorrect = false;
		if (theGuess == randomNumber) { 
			System.out.println("Congratulations, you have correctly guessed your number\n");
			isNumberCorrect = true;
		} else if (theGuess < randomNumber) {
			System.out.println("Too Low!");

		} else if (theGuess > randomNumber) {

			System.out.println("Too High!");
		}
		return isNumberCorrect;

	}
	private static boolean playersFinished(int players, int[] randomNumbers, int[] numberOfGuesses, int[][] numberArray) {
		for (int i = 0; i < players; i++) {
			int guessedNumber = numberOfGuesses[i];
			if (guessedNumber == 0) {
				return false;
			}

			if (randomNumbers[i] != numberArray[i][guessedNumber - 1]) {
				return false;
			}
		}
		return true;
	}
	public static int printValues(int players, int[] numberOfGuesses, int[][] numberArray) {

		int p = 0;
		for ( int i = 0; i < players; i++ ) {
			p = p + 1;
			System.out.println("Player " + p + " guessed " + numberOfGuesses[i]+ " time(s)");
		}

		p = 0;
		for (int i = 0; p < players; i++) {
			p = p + 1;
			System.out.println("Incorrect guesses for Player " + p + ": ");

			for (int k = 0; k <= 99; k++) {
				if (numberArray[i][k] == 0 ) {
				}
				else {
					System.out.println(numberArray[i][k]);

				}
			}
		} return 2;
	}
	private static void rankingGenerator (int numOfPlayers, int[] numberOfGuesses) {
		
		List<String> ranking = new ArrayList<String>();
		
		for (int i = 0; i < numOfPlayers; i++) {
			int guessedNumber = numberOfGuesses[i];
			ranking.add(Integer.toString(guessedNumber) + "," + (i + 1));
		}
		
		Collections.sort(ranking);
		
		int i = 1;
		System.out.println("Here is how all of the players stack-up\n");
		for (String a : ranking) {
			String rank[] = a.split(",");
			System.out.println("Rank " + i + " player " + rank[1] + " guessed " + rank[0] + " time(s)"); i++;
		}
	}
}