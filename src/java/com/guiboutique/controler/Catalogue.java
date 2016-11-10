package com.guiboutique.controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.guiboutique.objets.Produit;
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
@WebServlet(urlPatterns = {"/Catalogue"})
public class Catalogue extends HttpServlet {

    private Stock stock;

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

        this.getServletContext().setAttribute("stock", stock);

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        Stock stock = new Stock();

        stock.AddProduitintoStock(1234, new Produit(1234, "Muscadet", 2), 50);
        stock.AddProduitintoStock(1235, new Produit(1235, "Côte du Rhone", 5), 50);
        stock.AddProduitintoStock(1244, new Produit(1244, "Saint Emilion", 9), 50);
        stock.AddProduitintoStock(1246, new Produit(1246, "Saint Nicolas de Bourgueil", 4), 50);
        stock.AddProduitintoStock(1356, new Produit(1356, "Saumur", 5), 50);
        this.stock = stock;
    }
}
