package test;

import java.util.List;

import dao.ClientDaoImpl;
import metier.entities.Client;

public class TestMetier {
	public static void main(String[] args) {
		ClientDaoImpl cdao = new ClientDaoImpl();
		Client cli = cdao.save(new Client("melek","melek@gmail.com","nabeul",200));
		System.out.println(cli);
		List<Client> clis =cdao.clientsParMC("melek");
		for (Client c : clis)
		System.out.println(c.getChiffre_daffaire());
}}