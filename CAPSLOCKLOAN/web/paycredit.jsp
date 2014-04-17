<!DOCTYPE html>
<html lang="en">
    <%@include file="header.jsp"%>
    <%@page import="com.Model.JPerson"%>
    <%@page import="com.Model.JLoan"%>
    <%@page import="com.Model.JCreditCard"%>
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
            <h4>You can pay another person back by adding a valid credit card. Enter in your billing information and then the amount you wish to pay.</h4>
            <br>
            <form action="SendCreditPayment" method="post">
                <h4>First Name: &nbsp;</h4><input class="textbox2" type="text" name="firstName">
                <br><br>
                <h4>Last Name: &nbsp;</h4><input class="textbox2" type="text" name="lastName">
                <br><br>
                <h4>Email Address: &nbsp;</h4><input class="textbox2" type="text" name="email">
                <br><br>
                <h4>Phone Number: &nbsp;</h4><input class="textbox2" type="text" name="phoneNo">
                <br><br>
                <h4>Address: &nbsp;</h4><input class="textbox2" type="text" name="address1">
                <br><br>
                <h4>Address 2: &nbsp;</h4><input class="textbox2" type="text" name="address2">
                <br><br>
                <h4>City: &nbsp;</h4><input class="textbox2" type="text" name="city">
                <br><br>
                <h4>State: &nbsp;</h4><input class="textbox2" type="text" name="state">
                <br><br>
                <h4>Zip Code: &nbsp;</h4><input class="textbox2" type="text" name="zipcode">
                <br><br>
                <h4>Country: &nbsp;</h4><input class="textbox2" type="text" name="country">
                <br><br>
                <h4>Credit Card Type: &nbsp;</h4><input class="textbox2" type="text" name="cctype">
                <br><br>
                <h4>Credit Card Number: &nbsp;</h4><input class="textbox2" type="text" name="ccnum">
                <br><br>
                <h4>Expiration Date: &nbsp;</h4><input class="textbox2" type="text" name="expDate">
                <br><br>
                <h4>Card Security Code: &nbsp;</h4><input class="textbox2" type="text" name="csv">
                <br><br>
                <h4>Amount: &nbsp;</h4><input class="textbox2" type="text" name="amount">
                <br><br>
                <%
                    if(request.getParameter("score") != null)
                    {
                        out.println("The credit score is: " + request.getParameter("score"));
                    }
                %>
                <br>
                <button class="btn btn-3 btn-3b icon-star-2" onclick="javascript:document.location='creditscore.jsp'">Submit Payment</button>
            </form>
        </hgroup>
      </header>
      
<!-- Footer -->
    
  </div>
</div>

        <%@include file="footer.jsp"%>
    </body>
</html>