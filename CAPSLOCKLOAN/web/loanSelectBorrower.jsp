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
    <div class="wrapper row3">
  <div id="footer" class="clear">
      <header id="header" class="clear">
        <hgroup>
            <h3>Select or Add Borrower</h3>
        </hgroup>
      </header>
      <br>
      
<form action="person.jsp" method="post">     
    <label>
        <select id="Borrower" name="Borrower">
            <option selected value="0"> New Borrower </option>
            <%JPerson person = new JPerson();
            Integer personID = 0;
            personID = (Integer)session.getAttribute("LoggedInID");
            out.println(person.getBorrowerList(personID));
            %>
        </select>
    </label>
          <br><br>
          <button class="btn btn-8 btn-8h">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Next &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
</form>

<!-- Footer -->
    
  </div>
</div>

        <%@include file="footer.jsp"%>
    </body>
</html>