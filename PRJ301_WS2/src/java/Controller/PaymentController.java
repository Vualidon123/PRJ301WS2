/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PaymentDAO;
import model.PaymentDTO;

/**
 *
 * @author PC MSI
 */
public class PaymentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String mkeyword = request.getParameter("mkeyword");
        String dkeyword = request.getParameter("dkeyword");
        String sortCol = request.getParameter("colSort");

        PaymentDAO paymentDAO = new PaymentDAO();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usersession") == null) {
            response.sendRedirect("login.jsp");
        }
        List<PaymentDTO> list = null;
        PaymentDAO dao = new PaymentDAO();

        if (action == null || action.equals("list")) {
            if (mkeyword == null && dkeyword == null) {
                mkeyword = "";
                list = dao.list(mkeyword, sortCol,1 );
            } else if (mkeyword.isEmpty() && !dkeyword.isEmpty()) {
                list = dao.list(dkeyword, sortCol, 2);
            } else if (!mkeyword.isEmpty() && dkeyword.isEmpty()) {
                list = dao.list(mkeyword, sortCol, 1);
            } else {
                mkeyword = "";
                list = dao.list(mkeyword, sortCol, 1);
            }
            request.setAttribute("paymentlist", list);
            request.getRequestDispatcher("/PaymentList.jsp").forward(request, response);

        } else if (action.equals("details")) {

            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException ex) {
                log("Parameter id has wrong format.");
            }

            PaymentDTO payment = null;
            if (id != null) {
                payment = paymentDAO.load(id);
            }

            request.setAttribute("object", payment);
            RequestDispatcher rd = request.getRequestDispatcher("Paymentdetails.jsp");
            rd.forward(request, response);

        } else if (action.equals("edit")) {
            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                log("Parameter id has wrong format");
            }
            PaymentDTO student = null;
            if (id != null) {
                student = paymentDAO.load(id);
            }
            request.setAttribute("nextaction", "update");
            request.setAttribute("object", student);
            RequestDispatcher rd = request.getRequestDispatcher("Paymentedit.jsp");
            rd.forward(request, response);

        } else if (action.equals("create")) {
            PaymentDTO student = new PaymentDTO();
            request.setAttribute("object", student);
            request.setAttribute("nextaction", "insert");
            RequestDispatcher rd = request.getRequestDispatcher("Paymentedit.jsp");
            rd.forward(request, response);

        } else if (action.equals("update")) {
            Integer id = null;
            PaymentDTO payment = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                log("Parameter id has wrong format");
            }
            String method = request.getParameter("paymentmethod");
            String date = request.getParameter("paymentdate");
            float amount = 0;
            try {
                amount = Float.parseFloat(request.getParameter("amount"));
            } catch (NumberFormatException e) {
                log("Parameter amount has wrong format");
            }
            if (id == null || method.isEmpty() || date.isEmpty() || amount == 0) {
                payment = paymentDAO.load(id);
                request.setAttribute("object", payment);
                request.setAttribute("error", "Missing one or more field so cannot save");
                RequestDispatcher rd = request.getRequestDispatcher("Paymentdetails.jsp");
                rd.forward(request, response);
            }

            if (id != null) {
                payment = paymentDAO.load(id);
            }

            payment.setPaymentdate(date);
            payment.setPaymentmethod(method);
            payment.setAmount(amount);
            paymentDAO.update(payment);
            request.setAttribute("object", payment);
            request.setAttribute("nextaction", "update");
            RequestDispatcher rd = request.getRequestDispatcher("Paymentdetails.jsp");
            rd.forward(request, response);

        } else if (action.equals("insert")) {
            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                log("Parameter id has wrong format");
            }

            String method = request.getParameter("paymentmethod");
            String date = request.getParameter("paymentdate");
            float amount = 0;
            try {
                amount = Float.parseFloat(request.getParameter("amount"));
            } catch (NumberFormatException e) {
                log("Parameter amount has wrong format");
            }
            if (id == null || method.isEmpty() || date.isEmpty() || amount == 0) {
                request.setAttribute("error", "Missing one or more field so cannot save");
                RequestDispatcher rd = request.getRequestDispatcher("Paymentdetails.jsp");
                rd.forward(request, response);
            }
            PaymentDTO payment = new PaymentDTO();

            payment.setPaymentid(id);
            payment.setPaymentdate(date);
            payment.setPaymentmethod(method);
            payment.setAmount(amount);
            paymentDAO.update(payment);

            paymentDAO.insert(payment);
            request.setAttribute("object", payment);
            RequestDispatcher rd = request.getRequestDispatcher("Paymentdetails.jsp");
            rd.forward(request, response);

        } else if (action.equals("delete")) {
            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                log("Parameter id has wrong format");
            }
            PaymentDTO student = null;
            paymentDAO.delete(id);
            response.sendRedirect("./PaymentController");

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
