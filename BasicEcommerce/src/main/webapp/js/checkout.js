loadCart();

/*seding order details in servlet for  creating orders */
$(document).ready(function() {
	$('#orderForm').on("submit",function(e) {
		e.preventDefault();
		
		let name = $('#name').val();
		let email = $('#email').val();
		let address = $('#address').val();
		
		
		var productArray = Array();
		let storage = localStorage.getItem("cart");
		let cart = JSON.parse(storage);
		cart.map((item)=>{
			let product = item.productId + "=" + item.productQuntity;
			productArray.push(product);
		});
		console.log(productArray);
		$.ajax({
			url:"orderPayment",
			type:"POST",
			data:{productArrays:productArray,name:name,email:email,address:address,info:"testing"},
			success:function(data) {
				var response = JSON.parse(data);
				console.log(response);
				if(response.status == "created") {
					saveOrderDatabase();
					let options={
						key:"rzp_test_DIhp8Aqjojcgzl",
						amount:response.amount,
						currency:response.currency,
						image:"https://images.unsplash.com/photo-1653202916210-9e41f0173b6b?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070",
						name:"Ecommerce Payment",
						description:"payment integrations",
						order_id:response.id,
						handler: function (response){ 
							console.log(response.razorpay_payment_id); 
							console.log(response.razorpay_order_id); 
							console.log(response.razorpay_signature) 
							updateOrders();
							swal("Good job!", "Your payment has been successfuly", "success");
							
						},
						prefill: { 
							name: "",
							email: "", 
							contact: "" 
						}, 
						notes: { 
							address: "Galandwadi no 1"
						}, 
						theme: {
							color: "#3399cc" 
						}
					};
					var rzp = new Razorpay(options);
					
					rzp.on('payment.failed', function (response){ 
						console.log(response.error.code);
						swal("Oops Payment Failed","Error ocured please try later", "error");
					});
					
					rzp.open();
				}
				
			},
			error:function(error) {
				console.log(error);
			}
		});
	});
});



// seding orders in servlet throw database
function updateOrders() {
	var productArray = Array();
	let storage = localStorage.getItem("cart");
	let cart = JSON.parse(storage);
	cart.map((item)=>{
		let product = item.productId + "=" + item.productQuntity;
		productArray.push(product);
	});
	
	$.ajax({
		url:"statusUpdate",
		type:"POST",
		data:{productArrays:productArray,status:"Paid"},
		success:function(data) {
			if(data == "1") {
				setTimeout(function(){
					localStorage.removeItem("cart");
					window.location="homePage";
				},3000);
			}
		}
	});
}


// save order to database
function saveOrderDatabase() {
	let name = $('#name').val();
	let email = $('#email').val();
	let address = $('#address').val();
	var productArray = Array();
	let storage = localStorage.getItem("cart");
	let cart = JSON.parse(storage);
	cart.map((item)=>{
		let product = item.productId + "=" + item.productQuntity;
		productArray.push(product);
	});
	$.ajax({
		url:"shippingAddress",
		type:"POST",
		data:{productArrays:productArray,name:name,email:email,address:address},
		success:function(data) {
			if(data == "1") {
				console.log("Order successfuly Inserted to database");
			}
		}
	});
}



// function to remove product from to the localStorage
$(document).ready(()=>{
	$(document).on("click",".remove",function(){
		let productId = $(this).data("id");
		let cart = JSON.parse(localStorage.getItem("cart"));
		console.log(cart.length);
		if(cart.length == 1) {
			tost("Minimum one product are required to create order?");
			return;
		}
		let oldProduct = cart.find((item)=>item.productId == productId);
		let newCart = cart.filter((item)=>item.productId != productId);
		localStorage.setItem("cart",JSON.stringify(newCart));
		console.log("product remove successfully!");
		tost( `${oldProduct.productName.substr(0,16)}... Product Removed Successfully `);
		loadCart();
	});
});

// function to product decreament by 1 
$(document).ready(()=>{
	$(document).on("click",".decrease",function(){
		let productId = $(this).data("id");
		let cart = JSON.parse(localStorage.getItem("cart"));
		let oldProduct = cart.find((item)=>item.productId == productId);
		if(oldProduct && oldProduct.productQuntity >1) {
			oldProduct.productPrice   -= (oldProduct.productPrice/oldProduct.productQuntity);
			oldProduct.productAfterDiscPrice -= (oldProduct.productAfterDiscPrice/oldProduct.productQuntity);
			oldProduct.productQuntity -= 1;
			cart.map((item)=>{
				if(item.productId == oldProduct.productId) {
					item = oldProduct;
				}
			});
		}
		
		localStorage.setItem("cart",JSON.stringify(cart));
		loadCart();
	});
});



// function to product Increament by 1 
$(document).ready(()=>{
	$(document).on("click",".increase",function(){
		let productId = $(this).data("id");
		let cart = JSON.parse(localStorage.getItem("cart"));
		let oldProduct = cart.find((item)=>item.productId == productId);
		if(oldProduct) {
			oldProduct.productPrice   += (oldProduct.productPrice/oldProduct.productQuntity);
			oldProduct.productAfterDiscPrice += (oldProduct.productAfterDiscPrice/oldProduct.productQuntity);
			oldProduct.productQuntity += 1;
			cart.map((item)=>{
				if(item.productId == oldProduct.productId) {
					item = oldProduct;
				}
			});
		}
		
		localStorage.setItem("cart",JSON.stringify(cart));
		loadCart();
	});
});

//console.clear();