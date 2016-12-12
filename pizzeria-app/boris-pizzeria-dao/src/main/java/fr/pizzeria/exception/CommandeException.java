package fr.pizzeria.exception;

public class CommandeException extends Exception {

	public CommandeException() {
		super();
	}

	public CommandeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandeException(String message) {
		super(message);
	}

	public CommandeException(Throwable cause) {
		super(cause);
	}
	
	

}
