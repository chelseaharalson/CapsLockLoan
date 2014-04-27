/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Controller;

import com.Model.JDBFunctions;
import com.Model.JCredit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chelsea
 */
public class ProcessCredit extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            try{
                JDBFunctions db = new JDBFunctions();
                ResultSet rs = null;
                String sql;
                
                sql = "SELECT LoanID, LoanAmount, LoanTerm, dateLoaned, P.ppusername AS ppFrom, PP.ppusername, P.email AS borrowerEmail, PP.email AS loanEmail FROM Loans AS L " + 
                "JOIN Person AS P ON L.BorrowerID = P.personID " + 
                "JOIN Person AS PP ON L.LenderID = PP.personID " + 
                "WHERE PaymentMethod = 'cllCredit'";
                
                rs = db.select(sql);
                
                //if(rs.first())
                {
                    while(rs.next())
                    {
                        JCredit cllCredit = new JCredit();
                        out.println(cllCredit.processCredit(rs.getInt("LoanID"), rs.getDouble("LoanAmount") / rs.getInt("LoanTerm"), request.getParameter("date"), rs.getString("borrowerEmail"), rs.getString("loanEmail")));
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            response.sendRedirect("admin.jsp");
            
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
