<html lang="en">
    <%@include file="header.jsp"%>
    <%@page import="com.Model.JPerson"%>
    <%@page import="com.Model.JPayment"%>
    <%@page import="com.Model.JLoan"%>
    <%@page import="com.Model.JStatement"%>
    <%@page import="javax.servlet.http.HttpSession"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.util.Date"%>
    <%@page import="java.text.*"%>
    <%@page import="java.text.DecimalFormat;"%>
    <head>
	<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <link rel="shortcut icon" href="images/icon.ico">
        <title>CAPSLOCKLOAN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="styles/layout2.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
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
      <header id="header" class="clear">
        <hgroup>
        <%
            JPayment paymentInfo = new JPayment();
            JLoan loanPerson = new JLoan();
            Integer LoanID = 0;
            
            if(request.getParameter("LoanID").equals("0"))
            {
                loanPerson.session = request.getSession(true);
                LoanID = loanPerson.getLoanID();
            }
            else
            {
                LoanID = Integer.parseInt(request.getParameter("LoanID"));
            }

            String html = "";
            JStatement statement = new JStatement();
            html = statement.create(LoanID, true);
            out.println(html);
            
            paymentInfo.session = request.getSession(true);
            paymentInfo.saveLoanID(LoanID);

        %>
        </hgroup>
      </header>
      <br>
        
        <button class="btn btn-3 btn-3b icon-star-2" onclick="javascript:document.location='payment.jsp'">Add Payment</button>
<!-- Footer -->
  </div>
</div>
        

        <%@include file="footer.jsp"%>
    </body>
</html>