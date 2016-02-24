package fr.snekkja.cours.rastus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(value = "/bipbop")
public class BipBopWebService
{
	
	@GET
	public String test(@QueryParam(value = "nom") final String nom)
	{
		return "bipbop, bienvue " + nom;
	}
	
}
