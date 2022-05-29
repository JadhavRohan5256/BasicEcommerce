<!DOCTYPE html>
<%@page import="com.entities.UserAddress"%>
<%@page import="com.entities.User"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>checkout page</title>
    <link rel="stylesheet" href="css/checkout.css">
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
	<div id="tostMessege"></div>
    <section class="cartsBox containe">
        <div class="cartValue">
            <div class="topCart">
                <h3> </h3>
            </div>
            <section class="scrollContainer">
            <!-- this is dynamic product of cart start -->
                
            <!-- this is dynamic product of cart end -->
            </section>
         	<div class="cartPriceDetails">
				
			</div>	
        </div>   
        
        <%
        User user = (User)session.getAttribute("userDetails");
        if(user == null) {
	        request.setAttribute("messeges", "You are not logged!");
			request.getRequestDispatcher("loginPage").forward(request, response);
        }
        
      	String userAddress = user.getUserAddress().getUserAddressState() + ", " + user.getUserAddress().getUserAddressCity() + ", " + user.getUserAddress().getUserAddressHouseNo();
        %>
        <div class="addressForm">
        	<h3>Your Details for Orders</h3>
            <form id="orderForm">
                <div class="box">
                    <label for="name">Your name</label>
                    <input type="text" name="name" id="name" value="<%=user.getFirstName() + " " + user.getLastName()%>">
                </div>
                <div class="box">
                    <label for="email">Enter Email</label>
                    <input type="email" name="email" id="email" value="<%=user.getUserEmail()%>">
                </div>
                <div class="box">
                    <label for="address">Your shipping address</label>
                    <textarea name="address" id="address" cols="" rows="10"><%=userAddress.trim()%></textarea>
                </div>
                <div class="box btnBox">
                    <input type="submit" value="Order Now" id="order">
                    <a href="homePage">Continue Shopping</a>
                </div>
            </form>
        </div> 
    </section>   
    <script src="js/jquery.js"></script>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="js/cart.js"></script>
    <script src="js/checkout.js"></script>
</body>
</html>