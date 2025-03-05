package dao;

import java.util.List;

import metier.entities.Client;

public interface IClientDao {
	public Client save(Client c);
	public List<Client> clientsParMC(String mc);
	public Client getClient(Long id);
	public Client updateClient(Client p);
	public void deleteClient(Long id);
	

}
