<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details</title>
    </head>
    <body>

        <jsp:include page="/menu.jsp" flush="true" /> 
        <h1>Student Edit </h1>

        <p>Login user: ${sessionScope.usersession.username}</p>
        <form action="PaymentController" method="POST">
            <table>
                <tr><td>Id</td><td><input type="text" name="id" value="${requestScope.object.paymentid}"></td></tr>
                <tr><td>Date</td><td><input type="text" name="paymentdate" value="${requestScope.object.paymentdate}"></td></tr>
                <tr><td>Amount</td><td><input type="text" name="amount" value="${requestScope.object.amount}"></td></tr>
                <tr><td>Method</td><td><input type="text" name="paymentmethod" value="${requestScope.object.paymentmethod}"></td></tr>
             
                <tr>
                    <td colspan="2">

                        <input name="action" value=${requestScope.nextaction} type="hidden">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>

        </form>


    </body>
</html>
