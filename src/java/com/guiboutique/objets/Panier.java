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
    private int nb_articles;

    public Panier() {
        panier = new HashMap<>();
        this.panier = panier;

        refandproduit = new HashMap<>();
        this.refandproduit = refandproduit;
    }

    /**
     * Ajoute dans la Map panier contenant en clé la référence du produit et en
     * valeur la quantité
     *
     */
    public void addtoPanier(int reference, Produit produit) {
        /*
        Dans une Map la cle devant etre unique , on verrifie
        quelle n'existe pas déja, si elle existe déja on met
        seulement à jour la quantité
         */
        if (panier.containsKey(reference) != true) {
            panier.put(reference, 1);
            /*
            On créé une seconde Map pour éviter de faire une
            requete en base pour trouver le produit correspondant à une référence
             */
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

    public int getNbArticles() {
        if (panier.isEmpty()) {
            return 0;
        } else {
            nb_articles = 0;
            for (int ref : panier.keySet()) {
                nb_articles += panier.get(ref);
            }
            return nb_articles;
        }

    }

}
