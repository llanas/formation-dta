package fr.pizzeria.exception;

public class FichierException extends RuntimeException {

	public FichierException() {
		super();
	}

	public FichierException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FichierException(String arg0) {
		super(arg0);
	}

	public FichierException(Throwable arg0) {
		super(arg0);
	}

}
