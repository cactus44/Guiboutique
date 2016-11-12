/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.controler;

import com.guiboutique.beans.GestionDeStockItf;
import com.guiboutique.objets.Produit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GAYG7251
 */
@WebServlet(name = "GestionDeStockControler", urlPatterns = {"/GestionDeStockControler"})
public class GestionDeStockControler extends HttpServlet {

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
        
        /* utile seulement au premier lancement pour initialiser la base
        gds.init(); */
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>le nom du produit referencé 1234 est " + gds.getNomDuProduit(1234) + "</p>");
            out.println("<p>le prix du produit referencé 1234 est " + gds.getPrixDuProduit(1234) + "</p>");
            out.println("<p>la quantite du produit referencé 1234 est " + gds.getQuantiteDuProduit(1234) + "</p>");
            out.println("<p>TEST Liste</p>");
            List<Produit> liste = gds.getListeDesProduitsEnStock();
            for(Produit p : liste){
                out.println("<p>produit reference : " + p.getReference() + 
                " produit nom : " + p.getNom() + " prix : " + p.getPrix() 
                + " quantite : " + p.getQuantite() + "</p>" );  
            }
            out.println("</body>");
            out.println("</html>");
        }
        
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
