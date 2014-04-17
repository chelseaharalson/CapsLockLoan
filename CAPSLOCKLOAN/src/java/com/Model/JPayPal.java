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
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 *
 * @author Chelsea
 */
public class JPayPal {
    public String callerUID = "";
    public String callerPswd = "";
    public String callerSig = "";
    public String appID = "";
    public String receiverEdress = "";
    public String senderEdress = "";
    public String unitPrice = "";
    public String idNumber = "";
    public String PPinvoiceURL = "";
    
    public String getcURL()
    {
        String request = "curl -s " +
        "-H \"X-PAYPAL-SECURITY-USERID: " + callerUID + "\" " +
        "-H \"X-PAYPAL-SECURITY-PASSWORD: " + callerPswd + "\" " +
        "-H \"X-PAYPAL-SECURITY-SIGNATURE: " + callerSig + "\" " +
        "-H \"X-PAYPAL-REQUEST-DATA-FORMAT: JSON\" " +
        "-H \"X-PAYPAL-RESPONSE-DATA-FORMAT: JSON\" " +
        "-H \"X-PAYPAL-APPLICATION-ID: " + appID + "\" " +
        "https://svcs.sandbox.paypal.com/Invoice/CreateAndSendInvoice -d " +
        "{" +
        "\"requestEnvelope\": " +
        "{" +
        "\"errorLanguage\":\"en_US\" " +
        "}," +
        "\"invoice\": " +
        "{" +
        "\"merchantEmail\":\"" + receiverEdress + "\"," +
        "\"payerEmail\":\"" + senderEdress + "\"," +
        "\"currencyCode\":\"USD\"," +
        "\"paymentTerms\":\"DueOnReceipt\"," +
        "\"itemList\": " +
        "{" +
        "\"item\":[{" +
        "\"name\":\"LoanPayment\"," +
        "\"quantity\":\"1\"," +
        "\"unitPrice\":\"" + unitPrice +"\"" +
        "}]" +
        "}" +
        "}" +
        "}";
        return request;
    }    
    
   public String requestPayment(String cURL)
   {
       String response = "hi";
       try
       {
            URL url = new URL("https://svcs.sandbox.paypal.com/Invoice/CreateAndSendInvoice");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setRequestProperty("X-PAYPAL-SECURITY-USERID", callerUID);
            connection.setRequestProperty("X-PAYPAL-SECURITY-PASSWORD", callerPswd);
            connection.setRequestProperty("X-PAYPAL-SECURITY-SIGNATURE", callerSig);

            connection.setRequestProperty("X-PAYPAL-APPLICATION-ID", appID);

            connection.setRequestProperty("X-PAYPAL-REQUEST-DATA-FORMAT", "JSON");
            connection.setRequestProperty("X-PAYPAL-RESPONSE-DATA-FORMAT", "JSON");

            /* Form JSON into "requestStr" */

            //OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            //writer.write(requestStr);
            //writer.close();
            
            // Read response into StringBuffer
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            response = "hi2";
            while((response = reader.readLine()) != null){
                stringBuffer.append(response);
            }
            reader.close();
       }
       catch(Exception e)
       {
          response = e.toString();
       }

       return response;
   }
   
   public String processPayPal(Integer LoanID, Double AmountPaid, String DatePaid, String fromPPAccount, String borrowerEmail, String loanEmail)
   {
       String error = "";
       try{
            Integer dollarAmount = 0;
            boolean odd;
            Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(DatePaid);

            dollarAmount = AmountPaid.intValue();
            odd = (dollarAmount % 2) == 1;
            if(odd)
            {
                try{
                     JPaymentError paymentError = new JPaymentError();
                     paymentError.LoanID = LoanID;
                     paymentError.AmountPaid = AmountPaid;
                     paymentError.DatePaid = date;
                     paymentError.PaymentMethod = "PayPal";
                     paymentError.ErrorDesc = fromPPAccount + " failed";
                     paymentError.insert();
                     String html = sendHtmlPayPalEmail(DatePaid);
                     sendReceipt(borrowerEmail, "PayPal Payment Unsuccessful", html);
                     sendReceipt(loanEmail, "PayPal Payment Unsuccessful", html);
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
                     payment.PaymentMethod = "PayPal";
                     payment.ConfirmationNo = "TBA";
                     payment.insert();
                     String html = sendHtmlPayPalEmail(DatePaid);
                     sendReceipt(borrowerEmail, "PayPal Payment Successful", html);
                     sendReceipt(loanEmail, "PayPal Payment Successful", html);
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
    
    public String sendHtmlPayPalEmail(String DatePaid)
    {
        /*SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        String d = sf.format(DatePaid);
        String html = "The date paid is: " + d;*/
        String html = "hi";
        
        return html;
    }
   
}
