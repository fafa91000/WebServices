package fr.snekkja.cours.rastus.dao;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.snekkja.cours.rastus.domain.Boisson;
import fr.snekkja.cours.rastus.domain.BoissonDejaExistanteException;
import fr.snekkja.cours.rastus.domain.BoissonInexistanteException;
import fr.snekkja.cours.rastus.entite.BoissonJpaEntite;

@Stateless
@Transactional
public class BoissonJpaDao {

	@PersistenceContext
	private EntityManager em;
	
	public void creer(final Boisson objetMetier) throws BoissonDejaExistanteException
	{
		
		final BoissonJpaEntite entite = new BoissonJpaEntite();
		
		peuplerEntiteDepuisObjetMetier(objetMetier, entite);
		
		try
		{
			em.persist(entite);
		}
		catch(final EntityExistsException e)
		{
			final String message = MessageFormat.format("La boisson à créer {} existe déjà.", objetMetier);
			throw new BoissonDejaExistanteException(message, e);
		}
		
	}
	
	public boolean exister(final Boisson objetMetier)
	{
		
		final BoissonJpaEntite entite = new BoissonJpaEntite();
		
		peuplerEntiteDepuisObjetMetier(objetMetier, entite);
		
		return em.contains(entite);
		
	}
	
	public Boisson obtenir(final int identifiant) throws BoissonInexistanteException
	{
	
		final BoissonJpaEntite entite = em.find(BoissonJpaEntite.class, identifiant);
		
		if (entite == null)
		{
			final String message = MessageFormat.format("La boisson à obtenir d''identifiant {} n''existe pas.", identifiant);
			throw new BoissonInexistanteException(message);
		}
		
		final Boisson objetMetier = new Boisson();
		
		peuplerObjetMetierDepuisEntite(entite, objetMetier);
		
		return objetMetier;
		
	}
	
	public List<Boisson> obtenir()
	{
		
		final TypedQuery<BoissonJpaEntite> requete = em.createQuery("SELECT B FROM Boisson B", BoissonJpaEntite.class);
		
		final List<BoissonJpaEntite> entites = requete.getResultList();
		
		final List<Boisson> objetsMetier = new ArrayList<>();
		
		peuplerObjetsMetierDepuisEntites(entites, objetsMetier);
		
		return objetsMetier;
	}
	
	public void modifier(final Boisson objetMetier) throws BoissonInexistanteException
	{
		
		final BoissonJpaEntite entite = new BoissonJpaEntite();
		
		peuplerEntiteDepuisObjetMetier(objetMetier, entite);
		
		try
		{
			em.merge(entite);
		}
		catch(final IllegalArgumentException e)
		{
			final String message = MessageFormat.format("La boisson à modifier {} n''existe pas.", objetMetier);
			throw new BoissonInexistanteException(message);
		}
		
	}
	
	public void supprimer(final int identifiant) throws BoissonInexistanteException
	{
		
		BoissonJpaEntite entite = null;
		
		try
		{
			entite = em.getReference(BoissonJpaEntite.class, identifiant);
		}
		catch(final EntityNotFoundException e)
		{
			final String message = MessageFormat.format("La boisson à supprimer d''identifiant {} n''existe pas.", identifiant);
			throw new BoissonInexistanteException(message);
		}
		
		try
		{
			em.remove(entite);
		}
		catch(final IllegalArgumentException e)
		{
			final String message = MessageFormat.format("L''entité {} prédemment trouvée, n''a pu être supprimée.", entite);
			throw new MoteurPersistenceError(message, e);
		}
		
	}
	
	private void peuplerEntiteDepuisObjetMetier(final Boisson objetMetier, final BoissonJpaEntite entite)
	{
		
		entite.setIdentifiant(objetMetier.getIdentifiant());
		entite.setNom(objetMetier.getNom());
		entite.setPrix(objetMetier.getPrix().doubleValue());
		
	}
	
	private void peuplerObjetMetierDepuisEntite(final BoissonJpaEntite entite, final Boisson objetMetier)
	{
		objetMetier.setIdentifiant(entite.getIdentifiant());
		objetMetier.setNom(entite.getNom());
		objetMetier.setPrix(BigDecimal.valueOf(entite.getPrix()));
	}
	
	private void peuplerObjetsMetierDepuisEntites(final List<BoissonJpaEntite> entites, final List<Boisson> objetsMetier)
	{
		
		for (final BoissonJpaEntite entite : entites)
		{
			final Boisson objetMetier = new Boisson();
			peuplerObjetMetierDepuisEntite(entite, objetMetier);
			objetsMetier.add(objetMetier);
		}
		
	}
	
}
