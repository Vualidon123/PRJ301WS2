/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author PC MSI
 */
public class PaymentDAO {
 public List<PaymentDTO> list(String keyword, String sortCol) {

        List<PaymentDTO> list = new ArrayList<PaymentDTO>();

        try {

            Connection con = DBUtils.getConnection();
            String sql = " SELECT PaymentID, PaymentDate, Amount, PaymentMethod FROM Payments ";

            if (keyword != null && !keyword.isEmpty()) {
                sql += " WHERE PaymentMethod like ? OR PaymentDate like ? ";
            }

            if (sortCol != null && !sortCol.isEmpty()) {
                sql += " ORDER BY " + sortCol + " ASC ";
            }

            PreparedStatement stmt = con.prepareStatement(sql);

            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    int id = rs.getInt("PaymentID");
                    String date = rs.getString("PaymentDate");
                    float amount = rs.getFloat("Amount");
                    String method = rs.getString("PaymentMethod");

                    PaymentDTO payment = new PaymentDTO();
                    payment.setPaymentid(id);
                    payment.setPaymentdate(date);
                    payment.setAmount(amount);
                    payment.setPaymentmethod(method);

                    list.add(payment);
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();

        }
        return list;
    }    
  public PaymentDTO load(int id) {

        String sql = "SELECT PaymentID, PaymentDate, Amount, PaymentMethod FROM Payments where PaymentID = ?";

        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                    int id1 = rs.getInt("PaymentID");
                    String date = rs.getString("PaymentDate");
                    float amount = rs.getFloat("Amount");
                    String method = rs.getString("PaymentMethod");

                 PaymentDTO payment = new PaymentDTO();
                    payment.setPaymentid(id1);
                    payment.setPaymentdate(date);
                    payment.setAmount(amount);
                    payment.setPaymentmethod(method);
                return payment;
            }
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
  public Integer insert(PaymentDTO payment) {
        String sql = "INSERT INTO Payments( PaymentID, PaymentDate, Amount, PaymentMethod) "
                + " VALUES (?, ?, ?, ?) ";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, payment.getPaymentid());
            ps.setString(2, payment.getPaymentdate());
            ps.setFloat(3, payment.getAmount());
            ps.setString(4, payment.getPaymentmethod());

            ps.executeUpdate();
            conn.close();
            return payment.getPaymentid();
        } catch (SQLException ex) {
            System.out.println("Insert Student error!" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /*
    Update student and return Id
     */
    public boolean update(PaymentDTO payment) {
        String sql = " UPDATE Payments SET PaymentDate = ? , Amount = ?, PaymentMethod = ? ";
        sql += " WHERE PaymentID = ? ";
        try {
            Connection cn = DBUtils.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            
  
            ps.setString(1, payment.getPaymentdate());
            ps.setFloat(2, payment.getAmount());
            ps.setString(3, payment.getPaymentmethod());
            ps.setInt(4, payment.getPaymentid());

            ps.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            System.err.println("Update student error:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /*
    Delete student 
     */
    public boolean delete(Integer id) {
        String sql = " DELETE Payments ";
        sql += " WHERE PaymentID = ? ";
        try {
            Connection cn = DBUtils.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeQuery();
            cn.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Delete student error:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
