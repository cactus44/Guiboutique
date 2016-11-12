/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.beans;

import com.guiboutique.objets.Produit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author GAYG7251
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionDeStockFacade implements GestionDeStockItf {

    @PersistenceContext
    EntityManager em;

    @Resource
    private UserTransaction ut;

    @Override
    public void AddProduitToStock(Produit p) {
        em.persist(p);
    }

    /**
     * Methode à n'utiliser qu'à la premiere execution
     */
    @Override
    public void init() {
        try {
            ut.begin();
            em.persist(new Produit(1234, "Muscadet", 2, 50));
            em.persist(new Produit(1235, "Côte du Rhone", 5,50));
            em.persist(new Produit(1244, "Saint Emilion", 9,50));
            em.persist(new Produit(1246, "Saint Nicolas de Bourgueil", 4,50));
            em.persist(new Produit(1356, "Saumur",4, 50));         
            ut.commit();
        } catch (NotSupportedException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    /**
     * Méthode qui retourne le nom du produit à partir de la référence fournie
     * en paramètre
     *
     * @param reference est la clef primaire utilisée pour la recherche
     * @return retourne le nom du produit
     */
    @Override
    public String getNomDuProduit(int reference) {
        Produit p = em.find(Produit.class, reference);
        return p.getNom();
    }

    /**
     * Méthode qui retourne le prix du produit à partir de la référence fournie
     * en paramètre
     *
     * @param reference est la clef primaire utilisée pour la recherche
     * @return retourne le prix du produit
     */
    @Override
    public int getPrixDuProduit(int reference) {
        Produit p = em.find(Produit.class, reference);
        return p.getPrix();
    }

    /**
     * Méthode qui retourne la quantité le nombre de produits disponibles en
     * stock à partir de la référence fournie en paramètre
     *
     * @param reference
     * @return
     */
    @Override
    public int getQuantiteDuProduit(int reference) {
        Produit p = em.find(Produit.class, reference);
        return p.getPrix();
    }

    @Override
    public List<Produit> getListeDesProduitsEnStock() {
        //on initialise la requête qui sera passée
        Query q = em.createQuery("select OBJECT(p) from Produit p");

        //on stocke le resultat dans une liste
        List<Produit> liste = (List<Produit>) q.getResultList();
        return liste;
    }

    @Override
    public void updateQuantiteDuProduit(int reference, int nouvellequantite) {
        try {
            ut.begin();
            Produit p = em.find(Produit.class, reference);
            p.setQuantite(nouvellequantite);
            ut.commit();
        } catch (Exception e) {
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void updatePrixDuProduit(int reference, int nouveauprix) {
        try {
            ut.begin();
            Produit p = em.find(Produit.class, reference);
            p.setPrix(nouveauprix);
            ut.commit();
        } catch (Exception e) {
            try {
                ut.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Produit getProduit(int reference) {
        return em.find(Produit.class, reference);
    }

}
