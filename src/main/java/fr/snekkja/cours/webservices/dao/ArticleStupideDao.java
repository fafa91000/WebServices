package fr.snekkja.cours.webservices.dao;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import fr.snekkja.cours.webservices.domain.Article;
import fr.snekkja.cours.webservices.domain.ArticleDejaExistantException;
import fr.snekkja.cours.webservices.domain.ArticleInexistantException;

/**
 * Un DAO stupide des articles.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
@Stateless
public class ArticleStupideDao implements IArticleDao
{

	/** Ensemble des articles connus */
	private Map<String, Article> articlesConnus;
	
	/**
	 * Initialisation post-construction de l'EJB.
	 */
	@PostConstruct
	public void postConstruct()
	{
		articlesConnus = new HashMap<>();
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#obtenir()
	 */
	@Override
	public Set<Article> obtenir()
	{
		return new HashSet<>(articlesConnus.values());
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#obtenir(java.lang.String)
	 */
	@Override
	public Article obtenir(final String reference) throws ArticleInexistantException
	{
		final Article article = articlesConnus.get(reference);
		
		if (article == null)
			throw new ArticleInexistantException(MessageFormat.format("La référence {0} ne correspond à aucun article.", reference));
		
		return article;
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#obtenir(java.util.Collection)
	 */
	@SuppressWarnings("unused")
	@Override
	public Set<Article> obtenir(final Collection<String> references) throws ArticleInexistantException
	{
		// Nous ne vérifions pas l'existence ou le duplicat par soucis de simplicité. Cela reste le dumdumDao.
		return articlesConnus
			.entrySet()
			.stream()
			.filter(entree -> references.contains(entree.getKey()))
			.map(entree -> entree.getValue())
			.collect(Collectors.toSet());
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#obtenir(java.util.function.Predicate)
	 */
	@Override
	public Set<Article> obtenir(final Predicate<Article> condition)
	{
		return articlesConnus
				.values()
				.stream()
				.filter(condition)
				.collect(Collectors.toSet());
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#creer(fr.snekkja.cours.webservices.domain.Article)
	 */
	@Override
	public Article creer(final Article article) throws ArticleDejaExistantException
	{
		
		final String referenceArticle = article.getReference();
		
		if (articlesConnus.containsKey(article.getReference()))
		{
			final String message = MessageFormat.format("Un article ({0}) existe déjà.", articlesConnus.get(referenceArticle));
			throw new ArticleDejaExistantException(message);
		}
		
		articlesConnus.put(referenceArticle, article);
		
		return articlesConnus.get(referenceArticle);
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#creer(java.util.Collection)
	 */
	@Override
	public Set<Article> creer(final Collection<Article> articles) throws ArticleDejaExistantException
	{
		for (final Article article : articles)
			creer(article);
		
		return articlesConnus
				.values()
				.stream()
				.filter(articleConnu -> articles.contains(articleConnu))
				.collect(Collectors.toSet());
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#modifier(fr.snekkja.cours.webservices.domain.Article)
	 */
	@Override
	public Article modifier(final Article article) throws ArticleInexistantException
	{
		
		final String referenceArticle = article.getReference();
		
		if (!articlesConnus.containsKey(referenceArticle))
		{
			final String message = MessageFormat.format("Aucun article modifiable n''existe pour la référence d''article {0}.", referenceArticle);
			throw new ArticleInexistantException(message);
		}
		
		articlesConnus.put(referenceArticle, article);
		
		return articlesConnus.get(referenceArticle);
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#modifier(java.util.Collection)
	 */
	@Override
	public Set<Article> modifier(final Collection<Article> articles) throws ArticleInexistantException
	{
		for (final Article article : articles)
			modifier(article);
		
		return articlesConnus
				.values()
				.stream()
				.filter(articleConnu -> articles.contains(articleConnu))
				.collect(Collectors.toSet());
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#supprimer(java.lang.String)
	 */
	@Override
	public void supprimer(final String reference) throws ArticleInexistantException
	{
		if (articlesConnus.remove(reference) == null)
		{
			final String message = MessageFormat.format("Aucun article connu n''a pu être supprimé depuis la référence {0}.", reference);
			throw new ArticleInexistantException(message);
		}
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#supprimer(java.util.Collection)
	 */
	@Override
	public void supprimer(final Collection<String> references) throws ArticleInexistantException
	{
		for (final String reference : references)
			supprimer(reference);
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#supprimer(java.util.function.Predicate)
	 */
	@Override
	public Integer supprimer(final Predicate<Article> condition)
	{
		final Set<Article> articlesASupprimer = articlesConnus
			.values()
			.stream()
			.filter(condition)
			.collect(Collectors.toSet());
		
		for (final Article articleASupprimer : articlesASupprimer)
			articlesConnus.remove(articleASupprimer.getReference());
		
		return articlesASupprimer.size();
	}

	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#exister(java.lang.String)
	 */
	@Override
	public boolean exister(final String reference)
	{
		return articlesConnus.containsKey(reference);
	}
	
	/**
	 * @see fr.snekkja.cours.webservices.dao.IArticleDao#exister(java.util.function.Predicate)
	 */
	@Override
	public boolean exister(final Predicate<Article> condition)
	{
		return articlesConnus
				.values()
				.stream()
				.filter(condition)
				.findFirst()
				.isPresent();
	}

}
