<%-- 
    Document   : PaymentList
    Created on : 27-06-2024, 15:35:21
    Author     : PC MSI
--%>
<%@page import="java.util.List"%>
<%@page import="model.PaymentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment List</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action='' method=GET> 
            <input name=keyword type=text value="<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>">
            <input type=submit value=Search >
        </form>
              <table border="1">
            <tr><td>PaymentID</td>
                <td><a href=?colSort=PaymentDate>PaymentDate</a></td>
                <td>Amount</td>
                <td><a href=?colSort=PaymentMethod>PaymentMethod</a></td>
            </tr>

            <%

                List<PaymentDTO> list = (List<PaymentDTO>) request.getAttribute("Paymentlist");
                for (PaymentDTO student : list) {
            %>


            <tr><td><%=student.getPaymentID()%> </td>
                <td><%=student.getPaymentDate()%> </td>
                <td><%=student.getAmount()%> </td>
                <td><%= student.getPaymentMethod()%></td></tr>
                <%
                    }
                %>



        </table> 
    </body>
</html>
