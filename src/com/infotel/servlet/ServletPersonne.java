package com.infotel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotel.metier.Connexion;
import com.infotel.metier.Personne;
import com.infotel.service.Iservice;
import com.infotel.service.ServiceImpl;

/**
 * Servlet implementation class ServletPersonne
 */
@WebServlet("/ServletPersonne")
public class ServletPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Iservice service = new ServiceImpl();
	/**
	 * Default constructor.
	 */
	public ServletPersonne() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1-recuperation des informations
		String nom = null;
		String prenom = null;
		int age = 0;
		int id=0;
//		int age = Integer.parseInt(request.getParameter("age"));
        int adr = 0;
        String login=null;
        String mdp=null;
         //Faire traitement A
		if (request.getParameter("nom") != null) {
			age = Integer.parseInt(request.getParameter("age"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			adr= Integer.parseInt(request.getParameter("idadresse"));
			login= request.getParameter("login");
			mdp= request.getParameter("mdp");
		}
		
		// 2-envoyer à la couche service
		Personne p = new Personne();
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setAge(age);
		if(adr!= 0)
		{
			p.setAdresse(service.getAdresse(adr));
		}
		Connexion c= new Connexion();
		c.setLogin(login);
		c.setMdp(mdp);
		p.setConnexion(c);
		
		
		// ajout en base de données
		if (request.getParameter("ajouter") != null) {
			service.ajouterPersonne(p);
		}
		if (request.getParameter("modifier") != null) {
			id = Integer.parseInt(request.getParameter("idadresse"));
			p.setId(id);
			service.modifierPersonne(p);

		}

		// 3- preparation à l'envoi
		request.setAttribute("personnes", service.findAllpersonnes());
         request.setAttribute("adresses", service.findAlladresses()); //on fait le setadresse pour faire la liste deroulante
          
		// 4- appel de la jsp
		request.getRequestDispatcher("personnes.jsp").forward(request, response);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
