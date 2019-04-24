package com.infotel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotel.metier.Personne;
import com.infotel.service.Iservice;
import com.infotel.service.ServiceImpl;

/**
 * Servlet implementation class ModifierPersonne
 */
@WebServlet("/ModifierPersonne")
public class ModifierPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Iservice service = new ServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPersonne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Personne p=service.affichagePersonne(Integer.parseInt(request.getParameter("id")));
		
		//on passe de la servlet vers le formulaire
		request.setAttribute("id", p.getId()); // apres un p.get on ajoute les attributs mis dans la classe java
		request.setAttribute("nom", p.getNom());
		request.setAttribute("prenom", p.getPrenom());
		request.setAttribute("age", p.getAge()); // apres un request,on prend les attributs dans la jsp
		
		//preparer à l'envoi
		request.setAttribute("personnes", service.findAllpersonnes());
		request.setAttribute("adresses", service.findAlladresses());
		
		//appel de la jsp
		request.getRequestDispatcher("personnes.jsp").forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
