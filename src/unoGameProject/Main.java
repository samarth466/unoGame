package unoGameProject;

// Import Java modules
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Initialize Scanner object to except user input
		//Scanner input = new Scanner(System.in);
		// Welcome user to Uno Flip
		System.out.println("Welcome to Uno Flip");
		// Ask user their name
		//System.out.println("Input your name: ");
		//String playerName = input.next();
		// Ask user for number of player
		//System.out.println("Input the number of players you'd like to play against: ");
		//int numPlayers = input.nextInt()+1;
		// Ask user for minimum score
		//System.out.println("Enter the score you'd like to play till: ");
		//int threshold = input.nextInt();
		// Close scanner
		//input.close();
		// Create a new instance of game
		Game newGame = new Game(4,"Samarth",500);
	}

}
