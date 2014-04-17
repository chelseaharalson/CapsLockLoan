package com.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chelsea
 */
public class JDBFunctions {
    private Connection connect = null;
    private Statement statement = null;
    public HttpSession session = null;
   
    public void execute(String SQL) throws SQLException
    {
        try
        {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/CLL?" + "user=root&password=");
            statement = connect.createStatement();
            statement.executeUpdate(SQL);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Exception: " + e);
        }
    }
    
    public ResultSet select(String SQL) throws SQLException
    {
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/CLL?" + "user=root&password=");
            statement = connect.createStatement();
            rs = statement.executeQuery(SQL);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Exception: " + e);
        }
        return rs;
    }
    
    public void saveLoginID(Integer pLoginID)
    {
        session.setAttribute("LoggedInID", pLoginID);
    }
    
    public Integer getLoginID()
    {
        return (Integer)session.getAttribute("LoggedInID");
    }
    
    public void saveBorrowerID(Integer pBorrowerID)
    {
        session.setAttribute("BorrowerID", pBorrowerID);
    }
    
    public Integer getBorrowerID()
    {
        return (Integer)session.getAttribute("BorrowerID");
    }
    
    public Integer getLoanID()
    {
        return (Integer)session.getAttribute("LoanID");
    }
    
    public void saveLoanID(Integer pLoanID)
    {
        session.setAttribute("LoanID", pLoanID);
    }
    
}
