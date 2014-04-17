package com.Model;

/**
 *
 * @author Chelsea
 */
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JCreditCard extends JDBFunctions{
    private ResultSet resultSet = null;
    
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNo;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zipcode;
    public String country;
    public String cctype;
    public String ccnum;
    public String expDate;
    public String csv;
    public double amount;
    
    public String insert() throws SQLException
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        String sql;
        sql = "Insert into CreditCard(firstName, lastName, email, phoneNo, address1, address2, city, state, zipcode, country, cctype, ccnum, expDate, csv, amount) values("
                + "'" + firstName + "', "
                + "'" + lastName + "', "
                + "'" + email + "', "
                + "'" + phoneNo + "', "
                + "'" + address1 + "', "
                + "'" + address2 + "', "
                + "'" + city + "', "
                + "'" + state + "', "
                + "'" + zipcode + "', "
                + "'" + country + "', "
                + "'" + cctype + "', "
                + "'" + ccnum + "', "
                + "'" + expDate + "', "
                + "'" + csv + "', "
                + amount + " "
                + ")"
                ;
        System.out.println(sql);
            try
            {
                execute(sql);
            }
            catch(SQLException e)
            {
                System.out.println("JCreditCard: " + e);
            }
        return sql;
    }

    public static boolean isValid(long number) 
    {
        int sum = sumOfDoubleEvenPlace(number) + sumOfoddPlace(number);
        boolean sumTest = false;
        boolean lengthTest = false;
        boolean prefixTest = false;
        
        if(sum % 10 == 0)
        {
            sumTest = true;
        }
        
        if(getSize(number) == 16)
        {
            lengthTest = true;
        }
        
        if(prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 6) || prefixMatched(number, 37))
        {
            prefixTest = true;
        }
        
        if(sumTest && lengthTest && prefixTest)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // This function doubles every second digit from right to left. If doubling of a digit results in a two-digit number, it adds up the two digits to get a single-digit number.
    public static int sumOfDoubleEvenPlace(long number) 
    {
        int sum = 0;
        long temp = 0;
        
        for(int i = getSize(number); i > 0; i-=2)
        {
            number = number / 10;
            temp = number % 10;
            number = number / 10;
            temp = temp * 2;
            sum = sum + getDigit((int)temp);
        }
        return sum;
    }
    
    // This function returns the number if it is a single digit (less than 10). Otherwise, it returns the sum of the two digits.
    public static int getDigit(int number) 
    {
        if(number < 10)
        {
            return number;
        }
        else
        {
            // This breaks the number into their component digits and then sums them
            number = number % 10 + number / 10;
        }
        return number;
    }
    
    // This returns the sum of the odd place digts in number
    public static int sumOfoddPlace(long number) 
    {
        int sum = 0;
        long temp = 0;
        
        for(int i = getSize(number); i > 0; i-=2)
        {
            if(i != getSize(number))   // Want to skip this line for the first digit
            {
                number = number / 10;
            }
            temp = number % 10;
            number = number / 10;
            sum = sum + (int) temp;
        }
        return sum;
    }
    
    // This function returns true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d) 
    {
        int k = getSize(d);
        
        long prefix = getPrefix(number, k);  
        
        if(prefix == d)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // This function returns the number of digits from the input
    public static int getSize(long d) 
    {
        int size = 0;
        while(d != 0)
        {
            size++; // Counter
            d = d / 10; // As d moves over and loses a digit, size gets bigger. D starts at the maximum number of digits.
        }
        return size;
    }
    
    // This function returns the first k number of digits from the number. If the number of digits is less than k, it returns the number.
    public static long getPrefix(long number, int k) 
    {
        int length = getSize(number);
        for(int i = 0; i < length - k; i++)
        {
            number = number / 10;
        }
        return number;
    }
}