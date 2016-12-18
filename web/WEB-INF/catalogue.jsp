<%-- 
    Document   : main
    Created on : 9 nov. 2016, 22:28:15
    Author     : GAYG7251
--%>

<%@page import="java.util.List"%>
<%@page import="com.guiboutique.objets.Produit"%>
<%@page import="com.guiboutique.objets.Stock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>La Guiboutique</title>
    </head>
    <body> 
        <h1>Liste des produits en Stock</h1>

        <table class="table table-bordered">
            <th>référence</th>
            <th>nom</th>
            <th>prix</th>
            <th>quantité</th>
            <th>panier</th>

            <%-- On récupère une liste de produit fournie par la servlet Catalogue --%>
            <% List<Produit> stock = (List<Produit>) application.getAttribute("stock"); %>

            <%-- Pour chaque produit , on ajoute une ligne dans le tableau ... --%>
            <% for (Produit p : stock) {%>


            <tr>
                <td><%= p.getReference()%></td>
                <td><%= p.getNom()%></td>
                <td><%= p.getPrix()%></td>
                <td><%= p.getQuantite()%></td>

                <td><a href="PanierControler?reference=<%= p.getReference()%>">Ajouter au panier</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
