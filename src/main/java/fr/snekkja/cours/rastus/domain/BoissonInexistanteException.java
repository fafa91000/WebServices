package fr.snekkja.cours.rastus.domain;

public class BoissonInexistanteException extends Exception
{

	public BoissonInexistanteException() {
		super();
	}

	public BoissonInexistanteException(String message, Throwable cause) {
		super(message, cause);
	}

	public BoissonInexistanteException(String message) {
		super(message);
	}
	
}
