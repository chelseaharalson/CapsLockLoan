package com.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Chelsea
 */
public class JPerson extends JDBFunctions {
    private ResultSet resultSet = null;
    
    public int loggedInID;
    public int personID;
    public String email;
    public String password;
    public String firstname;
    public String lastname;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zipcode;
    public boolean active;
    public String ppusername;
    public String pppassword;
    public double credit;

    public Integer insert() throws SQLException
    {
        Integer pID = 0;
        ResultSet rs = null;
        String sql;
        sql = "Insert into Person(email, password, firstname, lastname, address1, address2, city, state, zipcode, active, ppusername, pppassword) values("
                + "'" + email + "', "
                + "'" + password + "', "
                + "'" + firstname + "', "
                + "'" + lastname + "', "
                + "'" + address1 + "', "
                + "'" + address2 + "', "
                + "'" + city + "', "
                + "'" + state + "', "
                + "'" + zipcode + "', "
                + "1, "
                + "'" + ppusername + "', "
                + "'" + pppassword + "'"
                + ")"
                ;
        System.out.println(sql);
            try
            {
                execute(sql);
                rs = select("SELECT max(personID) as personID from Person");
                if(rs.first())
                {
                    pID = rs.getInt("personID");
                }
            }
            catch(SQLException e)
            {
                System.out.println("JPerson: " + e);
            }
        return pID;
    }
    
    public void getUserInfo(Integer pPersonID)
    {
        String sql;
        sql = "SELECT * FROM Person where personID = " + pPersonID.toString(); 
        try
        {
            resultSet = select(sql);
            if(resultSet.first())
            {
                personID = resultSet.getInt("personID");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                firstname = resultSet.getString("firstname");
                lastname = resultSet.getString("lastname");
                address1 = resultSet.getString("address1");
                address2 = resultSet.getString("address2");
                city = resultSet.getString("city");
                state = resultSet.getString("state");
                zipcode = resultSet.getString("zipcode");
                ppusername = resultSet.getString("ppusername");
                pppassword = resultSet.getString("pppassword");
                credit = resultSet.getDouble("credit");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public String update(Integer pPersonID)
    {
        String sql;
        sql = "Update Person "
                + "set email = '" + email + "', "
                + "password = '" + password + "', "
                + "firstname = '" + firstname + "', "
                + "lastname = '" + lastname + "', "
                + "address1 = '" + address1 + "', "
                + "address2 = '" + address2 + "', "
                + "city = '" + city + "', "
                + "state = '" + state + "', "
                + "zipcode = '" + zipcode + "'"
                + " where personID = " + pPersonID.toString()
                ;
            try
            {
                execute(sql);
            }
            catch(SQLException e)
            {
                System.out.println("JPerson: " + e);
            }
        return sql;
    }
    
    public boolean login(String email, String password) throws Exception
    {
        String sql;
        sql = "SELECT COUNT(*) as cnt, personID FROM Person where email = '" + email + "' and password = '" + password + "'"; 
        boolean result = false;
        
        resultSet = select(sql);
        try
        {
            if(resultSet.first())
            {
                result = (resultSet.getInt("cnt") > 0);
                if(result)
                {
                    loggedInID = resultSet.getInt("personID");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return result;
    }
    
    public String getBorrowerList(Integer pLenderID) throws Exception
    {
        String sql;
        String html = "";
        sql = "SELECT BorrowerID, CONCAT(b.firstname, ' ', b.lastname) as FullName FROM Loans " +
        "join person as l on l.personID = Loans.LenderID " +
        "join person as b on b.personID = Loans.BorrowerID " +
        "WHERE LenderID = " + pLenderID.toString();
        try{
            resultSet = select(sql);
            while (resultSet.next())
            {
                html = html + "<option value=\"" + Integer.toString(resultSet.getInt("BorrowerID")) + "\"> " + resultSet.getString("FullName") + " </option>";
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return html;
    }
    
}
