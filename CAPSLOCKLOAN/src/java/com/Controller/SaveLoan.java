package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.JLoan;
import java.util.*;
import java.text.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chelsea
 */
@WebServlet(name = "SaveLoan", urlPatterns = {"/SaveLoan"})
public class SaveLoan extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveLoan</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveLoan at " + request.getContextPath() + "</h1>");

            JLoan loan = new JLoan(); 
            loan.session = request.getSession(true);
            loan.LenderID = loan.getLoginID();
            loan.BorrowerID = loan.getBorrowerID();
            loan.LoanAmount = Double.parseDouble(request.getParameter("LoanAmount"));
            
            try
            {
                loan.DateLoaned = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(request.getParameter("DateLoaned"));
            }
            catch(Exception e){
                System.out.println(e);
            }
            
            loan.LoanTerm = Integer.parseInt(request.getParameter("LoanTerm"));
            
            try
            {
                out.println(loan.insert());
            }
            catch(Exception e)
            {
                System.out.println("Save Loan: " + e);
            }
            
            response.sendRedirect("summary.jsp");
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
