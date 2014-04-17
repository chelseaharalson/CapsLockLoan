<!DOCTYPE html>
<html lang="en">
    <%@include file="header.jsp"%>
    <%@page import="com.Model.JPerson"%>
    <%@page import="javax.servlet.http.HttpSession"%>
    <head>
	<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <link rel="shortcut icon" href="images/icon.ico">
        <title>CAPSLOCKLOAN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="styles/layout.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style2.css" />
        <link rel="stylesheet" type="text/css" href="cssHover/normalize.css" />
	<link rel="stylesheet" type="text/css" href="cssHover/common.css" />
        <link rel="stylesheet" type="text/css" href="cssHover/style.css" />
        <link rel="stylesheet" type="text/css" href="css/component3.css" />
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

    <!-- content body -->
    <!-- ########################################################################################## --> 
    <div class="wrapper row3">
  <div id="footer" class="clear">
      <header id="header" class="clear">
        <hgroup>
            <h3>User Information</h3>
        </hgroup>
      </header>
      <%
          boolean newBorrower = true;
          JPerson person = new JPerson();
          Integer borrowerID = 0;
          if(request.getParameter("Borrower") != null)
          {
                String ID = request.getParameter("Borrower");
                borrowerID = Integer.parseInt(ID);
                newBorrower = borrowerID == 0;
                if(!newBorrower)
                {
                    person.getUserInfo(borrowerID);
                }
          }
      %>
      <form action="SavePerson" method="post">
        <input class="textbox" value="<%out.print(borrowerID.toString());%>" name="personID" type="hidden">
        <table border="0" cellpadding="10">
            <tr><td><h8>Email Address</h8></td><td><input class="textbox" type="text" name="email" <%if(!newBorrower){out.println("value=\""+person.email+"\"");}%>></td></tr>
            <tr><td><h8>Password</h8></td><td><input class="textbox" type="password" name="password"></td></tr>
            <tr><td><h8>First Name</h8></td><td><input class="textbox" type="text" name="firstname" <%if(!newBorrower){out.println("value=\""+person.firstname+"\"");}%>></td></tr>
            <tr><td><h8>Last Name</h8></td><td><input class="textbox" type="text" name="lastname" <%if(!newBorrower){out.println("value=\""+person.lastname+"\"");}%>></td></tr>
            <tr><td><h8>Address 1</h8></td><td><input class="textbox" type="text" name="address1" <%if(!newBorrower){out.println("value=\""+person.address1+"\"");}%>></td></tr>
            <tr><td><h8>Address 2</h8></td><td><input class="textbox" type="text" name="address2" <%if(!newBorrower){out.println("value=\""+person.address2+"\"");}%>></td></tr>
            <tr><td><h8>City</h8></td><td><input class="textbox" type="text" name="city" <%if(!newBorrower){out.println("value=\""+person.city+"\"");}%>></td></tr>
            <tr><td><h8>State</h8></td><td><input class="textbox" type="text" name="state" <%if(!newBorrower){out.println("value=\""+person.state+"\"");}%>></td></tr>
            <tr><td><h8>Zip Code</h8></td><td><input class="textbox" type="text" name="zipcode" <%if(!newBorrower){out.println("value=\""+person.zipcode+"\"");}%>></td></tr>    
            <tr><td><h8>PayPal Username</h8></td><td><input class="textbox" type="text" name="ppusername" <%if(!newBorrower){out.println("value=\""+person.ppusername+"\"");}%>></td></tr>
        </table>
            <button class="btn btn-8 btn-8h">&nbsp;&nbsp;&nbsp; Save & Next &nbsp;&nbsp;&nbsp;</button>
      </form>
      
      

    <!-- ########################################################################################## -->
<!-- Footer -->

    <!-- Footer -->

    <!-- / Section -->
    
  </div>
</div>

        <%@include file="footer.jsp"%>
    </body>
</html>