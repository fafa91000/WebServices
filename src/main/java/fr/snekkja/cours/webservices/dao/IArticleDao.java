package fr.snekkja.cours.webservices.dao;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import fr.snekkja.cours.webservices.domain.Article;
import fr.snekkja.cours.webservices.domain.ArticleDejaExistantException;
import fr.snekkja.cours.webservices.domain.ArticleInexistantException;


/**
 * Un contrat de DAO des {@link Article articles}.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
public interface IArticleDao
{
	
	/**
	 * Obtenir tous les articles connus.
	 * 
	 * @return Tous les articles connus.
	 */
	public Set<Article> obtenir();
	
	/**
	 * Obtenir un article depuis une référence unique d'article.
	 * 
	 * @param reference Une référence unique d'article.
	 * 
	 * @return L'article obtenu.
	 * 
	 * @throws ArticleInexistantException Si la référence unique fournie ne correspond à aucun article connu.
	 */
	public Article obtenir(final String reference) throws ArticleInexistantException;
	
	/**
	 * Obtenir des articles depuis des références uniques d'article.
	 * 
	 * @param references Des références uniques d'article.
	 * 
	 * @return Les articles obtenus.
	 * 
	 * @throws ArticleInexistantException Si l'une des références fournies ne correspond à aucun article connu.
	 */
	public Set<Article> obtenir(final Collection<String> references) throws ArticleInexistantException;
	
	/**
	 * Obtenir des articles depuis une condition discriminante.
	 * 
	 * @param condition Une condition discriminante.
	 *  
	 * @return Les articles obtenus.
	 */
	public Set<Article> obtenir(final Predicate<Article> condition);
	
	/**
	 * Créer un nouvel article.
	 * 
	 * @param article Un article à créer.
	 * 
	 * @return L'article créer.
	 * 
	 * @throws ArticleDejaExistantException Si l'article à créer existe déjà. 
	 */
	public Article creer(final Article article) throws ArticleDejaExistantException;
	
	/**
	 * Créer de nouveaux articles.
	 * 
	 * @param articles Des articles à créer.
	 * 
	 * @return Les articles crées.
	 * 
	 * @throws ArticleDejaExistantException Si l'un des articles à créer existe déjà.
	 */
	public Set<Article> creer(final Collection<Article> articles) throws ArticleDejaExistantException;
	
	/**
	 * Modifier un article.
	 * 
	 * @param article Un article à modifier.
	 * 
	 * @return L'article modifié.
	 * 
	 * @throws ArticleInexistantException Si l'article à modifier n'existe pas.
	 */
	public Article modifier(final Article article) throws ArticleInexistantException;
	
	/**
	 * Modifier des articles.
	 * 
	 * @param articles Des articles à modifier.
	 * 
	 * @return Les articles modifiés.
	 * 
	 * @throws ArticleInexistantException Si l'un des articles à modifier n'existe pas.
	 */
	public Set<Article> modifier(final Collection<Article> articles) throws ArticleInexistantException;
	
	/**
	 * Supprimer un article depuis une référence unique d'article.
	 * 
	 * @param reference Une référence unique d'article.
	 * 
	 * @throws ArticleInexistantException Si la référence unique fournie ne correspond à aucun article connu.
	 */
	public void supprimer(final String reference) throws ArticleInexistantException;
	
	/**
	 * Supprimer des articles depuis des références uniques d'article.
	 * 
	 * @param references Des références uniques d'article.
	 * 
	 * @throws ArticleInexistantException Si l'une des références uniques fournies ne correspond à aucun article connu.
	 */
	public void supprimer(final Collection<String> references) throws ArticleInexistantException;
	
	/**
	 * Supprimer des articles depuis une condition discriminante.
	 *  
	 * @param condition Une condition discriminante d'article.
	 * 
	 * @return Le nombre d'articles supprimés. 
	 */
	public Integer supprimer(final Predicate<Article> condition);
	
	/**
	 * Vérifier l'existence d'un article depuis une référence.
	 * 
	 * @param reference Une référence unique d'article.
	 * 
	 * @return Vrai si un article répond à la référence unique d'article.
	 */
	public boolean exister(final String reference);
	
	/**
	 * Vérifier l'existence d'au moins un article répondant à une condition discriminante.
	 * 
	 * @param condition Une condition discriminante d'article.
	 * 
	 * @return Vrai si au moins un article répond à la condition.
	 */
	public boolean exister(final Predicate<Article> condition);
	
}
