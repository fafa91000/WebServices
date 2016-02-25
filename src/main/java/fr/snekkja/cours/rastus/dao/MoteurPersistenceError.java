package fr.snekkja.cours.rastus.dao;

public class MoteurPersistenceError extends Error
{

	private static final long serialVersionUID = 1;

	public MoteurPersistenceError(String message, Throwable cause) {
		super(message, cause);
	}

	public MoteurPersistenceError(String message) {
		super(message);
	}
	
}
