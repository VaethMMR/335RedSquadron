package exceptions;

public class TileNotStandableException extends RuntimeException{
	
	public TileNotStandableException(){
		super("Units cannot stand on the selected tile.");
	}
}