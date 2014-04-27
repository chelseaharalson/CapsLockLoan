package com.Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 *
 * @author Chelsea Metcalf
 */
public class JCredit {
   
   public String processCredit(Integer LoanID, Double AmountPaid, String DatePaid, String borrowerEmail, String loanEmail)
   {
       String error = "";
       try{
                JDBFunctions db = new JDBFunctions();
                ResultSet rs = null;
                String sql;
                Double credit = 0.0;
                Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(DatePaid);
                String sqlAdd;
                String sqlSubtract;
                Integer BorrowerID = 0;
                Integer LenderID = 0;
                   
                sql = "SELECT BorrowerID, LenderID, credit FROM Loans " +
                       "JOIN Person AS Borrower ON BorrowerID = Borrower.PersonID " +
                        "WHERE LoanID = " + LoanID.toString();
                
                rs = db.select(sql);
                
                //if(rs.first())
                {
                    while(rs.next())
                    {
                        credit = rs.getDouble("credit");
                        BorrowerID = rs.getInt("BorrowerID");
                        LenderID = rs.getInt("LenderID");
                    }
                }
                
            if(credit < AmountPaid)
            {
                try{
                     JPaymentError paymentError = new JPaymentError();
                     paymentError.LoanID = LoanID;
                     paymentError.AmountPaid = AmountPaid;
                     paymentError.DatePaid = date;
                     paymentError.PaymentMethod = "CapsLockLoan credit";
                     paymentError.ErrorDesc = "Payment failed due to insufficient credit";
                     paymentError.insert();
                     String html = sendHtmlPayPalUNEmail(DatePaid);
                     sendReceipt(borrowerEmail, "Error: Not enough credits!", html);
                     sendReceipt(loanEmail, "Error: Not enough credits!", html);
                }
                catch(Exception e)
                {
                    error = e.toString();
                }
            }
            else
            {
                try{
                     JPayment payment = new JPayment();
                     payment.LoanID = LoanID;
                     payment.AmountPaid = AmountPaid;
                     payment.DatePaid = date;
                     payment.PaymentMethod = "CapsLockLoan credit";
                     payment.ConfirmationNo = "TBA";
                     payment.insert();
                     sqlAdd = "UPDATE person SET credit = (credit + " + AmountPaid.toString() + ") WHERE PersonID = " + LenderID.toString();
                     sqlSubtract = "UPDATE person SET credit = (credit - " + AmountPaid.toString() + ") WHERE PersonID = " + BorrowerID.toString();
                     db.execute(sqlAdd);
                     db.execute(sqlSubtract);
                     String html = sendHtmlPayPalSuccessEmail(DatePaid);
                     sendReceipt(borrowerEmail, "CapsLockLoan Payment Successful", html);
                     sendReceipt(loanEmail, "CapsLockLoan Payment Successful", html);
                }
                catch(Exception e)
                {
                    error = e.toString();
                }
            }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       return error;
   }
   
    public void sendReceipt(String mailTo, String subject, String html)
    {
            JAdminFunctions email = new JAdminFunctions();

            // SMTP server information
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "CAPSLOCKLOAN@gmail.com";
            String password = "fatcheeks77";

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
    
    public String sendHtmlPayPalSuccessEmail(String DatePaid)
    {
        String html = "Thank you for your payment. This email is to confirm your payment submitted on " + DatePaid + ".";
        return html;
    }
    
    public String sendHtmlPayPalUNEmail(String DatePaid)
    {
        String html = "Unfortunately, we were unable to receive your payment on " + DatePaid + ". Please try again.";
        return html;
    }
   
}
