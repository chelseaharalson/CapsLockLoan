/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Model.JCreditCard;

/**
 *
 * @author Chelsea
 */
public class SendCreditPayment extends HttpServlet {

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
            JCreditCard credit = new JCreditCard();
            
            credit.firstName = request.getParameter("firstName");
            credit.lastName = request.getParameter("lastName");
            credit.email = request.getParameter("email");
            credit.phoneNo = request.getParameter("phoneNo");
            credit.address1 = request.getParameter("address1");
            credit.address2 = request.getParameter("address2");
            credit.city = request.getParameter("city");
            credit.state = request.getParameter("state");
            credit.zipcode = request.getParameter("zipcode");
            credit.country = request.getParameter("country");
            credit.cctype = request.getParameter("cctype");
            
            
            boolean valid = credit.isValid(Long.parseLong(request.getParameter("ccnum")));
            if(valid)
            {
                out.println("The credit card number is valid!");
                credit.ccnum = request.getParameter("ccnum");
            }
            else
            {
                out.println("The credit card number is invalid!");
            }
            
            boolean validExp = credit.isValidExpDate(request.getParameter("expDate"));
            if(validExp)
            {
                out.println("The credit card expiration date is valid!");
                credit.ccnum = request.getParameter("expDate");
            }
            else
            {
                out.println("The credit card expiration date is invalid!");
            }
            
            credit.csv = request.getParameter("csv");
            credit.amount = Double.parseDouble(request.getParameter("amount"));
            
            if(valid && validExp)
            {
                response.sendRedirect("addcredit.jsp");
            }
            else
            {
                response.sendRedirect("errorcredit.jsp");
            }
            
            /*try
            {
                //out.println(credit.insert());
            }
            catch(Exception e)
            {
                out.println("Save Credit Payment: " + e);
            }*/
            
            //response.sendRedirect("summary.jsp");
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
