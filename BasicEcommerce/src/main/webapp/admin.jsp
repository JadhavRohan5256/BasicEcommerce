<%@page import="com.dao.ProductDao"%>
<%@page import="com.dao.CategoryDao"%>
<%@page import="com.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.User"%>
<%@page errorPage="error_page.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	// if user not an admin user then this user redirecting to the login page
	User user = (User)session.getAttribute("userDetails");
	if(user == null) {
		request.setAttribute("messeges", "You are not logged!");
		request.getRequestDispatcher("loginPage").forward(request, response);
	}
	else {
		int type = user.getUserType();
	    if(user != null && type == 0) {
			request.setAttribute("messeges", "You have not access of this page!");
			request.getRequestDispatcher("loginPage").forward(request, response);
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
<!-- header file -->
<%@include file="componets/header.jsp" %>

<%
// if data sended to the servlet and error occured then error can display here 
String messege = (String)request.getAttribute("messege");
if(messege != null){
	if(messege.matches("1")){
%>
	
	<div class="success">
		Data Successfully Submitted!
		<div class="successCross">
			<div></div>
			<div></div>
		</div>
	</div>
	<%
	}else{ 
	%>
	<div class="error">
		<%=messege %>
		<div id="errorCross" class="successCross" >
			<div></div>
			<div></div>
		</div>
	</div>
	<%
	}
}
	%>
	
	<!-- Dashboard start  -->
	<sections>
		<div class="info">
			<div class="box">
				<img src="img/users.png" alt="fsdf">
				<h2>Users (<%=UserDao.getCount()%>)</h2>
			</div>
			<div class="box">
				<img alt="png" src="img/category.png">
				<h2>Categorys (<%=CategoryDao.getCount()%>)</h2>
			</div>
			<a href="all_products">
				<div class="box">
					<img alt="png" src="img/product.png">
					<h2>Products (<%=ProductDao.getCount()%>)</h2>
				</div>
			</a>
		</div>
		<div class="productDetails">
			<div class="box">
				<img alt="png" src="img/add.png" class="addCategory">				
				<h2>Add Category</h2>				
			</div>
			<div class="box">
			<a href="#productForm"><img alt="png" src="img/add.png" class="addProduct"></a>
			<h2>Add Product</h2>				
			</div>
		</div>
	</sections>
	<!-- Dashboard end -->
	
	<!-- Category form section start  -->
	<!-- Sending all form data in CategoryServelet -->
	<sections>
		 <form action="addcategory" method="post" class = "categoryForm">
			 <div class="crossBar" onclick="closeBar();">
			 	<div></div>
			 	<div></div>
			 </div>
		 	<div class="formBox">
			 	<label for="title">Title</label>
			 	<input type="text" name="title" id="title" placeholder="Category Title">
		 	</div>
		 	<div class="formBox">
			 	<label for="desc">Description</label>
			 	<textarea rows="" cols="" name="desc" id="desc" placeholder="Category Description..."></textarea>
		 	</div>
		 	<div class="formBox">
		 		<input type="submit" id="categoryBtn" value="Save">
		 	</div>
		 </form>
	</sections>
	<!-- Category form ended -->
	
	
	<!-- add product form start -->
	<!--  -->
	<sections id="productForm">
		 <form action="addproduct" method="post" class = "proForm" enctype="multipart/form-data">
			 <div class="crossBar" onclick="closeBar();">
			 	<div></div>
			 	<div></div>
			 </div>
		 	<div class="formBox">
			 	<label for="proName">Product Name</label>
			 	<input type="text" name="proName" id="proName" placeholder="Product Name">
		 	</div>
		 	<div class="formBox">
			 	<label for="price">Product Price</label>
			 	<input type="number" name="price" id="price" placeholder="Product Price..">
		 	</div>
		 	<div class="formBox">
			 	<label for="discount">Product Discount</label>
			 	<input type="number" name="discount" id="discount" placeholder="Product Discount..">
		 	</div>
		 	<div class="formBox">
			 	<label for="proQuntity">Product Quntity</label>
			 	<input type="number" name="proQuntity" id="proQuntity" placeholder="Product Quntity">
		 	</div>
		 	<div class="formBox">
			 	<label for="category">Select Category</label>
			 	<select name="category" id="category">
			 		<option value=" " disabled selected>Select Category</option>
			 		<%
			 			// fetching all category data and displying select box of product form 
			 			List<Category> list = CategoryDao.getCategory();
			 			if(list != null){
			 			  for(Category cat:list) {
			 		%>
			 				<option value="<%=cat.getCategoryId() %>"><%=cat.getCategoryTitle() %></option>
			 		<%
			 				}
			 			}
			 		%>
			 	</select>
		 	</div>
		 	<div class="formBox">
	            <input type="file" name="proImg1" id="proImg1" multiple onchange="getName_1(this)">
	            <label for="proImg1">
	                <span>Browse</span>
	                <span id="file_name_1">Select Product Image</span>
	            </label>
        	</div>
		 	<div class="formBox">
	            <input type="file" name="proImg2" id="proImg2" multiple onchange="getName_2(this)">
	            <label for="proImg2">
	                <span>Browse</span>
	                <span id="file_name_2">Select Product Image</span>
	            </label>
        	</div>
		 	<div class="formBox">
	            <input type="file" name="proImg3" id="proImg3" multiple onchange="getName_3(this)">
	            <label for="proImg3">
	                <span>Browse</span>
	                <span id="file_name_3">Select Product Image</span>
	            </label>
        	</div>
		 	<div class="formBox">
			 	<label for="proDesc">Product Description</label>
			 	<textarea rows="" cols="" name="proDesc" id="proDesc" placeholder="Product Description..."></textarea>
		 	</div>
		 	<div class="formBox">
		 		<input type="submit" id="productBtn" value="Add">
		 	</div>
		 </form>
	</sections>
	<!-- add form ended -->
	<!-- all javaScript file  -->
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>
	<script src="js/header.js" type="text/javascript"></script>
	<script src="js/cart.js" type="text/javascript"></script>
	<script src="js/admin.js" type="text/javascript"></script>
</body>
</html>