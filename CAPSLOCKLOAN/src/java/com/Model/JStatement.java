package com.Model;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.*;

/**
 *
 * @author Chelsea
 */
public class JStatement {
    
    public String create(Integer LoanID, Boolean web)
    {
            JPayment paymentInfo = new JPayment();
            JLoan loanPerson = new JLoan();
            
            ResultSet rs = loanPerson.getLoanInfo(LoanID);
            
            ResultSet rsLend = loanPerson.getLenderInfo(LoanID);
            
            String creditScore = loanPerson.convertCreditScore(loanPerson.creditScore(LoanID));
            
            String html = "";
            String style = "";
            
            if(web)
            {
                style = "<style>td.tdfield {width:200px;}</style>";
                html = style + "<table>";
                try{
                    if(rs.first())
                    {
                        DecimalFormat df = new DecimalFormat("0.00");
                        DateFormat df2 = new SimpleDateFormat("M/d/yyyy");

                        html = html + "<tr>";
                        html = html + "<td style =\"tdfield\"><h4>" + "Borrower: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + rs.getString("firstname") + " " + rs.getString("lastname") + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "Start Date: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("dateLoaned")) + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "End Date: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + new SimpleDateFormat("MM/dd/yyyy").format(df2.parse(loanPerson.calculateDate(rs.getDate("dateLoaned"), rs.getInt("LoanTerm")))) + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "Amount: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + "$" + df.format(Double.parseDouble(rs.getString("LoanAmount"))) + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "Payments: "  + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + "$" + df.format(rs.getDouble("TotalPaid")) + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "Balance: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + "$" + df.format(rs.getDouble("LoanAmount")-rs.getDouble("TotalPaid")) + "</h4></td>";
                        html = html + "</tr><tr>";
                        html = html + "<td class =\"tdfield\"><h4>" + "Credit Score: " + "</h4></td>";
                        html = html + "<td class =\"tdfield\"><h4>" + creditScore + "</h4></td>";
                        html = html + "</tr>";
                    }
                    html = html + "</table><br>";
                    html = html + paymentInfo.getPaymentList(LoanID);
                }

                catch(Exception e)
                {
                    System.out.println(e);
                }
                
            }
            else // email
            {
                html = "<table>";
                try{
                    if(rs.first() && rsLend.first())
                    {
                        DecimalFormat df = new DecimalFormat("0.00");
                        DateFormat df2 = new SimpleDateFormat("mm/dd/yyyy");

                        html = html + "<tr>";
                        html = html + "<td width=\"200\"><b>" + "Lender: " + "</b></td>";
                        html = html + "<td width=\"200\">" + rsLend.getString("fn") + " " + rsLend.getString("ln") + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Address: " + "</b></td>";
                        html = html + "<td width=\"300\">" + rsLend.getString("a1") + "<br>" + rsLend.getString("c") + " " + rsLend.getString("s") + ", " + rsLend.getString("z") + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Borrower: " + "</b></td>";
                        html = html + "<td width=\"200\">" + rs.getString("firstname") + " " + rs.getString("lastname") + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Start Date: " + "</b></td>";
                        html = html + "<td width=\"200\">" + new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("dateLoaned")) + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "End Date: " + "</b></td>";
                        html = html + "<td width=\"200\">" + new SimpleDateFormat("MM/dd/yyyy").format(df2.parse(loanPerson.calculateDate(rs.getDate("dateLoaned"), rs.getInt("LoanTerm")))) + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Amount: " + "</b></td>";
                        html = html + "<td width=\"200\">" + "$" + df.format(Double.parseDouble(rs.getString("LoanAmount"))) + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Payments: "  + "</b></td>";
                        html = html + "<td width=\"200\">" + "$" + df.format(rs.getDouble("TotalPaid")) + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Balance: " + "</b></td>";
                        html = html + "<td width=\"200\">" + "$" + df.format(rs.getDouble("LoanAmount")-rs.getDouble("TotalPaid")) + "</td>";
                        html = html + "</tr><tr>";
                        html = html + "<td width=\"200\"><b>" + "Credit Score: " + "</b></td>";
                        html = html + "<td width=\"200\">" + creditScore + "</td>";
                        html = html + "</tr>";
                    }
                    html = html + "</table><br>";
                    html = html + paymentInfo.getEmailPaymentList(LoanID);
                }

                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
                     
            return html;
    }
}
