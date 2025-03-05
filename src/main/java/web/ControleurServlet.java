package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.ClientDaoImpl;
import dao.IClientDao;
import metier.entities.Client;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IClientDao metier;

	@Override
	public void init() throws ServletException {
		metier = new ClientDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (path.equals("/index.do")) {
			request.getRequestDispatcher("clients.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			ClientModele model = new ClientModele();
			model.setMotCle(motCle);
			List<Client> clis = metier.clientsParMC(motCle);
			model.setClients(clis);
			request.setAttribute("model", model);
			request.getRequestDispatcher("clients.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieClient.jsp").forward(request, response);
		} 
		else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
		    String nom = request.getParameter("nomclient");
		    String chiffreStr = request.getParameter("chiffre_daffaires");
		    double chiffre = 0.0;
		    if (chiffreStr != null && !chiffreStr.trim().isEmpty()) {
		        try {
		            chiffre = Double.parseDouble(chiffreStr);
		        } catch (NumberFormatException e) {
		            request.setAttribute("error", "Invalid chiffre d'affaires value");
		        }
		    }
		    String emailclient = request.getParameter("emailclient");
		    String adresseclient = request.getParameter("adresseclient");
		    Client c = new Client(nom, emailclient, adresseclient, chiffre);
		    System.out.println("Avant save : " + c);
		    c = metier.save(c);
		    System.out.println("Après save : " + c);
		    request.setAttribute("client", c);
		    request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		}
		else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteClient(id);
			response.sendRedirect("chercher.do?motCle=");
		}
		else if (path.equals("/editer.do") )
		{
		Long id= Long.parseLong(request.getParameter("id"));
		 Client c = metier.getClient(id);
		 request.setAttribute("client", c);
		request.getRequestDispatcher("editerClient.jsp").forward(request,response);
		}
		else if (path.equals("/update.do") )
		{
		Long id = Long.parseLong(request.getParameter("id"));
		String nom=request.getParameter("nom");
		String email=request.getParameter("email");
		String adr=request.getParameter("adr");
		double ca =Double.parseDouble(request.getParameter("ca"));
		Client c = new Client();
		c.setAdresseClient(adr);
		c.setChiffre_daffaire(ca);
		c.setEmailclient(email);
		c.setNomclient(nom);
		c.setIdclient(id);
		metier.updateClient(c);
		request.setAttribute("client", c);
		request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		response.sendRedirect("chercher.do?motCle=");}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
