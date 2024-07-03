<%@page import="model.PaymentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp" flush="true" />

        <h1>Student Details </h1>         
        <p> Login user: ${sessionScope.usersession.username}</p>

        <table>

            <tr><td>Id</td><td>${requestScope.object.paymentid}</td></tr>
            <tr><td>Date</td><td>${requestScope.object.paymentdate}</td></tr>
            <tr><td>Amount</td><td>${requestScope.object.amount}</td></tr>		 
             <tr><td>Method</td><td>${requestScope.object.paymentmethod}</td></tr>	
        </table>


        <form action="PaymentController">
            <input type=hidden name="id" value="${requestScope.object.paymentid}">
            <input type=hidden name="action" value="edit">
            <input type=submit value="Edit">
        </form>

 <%String error = (String) request.getAttribute("error");%>
        <%if (error != null) {%>
        <h3> <%=error%> </h3>
        <%}%>


    </body>
</html>
