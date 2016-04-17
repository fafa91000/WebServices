package fr.snekkja.cours.webservices.service;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import fr.snekkja.cours.webservices.domain.Article;
import fr.snekkja.cours.webservices.domain.ArticleDejaExistantException;
import fr.snekkja.cours.webservices.domain.ArticleInexistantException;


/**
 * Contrat d'un service de gestion des articles.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
public interface IArticleService
{

	/**
	 * Obtenir tous les {@link Article articles} connus.
	 * 
	 * @return Tous les articles connus.
	 */
	public Set<Article> obtenirArticles();
	
	/**
	 * Obtenir un {@link Article article} depuis une référence unique.
	 * 
	 * @param reference Une référence unique d'article
	 * 
	 * @return L'article obtenu.
	 * 
	 * @throws ArticleInexistantException Si la référence unique fournie ne correspond à aucun article connu.
	 */
	public Article obtenirArticle(final String reference) throws ArticleInexistantException;
	
	/**
	 * Obtenir des articles depuis des références uniques.
	 * 
	 * @param references Des références uniques d'article.
	 * 
	 * @return Les articles obtenus.
	 * 
	 * @throws ArticleInexistantException Si l'une des référence unique fournie de correspond à aucun article connu.
	 */
	public Set<Article> obtenirArticles(final Collection<String> references) throws ArticleInexistantException;
	
	/**
	 * Obtenir tous les articles répondant à une condition.
	 * 
	 * @param condition Une condition discriminante d'article
	 * 
	 * @return Les articles connus répondant à la condition.
	 */
	public Set<Article> obtenirArticle(final Predicate<Article> condition);
	
	/**
	 * Créer un nouvel article.
	 * 
	 * @param article Un article à créer.
	 * 
	 * @return L'article crée. 
	 * 
	 * @throws ArticleDejaExistantException Si l'article à créer existe déjà. 
	 */
	public Article creerArticle(final Article article) throws ArticleDejaExistantException;
	
	/**
	 * Créer de nouveaux articles.
	 * 
	 * @param articles Des articles à créer.
	 * 
	 * @return Les articles crées. 
	 * 
	 * @throws ArticleDejaExistantException Si l'un des articles à créer existe déjà.
	 */
	public Set<Article> creerArticles(final Collection<Article> articles) throws ArticleDejaExistantException;
	
	/**
	 * Modifier un article.
	 * 
	 * @param article Un article à modifier.
	 * 
	 * @return L'article modifié.
	 * 
	 * @throws ArticleInexistantException Si l'article à modifier n'existe pas.
	 */
	public Article modifierArticle(final Article article) throws ArticleInexistantException;
	
	/**
	 * Modifier des articles.
	 * 
	 * @param articles Des articles à modifier.
	 * 
	 * @return Les articles modifiés.
	 * 
	 * @throws ArticleInexistantException Si l'un des articles à modifier n'existe pas.
	 */
	public Set<Article> modifierArticles(final Collection<Article> articles) throws ArticleInexistantException;
	
	/**
	 * Supprimer un article depuis une référence unique.
	 * 
	 * @param reference Une référence unique d'article.
	 * 
	 * @throws ArticleInexistantException Si la référence ne correspond à aucun article connu.
	 */
	public void supprimerArticle(final String reference) throws ArticleInexistantException;
	
	/**
	 * Supprimer des articles depuis des références uniques.
	 * 
	 * @param references Des références uniques d'article.
	 * 
	 * @throws ArticleInexistantException Si l'une des références ne correspond à aucun article connu.
	 */
	public void supprimerArticles(final Collection<String> references) throws ArticleInexistantException;
	
	/**
	 * Supprimer des articles répondant à une condition discriminante.
	 * 
	 * @param condition Une condition discriminante d'article.
	 * 
	 * @return Le nombre d'articles supprimés.
	 */
	public Integer supprimerArticles(final Predicate<Article> condition);
	
	/**
	 * Vérifier l'existence d'au moins un article répondant à une condition discriminante.
	 * 
	 * @param condition Une condition discriminante d'article.
	 * 
	 * @return Vrai si il existe au moins un article répondant à la condition discriminante.
	 */
	public boolean existerArticle(final Predicate<Article> condition);
	
}
