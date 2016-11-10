/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.objets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GAYG7251
 */
public class Stock {

    private Map<Integer, Produit> refandproduit;
    private Map<Integer, Integer> refandquantite;
    private int reference;
    private Produit produit;

    public Stock() {
        Map<Integer, Produit> refandprod = new HashMap<>();
        this.refandproduit = refandprod;
        Map<Integer, Integer> refandquantite = new HashMap<>();
        this.refandquantite = refandquantite;
    }

    public List<Produit> getAllProduitsFromStock() {
        List<Produit> lp = new ArrayList<>();
        for (int reference : refandproduit.keySet()) {
            Produit p = refandproduit.get(reference);
            lp.add(p);
        }
        return lp;
    }

    public void AddProduitintoStock(int reference, Produit produit, int quantite) {
        if (refandproduit.containsKey(reference) != true) {
            refandproduit.put(reference, produit);
        }

        /**
         * si la clef existe pas , on ajoute la quantite fournie sinon , on met
         * à jour la valeur déja présente
         */
        if (refandquantite.containsKey(reference) != true) {
            refandquantite.put(reference, quantite);
        } else {
            int q = refandquantite.get(reference);
            refandquantite.put(reference, q += quantite);
        }
    }
    
    public int getProduitQuantite(int reference){
        return refandquantite.get(reference);
    }
    
    public void removeProduitFromStock(int reference, int quantite){
        int q = refandquantite.get(reference);
        refandquantite.put(reference, q -= quantite);        
    }
}
