package fr.snekkja.cours.rastus;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.snekkja.cours.rastus.domain.Boisson;
import fr.snekkja.cours.rastus.domain.BoissonDejaExistanteException;
import fr.snekkja.cours.rastus.domain.BoissonInexistanteException;
import fr.snekkja.cours.rastus.service.BoissonService;

@Path(value = "/boisson")
public class BoissonWebService {

	@EJB
	private BoissonService boissonService;
	
	@GET
	@Produces(value = "application/json")
	public List<Boisson> obtenirBoissons()
	{
		return boissonService.obtenirBoissons();
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces(value = "application/json")
	public Boisson obtenirBoisson(@PathParam(value = "id") final int identifiant) throws BoissonInexistanteException
	{
		return boissonService.obtenirBoisson(identifiant);
	}
	
	@DELETE
	@Path(value = "/{id}")
	public void supprimerBoisson(@PathParam(value = "id") final int identifiant) throws BoissonInexistanteException
	{
		boissonService.supprimerBoisson(identifiant);
	}
	
	@PUT
	public Response creerBoisson(
			@FormParam(value = "nom") final String nom,
			@FormParam(value = "prix") final double prix)
	{
		
		Response reponse;
		
		final Boisson boisson = new Boisson();
		boisson.setNom(nom);
		boisson.setPrix(BigDecimal.valueOf(prix));
		
		try {
			boissonService.creerBoisson(boisson);
			reponse = Response.ok().build();
		} catch (BoissonDejaExistanteException e) {
			reponse = Response.status(409).build();
		}
		
		return reponse;
	}
	
	@POST
	public void modifierBoisson(
			@FormParam(value = "id") final int identifiant,
			@FormParam(value = "nom") final String nom,
			@FormParam(value = "prix") final double prix) throws BoissonInexistanteException
	{
		final Boisson boisson = new Boisson(identifiant, nom, BigDecimal.valueOf(prix));
		
		boissonService.modifierBoisson(boisson);
	}

}
