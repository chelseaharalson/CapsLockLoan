package com.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.*;
import java.util.Calendar;
/**
 *
 * @author Chelsea
 */
public class JLoan extends JDBFunctions {
    
    public int LenderID;
    public int BorrowerID;
    public double LoanAmount;
    public Date DateLoaned;
    public Integer LoanTerm;
    public String PaymentMethod;
    private ResultSet resultSet = null;
    
    public String insert() throws SQLException
    {
        String sql;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        sql = "Insert into Loans(LenderID, BorrowerID, LoanAmount, DateLoaned, LoanTerm, PaymentMethod) values("
                + LenderID + ", "
                + BorrowerID + ", "
                + "'" + LoanAmount + "', "
                + "'" + sf.format(DateLoaned) + "', "
                + LoanTerm + ", "
                + "'" + PaymentMethod + "' "
                + ")"
                ;
            try
            {
                execute(sql);
            }
            catch(Exception e)
            {
                System.out.println("JLoan: " + e);
            }
            return sql;
    }
    
    public String getLoanList(Integer pLenderID) throws Exception
    {
        String sql;
        String style = "<style>td.tdname {width:300px;}</style>";
        String style2 = "<style>td.tddate {width:300px;}</style>";
        String style3 = "<style>td.tdamount {width:300px;}</style>";
        String html = style + style2 + style3 + "<table>";
        html = html + "<tr><th><h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name</h4></th><th><h4>Date</h4></th><th><h4>Amount</h4></th><th></th></tr>";
        
        sql = "SELECT LoanID, firstname, lastname, LoanAmount, dateLoaned FROM Loans AS L " + 
        "JOIN Person AS P ON L.BorrowerID = P.personID " + 
        "WHERE L.LenderID = " + pLenderID.toString();
        
        DecimalFormat df = new DecimalFormat("0.00");
        
        try{
            resultSet = select(sql);
            while (resultSet.next())
            {
                html = html + "<tr>";
                html = html + "<td class =\"tdname\"><form action=\"loandetail.jsp\" method=\"post\"><input type=\"submit\" class=\"fsSubmitButton\" value=\"Select\"> &nbsp;&nbsp;" 
                        + "<input type=\"hidden\" value=\"" + resultSet.getString("LoanID") + "\" name=\"LoanID\">"
                        + resultSet.getString("firstname") + " " + resultSet.getString("lastname") + "</form></td>";
                html = html + "<td class =\"tddate\">" + new SimpleDateFormat("MM/dd/yyyy").format(resultSet.getDate("dateLoaned")) + "</td>";
                html = html + "<td class =\"tdamount\">" + "$" + df.format(Double.parseDouble(resultSet.getString("LoanAmount"))) + "</td>";
                html = html + "</tr>";
            }
            html = html + "</table>";
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return html;
    }
    
    public ResultSet getPPLoanList(Integer pLenderID) throws Exception
    {
        String sql;
        
        sql = "SELECT LoanID, firstname, lastname, LoanAmount, dateLoaned, ppusername FROM Loans AS L " + 
        "JOIN Person AS P ON L.BorrowerID = P.personID " + 
        "JOIN Person AS PP ON L.LenderID = PP.personID " + 
        "WHERE P.ppusername IS NOT NULL AND PP.username IS NOT NULL";
        
        return select(sql);
    }
    
    
    public ResultSet getLoanInfo(Integer pLoanID)
    {
        String sql;
        sql = "SELECT firstname, lastname, address1, city, state, zipcode, LoanAmount, DateLoaned, sum(AmountPaid) AS TotalPaid, count(*) AS cnt, LoanTerm FROM Person as P " + 
        "JOIN Loans as L on P.personID = L.BorrowerID " + 
        "JOIN Payments as Pay on Pay.LoanID = L.LoanID " +
        "WHERE L.LoanID = " + pLoanID.toString();
        
        try
        {
            resultSet = select(sql);  
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return resultSet;
    }
    
    
    public ResultSet getLenderInfo(Integer pLoanID)
    {
        String sql;
        sql = "SELECT firstname AS fn, lastname AS ln, address1 AS a1, city AS c, state AS s, zipcode AS z FROM Person as P " +
                "JOIN Loans as L on P.personID = L.LenderID " +
                "WHERE L.LoanID = " + pLoanID.toString();
        
        try
        {
            resultSet = select(sql);  
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return resultSet;
    }
    
    
    public String calculateDate(Date d, Integer months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Integer x = year * 12 + month + months + 1;
        Integer iYear = x / 12;
        Integer iMonth = x % 12;
        return iMonth.toString() + "/01/" + iYear.toString();
    }
    
    public Integer creditScore(Integer pLoanID)
    {
        Integer creditScore = 0;
        String sql;
        sql = "SELECT COUNT(*), LEAST(PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'), DATE_FORMAT(DateLoaned, '%Y%m')), 6) - COUNT(*) AS Score, DateLoaned FROM Payments AS P "
                + "JOIN Loans AS L ON L.LoanID = P.LoanID "
                + "WHERE L.LoanID = " + pLoanID.toString()
                + " AND DatePaid >= date_add(DATE_SUB(NOW(),INTERVAL DAY(NOW())-1 DAY), INTERVAL -6 month) "
                + "GROUP BY L.LoanID";
        
        try
        {
            resultSet = select(sql);
            if(resultSet.first())
            {
                creditScore = resultSet.getInt("Score");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return creditScore;
    }
    
    public Integer getUserCreditScore(String pEmail)
    {
        String sql;
        int total = 0;
        int count = 0;
        int avgCreditScore = 0;
        sql = "SELECT LoanID " +
                "FROM Person AS P " +
                "JOIN Loans AS L ON L.BorrowerID = P.PersonID " +
                "WHERE email = '"  + pEmail + "'";
        
        try
        {
            resultSet = select(sql);
            while(resultSet.next())
            {
                total = total + creditScore(resultSet.getInt("LoanID"));
                count = count + 1;
            }
            avgCreditScore = total / count;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return avgCreditScore;
    }
    
    public String convertCreditScore(Integer pCreditScore)
    {
        String score = "";
        switch(pCreditScore)
        {
            case 0: score = "A";
                     break;
            case 1: score = "B";
                     break;
            case 2: score = "C";
                     break;
            case 3: score = "D";
                     break;
            case 4: score = "F";
                     break;
            default: score = "F";
                     break;
        }
        return score;
    }
}
