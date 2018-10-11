/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.forms.ClientForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteClient extends HttpServlet {

    public static final String VUE = "/WEB-INF/listeClient.jsp";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        form.deleteClient(request);
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
