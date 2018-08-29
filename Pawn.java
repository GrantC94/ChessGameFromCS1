import java.util.*;

public class Pawn extends Piece
{
	//Creates boolean used for player ID
	boolean player;
	
	//Creates new Pawn
	public Pawn(boolean player)
	{
		super(player);
		this.player = player;
	}
	
	//Creates and returns a list of integers representing the
	//coordinates of possible moves
	public ArrayList<Integer> select(int x, int y, Piece[][] board)
	{
		int BOARD_SIZE = 8;
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		boolean player = board[x][y].getPlayer();
		boolean leftPlayer;
		boolean rightPlayer;
		Piece right = new Blank(true);
		Piece left = new Blank(true);
		
		if(x == 7 || x == 0)
		{
			changePawn(x, y, player, board);
		}
		
		else if(player == true)
		{
			if(x == 6)
			{
				if(board[x-1][y].toString().equals("*"))
				{
					possibleMoves.add(x-1);
					possibleMoves.add(y);
				}
				if(board[x-2][y].toString().equals("*"))
				{
					possibleMoves.add(x-2);
					possibleMoves.add(y);
				}
			}
			else
			{
				if(board[x-1][y].toString().equals("*"))
				{
					possibleMoves.add(x-1);
					possibleMoves.add(y);
				}
			}
			if(x-1 < BOARD_SIZE && y-1 < BOARD_SIZE && x-1 >= 0 && y-1 >= 0)
			{
				left = board[x-1][y-1];
			}
			
			leftPlayer = left.getPlayer();
			
			if(x-1 < BOARD_SIZE && y+1 < BOARD_SIZE && x-1 >= 0 && y+1 >= 0)
			{
				right = board[x-1][y+1];
			}
			
			rightPlayer = right.getPlayer();
			
			if(leftPlayer == false && !left.toString().equals("*"))
			{
				possibleMoves.add(x-1);
				possibleMoves.add(y-1);
			}
			
			if(rightPlayer == false && !right.toString().equals("*"))
			{
				possibleMoves.add(x-1);
				possibleMoves.add(y+1);
			}
		}
		
		else if(player == false)
		{
			if(x == 1)
			{
				if(board[x+1][y].toString().equals("*"))
				{
					possibleMoves.add(x+1);
					possibleMoves.add(y);
				}
				if(board[x+2][y].toString().equals("*"))
				{
					possibleMoves.add(x+2);
					possibleMoves.add(y);
				}
			}
			else
			{
				if(board[x+1][y].toString().equals("*"))
				{
					possibleMoves.add(x+1);
					possibleMoves.add(y);
				}
			}
			
			if(x+1 < BOARD_SIZE && y-1 < BOARD_SIZE && x+1 >= 0 && y-1 >= 0)
			{
				left = board[x+1][y-1];
			}
			
			leftPlayer = left.getPlayer();
			
			if(x+1 < BOARD_SIZE && y+1 < BOARD_SIZE && x-1 >= 0 && y+1 >= 0)
			{
				right = board[x+1][y+1];
			}
			
			rightPlayer = right.getPlayer();
			
			if(leftPlayer == true && !left.toString().equals("*"))
			{
				possibleMoves.add(x+1);
				possibleMoves.add(y-1);
			}
			
			if(rightPlayer == true && !right.toString().equals("*"))
			{
				possibleMoves.add(x+1);
				possibleMoves.add(y+1);
			}
		}
		return possibleMoves;
	}
	
	//Reverses the order of the move input
	public ArrayList<Integer> move(int w, int x, int y, int z)
	{
		ArrayList<Integer> moves = new ArrayList<Integer>();
		moves.add(y);
		moves.add(z);
		moves.add(w);
		moves.add(x);
		return moves;
	}
	
	//returns the player of the piece
	public boolean getPlayer()
	{
		return player;
	}
	
	//Allows the user to reassign the value of a
	//pawn the reaches the other side of the board
	public Piece[][] changePawn(int x, int y, boolean player, Piece[][] board)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("What type of piece?  ");
		String decision = scan.next();
		
		if(decision.equalsIgnoreCase("bishop"))
		{
			board[x][y] = new Bishop(player);
		}
		if(decision.equalsIgnoreCase("knight"))
		{
			board[x][y] = new Knight(player);
		}
		if(decision.equalsIgnoreCase("Queen"))
		{
			board[x][y] = new Queen(player);
		}
		if(decision.equalsIgnoreCase("rook"))
		{
			board[x][y] = new Rook(player);
		}
		
		return board;
	}
	
	//Returns string representation of the piece
	public String toString()
	{
		if(player == true){return "P";}
		else{return "p";}
	}
}