package exceptions;

import java.io.Serializable;

public class TileOccupiedException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7211321659294123615L;

	public TileOccupiedException(){
		super("The selected tile is already occupied by another unit.");
	}
}