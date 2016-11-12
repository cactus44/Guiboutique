/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.tests;

import com.guiboutique.objets.Produit;
import com.guiboutique.objets.Stock;
import java.util.List;

/**
 *
 * @author GAYG7251
 */
public class Test {

    public static void main(String[] args) {
        //initialisation d'un stock 
        Stock stock = new Stock();

        //Ajout d'un produit dans le stock
        Produit produit = new Produit(23466, "Chaise", 22,50);
        stock.AddProduitintoStock(produit.getReference(), produit, 2);
        //Ajout d'un second exemplaire du même produit
        stock.AddProduitintoStock(produit.getReference(), produit, 2);

        //Ajout d'un nouvel article
        Produit produit1 = new Produit(23468, "Boulon", 122,50);
        stock.AddProduitintoStock(produit1.getReference(), produit1, 5);
        
        stock.removeProduitFromStock(produit1.getReference(), 2);

        //On récupère l'ensemble des articles en stock 
        List<Produit> lp = stock.getAllProduitsFromStock();
        for (Produit p : lp) {
        // On recupère la quantité pour chaque produit en stock
        int quantite = stock.getProduitQuantite(p.getReference());
        // On concatène tout ca
        System.out.println("produit : " + p.getNom() + " ,reference : " + p.getReference() + " ,quantite : " + quantite);

        }
    }

}
