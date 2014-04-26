package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.JPayment;
import java.util.*;
import java.text.*;

/**
 *
 * @author Chelsea
 */
@WebServlet(name = "SavePayment", urlPatterns = {"/SavePayment"})
public class SavePayment extends HttpServlet {

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
            
            JPayment payment = new JPayment();
            payment.AmountPaid = Double.parseDouble(request.getParameter("AmountPaid"));
            payment.session = request.getSession(true);
            payment.LoanID = payment.getLoanID();
            try{
                payment.DatePaid = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(request.getParameter("DatePaid"));
            }
            catch(Exception e){
                System.out.println(e);
            }
            payment.PaymentMethod = request.getParameter("PaymentMethod");
            payment.ConfirmationNo = payment.LoanID.toString() + new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
            
            try
            {
                out.println(payment.insert());
            }
            catch(Exception e)
            {
                System.out.println("Save Loan: " + e);
            }
            
            response.sendRedirect("loandetail.jsp?LoanID=0");
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
