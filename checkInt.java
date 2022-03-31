/*
Guess integers
*/
import java.util.*;
class checkInt{
	public static void main(String[]args){
		Scanner cin = new Scanner(System.in);
		System.out.println("This app is a game of guessing integers. You have up to three chances to guess. The integer range from 0 to 100.\n");
		int chances=3;
		int answer = (int) (Math.random() * 100);
		boolean win=false;
		for(int i=0;i<chances;i++){
			System.out.println("Enter the integer you think is correct: ");
			int guess = cin.nextInt(); // 
			if(guess==answer){
				System.out.println("Correct! You win!\n");
				win=true;
			}
		}
		if (win==false){
			System.out.println("Sorry! You lose!\n");
		}
		System.out.println("The answer is: "+answer);
	}
}//class 