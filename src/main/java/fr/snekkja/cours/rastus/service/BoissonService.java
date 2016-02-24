package fr.snekkja.cours.rastus.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import fr.snekkja.cours.rastus.domain.Boisson;
import fr.snekkja.cours.rastus.domain.BoissonInexistanteException;

@Stateless
public class BoissonService {

	private static Logger LOG = Logger.getLogger(BoissonService.class.getName());
	
	/** Liste des boissons mockées */
	private List<Boisson> boissons;

	@PostConstruct
	public void postConstruct()
	{
		
		boissons = new ArrayList<>();

		final Boisson cafe = new Boisson(1, "café", BigDecimal.valueOf(1.2));
		final Boisson cafeAuLait = new Boisson(2, "café au lait", BigDecimal.valueOf(2d));
		final Boisson chocolat = new Boisson(3, "chocolat", BigDecimal.valueOf(2));
		final Boisson the = new Boisson(4, "thé", BigDecimal.valueOf(1.5));

		boissons.add(cafe);
		boissons.add(cafeAuLait);
		boissons.add(chocolat);
		boissons.add(the);
		
	}

	public List<Boisson> obtenirBoissons()
	{
		return boissons;
	}

	public Boisson obtenirBoisson(final int identifiant) throws BoissonInexistanteException
	{
		final Boisson boissonTrouvee = boissons.stream().filter(boisson -> boisson.getIdentifiant() == identifiant)
				.findFirst().orElse(null);

		if (boissonTrouvee == null) {
			final String message = MessageFormat.format("Il n''existe pas de boisson d''identifiant {0}.", identifiant);
			throw new BoissonInexistanteException(message);
		}

		return boissonTrouvee;
	}
	
	public void supprimerBoisson(final int identifiant) throws BoissonInexistanteException
	{
		
		final boolean resultat = boissons.removeIf(boisson -> boisson.getIdentifiant() == identifiant);
		
		if (!resultat)
		{
			final String message = MessageFormat.format("Il n''existe pas de boisson d''identifiant {0}.", identifiant);
			throw new BoissonInexistanteException(message);
		}
		
	}
	
	public void creerBoisson(final Boisson boisson)
	{
		boissons.add(boisson);
		
		LOG.info(boissons.toString());
	}
	
	public void modifierBoisson(final Boisson boisson) throws BoissonInexistanteException
	{
	
		final Boisson ancienneBoisson = boissons.stream().filter(b -> b.getIdentifiant() == boisson.getIdentifiant()).findAny().orElse(null);
		
		if (ancienneBoisson == null)
		{
			final String message = MessageFormat.format("Il n''existe pas de boisson d''identifiant {0}.", boisson.getIdentifiant());
			throw new BoissonInexistanteException(message);
		}
		else
		{
			boissons.remove(ancienneBoisson);
			boissons.add(boisson);
		}
		
		LOG.info(boissons.toString());
		
	}

}
