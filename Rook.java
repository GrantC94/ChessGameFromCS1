import java.util.*;

public class Rook extends Piece
{
	//Creates boolean used for player ID
	boolean player;
	
	//Creates new Rook
	public Rook(boolean player)
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
		boolean tester;
		
		for(int i = x+1; i < BOARD_SIZE; i++)
		{
			tester = checkMove(i, y, board);
			
			if(tester == true)
			{
				possibleMoves.add(i);
				possibleMoves.add(y);
				if(board[i][y].getPlayer() != player && !board[i][y].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
		}
		
		
		for(int i = x-1; i >= 0; i--)
		{
			tester = checkMove(i, y, board);
			if(tester == true)
			{
				possibleMoves.add(i);
				possibleMoves.add(y);
				if(board[i][y].getPlayer() != player && !board[i][y].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
		}
			
		for(int i = y+1; i < BOARD_SIZE; i++)
		{
			tester = checkMove(x, i, board);
			if(tester == true)
			{
				possibleMoves.add(x);
				possibleMoves.add(i);
				if(board[x][i].getPlayer() != player && !board[x][i].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
		}
			
		for(int i = y-1; i >= 0; i--)
		{
			tester = checkMove(x, i, board);
			if(tester == true)
			{
				possibleMoves.add(x);
				possibleMoves.add(i);
				if(board[x][i].getPlayer() != player && !board[x][i].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
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
	
	//Tests to see if a move is valid
	public boolean checkMove(int x, int y, Piece[][] board)
	{
		if(x >= 0 && y >= 0 && x < board.length && y < board.length)
		{
			if(board[x][y].toString().equals("*") || player != board[x][y].getPlayer())
			{
				return true;
			}
		
			else{return false;}
		}
		
		else{return false;}
	}
	
	//returns the player of the piece
	public boolean getPlayer()
	{
		return player;
	}
	
	//Returns string representation of the piece
	public String toString()
	{
		if(player == true){return "R";}
		else{return"r";}
	}
}