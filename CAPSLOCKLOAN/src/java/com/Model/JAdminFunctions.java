package com.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.internet.AddressException;
import java.util.Date;
 
public class JAdminFunctions extends JDBFunctions{
    
    private ResultSet resultSet = null;
    public int adminLoggedInID;
    public int adminID;
    
    public void sendHtmlEmail(String host, String port, final String userName, final String password, String toAddress, String subject, String message) throws AddressException, MessagingException
    {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator()
        {
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
    }
        
    public boolean loginAdmin(String email, String password) throws Exception
    {
        String sql;
        sql = "SELECT COUNT(*) as cnt, adminID FROM Admin where email = '" + email + "' and password = '" + password + "'"; 
        boolean result = false;
        
        resultSet = select(sql);
        try
        {
            if(resultSet.first())
            {
                result = (resultSet.getInt("cnt") > 0);
                if(result)
                {
                    adminLoggedInID = resultSet.getInt("adminID");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return result;
    }
    
    public void saveAdminLoginID(Integer pLoginID)
    {
        session.setAttribute("AdminLoggedInID", pLoginID);
    }
    
    public Integer getAdminLoginID()
    {
        return (Integer)session.getAttribute("AdminLoggedInID");
    }
}