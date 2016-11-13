/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.controler;

import com.guiboutique.beans.GestionDeStockItf;
import com.guiboutique.objets.Panier;
import com.guiboutique.objets.Produit;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
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

    /* On permet l'accès au stock à cette servlet, ca sera plus pratique
    pour destocker (inclusion EJB) */
    @EJB
    private GestionDeStockItf gds;
    /* Variables */
    private Panier panier;
    private List<Produit> liste;

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

        //mise à jour du stock sur confirmation (uri:PanierControler?confirm=yes)
        String confirmation = request.getParameter("confirm");
        if ("yes".equals(confirmation)) {

            /* on itère sur chacune des clefs contenues dans la map et on
            recupère la value associée et on destocke */
            for (int reference : panier.getQuantitesFromReferences().keySet()) {
                int qt = panier.getQuantitesFromReferences().get(reference);

                //pour chaque article, on destocke                 
                int qstock = gds.getQuantiteDuProduit(reference);
                int newquantite = (qstock - qt);
                gds.updateQuantiteDuProduit(reference, newquantite);
            }
            //on vide le panier
            panier.getQuantitesFromReferences().clear();
            panier.getReferencesProduits().clear();
            // on redirige sur la page de confirmation en utilisant le dispatcher
            this.getServletContext().getRequestDispatcher("/WEB-INF/confirm.jsp").forward(request, response);
        }

        //On recupere la reference de l'article que l'on va ajouter à l'objet Panier
        int reference = Integer.parseInt(request.getParameter("reference"));

        /*On ajoute la reference et le produit associé dans l'objet Panier. On
        récupère l'Objet Produit dans la base de donnée à partir de la référence
        passée en paramètre dans l'url (clé primaire), ils sont ensuite passées
        en paramêtres
         */
        this.panier.addtoPanier(reference, gds.getProduit(reference));

        //On indique l'attribut (référence de l'objet) que l'on retourne à la jsp panier.jsp
        request.setAttribute("panier", panier);
        //On redirige vers panier.jsp
        this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
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

    /**
     * Méthode appelée au premier chargement de la servlet , on l'utilise ici
     * pour instancier un Panier
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //On instancie le panier au premier chargement de la servlet seulement
        Panier panier = new Panier();
        this.panier = panier;

    }

}
