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
    private Map<Integer,Integer> panier;

    public Panier() {
        Map<Integer,Integer> panier = new HashMap<>();
        this.panier = panier;  
    }
    
    public void AddtoPanier(int reference){
        if (panier.containsKey(reference) != true){
        panier.put(reference, 1);}
        else {int q = panier.get(reference);
        panier.put(reference, q++);
        }    
    }
    
    public Map<Integer,Integer> GetPanier(){
        return panier;    
        }
    }
    

    
    
    

