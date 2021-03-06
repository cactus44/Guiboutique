package com.guiboutique.controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.guiboutique.beans.GestionDeStockItf;
import com.guiboutique.objets.Panier;
import com.guiboutique.objets.Produit;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Catalogue
 *
 * @author GAYG7251
 */
@WebServlet(urlPatterns = {"/Catalogue"})
public class Catalogue extends HttpServlet {

    /*Injection de dépendance de façon à pouvoir utiliser l'interface et les
    méthodes associées */
    @EJB
    private GestionDeStockItf gds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* On récupère le contenu du stock, on place tous les objets Produit
        dans une liste , seulement si elle n'existe pas déja , fonctionnalité
        mise à jour interractive (affichage seulement, la mise à jour du stock
        n'est faite seulement à la confirmation
         */
        if (this.getServletContext().getAttribute("stock") == null) {
            List<Produit> stock = gds.getListeDesProduitsEnStock();

            /*
            Si jamais la base est vide , premier lancement par exemple, on
            l'initialise avec un jeu de données.
             */
            if (stock.isEmpty()) {
                gds.init();
                stock = gds.getListeDesProduitsEnStock();
            }

            /* On rend le stock accessible à toutes les servlets et JSP (niveau application) */
            this.getServletContext().setAttribute("stock", stock);
        }

        //init Panier
        HttpSession session = request.getSession();
        if (session.getAttribute("panier") == null) {
            Panier panier = new Panier();
            session.setAttribute("panier", panier);
        }

        /* On forward les requetes et reponses à la jsp catalogue.jsp qui sera chargée de faire
        l'affichage
         */
        this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
