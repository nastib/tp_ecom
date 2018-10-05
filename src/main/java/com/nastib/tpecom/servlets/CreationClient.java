package com.nastib.tpecom.servlets;

import com.nastib.tpecom.beans.BeanException;
import com.nastib.tpecom.beans.Client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreationClient extends HttpServlet {

    public static final String VUE_AFFICHE = "/afficherClient.jsp";
    public static final String VUE_CREATION = "/creerClient.jsp";
    public static final String MSG_NOM = "Erreur - le <b>nom</b> est obligatoire. ";
    public static final String MSG_ADRESSE = "Erreur - l\'<b>adresse</b> est obligatoire. ";
    public static final String MSG_TEL = "Erreur - le <b>Téléphone</b> est obligatoire. ";
    public static final String MSG_SUCCES = "Client créé avec succès !";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String adresseClient = request.getParameter("adresseClient");
        String telephoneClient = request.getParameter("telephoneClient");
        String emailClient = request.getParameter("emailClient");

        if ((nomClient != null && !nomClient.isEmpty())
                && (adresseClient != null && !adresseClient.isEmpty()
                && (telephoneClient != null && !telephoneClient.isEmpty()))) {
           

            try {
                Client client = new Client();
                client.setNomClient(nomClient);
                client.setPrenomClient(prenomClient);
                client.setAdresseClient(adresseClient);
                client.setTelephoneClient(telephoneClient);
                client.setEmailClient(emailClient);
                
                request.setAttribute("client", client);
                request.setAttribute("message", MSG_SUCCES);
                this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
            
            } catch (BeanException ex) {
                
                request.setAttribute("nomHelp", ex.getMessage());
                this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
            }

        } else {
            if (nomClient == null || nomClient.isEmpty()) {
                request.setAttribute("nomHelp", MSG_NOM);
            } else {
                request.setAttribute("nomHelp", null);
            }

            if (adresseClient == null || adresseClient.isEmpty()) {
                request.setAttribute("adresseHelp", MSG_ADRESSE);
            } else {
                request.setAttribute("adresseHelp", null);
            }

            if (telephoneClient == null || telephoneClient.isEmpty()) {
                request.setAttribute("telephoneHelp", MSG_TEL);
            } else {
                request.setAttribute("telephoneHelp", null);
            }
            this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
        }
    }
}
