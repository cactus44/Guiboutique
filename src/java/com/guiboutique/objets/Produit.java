/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.objets;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author GAYG7251
 */
@Entity
public class Produit {
    
    @Id
    private int reference;
    private String nom;
    private int prix;
    private int quantite;

    public Produit() {
    }

    public Produit(int reference, String nom, int prix, int quantite) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "reference=" + reference + ", nom=" + nom + ", prix=" + prix + '}';
    } 
}