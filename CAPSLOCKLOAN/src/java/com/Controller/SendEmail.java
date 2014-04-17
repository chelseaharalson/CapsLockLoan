/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import com.Model.JAdminFunctions;
import com.Model.JStatement;
import com.Model.JLoan;

/**
 *
 * @author Chelsea
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/SendEmail"})
public class SendEmail extends HttpServlet {

    public void sendStatement(Integer loanID, String borrowerEmail)
    {
            JAdminFunctions email = new JAdminFunctions();
            JStatement statement = new JStatement();
            String html = "<html>" + statement.create(loanID, false) + "</html>";
            // SMTP server information
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "CAPSLOCKLOAN@gmail.com";
            String password = "fatcheeks77";

            // outgoing message information
            String mailTo = borrowerEmail;
            String subject = "Monthly Statement";

            try
            {
                email.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, html);
                System.out.println("Email sent.");
            }
            catch (Exception ex)
            {
                System.out.println("Failed to send email.");
                ex.printStackTrace();
            }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            JLoan emailPerson = new JLoan();
            String borrowerEmails = "SELECT L.LoanID, email FROM Person as P "
                    + "JOIN Loans as L on P.personID = L.BorrowerID";
            try
            {
                  ResultSet rs = emailPerson.select(borrowerEmails);
                  while(rs.next())
                  {
                      sendStatement(rs.getInt("LoanID"), rs.getString("email"));
                  }
            }
            catch(Exception e)
            {
                  System.out.println(e);  
            }
            
            //sendStatement(1, "swordsandcarrots@gmail.com");
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
