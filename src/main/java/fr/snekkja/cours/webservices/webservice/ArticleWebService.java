package fr.snekkja.cours.webservices.webservice;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fr.snekkja.cours.webservices.service.IArticleService;

@Path(value="/article")
public class ArticleWebService
{
	
	@Inject
	@SessionScoped
	private IArticleService articleService;
	
	@GET
	public String test()
	{
		return "OK";
	}
	
//	@GET
//	@Produces(value = "application/json")
//	public List<Boisson> obtenirBoissons()
//	{
//		return boissonService.obtenirBoissons();
//	}
//	
//	@GET
//	@Path(value = "/{id}")
//	@Produces(value = "application/json")
//	public Boisson obtenirBoisson(@PathParam(value = "id") final int identifiant) throws BoissonInexistanteException
//	{
//		return boissonService.obtenirBoisson(identifiant);
//	}
//	
//	@DELETE
//	@Path(value = "/{id}")
//	public void supprimerBoisson(@PathParam(value = "id") final int identifiant) throws BoissonInexistanteException
//	{
//		boissonService.supprimerBoisson(identifiant);
//	}
//	
//	@PUT
//	public Response creerBoisson(
//			@FormParam(value = "nom") final String nom,
//			@FormParam(value = "prix") final double prix)
//	{
//		
//		Response reponse;
//		
//		final Boisson boisson = new Boisson();
//		boisson.setNom(nom);
//		boisson.setPrix(BigDecimal.valueOf(prix));
//		
//		try {
//			boissonService.creerBoisson(boisson);
//			reponse = Response.ok().build();
//		} catch (final BoissonDejaExistanteException e) {
//			reponse = Response.status(409).build();
//		}
//		
//		return reponse;
//	}
//	
//	@POST
//	public void modifierBoisson(
//			@FormParam(value = "id") final int identifiant,
//			@FormParam(value = "nom") final String nom,
//			@FormParam(value = "prix") final double prix) throws BoissonInexistanteException
//	{
//		final Boisson boisson = new Boisson(identifiant, nom, BigDecimal.valueOf(prix));
//		
//		boissonService.modifierBoisson(boisson);
//	}
	
}
