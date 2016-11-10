<%-- 
    Document   : panier
    Created on : 9 nov. 2016, 23:33:32
    Author     : GAYG7251
--%>

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
            
                <% Integer s = (Integer) request.getAttribute("reference");
        Panier p = (Panier) request.getAttribute("panier");
        Stock stock = (Stock) application.getAttribute("stock");

        int montant = 0;
        for(int reference : p.getPanier().keySet()){
            int quantite = p.getPanier().get(reference);
            String nom = stock.getProduitFromReference(reference);
            int prix = stock.getPrixFromReference(reference);
            
            montant += (prix * quantite);

     %>
            <tr><td><%= reference%></td>
                <td><%= nom %></td>
                <td><%= quantite %></td>
                <td><%= prix %></td>
                <td><%= prix * quantite%></td>
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





</body>       


</html>
