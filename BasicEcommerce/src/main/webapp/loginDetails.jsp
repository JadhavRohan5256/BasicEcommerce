<%@page import="com.helper.FactoryBuilder"%>
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
	<!-- login form  seciton  start-->
	 <section class="container">
        <div class="loginForm">
            <form action="home" method="post">
        		<img src="img/login.png" alt="png" class="img">
		         <%
		         	String result = (String)request.getAttribute("messeges");
		         	if(result != null) {
		         %>
		        	<div class="cross">
		                <div></div>
		                <div></div>
		            </div>
		        	<div class="messege error">
		        		<%=result %>
		        	</div>
		        <% } %>
                <div class="box">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" value="jadhav@gmail.com">
                </div>
                <div class="box">
                    <label for="pass">Password</label>
                    <input type="password" name="pass" id="pass" value="rohan123">
                </div>
                <div class="box">
                    <input type="submit" value="Sign In" id="btn">
                </div>
            </form>
            <p>Don't have account yet? <a href="registerPage">Sign Up</a></p>
        </div>
    </section>
	<!-- login form  seciton  end-->
	<script src="js/jquery.js"></script>
	<script src="js/header.js"></script>
	<script src="js/index.js"></script>
	<script src="js/loginDetails.js"></script>
</body>
</html>