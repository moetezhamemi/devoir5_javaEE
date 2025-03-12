package metier.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "clients")
public class Client implements  Serializable{
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idclient;
	private String nomclient;
	private String emailclient;
	@Column (name = "adresseclient")
	private String adresseClient;
	@Column (name = "chiffre_daffaires")
	private double chiffre_daffaire;
	public Client() {super();}
	public Long getIdclient() {
		return idclient;
	}
	public void setIdclient(Long idclient) {
		this.idclient = idclient;
	}
	public String getNomclient() {
		return nomclient;
	}
	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}
	public String getEmailclient() {
		return emailclient;
	}
	public void setEmailclient(String emailclient) {
		this.emailclient = emailclient;
	}
	public String getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
	public double getChiffre_daffaire() {
		return chiffre_daffaire;
	}
	public void setChiffre_daffaire(double chiffre_daffaire) {
		this.chiffre_daffaire = chiffre_daffaire;
	}
	public Client( String nomclient, String emailclient, String adresseClient, double chiffre_daffaire) {
		super();
		this.nomclient = nomclient;
		this.emailclient = emailclient;
		this.adresseClient = adresseClient;
		this.chiffre_daffaire = chiffre_daffaire;
	}
	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", nomclient=" + nomclient + ", emailclient=" + emailclient
				+ ", adresseClient=" + adresseClient + ", chiffre_daffaire=" + chiffre_daffaire + "]";
	}
	
	
	
 
}
