<!DOCTYPE html>
<%@page import="com.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.ProductDao"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product</title>
    <link rel="stylesheet" href="css/header.css">  
    <link rel="stylesheet" href="css/productList.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Oxygen+Mono&family=Oxygen:wght@400&display=swap">
</head>
<body>
	<%@ include file="componets/header.jsp" %>
   	 <div class="container tableContainer">
        <table id="productTable">
            <thead>
                <th>Sr.no</th>
                <th>Product Image</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Quntity</th>
                <th>Delete</th>
            </thead>
            <tbody>
            
	            <%
	            	
	            int pageNo = 1;
	            if(request.getParameter("page") != null) {
	            	pageNo = Integer.parseInt(request.getParameter("page"));
	            }
	            int offset = 0;
	            int limit = 2;
	            if(pageNo >= 1) {
	            	offset = (pageNo-1)*limit;
	            }
	         	
	            
	            List<Product> products = ProductDao.getProductByLimit(offset, limit);
	            if(products != null) {
	            	for(Product product:products) {
	            %>
	            		<tr>
		                    <td><%=product.getProductId() %></td>
		                    <td class="editBtn">
		                        <a href="#">
		                            <img src="UploadedImage/ProductImg/<%=product.getProductPhotos().get(0).getProductPhotosName() %>" alt="img">
		                        </a>
		                    </td>
		                    <td class="editBtn">
		                        <a href="#">
		                            <%
		                            	if(product.getProductTitle().length()>20) {
		                            		out.print(product.getProductTitle().substring(0,20) + "...");
		                            	}
		                            	else {
		                            		out.print(product.getProductTitle());
		                            	}
		                            %>
		                        </a>
		                    </td>
		                    <td>&#8377; <%=product.getAfterDiscountPrice() %></td>
		                    <td><%=product.getProductDiscount()%>%</td>
		                    <td><%=product.getProductQuantity() %></td>
		                    <td class="deleteBtn">
		                        <a href="productDelete?productname=<%=product.getProductTitle()%>">Delete</a>
		                    </td>
               			 </tr>
	            <%
	            	}
	            }
	            else {
	            %>
					<td colspan="7">Products Data not available</td>
				<%} %>                
                
            </tbody>
        </table>
        
        <%
       		long countOfProduct = ProductDao.getCount();
        	if(countOfProduct >limit) {
	        	double calc = (double)countOfProduct/limit;
	        	int totalPages = (int)Math.ceil(calc);
        %>
        <div class="pagenation">
            <ul>
             <%
             	if(pageNo > 1) {
             %>
             	<li class="prev">
                    <a href="all_products?page=<%=pageNo - 1%>">Prev</a>
                </li>
             <%
             	} //checking pageNo is greater then 1 end statement
             	String active ="";
               for(int i=1; i<=totalPages; ++i) {
            	   if(i==pageNo) {
            		   active = "active";
            	   }
            	   else {
            		   active = "";
            	   }
             %>
                
                <li id="<%=active%>">
                    <a href="all_products?page=<%=i%>"><%=i %></a>
                </li>
             <%
               } // totalPages number forloop end statement 
             %>
                <%
                	if(pageNo < totalPages) {
                		
                %>
                	<li class="next">
                 	   <a href="all_products?page=<%=pageNo + 1%>">Next</a>
               		</li>
               	<%
                	} //checking pageNO is less then totalPages end statements;
               	%>
            </ul>
        </div>
        <%
        	}//checking countOfProduct is greater then limit end statement
        %>
    </div>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/header.js" type="text/javascript"></script>
    <script src="js/cart.js" type="text/javascript"></script>
   <!-- <script src="js/index.js" type="text/javascript"></script> --> 
</body>
</html>