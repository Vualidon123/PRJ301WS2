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
         <%@ include file="/menu.jsp" %>
        <form action='' method=GET> 
           method <input name=mkeyword type=text value="<%=request.getParameter("mkeyword") != null ? request.getParameter("mkeyword") : ""%>">
           date <input name=dkeyword type=text value="<%=request.getParameter("dkeyword") != null ? request.getParameter("dkeyword") : ""%>">
            <input type=submit value=Search >
        </form>
              <table>
            <tr><td>Payment ID</td>
                <td><a href=?colSort=PaymentDate>Payment Date</a></td>
                <td>Amount</td>
                <td><a href=?colSort=PaymentMethod>Payment Method</a></td>
            </tr>

            <%

                List<PaymentDTO> list = (List<PaymentDTO>) request.getAttribute("paymentlist");
                for (PaymentDTO payment : list) {
                      pageContext.setAttribute("payment", payment);
            %>


            <tr><td>
                <a href="PaymentController?action=details&id=${payment.paymentid}">${payment.paymentid}</a></td>
                <td>${payment.paymentdate} </td>
                <td>${payment.amount} </td>
                <td>${payment.paymentmethod}</td>
                 <td>
                    <form action="PaymentController" method="POST">
                        <input name="action" value="delete" type="hidden">
                        <input name="id" type="hidden" value=${payment.paymentid}>
                        <input type="submit" value="Delete">
                    </form>
                </td>
           
            </tr>
            
                <%
                    }
                %>
             <tr>
                <td>
                    <form action="PaymentController" method="POST">
                        <input name="action" value="create" type="hidden">
                        <input type="submit" value="Create">
                    </form>
                </td>
            </tr>


        </table> 
    </body>
</html>
