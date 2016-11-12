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
        <link rel="stylesheet" href="newcss.css">
        <title>La Guiboutique</title>
    </head>
    <body> 
        <h1>Liste des produits en Stock via Servlet</h1>

        <table>
            <th>référence</th>
            <th>nom</th>
            <th>prix</th>
            <th>quantité</th>
            <th>panier</th>

            <% List<Produit> stock = (List<Produit>) application.getAttribute("stock");
                for (Produit p : stock) {%>


            <tr>
                <td><%= p.getReference()%></td>
                <td><%= p.getNom()%></td>
                <td><%= p.getPrix()%></td>
                <td><%= p.getQuantite() %></td>

                <td><a href="PanierControler?reference=<%= p.getReference()%>">Ajouter au panier</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
