<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.container {
		width:500px;
		margin:2rem auto;
	}
	.container img {
		width:100%;
	}
	p {
		font-size:1.2rem;
		color:#ff0000;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		padding:0.5rem;
		border:1px solid #039be5;
		margin-left: calc(500px / 2 - 148px / 2 );
		border-radius:5px;
		transition:all 0.5s ease-in-out;
	}
	a:hover {
		background-color:#039be5;
		color:#fff;
	}
</style>
</head>
<body>
	<div class="container">
		<img alt="error 404" src="img/error.png">
		<p>Sorry!! Something went wrong. </p>
		<p>Try after some time</p>
		<a href="homePage">Go To HomePage</a>
	</div>
</body>
</html>