package exceptions;

import java.io.Serializable;

public class NotEnoughCoinsException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 644041064154166120L;

	public NotEnoughCoinsException(){
		super("You do not have enough coins to purchase this item.");
	}
}
