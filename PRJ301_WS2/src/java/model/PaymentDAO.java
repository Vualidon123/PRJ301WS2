/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
                    Date date = rs.getDate("PaymentDate");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    String method = rs.getString("PaymentMethod");

                    PaymentDTO payment = new PaymentDTO();
                    payment.setPaymentID(id);
                    payment.setPaymentDate(date);
                    payment.setAmount(amount);
                    payment.setPaymentMethod(method);

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
                    Date date = rs.getDate("PaymentDate");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    String method = rs.getString("PaymentMethod");

                 PaymentDTO payment = new PaymentDTO();
                    payment.setPaymentID(id1);
                    payment.setPaymentDate(date);
                    payment.setAmount(amount);
                    payment.setPaymentMethod(method);
                return payment;
            }
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
  public Integer insert(PaymentDTO student) {
        String sql = "INSERT INTO student( id, firstname, lastname, age) "
                + " VALUES (?, ?, ?, ?) ";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, student.getId());
                      ps.setInt(4, student.getAge());

            ps.executeUpdate();
            conn.close();
            return student.getId();
        } catch (SQLException ex) {
            System.out.println("Insert Student error!" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }ps.setString(2, student.getFirstname());
            ps.setString(3, student.getLastname());
  
   public boolean update(PaymentDTO student) {
        String sql = " UPDATE student SET firstname = ? , lastname = ?, age = ? ";
        sql += " WHERE id = ? ";
        try {
            Connection cn = DBUtils.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getFirstname());
            ps.setString(3, student.getLastname());
            ps.setInt(4, student.getAge());

            ps.executeQuery();
            cn.close();
        } catch (SQLException e) {
            System.err.println("Update student error:" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(Integer id) {
        String sql = " DELETE student ";
        sql += " WHERE id = ? ";
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
