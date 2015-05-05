package exceptions;

public class NotEnoughCoinsException extends RuntimeException{
	
	public NotEnoughCoinsException(){
		super("You do not have enough coins to purchase this item.");
	}
}
