package exceptions;

import java.io.Serializable;

public class InventoryFullException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3826167160622383327L;

	public InventoryFullException(){
		super("Your inventory already contains its maximum number of items.");
	}
}