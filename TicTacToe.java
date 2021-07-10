import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
//import java.util.List;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char [][] gameBoard = {{' ' , '|' , ' ' , '|' , ' '},
				{'�' , '+' , '�' , '+' , '�'},
				{' ' , '|' , ' ' , '|' , ' '},
				{'�' , '+' , '�' , '+' , '�'},
				{' ' , '|' , ' ' , '|' , ' '}};
		System.out.println("Hello!\nWelcome to TicTacToe");
		printGameBoard(gameBoard);		
		
		while(true) {
			Scanner scn = new Scanner(System.in);
			System.out.println("Enter your placement (1 - 9) : ");
			int playerPos = scn.nextInt();
			while( playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position taken!  Enter a correct Position");
				playerPos = scn.nextInt();
			}
			
			placePiece(gameBoard , playerPos , "player");
			
			String result = checkWinner();
			if( result.length() > 0 ) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while( playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
			cpuPos = rand.nextInt(9) + 1;
			}
			
			placePiece(gameBoard , cpuPos , "cpu");
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if( result.length() > 0 ) {
				System.out.println(result);
				break;
			}
		}
		
		printGameBoard(gameBoard);
	}
	public static void printGameBoard(char [][] gameBoard) {
		for( char i = 0 ; i < gameBoard.length ; i++ ) {
			for( char j = 0 ; j < gameBoard[0].length ; j++ ) {
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	}
	public static void placePiece(char [][] gameBoard , int pos , String user ) {
		
		char symbol = ' ';
		
		if( user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}else if ( user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch (pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;

		}
	}
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(bottomRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(diagonal1);
		winning.add(diagonal2);
		
		for( List l : winning ) {
			if(playerPositions.containsAll(l)) {
				return "Congrats!, You won";
			}
			else if(cpuPositions.containsAll(l))
				return "Oh no.....CPU won";
		}
			
		if( playerPositions.size() + cpuPositions.size() == 9 ) {
			return "It's a tie.";		
		}
		return "";
	}
}