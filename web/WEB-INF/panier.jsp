<%-- 
    Document   : panier
    Created on : 9 nov. 2016, 23:33:32
    Author     : GAYG7251
--%>

<%@page import="java.util.List"%>
<%@page import="com.guiboutique.objets.Produit"%>
<%@page import="com.guiboutique.objets.Stock"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="com.guiboutique.objets.Panier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="newcss.css">
    <title>JSP Page</title>
</head>
<body>
    <h1>Panier</h1>



    <table>
        <th>reference</th>
        <th>nom</th>
        <th>quantite</th>
        <th>prix unitaire</th>
        <th>montant</th>

        <%

            Panier panier = (Panier) request.getAttribute("panier");
            // Liste des produits ajoutés au panier
            //List<Produit> liste = (List<Produit>) request.getAttribute("liste");

            //Variable pour calcul du montant du panier
            int montant = 0;

            /*  for (Produit p : liste){
            Pour chaque produit ajouté au panier , je recupere la référence
           que j'utilise pour récupere la quantite ajoutée au panier 
                int quantite = panier.getPanier().get(p.getReference());
                montant += p.getPrix();
             */
            for (int reference : panier.getReferencesProduits().keySet()) {
                Produit p = panier.getReferencesProduits().get(reference);

                int quantite = panier.getQuantitesFromReferences().get(reference);
             
                //calcul du montant total
                montant += (p.getPrix() * quantite);


        %>
        <tr><td><%= p.getReference()%></td>
            <td><%= p.getNom()%></td>
            <td><%= quantite%></td>
            <td><%= p.getPrix()%></td>
            <td><%= p.getPrix() * quantite%></td>
        </tr>
        <%}%>
        <tr>
            <td>Montant Total en Euros</td>
            <td></td>
            <td></td>
            <td></td>
            <td><%= montant%></td>
        </tr>



    </table>

    <p>
        <a href="Catalogue">Poursuivre ma commande </a>
    </p>
    <p>
        <a href="PanierControler?confirm=yes">Finaliser la commande</a>
    </p>





</body>       


</html>
