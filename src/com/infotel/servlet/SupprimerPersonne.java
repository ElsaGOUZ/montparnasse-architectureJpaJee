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
 * Servlet implementation class SupprimerPersonne
 */
@WebServlet("/SupprimerPersonne")
public class SupprimerPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Iservice service = new ServiceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPersonne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1-recuperation des informations
				int id= Integer.parseInt(request.getParameter("id"));
				
				//Supprimer en BDD
				Personne p=new Personne();
				p = service.getPersonne(id);
				service.supprimerPersonne(p);
				
				//Preparation � l'envoi
				request.setAttribute("personnes",service.findAllpersonnes());
				 request.setAttribute("adresses", service.findAlladresses());
				 
				//Appel de la jsp
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
