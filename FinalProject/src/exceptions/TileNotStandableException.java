package exceptions;

import java.io.Serializable;

public class TileNotStandableException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 649126160475372008L;

	public TileNotStandableException(){
		super("Units cannot stand on the selected tile.");
	}
}