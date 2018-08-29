import java.util.*;

public class Bishop extends Piece
{
	//creates boolean used for player ID
	private boolean player;

	//Creates new Bishop
	public Bishop(boolean player)
	{
		super(player);
		this.player = player;
	}
		
	//Creates and returns a list of integers representing the
	//coordinates of possible moves
	public ArrayList<Integer> select(int x, int y, Piece[][] board)
	{
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		int BOARD_SIZE = 8;
		int r = 0;
		int c = 0;
		boolean tester = true;
		
		r = x+1;
		c = y+1;
		while(true)
		{
			tester = checkMove(r, c, board);
			if(tester == true)
			{
				possibleMoves.add(r);
				possibleMoves.add(c);
				if(board[r][c].getPlayer() != player && !board[r][c].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
			
			r++;
			c++;
		}
		
		r = x-1;
		c = y+1;
		while(true)
		{
			tester = checkMove(r, c, board);
			if(tester == true)
			{
				possibleMoves.add(r);
				possibleMoves.add(c);
				if(board[r][c].getPlayer() != player && !board[r][c].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
			
			r--;
			c++;
		}
		
		r = x-1;
		c = y-1;
		while(true)
		{
			tester = checkMove(r, c, board);
			if(tester == true)
			{
				possibleMoves.add(r);
				possibleMoves.add(c);
				if(board[r][c].getPlayer() != player && !board[r][c].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
			
			r--;
			c--;
		}
		
		r = x+1;
		c = y-1;
		while(true)
		{
			tester = checkMove(r, c, board);
			if(tester == true)
			{
				possibleMoves.add(r);
				possibleMoves.add(c);
				if(board[r][c].getPlayer() != player && !board[r][c].toString().equals("*"))
				{
					break;
				}
			}
			else{break;}
			
			r++;
			c--;
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
	
	//Returns string representation of the piece
	public String toString()
	{
		if(player == true){return "B";}
		else{return "b";}
	}
}