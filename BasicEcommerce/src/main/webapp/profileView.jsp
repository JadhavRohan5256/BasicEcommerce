<%@page import="com.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<%@include file="componets/header.jsp" %>
	<section class="container">
		<div class="profileContainer">
			<%
			User user = (User)session.getAttribute("userDetails");
			if(user != null) {
			%>
			<form action="profileUpdate" method="post" enctype="multipart/form-data" id="profileUpdate" >
			<%
			  String messege = (String)request.getAttribute("messege");
			  if(messege == "1" ){
			%>
				<div class="successMessege">
					Profile Successfully Updated
					<div class="successCross">
						<div></div>
						<div></div>
					</div>
				</div>
			<%
			  }
			  else if(messege != null){
			%>
				<div class="errorMessege">
					<%=messege %>
					<div class="errorCross">
						<div></div>
						<div></div>
					</div>
				</div>
			<%
			  }
			%>
				<div class="boxContainer">
					<div class="file">
						<img alt="PrifleImg" src="UploadedImage/Profile/<%=user.getUserPro()%>">
						<label for="file" class="fileLabel"><i class="fa fa-camera"></i></label>
						<input type="file" name="file" id="file" value="<%=user.getUserPro()%>"> 
					</div>
				</div>
				
				<div class="userDetails">
					<div class="boxContainer">
						<label for="firstName">First Name</label>
						<input type="text" name="firstName" id="firstName" value="<%=user.getFirstName()%>">
					</div>
					<div class="boxContainer">
						<label for="lastName">Last Name</label>
						<input type="text" name="lastName" id="lastName" value="<%=user.getLastName()%>">
					</div>
					<div class="boxContainer">
						<label for="contactNo">Contact No</label>
						<input type="number" name="contactNo" id="contactNo" value="<%=user.getUserPhone()%>">
					</div>
					<div class="boxContainer">
						<label for="birthDate">Birth Date</label>
						<input type="date" name="birthDate" id="birthDate" value="<%=user.getBirthDate()%>">
					</div>
					<div class="boxContainer">
						<label for="email">Email Id</label>
						<input type="email" name="email" id="email" value="<%=user.getUserEmail()%>">
					</div>
					<div class="boxContainer">
						<label for="password">Password</label>
						<input type="text" name="password" id="password" value="<%=user.getUserPassword() %>">
					</div>
					
					<div class="boxContainer">
						<label for="city">City</label>
						<input type="text" name="city" id="city" value="<%=user.getUserAddress().getUserAddressCity() %>">
					</div>
					<div class="boxContainer">
						<label for="pinCode">Pin Code</label>
						<input type="text" name="pinCode" id="pinCode" value="<%=user.getUserAddress().getUserAddressPinCode() %>">
					</div>
					<div class="boxContainer">
						<label for="state">State</label>
						<input type="text" name="state" id="state" value="<%=user.getUserAddress().getUserAddressState() %>">
					</div>
					<div class="boxContainer">
						<label for="house">House No/Appartment No</label>
						<input type="text" name="house" id="house" value="<%=user.getUserAddress().getUserAddressHouseNo() %>">
					</div>
					
					<div class="boxContainer">
						<input type="submit" value="Update" id="update">
					</div>
					
				</div>
			</form>
			<%
			}
			else {
				out.println("<h1 style='text-align:center;'>You are not logged</h1>");
			}
			%>
		</div>
	</section>
	
	<script src="js/jquery.js"></script>
	<script src="js/header.js"></script>
	<script src="js/index.js"></script>
	<script src="js/profile.js"></script>
	<script src="https://use.fontawesome.com/73abffe420.js"></script>
</body>
</html>