import java.util.ArrayList;

//Creates the abstrac "Piece" class that all other
//pieces are subclasses of
public abstract class Piece
{

	//Constructs new generic piece
	public Piece(boolean player)
	{
		
	}
	
	public abstract ArrayList<Integer> select(int x, int y, Piece[][] board);
	
	public abstract ArrayList<Integer> move(int w, int x, int y, int z);

	public abstract boolean getPlayer();
	
	public abstract String toString();
}