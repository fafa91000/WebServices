package fr.snekkja.cours.rastus.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import fr.snekkja.cours.rastus.dao.BoissonJpaDao;
import fr.snekkja.cours.rastus.domain.Boisson;
import fr.snekkja.cours.rastus.domain.BoissonDejaExistanteException;
import fr.snekkja.cours.rastus.domain.BoissonInexistanteException;

@Stateless
@Transactional
public class BoissonService {

	@EJB
	private BoissonJpaDao boissonDao;

	public List<Boisson> obtenirBoissons()
	{
		return boissonDao.obtenir();
	}

	public Boisson obtenirBoisson(final int identifiant) throws BoissonInexistanteException
	{
		return boissonDao.obtenir(identifiant);
	}
	
	public void supprimerBoisson(final int identifiant) throws BoissonInexistanteException
	{
		boissonDao.supprimer(identifiant);
	}
	
	public void creerBoisson(final Boisson boisson) throws BoissonDejaExistanteException
	{
		boissonDao.creer(boisson);
	}
	
	public void modifierBoisson(final Boisson boisson) throws BoissonInexistanteException
	{
		boissonDao.modifier(boisson);
	}

}
