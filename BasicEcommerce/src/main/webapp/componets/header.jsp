<!-- Navagatin menu bar start -->
    <%@page import="com.entities.User"%>
<%@page import="java.io.File"%>
<%@page import="com.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CategoryDao"%>
<section class="header">
        <div class="container navBar">
            <div class="category">
               <h3> 
               	 	<a href="homePage" id="brandLogo">
               			<img alt="logo" src="img/logo.png">
               		</a>
               </h3>
                <div class="toggle">
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <ul>
                    <li><a href="homePage">Home</a></li>
                 <%
                 	User userSafeCheck = (User)session.getAttribute("userDetails");
                 	if(userSafeCheck != null && userSafeCheck.getUserType() == 1) { 
                 %>
                 	<li><a href="adminPannel">Admin</a></li>
                 <%
                 	}
                 %>
                    <!-- <li><a href="#">AboutUs</a></li>
                    <li><a href="#">ContactUs</a></li> -->
                    <li class="cate"><a>category</a>
                        <ul>
                       
                        	<%
                        		List<Category> catList = CategoryDao.getCategory();
                        	if(catList != null) {
                        		for(Category cat:catList) {
                        	%>
                            	<li><a href="homePage?categoryname=<%=cat.getCategoryTitle()%>"><%=cat.getCategoryTitle() %></a></li>
                          	<%
                        		}
                          	}
                        	%>
                        </ul>
                    </li>
                   <%
                   	if(session.getAttribute("userDetails") == null) {
                   	%>
                  	 	<li><a href="loginPage">Login</a></li>
                   	<%
                   	}else {
                   	%>
	                    <li><a href="profile">Profile</a></li>
	              	    <li><a href="logout">Logout</a></li> 
                    <%
                    } 
                    %>
                </ul>
            </div>
            <div class="search">
                <div class="container">
                  <form action = "searchPage" method="post">
                    <input type="search" name="search" id="search" placeholder="Search for Product and more..">
                  </form>
                </div>
            </div>
            <div class="loginSection">
                <ul>
                    <li>
                    	<a href="#" id="cart">
                     		<img alt="cart" src="img/cart-48.png"> <span class="totalCartItems"></span>
                     	</a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
    <div id="tostMessege"></div>
    
    <section id="cartView">
			<div class="crossBar">
				 <div></div>
				 <div></div>
			</div>
		<section class="cartsBox containe">
			<div class="cartValue">
				<div class="topCart">
					<h3> </h3>
				</div>
				<section class="scrollContainer">
				<!-- this is dynamic product of cart start -->
					
				<!-- this is dynamic product of cart end -->
				</section>
			</div>    
			<!--  this is cart price details  -->
			<div class="cartPriceDetails">
				
			</div>	
	    </section>
	</section>