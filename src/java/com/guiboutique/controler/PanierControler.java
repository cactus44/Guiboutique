/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.controler;

import com.guiboutique.objets.Panier;
import com.guiboutique.objets.Stock;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GAYG7251
 */
@WebServlet(name = "PanierControler", urlPatterns = {"/PanierControler"})
public class PanierControler extends HttpServlet {

    private Panier panier;
    

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
       
        
        Stock stock = (Stock) this.getServletContext().getAttribute("stock");
        
        //mise à jour du stock sur confirmation
        String confirmation = request.getParameter("confirm");
        if("yes".equals(confirmation)){
            for(int reference : panier.getPanier().keySet()){
                int qt = panier.getPanier().get(reference);
                stock.removeProduitFromStock(reference, qt);
                panier.getPanier().clear();
            } this.getServletContext().getRequestDispatcher( "/WEB-INF/confirm.jsp" ).forward( request, response );
        }
        

        //on recupere la reference de l'article ajouté au panier
        
        int reference = Integer.parseInt(request.getParameter("reference"));
        
        //on ajoute cette reference au panier
        this.panier.addtoPanier(reference);
        
        request.setAttribute("panier", panier);
        request.setAttribute("reference", reference);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/panier.jsp" ).forward( request, response );
        
        
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
    
 public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        //On instancie le panier au premier chargement de la servlet seulement
        
        Panier panier = new Panier();
        this.panier = panier;
}}
