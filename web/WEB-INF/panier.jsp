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
    <title>La Guiboutique</title>
</head>
<body>
    session     <%= session.getId() %>
    creation time <%= session.getCreationTime() %>
    
    <h1>Panier</h1>
    

    <table>
        <th>reference</th>
        <th>nom</th>
        <th>quantite</th>
        <th>prix unitaire</th>
        <th>montant</th>

        <%
            //On récupère le panier dans la JSP
            Panier panier = (Panier) session.getAttribute("panier");

            //Variable pour calcul du montant du panier
            int montant = 0;

            /* Pour chaque produit ajouté au Panier(Map) , je recupere la référence
            /* que j'utilise ensuite pour récuperer la quantite ajoutée au panier */
            
            for (int reference : panier.getReferencesProduits().keySet()) {
                //On récupère le produit associé à la référence passée en paramètre (la référence est la clé de la Map)
                Produit p = panier.getReferencesProduits().get(reference);
                
                //On récupère la quantité associée à la référence passée en paramètre
                int quantite = panier.getQuantitesFromReferences().get(reference);
                
                //Calcul du montant total
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
