package com.Controller;
import com.Model.JPayPal;
import com.Model.JLoan;
import com.Model.JDBFunctions;
import java.sql.ResultSet;
import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chelsea
 */
@WebServlet(name = "ProcessPayPal", urlPatterns = {"/ProcessPayPal"})
public class ProcessPayPal extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*JPayPal process = new JPayPal();
            process.callerUID = "makomark_api1.msn.com";
            process.callerPswd = "7Q6LSRTHBLZQFC93";
            process.callerSig = "ATy.GCKh5j8sNoTjVHB8PsHRTcXNA3qWB1cGBeWdoNFz2meIGZUy4.vH";
            process.appID = "APP-80W284485P519543T";
            process.receiverEdress = "makomark@msn.com";
            process.senderEdress = "swordsandcarrots@gmail.com";
            process.unitPrice = "0.50";
            out.println(process.requestPayment(process.getcURL()));*/
            
            try{
                JDBFunctions db = new JDBFunctions();
                ResultSet rs = null;
                String sql;
                
                sql = "SELECT LoanID, LoanAmount, LoanTerm, dateLoaned, P.ppusername AS ppFrom, PP.ppusername, P.email AS borrowerEmail, PP.email AS loanEmail FROM Loans AS L " + 
                "JOIN Person AS P ON L.BorrowerID = P.personID " + 
                "JOIN Person AS PP ON L.LenderID = PP.personID " + 
                "WHERE P.ppusername IS NOT NULL AND PP.ppusername IS NOT NULL";
                
                rs = db.select(sql);
                
                if(rs.first())
                {
                    while(rs.next())
                    {
                        //out.println("hi");
                        JPayPal pp = new JPayPal();
                        out.println(pp.processPayPal(rs.getInt("LoanID"), rs.getDouble("LoanAmount") / rs.getInt("LoanTerm"), request.getParameter("date"), rs.getString("ppFrom"), rs.getString("borrowerEmail"), rs.getString("loanEmail")));
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
