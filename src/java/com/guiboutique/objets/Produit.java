/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.objets;

/**
 *
 * @author GAYG7251
 */
public class Produit {
    
    private int reference;
    private String nom;
    private int prix;

    public Produit(int reference, String nom, int prix) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
    }

    public int getReference() {
        return reference;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Bouteille{" + "reference=" + reference + ", nom=" + nom + ", prix=" + prix + '}';
    } 
}