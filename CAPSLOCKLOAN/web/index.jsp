<!DOCTYPE html>
<html lang="en">
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
        <link rel="stylesheet" type="text/css" href="css/component2.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,700' rel='stylesheet' type='text/css' />
        <script type="text/javascript" src="js/modernizr.custom.79639.js"></script> 
        <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
        <noscript>
                <link rel="stylesheet" type="text/css" href="css/nojs.css" />
        </noscript>
    </head>
    <body>
<div class="wrapper row1">
  <header id="header" class="clear">
    <hgroup>
      <h1><img src="images/CapsLockLogo-resize.png" alt="logo"><a href="index.jsp">&nbsp;<different>C </different><span>A P S </span><different>L</different><span> O C K </span><different>L </different><span>O A N</span></a></h1>
    </hgroup>
    <div class="fl_right">
    </div>
  </header>
</div>
       
        
<div class="wrapper row2">
  <nav id="topnav">
    <form action="Login" method="post">
    <ul class="clear">
        <form action="Login" method="post">
            <li><h7>Username: &nbsp;</h7><input class="textbox" type="text" name="email">&nbsp;
            <h7>Password: &nbsp;</h7><input class="textbox" input type="password" name="password">&nbsp;
            <button class="btn btn-7 btn-7h">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Login &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
        </form>
        
        <form style="display: inline" action="person.jsp">
            <button class="btn btn-6 btn-6h" onclick="javascript:document.location='person.jsp'">Create Account</button>
        </form>
    </ul>

  </nav>
</div>
<!-- content -->

    <!-- content body -->
    <!-- ########################################################################################## -->      
			
			<div id="da-slider" class="da-slider">
				<div class="da-slide">
					<h2>Lending Money</h2>
					<p>Have you ever lent money to a friend or family member only to never see that money again?</p>
					<a href="person.jsp" class="da-link">Create Account</a>
					<div class="da-img"><img src="images/02.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>Paying Back</h2>
					<p>Do you feel uncomfortable asking for someone to pay money back?</p>
					<a href="person.jsp" class="da-link">Create Account</a>
					<div class="da-img"><img src="images/03.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>Losing Track</h2>
					<p>Have you lost track of how much a friend of family member owes you?</p>
					<a href="person.jsp" class="da-link">Create Account</a>
					<div class="da-img"><img src="images/01.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>Purpose</h2>
					<p>CAPSLOCKLOAN will send a statement every month to the borrower reminding them to pay. All parties will be notified.</p>
					<a href="person.jsp" class="da-link">Create Account</a>
					<div class="da-img"><img src="images/04.png" alt="image01" /></div>
				</div>
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span>
					<span class="da-arrows-next"></span>
				</nav>
			</div>
        </div>

<!-- Footer -->
<div class="wrapper row4">
  <div id="footer" class="clear">
    <!-- Section One -->
    <section class="one_quarter">
      <section class="main">
                   <ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-1">
							<div class="ch-info">
								<h3>Add Lender Information</h3>
								<p>Name, Address, Email</p>
							</div>
						</div>
					</li>			
			</section>
    </section>
    <!-- Section Two -->
    <section class="one_quarter">
      <section class="main">
                   <ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-2">
							<div class="ch-info">
								<h3>Add Borrower Information</h3>
								<p>Name, Address, Email</p>
							</div>
						</div>
					</li>			
			</section>
    </section>
    <!-- Section Three -->
    <section class="one_quarter">
      <section class="main">
                   <ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-3">
							<div class="ch-info">
								<h3>Add Loan Information</h3>
								<p>Amount Owed, Start Date of Loan, Term of Loan</p>
							</div>
						</div>
					</li>			
			</section>
    </section>
    <!-- Section Four -->
    <section class="one_quarter">
      <section class="main">
                   <ul class="ch-grid">
					<li>
						<div class="ch-item ch-img-4">
							<div class="ch-info">
								<h3>Track Payments</h3>
								<p>Enter Payments, Send Payment Confirmation</p>
							</div>
						</div>
					</li>			
			</section>
    </section>
    </div>
    </div>
    <!-- Footer -->

    <!-- / Section -->

    <%@include file="footer.jsp"%>
</div>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.cslider.js"></script>
		<script type="text/javascript">
			$(function() {
			
				$('#da-slider').cslider({
					autoplay	: true,
					bgincrement	: 450
				});
			
			});
		</script>	
    </body>
</html>