package exceptions;

public class TileOccupiedException extends RuntimeException{
	
	public TileOccupiedException(){
		super("The selected tile is already occupied by another unit.");
	}
}