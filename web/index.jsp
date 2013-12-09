<%-- 
    Document   : index
    Created on : Dec 1, 2013, 2:50:38 PM
    Author     : ize
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div id="container">
            <br />
            <form id="mainform">
                <input type="text" name="query" placeholder="Masukkan keyword tweet" /><br />
                <input type="text" name="positif" placeholder="Masukkan keyword tweet Positif" /><br />
                <input type="text" name="negatif" placeholder="Masukkan keyword tweet Negatif" /><br />
                <input type="submit" value="Cari" />
            </form>
            <br /><br /><br />
            <div id="result"></div>
        </div>
    </body>
</html>
