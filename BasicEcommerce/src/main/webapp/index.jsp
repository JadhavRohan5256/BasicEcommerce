<%@page import="com.entities.ProductPhotos"%>
<%@page import="com.entities.Product"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.helper.FactoryBuilder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<%@include file="componets/header.jsp" %>
	<section class="content container" >
			<div class="leftView">
				<h3><a href="homePage?categoryname=all">All Product</a></h3>
				<%
					List<Category> cateList = CategoryDao.getCategory();
					if(cateList != null){
						for(Category category:cateList) {
				%>
							<a href="homePage?categoryname=<%=category.getCategoryTitle()%>">
								<%=category.getCategoryTitle() %>
							</a>
				<%
						}
					}
					else {
						
				%>
						<p>No Category Available</p>
				<%	
					}
				%>
			</div>
			<div class="rightView">
			<%
			
				String categoryName = "all";
				if(request.getParameter("categoryname") != null){
					categoryName = request.getParameter("categoryname");
				}
				
				List<Product> proList = null;
				if(request.getParameter("search") != null) {
					proList = ProductDao.getProductBySearch(request.getParameter("search").trim());
				}
				else if(categoryName.trim().matches("all")){
					proList = ProductDao.getProduct();
				}
				else {
					proList = ProductDao.getProductByCategoryName(categoryName);
				}
				if(proList.size()>0) {
					for(Product product:proList){
			%>
				<div class="box">
					<a href="product?productname=<%=product.getProductTitle()%>">
						<img alt="productImg" src="UploadedImage/ProductImg/<%=product.getProductPhotos().get(0).getProductPhotosName()%>">
					</a>
					<div class="bottom">
						<h3 class="title">
							<a href="product?productname=<%=product.getProductTitle()%>"><%=product.getProductTitle() %></a>
						</h3>
						<p class="desc">
						<%
							if(product.getProductDesc().length()>50)
								out.print(product.getProductDesc().substring(0, 50)+"...");
							else 
								out.print(product.getProductDesc());
						%>
						</p>
						<div class="add">
							<a onclick="addCart(<%=product.getProductId()%>,
												'<%=product.getProductTitle()%>',
												'<%=product.getProductPhotos().get(0).getProductPhotosName() %>',
												<%=product.getProductPrice() %>,
												<%=product.getProductDiscount() %>,
												<%=product.getAfterDiscountPrice()%>)" class="addProductToCart">Add To Cart</a>
							<a href="product?productname=<%=product.getProductTitle()%>">&#8377 <%=product.getAfterDiscountPrice()%> 
								<span class="discount">
									<span class="actualPrice">
										<%=product.getProductPrice()+" "%>
									</span>
								<%=product.getProductDiscount()%>%</span>
							</a>
						</div>
					</div>
				</div>
			<%
					}
				}
				else {
					out.print("<p class='notFound'>No Product Found!</p>");
				}
			%>
			</div>
	</section>
	
	
	<script src="js/jquery.js"></script>
	<script src="js/index.js"></script>
	<script src="js/header.js"></script>
</body>
</html>