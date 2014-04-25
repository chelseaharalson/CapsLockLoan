<!DOCTYPE html>
<html lang="en">
    <%@include file="header.jsp"%>
    <%@page import="com.Model.JPerson"%>
    <%@page import="com.Model.JLoan"%>
    <%@page import="javax.servlet.http.HttpSession"%>
    <head>
	<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <link rel="shortcut icon" href="images/icon.ico">
        <title>CAPSLOCKLOAN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="styles/layout2.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <link rel="stylesheet" type="text/css" href="css/component4.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,700' rel='stylesheet' type='text/css' />
        <script type="text/javascript" src="js/modernizr.custom.79639.js"></script> 
        <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
        <noscript>
                <link rel="stylesheet" type="text/css" href="css/nojs.css" />
        </noscript>
    </head>
    <body>
<!-- content -->
<div class="wrapper row3">
  <div id="footer" class="clear">
      
      <br>
      
      <header id="header" class="clear">
        <hgroup>
            <input type="submit" class="fsSubmitButton2" value="&nbsp;&nbsp; Add Credit &nbsp;&nbsp;" onclick="window.location='addcredit.jsp';">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" class="fsSubmitButton2" value="Redeem Credit" onclick="window.location='redeemcredit.jsp';">
            <br><br>
            <%JPerson person = new JPerson();
            Integer personID = 0;
            personID = (Integer)session.getAttribute("LoggedInID");
            person.getUserInfo(personID);
            %>
            <h3><%out.println(person.firstname + " " + person.lastname);%></h3>
            <%
            out.println(person.address1);
            out.println("<br>");
            out.println(person.city + ", " + person.state + " " + person.zipcode);
            out.println("<br><br>");
            %>
            <%JLoan loan = new JLoan();
            out.println(loan.getLoanList(personID));
            %>
        </hgroup>
      </header>
      <br>
      <button class="btn btn-3 btn-3b icon-star-2" onclick="javascript:document.location='loanSelectBorrower.jsp'">&nbsp; &nbsp; Add Loan &nbsp; &nbsp;</button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button class="btn btn-3 btn-3b icon-star-2" onclick="javascript:document.location='creditscore.jsp'">Credit Score</button>
      

<!-- Footer -->
    
  </div>
</div>

        <%@include file="footer.jsp"%>
    </body>
</html>