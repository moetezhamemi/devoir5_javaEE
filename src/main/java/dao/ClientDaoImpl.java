package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.entities.Client;

public class ClientDaoImpl implements IClientDao {
	@Override
	public Client save(Client c) {
	    Connection conn = SingletonConnection.getConnection();
	    if (conn == null) {
	        System.out.println("Erreur : Connexion null");
	        return c;
	    }
	    try {
	        PreparedStatement ps = conn.prepareStatement(
	            "INSERT INTO CLIENTS(NOMCLIENT, emailclient, adresseclient, chiffre_daffaires) VALUES(?, ?, ?, ?)",
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );
	        ps.setString(1, c.getNomclient());
	        ps.setString(2, c.getEmailclient());
	        ps.setString(3, c.getAdresseClient());
	        ps.setDouble(4, c.getChiffre_daffaire());
	        int rows = ps.executeUpdate();
	        System.out.println("Lignes insérées : " + rows);

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            long id = rs.getLong(1);
	            c.setIdclient(id);
	            System.out.println("ID généré : " + id);
	        } else {
	            System.out.println("Aucun ID généré !");
	        }
	        ps.close();
	    } catch (SQLException e) {
	        System.err.println("Erreur SQL : " + e.getMessage());
	        e.printStackTrace();
	    }
	    return c;
	}
	@Override
	public List<Client> clientsParMC(String mc) {
		List<Client> clients = new ArrayList<Client>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from CLIENTS where NOMCLIENT LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client c = new Client();
				c.setIdclient(rs.getLong("IDCLIENT"));
				c.setNomclient(rs.getString("NOMCLIENT"));
				c.setEmailclient(rs.getString("emailclient"));
				c.setAdresseClient(rs.getString("adresseclient"));
				c.setChiffre_daffaire(rs.getDouble("chiffre_daffaires"));
				clients.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public Client getClient(Long id) {
		Connection conn = SingletonConnection.getConnection();
		Client c = new Client();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from clients where IDclient = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setIdclient(rs.getLong("idclient"));
				c.setNomclient(rs.getString("nomclient"));
				c.setAdresseClient(rs.getString("adresseclient"));
				c.setEmailclient(rs.getString("emailclient"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Client updateClient(Client c) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE clients SET NOMclient=?,emailclient=?,adresseclient=?,chiffre_daffaires=? WHERE IDclient=?");
			ps.setString(1, c.getNomclient());
			ps.setString(2, c.getEmailclient());
			ps.setString(3, c.getAdresseClient());
			ps.setDouble(4, c.getChiffre_daffaire());
			ps.setLong(5, c.getIdclient());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;

	}

	@Override
	public void deleteClient(Long id) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("DELETE FROM clients WHERE IDclient = ?");
		ps.setLong(1, id);
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
	}
}