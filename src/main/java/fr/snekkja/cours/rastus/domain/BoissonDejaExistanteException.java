package fr.snekkja.cours.rastus.domain;

public class BoissonDejaExistanteException extends Exception {

	private static final long serialVersionUID = 1;

	public BoissonDejaExistanteException(String message, Throwable cause) {
		super(message, cause);
	}

	public BoissonDejaExistanteException(String message) {
		super(message);
	}
	
}
