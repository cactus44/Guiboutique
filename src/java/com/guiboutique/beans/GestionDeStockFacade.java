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
 * Session Bean - Facade
 * Entity Manager de l'@Entity Produit qui implémente la session bean Local
 *
 * @author GAYG7251
 */
@Stateless
//Définition du mode de transaction type BMT
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionDeStockFacade implements GestionDeStockItf {

    //Injection dépendance EntityManager
    @PersistenceContext
    EntityManager em;
    //Accès en Mode transactionnel à l'unité de persistance via cet variable
    @Resource
    private UserTransaction ut;

    /**
     * Méthode pour ajouter un produit à la BDD
     *
     * @param p prend en paramètre le produit à ajouter à la bdd
     */
    @Override
    public void AddProduitToStock(Produit p) {
        em.persist(p);
    }

    /**
     * Methode à n'utiliser qu'à la premiere execution (initialisation de
     * données dans la base de données
     */
    @Override
    public void init() {
        try {
            ut.begin();
            em.persist(new Produit(1234, "Muscadet", 2, 50));
            em.persist(new Produit(1235, "Côte du Rhone", 5, 50));
            em.persist(new Produit(1244, "Saint Emilion", 9, 50));
            em.persist(new Produit(1246, "Saint Nicolas de Bourgueil", 4, 50));
            em.persist(new Produit(1356, "Saumur", 4, 50));
            ut.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
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
        return p.getQuantite();
    }

    /**
     * Méthode qui retourne une liste contenant tous les Produit présents dans
     * la base de données
     *
     * @return retourne une liste d'objets Produit
     */
    @Override
    public List<Produit> getListeDesProduitsEnStock() {
        //On initialise la requête qui sera passée
        Query q = em.createQuery("select OBJECT(p) from Produit p");
        //On stocke le resultat dans une liste de Produit
        List<Produit> liste = (List<Produit>) q.getResultList();
        return liste;
    }

    /**
     * Méthode pour mettre à jour la quantite d'un produit en BDD
     *
     * @param reference référence du Produit à mettre à jour
     * @param nouvellequantite nouvelle quantitée disponible
     */
    @Override
    public void updateQuantiteDuProduit(int reference, int nouvellequantite) {
        try {
            ut.begin();
            Produit p = em.find(Produit.class, reference);
            p.setQuantite(nouvellequantite);
            ut.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            try {
                ut.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Méthode pour mettre à jour le prix d'un produit en BDD
     * 
     * @param reference référence du Produit à mettre à jour
     * @param nouveauprix nouveaux prix du Produit
     */
    @Override
    public void updatePrixDuProduit(int reference, int nouveauprix) {
        try {
            ut.begin();
            Produit p = em.find(Produit.class, reference);
            p.setPrix(nouveauprix);
            ut.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            try {
                ut.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(GestionDeStockFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
/**
 * Méthode qui permet de récupérer l'objet Produit associé à l'enregistrement
 * associé à la référence
 * 
 * @param reference
 * @return un Objet de type Produit 
 */
    @Override
    public Produit getProduit(int reference) {
        return em.find(Produit.class, reference);
    }

}
