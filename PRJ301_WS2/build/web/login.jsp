<%-- 
    Document   : login
    Created on : 27-06-2024, 07:52:19
    Author     : PC MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>PRJ301 Demo - Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>

        <h1>Please login</h1>

        <%String error = (String) request.getAttribute("error");%>
        <%if (error != null) {%>
        <h3> <%=error%> </h3>
        <%}%>
        <form action="LoginController" method="POST">
            <input name="user" type="text">
            <input name="password" type="password">
            <input value="Login" name="action" type="submit">
        </form>

    </body>
</html>

