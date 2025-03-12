package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Client;
import util.JPAutil;

public class ClientDaoImpl implements IClientDao {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE");

	@Override
	public Client save(Client c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(c);
		tx.commit();
		return c;
	}

	@Override
	public List<Client> clientsParMC(String mc) {
	List<Client> cls = entityManager.createQuery("select c from Client c where c.nomclient like :mc").setParameter("mc", "%"+mc+"%").getResultList();
	 return cls;
	}

	@Override
	public Client getClient(Long id) {
		return entityManager.find(Client.class, id);
	}

	@Override
	public Client updateClient(Client c) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(c);
		tx.commit();
		return c;
	}

	@Override
	public void deleteClient(Long id) {
		Client client = entityManager.find(Client.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(client);
		entityManager.getTransaction().commit();
	}
}