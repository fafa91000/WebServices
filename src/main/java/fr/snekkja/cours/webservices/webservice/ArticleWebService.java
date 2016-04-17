package fr.snekkja.cours.webservices.webservice;

import java.math.BigDecimal;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.snekkja.cours.webservices.domain.Article;
import fr.snekkja.cours.webservices.domain.ArticleDejaExistantException;
import fr.snekkja.cours.webservices.domain.ArticleInexistantException;
import fr.snekkja.cours.webservices.service.IArticleService;

/**
 * Un WebService REST de gestion des articles.
 * 
 * @author Snekkja
 * @since 17 avr. 2016
 */
@Path(value="/article")
public class ArticleWebService
{
	
	/** Service de gestion des articles */
	@Inject
	@SessionScoped
	private IArticleService articleService;
	
	/**
	 * Obtenir tous les articles existants.
	 * 
	 * @return Une collection contenant tous les articles existants.
	 */
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Set<Article> obtenirArticles()
	{
		return articleService.obtenirArticles();
	}
	
	/**
	 * Obtenir un article depuis une référence.
	 * 
	 * @param reference Une référence d'article existant.
	 * 
	 * @return L'article obtenu.
	 * 
	 * @throws ArticleInexistantException Si la référence ne correspond à aucun article existant.
	 */
	@GET
	@Path(value = "/{reference}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Article obtenirArticle(@PathParam(value = "reference") final String reference) throws ArticleInexistantException
	{
		return articleService.obtenirArticle(reference);
	}
	
	/**
	 * Créer un nouvel article.
	 * 
	 * @param label Un label
	 * @param description Une description
	 * @param prix Un prix
	 * 
	 * @return L'article crée 
	 * 
	 * @throws ArticleDejaExistantException Si l'article à créer existe déjà. 
	 */
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public Article creerArticle(
			@FormParam(value="label") final String label,
			@FormParam(value="description") final String description,
			@FormParam(value="prix") final double prix
		) throws ArticleDejaExistantException
	{
		
		final Article candidat = new Article();
		
		candidat.setReference(null);
		candidat.setCategorieArticle(null);
		
		candidat.setDescription(description);
		candidat.setLabel(label);
		candidat.setPrix(BigDecimal.valueOf(prix));
		
		return articleService.creerArticle(candidat);
	}
	
	/**
	 * Modifier un article existant.
	 * 
	 * @param reference Une référence d'article existant
	 * @param label Un label
	 * @param description Une description
	 * @param prix Un prix
	 * 
	 * @return L'article modifié
	 * 
	 * @throws ArticleInexistantException Si l'article à modifier n'existe pas.
	 */
	@POST
	@Path(value = "/{reference}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Article modifierArticle(
			@PathParam(value="reference") final String reference,
			@FormParam(value="label") final String label,
			@FormParam(value="description") final String description,
			@FormParam(value="prix") final double prix
			) throws ArticleInexistantException
	{
		
		final Article candidat = new Article();
		
		candidat.setCategorieArticle(null);
		
		candidat.setReference(reference);
		candidat.setDescription(description);
		candidat.setLabel(label);
		candidat.setPrix(BigDecimal.valueOf(prix));
		
		return articleService.modifierArticle(candidat);
	}
	
	/**
	 * Supprimer un article existant.
	 * 
	 * @param reference Une référence d'article existant.
	 * 
	 * @throws ArticleInexistantException Si l'article à supprimer n'existe pas.
	 */
	@DELETE
	@Path(value = "/{reference}")
	public void supprimerArticle(@PathParam(value = "reference") final String reference) throws ArticleInexistantException
	{
		articleService.supprimerArticle(reference);
	}
	
}
