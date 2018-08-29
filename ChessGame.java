/////////////////////////////////////////////////////
//	Chess Game Program
//	Grant Coles
//	CS 1113.012
//
//	Description: functions as a text based game of 
//	the classic chess.
/////////////////////////////////////////////////////

import java.util.*;

public class ChessGame
{

	//Declares constants and the arrays
	//used as a game board and for testing
	private static int BOARD_SIZE = 8;
	public static Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
	public static Piece[][] tempBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
	public static Piece[][] checkBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
	
	//Keeps track of whose turn it is
	public static boolean turn = true;
	
	public static void main(String[] args)
	{
		//Declares the booleans used for
		//for check testing, creates the new
		//board, declares the scanner, and
		//creates the winner/loser strings,
		//as well as the input string and
		//delimiter
		boolean checkTest = true;
		boolean checkmateTest = true;
		createNewBoard();
		Scanner scan = new Scanner(System.in);
		String loser = "";
		String winner = "";
		String input;
		String delimiter = "[ ]+";
		String[] inTokens = new String[5];
		
		System.out.println("\n\nChess Game by Grant Coles");
		System.out.println("For help, use command <help>\n\n");
		
		while(true)
		{
			//Copies the current board to
			//the checkBoard for check testing
			for(int r = 0; r < BOARD_SIZE; r++)
			{
				for(int c = 0; c < BOARD_SIZE; c++)
				{
					checkBoard[r][c] = board[r][c];
				}
			}
			
			//Checks to see if the player is in check
			checkTest = CheckCheck.checkCheck(checkBoard, turn);
			
			//Handles the case in which the player is in check
			if(checkTest == false)
			{
				//Checks to see if player is in checkmate
				checkmateTest = CheckCheck.checkmateCheck(checkBoard, turn);
				
				//Handles case in which the player is not in checkmate
				if(checkmateTest == true)
				{
					System.out.println("You are in Check!");
				}
				
				//Handels the case in which the player is in checkmate,
				//Declaring winners and giving option to play again
				if(checkmateTest == false)
				{
					if(turn == true)
					{
						winner = "Black";
						loser = "White";
					}
					
					if(turn == false)
					{
						winner = "White";
						loser = "Black";
					}
					
					System.out.println(loser + " is in Checkmate!");
					System.out.println(winner + " wins! Congrats!");
					System.out.println("Play again? yes/no");
					input = scan.nextLine();
					
					if(input.equalsIgnoreCase("yes"))
					{
						createNewBoard();
					}
					
					if(input.equalsIgnoreCase("no"))
					{
						System.exit(0);
					}
				}
			}
			
			//Prints whose turn it is
			if(turn == true)
			{
				System.out.println("White Player Turn");
			}
			
			else
			{
				System.out.println("Black Player Turn");
			}
			
			//Prints board and splits input
			printBoard(board);
			input = scan.nextLine();
			inTokens = input.split(delimiter);
			
			//Sends the input the appropriate methods
			if(inTokens.length == 1)
			{
				if(inTokens[0].equalsIgnoreCase("quit"))
				{
					break;
				}
				
				else if(inTokens[0].equalsIgnoreCase("help"))
				{
					printHelp();
				}
				
				else
				{
					System.out.println("That is an invalid input!");
				}
			}
			
			else if(inTokens.length == 2)
			{
				if(input.equalsIgnoreCase("new game"))
				{
					createNewBoard();
					turn = true;
				}
				else
				{
					System.out.println("That is an invalid input!");
				}
			}
			
			else if(inTokens.length == 3)
			{
				if(inTokens[0].equalsIgnoreCase("select"))
				{
					select(inTokens[1], inTokens[2]);	
				}
				
				else
				{
					System.out.println("That is an invalid input!");
				}
			}
			
			else
			{
				System.out.println("That is an invalid input!");
			}
		}
	}
	
	//Sets the actual play board to the 
	//original default setting of
	//a chess board
	public static void createNewBoard()
	{
		//Creates and places each piece
		Pawn a7 = new Pawn(false);
		board[1][7] = a7;
		Pawn b7 = new Pawn(false);
		board[1][6] = b7;
		Pawn c7 = new Pawn(false);
		board[1][5] = c7;
		Pawn d7 = new Pawn(false);
		board[1][4] = d7;
		Pawn e7 = new Pawn(false);
		board[1][3] = e7;
		Pawn f7 = new Pawn(false);
		board[1][2] = f7;
		Pawn g7 = new Pawn(false);
		board[1][1] = g7;
		Pawn h7 = new Pawn(false);
		board[1][0] = h7;
		
		Pawn a2 = new Pawn(true);
		board[6][7] = a2;
		Pawn b2 = new Pawn(true);
		board[6][6] = b2;
		Pawn c2 = new Pawn(true);
		board[6][5] = c2;
		Pawn d2 = new Pawn(true);
		board[6][4] = d2;
		Pawn e2 = new Pawn(true);
		board[6][3] = e2;
		Pawn f2 = new Pawn(true);
		board[6][2] = f2;
		Pawn g2 = new Pawn(true);
		board[6][1] = g2;
		Pawn h2 = new Pawn(true);
		board[6][0] = h2;
		
		Rook a8 = new Rook(false);
		board[0][0] = a8;
		Rook h8 = new Rook(false);
		board[0][7] = h8;
		Rook a1 = new Rook(true);
		board[7][0] = a1;
		Rook h1 = new Rook(true);
		board[7][7] = h1;
		
		Knight b8 = new Knight(false);
		Knight g8 = new Knight(false);
		Knight b1 = new Knight(true);
		Knight g1 = new Knight(true);
		board[0][1] = b8;
		board[0][6] = g8;
		board[7][1] = b1;
		board[7][6] = g1;
		
		Bishop c8 = new Bishop(false);
		Bishop f8 = new Bishop(false);
		Bishop c1 = new Bishop(true);
		Bishop f1 = new Bishop(true);
		board[0][2] = c8;
		board[0][5] = f8;
		board[7][2] = c1;
		board[7][5] = f1;
		
		King e8 = new King(false);
		King e1 = new King(true);
		board[0][4] = e8;
		board[7][4] = e1;
		
		Queen d8 = new Queen(false);
		Queen d1 = new Queen(true);
		board[0][3] = d8;
		board[7][3] = d1;
		
		//Fills in the empty areas with blank spaces
		for(int r = 2; r < BOARD_SIZE-2; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
					board[r][c] = new Blank(false);
			}
		}
	}
	
	//Prints the board that is passed through
	public static void printBoard(Piece[][] a)
	{
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				System.out.printf("%-3s", a[r][c]);
			}
			System.out.print("\n\n");
		}
	}
	
	//Handles everything from the selection of a 
	//piece up to the end of that person's turn
	public static void select(String a, String b)
	{
		//Creates the scanner and declares various variables
		Scanner scan = new Scanner(System.in);
		int moveCoordinateX = 0;
		int moveCoordinateY = 0;
		boolean checkTest = true;
		
		//Converts the input given into Array indexes 
		int[] selections = locationConverter(a,b);
		
		//Sends the user back if invalid input is given
		if(selections[0] == -1 || selections[1] == -1)
		{
			System.out.println("Invalid Input Recieved!");
			return;
		}
		
		//Sets the correct indexs to required variables
		int xAxis = selections[1];
		int yAxis = selections[0];
		
		//initializes and sets output and input
		ArrayList<Integer> output = new ArrayList<Integer>();
		String input = board[xAxis][yAxis].toString();
		
		String moveInput = "";
		String delimiter = "[ ]+";
		String[] inTokens = new String[5];
		
		//declares the actual piece selected
		Piece selected = board[xAxis][yAxis];
		
		//Copies the temporary board used for
		//reseting the play board after a piece is selected
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				tempBoard[r][c] = board[r][c];
			}
		}
		
		//handles the situation in which the piece selected
		//is not the current players piece
		if(selected.getPlayer() != turn)
		{
			System.out.println("That's not your piece!");
			replaceXs(board, tempBoard, selected);
			return;
		}
		
		else
		{
			//Sets the value of output and creates the 
			//Piece which will fill the spots of possible moves
			output = selected.select(xAxis, yAxis, board);
			PossibleMove aa = new PossibleMove(true);
			
			//Copies every piece which will be a possible move over to 
			//the temporary board, so that possible moves that appear
			//as "X"s can be converted back to the original piece after
			//an attempted move
			for(int i = 0; i < output.size(); i=i+2)
			{
				if(output.get(i) < BOARD_SIZE && output.get(i+1) < BOARD_SIZE && output.get(i) >= 0 && output.get(i+1) >= 0)
				{
					tempBoard[output.get(i)][output.get(i+1)] = board[output.get(i)][output.get(i+1)];
					board[output.get(i)][output.get(i+1)] = aa;
				}
			}
		
			//Prints board with possible moves
			printBoard(board);
			
			//Takes in input to see whether a move is being made,
			//or other commands are given
			moveInput = scan.nextLine();
			inTokens = moveInput.split(delimiter);
			
			//handles the case in which a move is attempted
			if(inTokens[0].equals("move"))
			{
				//converts input into array indexes
				int[] moveLocation = locationConverter(inTokens[1], inTokens[2]);
				
				//Sends user back if invalid input is given
				if(moveLocation[0] == -1 || moveLocation[1] == -1)
				{
					System.out.println("Invalid Input Recieved!");
					return;
				}
				
				//sets value of the coordinates to be moved to
				moveCoordinateX = moveLocation[1];
				moveCoordinateY = moveLocation[0];
				
				//Reassigns values of output and input
				output = selected.move(xAxis, yAxis, moveCoordinateX, moveCoordinateY);
				input = board[moveCoordinateX][moveCoordinateY].toString();
				
				if(input.equals("X"))
				{
					//Moves the piece and determines if the
					//move puts the user into check
					move(output, selected);
					checkTest = CheckCheck.checkCheck(board, selected.getPlayer());
					
					//If the user will not be in check, replaces
					//the "X"s on the board and changes turns
					if(checkTest == true)
					{
						replaceXs(board, tempBoard, selected);
						turn = turnChanger(turn);
					}
					
					//If the user will be in check, sends the user back to original board
					else if(checkTest == false)
					{
						unmove(output, selected);
						System.out.println("That will put you in check!");
						replaceXs(board, tempBoard, selected);
					}
				}	
				
				//Handles the case in which the user attempts
				//a move that is illegal
				else
				{
					System.out.print("Invalid Move\n");
					replaceXs(board, tempBoard, selected);
				}
			}
			
			//Creates new game
			else if(input.equalsIgnoreCase("new game"))
			{
				System.out.println("\n\n");
				createNewBoard();
			}
			
			//Prints help screen
			else if(input.equalsIgnoreCase("help"))
			{
				printHelp();
			}
			
			//Sends user back to original screen
			else if(inTokens[0].equalsIgnoreCase("Cancel"))
			{
				replaceXs(board, tempBoard, selected);
				return;
			}
			
			//Allows the user to select a different piece
			else if(inTokens[0].equalsIgnoreCase("select"))
			{
				replaceXs(board, tempBoard, selected);	
				select(inTokens[1], inTokens[2]);
			}
			
			//Exits the game
			else if(inTokens[0].equalsIgnoreCase("quit"))
			{
				System.out.println("\n");
				System.exit(0);
			}	
			
			//Handles the case in which an invalid input is given
			else
			{
				System.out.println("Invalid Input Received!\n");
				replaceXs(board, tempBoard, selected);
				return;
			}
		}
		
		System.out.println("\n");
	}
	
	//Moves the piece selected, and replacing its former location with a blank
	public static void move(ArrayList<Integer> output, Piece selected)
	{
		board[output.get(0)][output.get(1)] = selected;
		board[output.get(2)][output.get(3)] = new Blank(true);
	}
	
	//Moves a piece to it's former location, replacing its attempted move with a blank
	public static void unmove(ArrayList<Integer> output, Piece selected)
	{
		board[output.get(0)][output.get(1)] = new Blank(true);
		board[output.get(2)][output.get(3)] = selected;
	}
	
	//Switches which player's turn it is
	public static boolean turnChanger(boolean input)
	{
		if(input == false){return true;}
		else{return false;}
	}
	
	//Converts the "letter-number" chess board coordinate that
	//is received into array indexes that correspond to that location
	public static int[] locationConverter(String a, String b)
	{
		//Creates the array that will hold the array indexes
		int[] output = new int[2];
		
		try
		{
			//Converts the row coordinate
			int row = Integer.parseInt(b)-1;
			if(row > -1 && row < 8)
			{
				if(row == 7)
				{
					output[1] = 0;
				}
			
				if(row == 6)
				{
					output[1] = 1;
				}	
			
				if(row == 5)
				{
					output[1] = 2;
				}
			
				if(row == 4)
				{
					output[1] = 3;
				}
			
				if(row == 3)
				{
					output[1] = 4;
				}
			
				if(row == 2)
				{
					output[1] = 5;
				}
			
				if(row == 1)
				{
					output[1] = 6;
				}
			
				if(row == 0)
				{
					output[1] = 7;
				}
			
			}
		
			else
			{
				output[1] = -1;
			}
			
			//Converts the column coordinate
			if(a.equalsIgnoreCase("a"))
			{
				output[0] = 0;
			}
		
			else if(a.equalsIgnoreCase("b"))
			{
				output[0] = 1;
			}
		
			else if(a.equalsIgnoreCase("c"))
			{
				output[0] = 2;
			}
		
			else if(a.equalsIgnoreCase("d"))
			{
				output[0] = 3;
			}
		
			else if(a.equalsIgnoreCase("e"))
			{
				output[0] = 4;
			}
		
			else if(a.equalsIgnoreCase("f"))
			{
				output[0] = 5;
			}
		
			else if(a.equalsIgnoreCase("g"))
			{
				output[0] = 6;
			}
		
			else if(a.equalsIgnoreCase("h"))
			{
				output[0] = 7;
			}
		
			else
			{
				output[0] = -1;
			}
			
			return output;
		}
		
		//Catches the exception in which an invalid parse is attempted
		catch(Exception e)
		{
			output[0] = -1;
			output[1] = -1;
			return output;
		}

	}
	
	//Prints the helps screem
	public static void printHelp()
	{
		//Creates the 2D array of strings that will print to
		//give an example of the board rows and columns
		String[][] helpBoard = new String[BOARD_SIZE+1][BOARD_SIZE+1];
		helpBoard[0][0] = "8";
		helpBoard[1][0] = "7";
		helpBoard[2][0] = "6";
		helpBoard[3][0] = "5";
		helpBoard[4][0] = "4";
		helpBoard[5][0] = "3";
		helpBoard[6][0] = "2";
		helpBoard[7][0] = "1";
		helpBoard[8][0] = "X";
		helpBoard[8][1] = "A";
		helpBoard[8][2] = "B";
		helpBoard[8][3] = "C";
		helpBoard[8][4] = "D";
		helpBoard[8][5] = "E";
		helpBoard[8][6] = "F";
		helpBoard[8][7] = "G";
		helpBoard[8][8] = "H";
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 1; c < BOARD_SIZE+1; c++)
			{
				helpBoard[r][c] = "*";
			}
		}
		
		//Prints out how to select and move pieces
		System.out.print("\n\nTo select a piece, use <select> <(columnletter)> <(rownumber)>\n\n");
		System.out.print("To move selected piece, use <move> <(columnletter)> <(rownumber)>\n\n");
		
		//Prints example board
		for(int r = 0; r <= BOARD_SIZE; r++)
		{
			for(int c = 0; c <= BOARD_SIZE; c++)
			{
				System.out.printf("%-3s", helpBoard[r][c]);
			}
			System.out.print("\n\n");
		}
		
		//Prints more commands
		System.out.print("To cancel a selected piece, use command <cancel>\n\n");
		System.out.print("To quit at any time, use command <quit>\n\n");
		System.out.println("Have Fun!\n\n");
	}
	
	public static void replaceXs(Piece[][] a, Piece[][] b, Piece selected)
	{
	
		//Replaces and possible moves "X"s on the board with
		//the Pieces found in that location from the temporary board
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c ++)
			{
				if(a[r][c].toString().equals("X"))
				{
					a[r][c] = b[r][c];
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
}