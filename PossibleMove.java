import java.util.*;

public class PossibleMove extends Piece
{
	//Creates boolean used for player ID
	boolean player;
	
	//Creates new Possible Move(Only really used for its string representation)
	public PossibleMove(boolean player)
	{
		super(player);
		this.player = player;
	}
	
	//Creates and returns a list of integers representing the
	//coordinates of possible moves
	public ArrayList<Integer> select(int x, int y, Piece[][] board)
	{
		ArrayList<Integer> moves = new ArrayList<Integer>();
		moves.add(x);
		moves.add(y);
		return moves;
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
	
	//Returns string representation of the piece
	public String toString()
	{
		return "X";
	}
	
	
}