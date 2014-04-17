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
            <h4>CapsLockLoan credit score is based on the last 6 months of loan payments by the borrower.</h4>
            <br>
            <h7>A:</h7> 0 missed payments in the last 6 months
            <br>
            <h7>B:</h7> 1 missed payment in the last 6 months
            <br>
            <h7>C:</h7> 2 missed payments in the last 6 months
            <br>
            <h7>D:</h7> 3 missed payments in the last 6 months
            <br>
            <h7>F:</h7> 4 or more missed payments in the last 6 months
            <br><br>
            <form action="GetCreditScore" method="post">
                <h4>Enter email address: &nbsp;</h4><input class="textbox2" type="text" name="email">
                <br><br>
                <%
                    if(request.getParameter("score") != null)
                    {
                        out.println("The credit score is: " + request.getParameter("score"));
                    }
                %>
                <br>
                <button class="btn btn-3 btn-3b icon-star-2" onclick="javascript:document.location='creditscore.jsp'">Get Credit Score</button>
            </form>
        </hgroup>
      </header>
      
<!-- Footer -->
    
  </div>
</div>

        <%@include file="footer.jsp"%>
    </body>
</html>