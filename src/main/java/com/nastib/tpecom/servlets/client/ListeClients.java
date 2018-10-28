
package com.nastib.tpecom.servlets.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listeclients")
public class ListeClients extends HttpServlet {
    public static final String VUE_LISTECLIENTS = "/WEB-INF/listeClients.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        this.getServletContext().getRequestDispatcher(VUE_LISTECLIENTS).forward(request, response);
    }

}
