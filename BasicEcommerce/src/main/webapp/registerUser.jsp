<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%@include file="componets/header.jsp" %>
	<section class="container">
        <div class="loginForm">
            <form  action="register" method="post" enctype="multipart/form-data" id="form">
	     	   <img src="img/login.png" alt="png" class="img">
		        <%
		        	String result = (String) request.getAttribute("messeges");
		        	if(result != null) {
		        %>
		            <div class="cross">
		                <div></div>
		                <div></div>
		            </div>
		       		<div class="messege error">
		       		<%=result %>
		            </div>
		           <%} %>
            
                <div class="box center">
                	Personal Details:
                </div>
                <div class="box">
                    <label for="firstName">First Name</label>
                    <input type="text" name="firstName" id="firstName" autocomplete="off">
                </div>
                <div class="box">
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastName" id="lastName" autocomplete="off">
                </div>
                <div class="box">
                    <label for="contactNo">Contact No</label>
                    <input type="number" name="contactNo" id="contactNo" autocomplete="off">
                </div>
                <div class="box">
                    <label for="birthDate">Data Of Birth</label>
                    <input type="date" name="birthDate" id="birthDate">
                </div>
                <div class="box">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" autocomplete="off">
                </div>
                <div class="box">
                    <label for="pass">Password</label>
                    <input type="password" name="pass" id="pass" autocomplete="off">
                </div>
                <div class="box">
                    <label for="profileImage">Profile Image</label>
                    <input type="file" name="profileImage" id="profileImage">
                </div>
                <div class="box center">
                	Address Details:
                </div>
                <div class="box">
                    <label for="addressCity">City</label>
                    <input type="text" name="addressCity" id="addressCity" autocomplete="off">
                </div>
                <div class="box">
                    <label for="addressPin">Pin Code</label>
                    <input type="number" name="addressPin" id="addressPin" autocomplete="off">
                </div>
                <div class="box">
                    <label for="addressState">State</label>
                    <input type="text" name="addressState" id="addressState" autocomplete="off">
                </div>
                <div class="box">
                    <label for="addressHouse">House no/Appartment no</label>
                    <input type="text" name="addressHouse" id="addressHouse" autocomplete="off">
                </div>
               
                <div class="box">
                    <input type="submit" value="Sign In" id="btn">
                </div>
            </form>
            <p>Already have a Account? <a href="loginPage">Sign In</a></p>
        </div>
    </section>
	<script src="js/jquery.js"></script>
	<script src="js/index.js"></script>
	<script src="js/registerUser.js"></script>
	<script src="js/header.js"></script>
</body>
</html>