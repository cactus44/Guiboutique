/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.beans;

import com.guiboutique.objets.Produit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author GAYG7251
 */

@Stateless
public class GestionDeStockFacade implements GestionDeStockItf {
    @PersistenceContext
    EntityManager em;

    @Override
    public void AddProduitToStock(Produit p) {
        em.persist(p);
    }

    @Override
    public void init() {
        Produit p = new Produit(1234,"bouteille bordeaux",2,4);
        em.persist(p);
    }
    
}
