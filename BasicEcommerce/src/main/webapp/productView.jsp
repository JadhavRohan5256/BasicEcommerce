<%@page import="com.entities.ProductPhotos"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.Product"%>
<%@page import="com.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page errorPage="error_page.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/productView.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@include file="componets/header.jsp" %>
	<section class="products container">
	<!-- product image gallery view -->
		<div class="left">
			<%
				String name = request.getParameter("productname");
				Product product = (Product)ProductDao.getProductByName(name.trim());
				if(product != null) {
			%>
			<div class="imgGallery">
				<div class="allImg">
					<%	
						List<ProductPhotos> lists = product.getProductPhotos();
						for(ProductPhotos p:lists){
					%>
						<img alt="productImg not find" class="shortImg" src="UploadedImage/ProductImg/<%=p.getProductPhotosName()%>" onclick="gallery(this);">
					<%} %>
				</div>
				<div class="mainImg">
					<img alt="" src="UploadedImage/ProductImg/<%=product.getProductPhotos().get(0).getProductPhotosName()%>">
				</div>
			</div>
		</div>
		<!-- product information details -->
		<div class="productDetails">
			<h2><%=product.getProductTitle() %></h2>
			<div class="price">
				<h3 class="discountPrice">&#8377 <%=product.getAfterDiscountPrice() %></h3>
				<p class="originPrice">&#8377 <%=product.getProductPrice() %></p>
				<p class="discount"><%=product.getProductDiscount()%>% off</p>
			</div>
			<h3>Description</h3>
			<p class="desc"><%=product.getProductDesc() %></p>
			<!-- add to cart and buy now button  -->
			<div class="add">
				<button onclick="addCart(<%=product.getProductId()%>,
												'<%=product.getProductTitle()%>',
												'<%=product.getProductPhotos().get(0).getProductPhotosName() %>',
												<%=product.getProductPrice() %>,
												<%=product.getProductDiscount() %>,
												<%=product.getAfterDiscountPrice()%>)" class="addProductToCart">Add to Cart</button>
				<a href="#" onclick="addCart(<%=product.getProductId()%>,
												'<%=product.getProductTitle()%>',
												'<%=product.getProductPhotos().get(0).getProductPhotosName() %>',
												<%=product.getProductPrice() %>,
												<%=product.getProductDiscount() %>,
												<%=product.getAfterDiscountPrice()%>);" class="buy">Buy Now</a>
			</div>
		</div>
		<%} %>
	</section>
	
	<!--  related all product of this product -->
	<section class="relatedProduct container">
		<h2>This Category Related Product </h2>
		 <div class="rightView">
			<%
			
				List<Product> proList = ProductDao.getProductByCategoryName(product.getCategory().getCategoryTitle());
				if(proList.size()>0) {
					for(Product products:proList){
			%>
				<div class="box">
					<a href="product?productname=<%=products.getProductTitle()%>">
						<img alt="productImg" src="UploadedImage/ProductImg/<%=products.getProductPhotos().get(0).getProductPhotosName()%>">
					</a>
					<div class="bottom">
						<h3 class="title">
							<a href="product?productname=<%=products.getProductTitle()%>"><%=products.getProductTitle().substring(0,15)+"..." %></a>
						</h3>
						<p class="desc">
						<%
							if(products.getProductDesc().length()>50)
								out.print(products.getProductDesc().substring(0, 50)+"...");
							else 
								out.print(products.getProductDesc());
						%>
						</p>
						<div class="add">
							<a onclick="addCart(<%=products.getProductId()%>,
												'<%=products.getProductTitle()%>',
												'<%=products.getProductPhotos().get(0).getProductPhotosName() %>',
												<%=products.getProductPrice() %>,
												<%=products.getProductDiscount() %>,
												<%=products.getAfterDiscountPrice()%>)" class="addProductToCart">Add To Cart</a>
							<a href="">&#8377 <%=products.getAfterDiscountPrice()%></a>
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
	<script src="js/product.js"></script>
	<!-- <script src="js/index.js"></script> -->
	<script src="js/header.js"></script>
	<script src="js/cart.js"></script>
</body>
</html>