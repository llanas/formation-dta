package fr.pizzeria.exception;

public class ClientException extends RuntimeException {

	public ClientException() {
		super();
	}

	public ClientException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientException(String arg0) {
		super(arg0);
	}

	public ClientException(Throwable arg0) {
		super(arg0);
	}

	
	
}
