package fr.snekkja.cours.rastus;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.snekkja.cours.rastus.domain.Boisson;
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

}
