import java.util.*;

public class CheckCheck
{
	//declares constants
	private static int BOARD_SIZE = 8;
	
	//Determines whether the given player is in check
	public static boolean checkCheck(Piece[][] a, boolean player)
	{
		//Declares variables and the array list of possible moves
		int xValue = 0;
		int otherX = 0;
		int yValue = 0;
		int otherY = 0;
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

		//Locates the player's king
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				if(a[r][c].toString().equalsIgnoreCase("k") && a[r][c].getPlayer() == player)
				{
					xValue = r;
					yValue = c;
					break;
				}
			}
		}
		
		//Checks each individual piece of the enemy player to see if 
		//any of them could capture the king.
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				//Tests to see if the piece at this location is able to 
				//is of the enemy team
				if(a[r][c].getPlayer() != player && !a[r][c].toString().equalsIgnoreCase("*"))
				{
					
					possibleMoves = a[r][c].select(r, c, a);
					
					
					if(possibleMoves.size() != 0 || possibleMoves.size() % 2 == 0)
					{
						for(int i = 0; i < possibleMoves.size(); i = i + 2)
						{
							otherX = possibleMoves.get(i);
							otherY = possibleMoves.get(i+1);
							
							//Checks to see if the enemy piece could capture the king
							if(otherX == xValue && otherY == yValue)
							{
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	//Prepares to check for checkMates
	public static boolean checkmateCheck(Piece[][] a, boolean player)
	{
		//Creates a list of pieces and a boolean for testing
		ArrayList<Integer> pieces = new ArrayList<Integer>();
		boolean tester = true;
		
		//Add every piece of the player to the list of Pieces
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				if(a[r][c].getPlayer() == player && !a[r][c].toString().equals("*"))
				{
					pieces.add(r);
					pieces.add(c);
				}
			}
		}
	
		//Tests each piece to see if moving that piece
		//will get the king out of check
		for(int i = 0; i < pieces.size(); i = i + 2)
		{
			tester = test(pieces.get(i), pieces.get(i+1), a);
			if(tester == true)
			{
				return true;
			}
		}
		return false;
	}
	
	//Performs the actual test to see if a
	//player is currently in check
	public static boolean test(int a, int b, Piece[][] testBoard)
	{
		//Declares variables and the board that will be used for testing,
		//as well as the output list and piece used for testing
		Piece[][] replacerBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
		int moveCoordinateX = 0;
		int moveCoordinateY = 0;
		int xAxis = a;
		int yAxis = b;
		String input = "";
		boolean checkTest = true;
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		Piece selected = testBoard[xAxis][yAxis];
		output = selected.select(xAxis, yAxis, testBoard);
		PossibleMove aa = new PossibleMove(true);
		
		//Sets the board used for testing and the replacer board
		//equal to each other, then changes the possible moves to
		//a "PossibleMove" piece on the test board
		for(int i = 0; i < output.size(); i=i+2)
		{
			if(output.get(i) < BOARD_SIZE && output.get(i+1) < BOARD_SIZE && output.get(i) >= 0 && output.get(i+1) >= 0)
			{
				replacerBoard[output.get(i)][output.get(i+1)] = testBoard[output.get(i)][output.get(i+1)];
				testBoard[output.get(i)][output.get(i+1)] = aa;
			}
		}
		
		//Moves the selected piece to each possible move that it could make
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				if(testBoard[r][c].toString().equals("X"))
				{
					moveCoordinateX = r;
					moveCoordinateY = c;
		
					output = selected.move(xAxis, yAxis, moveCoordinateX, moveCoordinateY);

					testBoard[output.get(0)][output.get(1)] = selected;
					testBoard[output.get(2)][output.get(3)] = new Blank(true);
					
					//Checks to see if this move will get the player out of check
					checkTest = checkmateCheckCheck(testBoard, selected.getPlayer());
					
					if(checkTest == true)
					{
						testBoard[output.get(0)][output.get(1)] = new Blank(true);
						testBoard[output.get(2)][output.get(3)] = selected;
						ChessGame.replaceXs(testBoard, replacerBoard, selected);
						return true;
					}
			
					else if(checkTest == false)
					{
						testBoard[output.get(0)][output.get(1)] = new Blank(true);
						testBoard[output.get(2)][output.get(3)] = selected;
						ChessGame.replaceXs(testBoard, replacerBoard, selected);
					}
					
				}
			}
		}
		return false;
	}
	
	//Performs the checkmate Check
	public static boolean checkmateCheckCheck(Piece[][] a, boolean player)
	{	
		//Declares variables and the list of Possible Moves
		int xValue = 0;
		int yValue = 0;
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		boolean tester = !player;
		String pieceString = "";
		
		//Locates the king of the selected player
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				if(a[r][c].toString().equalsIgnoreCase("k") && a[r][c].getPlayer() == player)
				{
					xValue = r;
					yValue = c;
					break;
				}
			}
		}

		//Creates a list of opposing player's pieces
		ArrayList<Integer> pieces = new ArrayList<Integer>();
		
		for(int r = 0; r < BOARD_SIZE; r++)
		{
			for(int c = 0; c < BOARD_SIZE; c++)
			{
				pieceString = a[r][c].toString();
				if(a[r][c].getPlayer() == tester && !pieceString.equals("*") && !pieceString.equals("X"))
				{
					pieces.add(r);
					pieces.add(c);
				}
			}
		}
	
		//Checks to see any of the opposing players pieces
		//could capture the king by checking every possible move
		for(int i = 0; i < pieces.size(); i = i + 2)
		{
			Piece testPiece = a[pieces.get(i)][pieces.get(i+1)];
			possibleMoves = testPiece.select(pieces.get(i), pieces.get(i+1), a);
			
			for(int j = 0; j < possibleMoves.size(); j = j + 2)
			{
				
				if(possibleMoves.get(j) == xValue && possibleMoves.get(j+1) == yValue)
				{	
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
}