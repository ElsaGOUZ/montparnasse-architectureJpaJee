package com.infotel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infotel.metier.Adresse;
import com.infotel.metier.Connexion;
import com.infotel.metier.Personne;

public class DaoImpl implements Idao {

	// Unité de persistence
	EntityManagerFactory emf;

	// porteur de requête
	EntityManager em;

	@Override
	public int ajouterPersonne(Personne p) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.persist(p);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			// on annule la transaction
			tx.rollback(); // si transaction echoue
		}
		return p.getId();

	}

	@Override
	public Personne getPersonne(int idPersonne) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Personne p = new Personne();

		try {
			// 2-effectuer la requête
			p = em.getReference(Personne.class, idPersonne);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;

	}

	@Override
	public Personne affichagePersonne(int idPersonne) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Personne p = new Personne();

		try {
			// 2-effectuer la requête
			p = em.find(Personne.class, idPersonne);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;

	}

	public int supprimerPersonne(Personne p) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.remove(p);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return p.getId();
	}

	@Override
	public int modifierPersonne(Personne p) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			// 2-effectuer la requête
			em.merge(p);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return p.getId();
	}

	@Override
	public List<Personne> findAllpersonnes() {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Personne> l = new ArrayList<Personne>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT p FROM Personne p");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Personne> rechercherParMC(String mc) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Personne> l = new ArrayList<Personne>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT p FROM Personne p where nom like:lenom");
			q.setParameter("lenom", "%" + mc + "%");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Personne> findAllPersonnesConnexion() {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Personne> l = new ArrayList<Personne>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT p FROM Personne p join p.connexion");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

	@Override
	public List<Personne> findAllPersonnesAdresse() {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Personne> l = new ArrayList<Personne>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT p FROM Personne p join p.adresse");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

// ADRESSE

	@Override
	public int ajouterAdresse(Adresse a) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.persist(a);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			// on annule la transaction
			tx.rollback(); // si transaction echoue
		}
		return a.getIdAdresse();

	}

	@Override
	public Adresse getAdresse(int idAdresse) {
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Adresse a = new Adresse();

		try {
			// 2-effectuer la requête
			a = em.getReference(Adresse.class, idAdresse);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	@Override
	public Adresse affichageAdresse(int idAdresse) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Adresse a = new Adresse();

		try {
			// 2-effectuer la requête
			a = em.find(Adresse.class, idAdresse);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	@Override
	public int supprimerAdresse(Adresse a) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.remove(a);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int modifierAdresse(Adresse a) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			// 2-effectuer la requête
			em.merge(a);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Adresse> findAlladresses() {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Adresse> l = new ArrayList<Adresse>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT a FROM Adresse a");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

	@Override
	public List<Adresse> rechercherAdresseParMC(String mcAdresse) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Adresse> l = new ArrayList<Adresse>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT a FROM Adresse a where nom like:lenom");
			q.setParameter("lenom", "%" + mcAdresse + "%");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

	// CONNEXION

	@Override
	public int ajouterConnexion(Connexion c) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.persist(c);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			// on annule la transaction
			tx.rollback(); // si transaction echoue
		}
		return c.getIdConnexion();

	}

	@Override
	public Connexion getConnexion(int idConnexion) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Connexion c = new Connexion();

		try {
			// 2-effectuer la requête
			c = em.getReference(Connexion.class, idConnexion);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Connexion affichageConnexion(int idConnexion) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Connexion c = new Connexion();

		try {
			// 2-effectuer la requête
			c = em.find(Connexion.class, idConnexion);

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;

	}

	@Override
	public int supprimerConnexion(Connexion c) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			// 1-debuter la transaction
			tx.begin();

			// 2-effectuer la requête
			em.remove(c);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return c.getIdConnexion();
	}

	@Override
	public int modifierConnexion(Connexion c) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			// 2-effectuer la requête
			em.merge(c);

			// 3- valider la transaction
			tx.commit(); // veut dire toutes les opérations sont bien passées je valide

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return c.getIdConnexion();

	}

	@Override
	public List<Connexion> findAllconnexion() {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Connexion> l = new ArrayList<Connexion>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT c FROM Connexion c");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

	@Override
	public List<Connexion> rechercherConnexionParMC(String mcConnexion) {
		// TODO Auto-generated method stub
		// ouverture unité de persistence et transaction
		emf = Persistence.createEntityManagerFactory("demojpa-pu");
		em = emf.createEntityManager();
		Query q = null;
		List<Connexion> l = new ArrayList<Connexion>();
		try {

			// 2-effectuer la requête
			q = em.createQuery("SELECT c FROM Connexion c where nom like:lenom");
			q.setParameter("lenom", "%" + mcConnexion + "%");
			l = q.getResultList();

			// 4-fermer l'unité de persistence
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

}
