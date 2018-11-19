
package com.nastib.tpecom.servlets.commande;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listecommandes")
public class ListeCommandes extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    public static final String VUE_LISTECLIENTS = "/WEB-INF/listeCommandes.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher(VUE_LISTECLIENTS).forward(request, response);
    }

}
