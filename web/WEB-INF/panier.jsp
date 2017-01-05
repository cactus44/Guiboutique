<%-- 
    Document   : panier
    Created on : 9 nov. 2016, 23:33:32
    Author     : GAYG7251
--%>

<%@page import="java.util.List"%>
<%@page import="com.guiboutique.objets.Produit"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="com.guiboutique.objets.Panier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>La Guiboutique</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="starter-template.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">La Guiboutique</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li ><a href="/Guiboutique">Accueil</a></li>
                        <li class="inactive"><a href="Catalogue">Catalogue</a></li>
                        <li class="active"><a href="PanierControler?action=panier">Mon Panier</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        
        <div class="container-fluid">
        <div class="page-header">
            <h1>Panier</h1>
        </div></div>

        <div class="container-fluid">
            <section class="row">
                <div class="col-md-5"> 
        <table class="table table-bordered table-striped">
            <th>Référence</th>
            <th>Nom du produit</th>
            <th>Quantité</th>
            <th>Prix unitaire</th>
            <th>Montant</th>

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
                <td><b>Montant Total en Euros</b></td>
                <td></td>
                <td></td>
                <td></td>
                <td><%= montant%></td>
            </tr>
        </table>
            <a href="Catalogue"><button class="btn btn-success">Poursuivre ma Commande</button></a>
            <a href="PanierControler?action=confirm"><button class="btn btn-danger">Finaliser ma Commande</button></a>
        </div>
            </section>
        </div>
            
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
