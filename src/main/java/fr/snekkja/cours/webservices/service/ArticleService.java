package fr.snekkja.cours.webservices.service;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import fr.snekkja.cours.webservices.dao.IArticleDao;
import fr.snekkja.cours.webservices.domain.Article;
import fr.snekkja.cours.webservices.domain.ArticleDejaExistantException;
import fr.snekkja.cours.webservices.domain.ArticleInexistantException;

/**
 * Un service de gestion des articles.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
@Stateless
public class ArticleService implements IArticleService
{

	/** DAO des articles */
	@Inject
	@ApplicationScoped
	private IArticleDao articleDao;
	
	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#obtenirArticles()
	 */
	@Override
	public Set<Article> obtenirArticles()
	{
		return articleDao.obtenir();
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#obtenirArticle(java.lang.String)
	 */
	@Override
	public Article obtenirArticle(final String reference) throws ArticleInexistantException
	{
		return articleDao.obtenir(reference);
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#obtenirArticles(java.util.Collection)
	 */
	@Override
	public Set<Article> obtenirArticles(final Collection<String> references) throws ArticleInexistantException
	{
		return articleDao.obtenir(references);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#obtenirArticle(java.util.function.Predicate)
	 */
	@Override
	public Set<Article> obtenirArticle(final Predicate<Article> condition)
	{
		return articleDao.obtenir(condition);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#creerArticle(fr.snekkja.cours.webservices.domain.Article)
	 */
	@Override
	public Article creerArticle(final Article article) throws ArticleDejaExistantException
	{
		// Nous générons une nouvelle référence unique pour l'article.
		peuplerReferenceArticle(article);
		
		return articleDao.creer(article);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#creerArticles(java.util.Collection)
	 */
	@Override
	public Set<Article> creerArticles(final Collection<Article> articles) throws ArticleDejaExistantException
	{
		// Nous générons une nouvelle référence unique pour chaque article.
		peuplerReferencesArticles(articles);
		
		return articleDao.creer(articles);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#modifierArticle(fr.snekkja.cours.webservices.domain.Article)
	 */
	@Override
	public Article modifierArticle(final Article article) throws ArticleInexistantException
	{
		return articleDao.modifier(article);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#modifierArticles(java.util.Collection)
	 */
	@Override
	public Set<Article> modifierArticles(final Collection<Article> articles) throws ArticleInexistantException
	{
		return articleDao.modifier(articles);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#supprimerArticle(java.lang.String)
	 */
	@Override
	public void supprimerArticle(final String reference) throws ArticleInexistantException
	{
		articleDao.supprimer(reference);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#supprimerArticles(java.util.Collection)
	 */
	@Override
	public void supprimerArticles(final Collection<String> references) throws ArticleInexistantException
	{
		articleDao.supprimer(references);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#supprimerArticles(java.util.function.Predicate)
	 */
	@Override
	public Integer supprimerArticles(final Predicate<Article> condition)
	{
		return articleDao.supprimer(condition);
	}

	/**
	 * @see fr.snekkja.cours.webservices.service.IArticleService#existerArticle(java.util.function.Predicate)
	 */
	@Override
	public boolean existerArticle(final Predicate<Article> condition)
	{
		return articleDao.exister(condition);
	}
	
	/**
	 * Peupler la référence d'un article.
	 * 
	 * @param article Un article à peupler.
	 */
	private void peuplerReferenceArticle(final Article article)
	{
		article.setReference(genererReferenceArticleUnique());
	}

	/**
	 * Peupler les références d'articles.
	 * 
	 * @param articles Des articles à peupler.
	 */
	private void peuplerReferencesArticles(final Collection<Article> articles)
	{
		for (final Article article : articles)
			peuplerReferenceArticle(article);
	}

	/**
	 * Générer une référence unique d'article.
	 * 
	 * @return La référence unique d'article générée.
	 */
	private String genererReferenceArticleUnique()
	{
		
		String reference = null;
		
		do
		{
			reference = RandomStringUtils.randomAlphabetic(5);
		}
		while (articleDao.exister(reference));
		
		return reference;
	}
	
}
