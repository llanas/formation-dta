package fr.pizzeria.exception;

public class PizzaException extends RuntimeException {
		
	private String message;
	
	public PizzaException() {
		
	}
	
	public PizzaException( String message ) {
		this.message = message;
	}

	public String message() {
		return this.message;
	}

}
