package exceptions;

public class InventoryFullException extends RuntimeException{
	
	public InventoryFullException(){
		super("Your inventory already contains its maximum number of items.");
	}
}