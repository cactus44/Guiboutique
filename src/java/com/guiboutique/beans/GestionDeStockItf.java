/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guiboutique.beans;

import com.guiboutique.objets.Produit;
import javax.ejb.Local;

/**
 *
 * @author GAYG7251
 */
@Local
public interface GestionDeStockItf {
    
    public void AddProduitToStock(Produit p);   
    public void init();
    
}