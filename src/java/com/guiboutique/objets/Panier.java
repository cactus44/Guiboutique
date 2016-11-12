/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.objets;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author GAYG7251
 */
public class Panier {

    private Produit produit;
    private int reference;
    private int quantite;
    private Map<Integer, Integer> panier;
    private Map<Integer, Produit> refandproduit;

    public Panier() {
        panier = new HashMap<>();
        this.panier = panier;

        refandproduit = new HashMap<>();
        this.refandproduit = refandproduit;
    }

    public void addtoPanier(int reference, Produit produit) {
        if (panier.containsKey(reference) != true) {
            panier.put(reference, 1);
            refandproduit.put(reference, produit);
            this.refandproduit = refandproduit;

        } else {
            //on recupere la quantite pour une reference donnée
            int q = panier.get(reference);
            q++;
            panier.put(reference, q);
            this.panier = panier;

        }
    }

    /**
     * Map contenant en key la reference produit et en value la quantite
     *
     * @return
     */
    public Map<Integer, Integer> getQuantitesFromReferences() {
        return panier;
    }

    /**
     * Map contenant en key la reference produit et en value le produit
     *
     * @return
     */
    public Map<Integer, Produit> getReferencesProduits() {
        return refandproduit;
    }
}
