package fr.snekkja.cours.webservices.domain;

import java.io.Serializable;


/**
 * Une exception levée si un article existe déjà.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
public class ArticleDejaExistantException extends Exception
{

	/**
	 * Identifiant de version
	 * @see Serializable
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Construire une exception d'article déjà existant.
	 * 
	 * @param message Le message de l'exception.
	 * @param cause La cause de l'exception.
	 */
	public ArticleDejaExistantException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Construire une exception d'article déjà existant.
	 * 
	 * @param message Le message de l'exception.
	 */
	public ArticleDejaExistantException(final String message)
	{
		super(message);
	}
	
}
