
package com.nastib.tpecom.servlets.commande;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeCommande extends HttpServlet {
    public static final String VUE_LISTECLIENT = "/WEB-INF/listeCommande.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher(VUE_LISTECLIENT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_LISTECLIENT).forward(request, response);
        
    }
}
