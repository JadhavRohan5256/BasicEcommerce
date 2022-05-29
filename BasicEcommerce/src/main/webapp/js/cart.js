// cart javascript code begin 

/*removing cartbox on click of cross icon */
let cross2 = document.querySelector(".crossBar");
let box2 = document.querySelector("#cartView");
cross2.onclick = ()=>{
	box2.style.display="none";
}


$(document).ready(function(){
	$(".addProductToCart").click((e)=>{
		e.preventDefault();
	})
});


// tost displaying added cart messege or remove messege
function tost(title) {
	$(document).ready(()=>{
		$('#tostMessege').html(title).slideDown();
		setTimeout(()=>{
			$("#tostMessege").slideUp();
		},5000);
	});
}

// After Click Add to cart button clicked product can be added to localStorage 
function addCart(pId,pName,pPhoto,pPrice,pDisc,pAfterDisPrice) {
	let storage = localStorage.getItem("cart");
	if(storage == null) {
		let products =[];
		let product = {
				productId:pId,
				productName:pName,
				productPhoto:pPhoto,
				productPrice:pPrice,
				productDisc:pDisc,
				productQuntity:1,
				productAfterDiscPrice:pAfterDisPrice,
		}
		
		products.push(product);
		
		localStorage.setItem("cart",JSON.stringify(products));
		console.log("First Time product successfully added");
		tost(`${productName.substr(0,15)} ... Your First Product added to the cart`);
	}
	else {
		let cart = JSON.parse(storage);
		let oldProduct = cart.find((item)=>item.productId == pId);
		
		if(oldProduct) {
			oldProduct.productQuntity += 1;
			oldProduct.productPrice += pPrice;
			oldProduct.productAfterDiscPrice += pAfterDisPrice;
			cart.map((item)=>{
				if(item.productId == oldProduct.product) {
					item = oldProduct;
				}
			})
			
			console.log("Existing product are increamented");
			tost(`${oldProduct.productName.substr(0,15)} ... Product Quntity Increament`);
		}
		else {
			let product = {
				productId:pId,
				productName:pName,
				productPhoto:pPhoto,
				productPrice:pPrice,
				productDisc:pDisc,
				productQuntity:1,
				productAfterDiscPrice:pAfterDisPrice,
			}
			
			cart.push(product);
			console.log("next product can be added");
			tost(`${product.productName.substr(0,15)}... Product Added to the cart `);
		}
		localStorage.setItem("cart",JSON.stringify(cart));
	}
}

// this function is loading all product in cart
function loadCart() {
		let storage = localStorage.getItem("cart");
		let cart = JSON.parse(storage);
		let flag = false;
		if(cart.length == 0) {
			flag = true;
		}
		$('.topCart h3').html(`Cart items(${cart.length})`);
		let products = ``;
		let totalPrice = 0;
		let discountPrice = 0;
		cart.map((item)=>{
			// storing all product details which availabe in cart array
			products +=`
						<div class="cartProduct">
							<a href="product?productname=`+item.productName+`">
								<img alt="product" src="UploadedImage/ProductImg/`+item.productPhoto+`">
							</a>
							<div class="proInfo">
								<a href="product?productname=`+item.productName+`">`+item.productName+`</a>
								<p class="paraOfPrice">
									&#8377 `+item.productAfterDiscPrice+` 
									<span>&#8377 `+item.productPrice+`</span>
									<span>`+item.productDisc+`% off</span>
								</p>
								<div class="proIncreament">
									<button data-id="`+item.productId+`"	class="decrease">-</button>
									<p>`+item.productQuntity+`</p>
									<button data-id="`+item.productId+`" class="increase">+</button>
									<button data-id="`+item.productId+`" class="remove">Remove</button>
								</div>
							</div>
						</div>
						`;
						
			totalPrice += item.productPrice;
		    discountPrice += (item.productPrice - item.productAfterDiscPrice);
		});
		
		if(flag) {
			$('.scrollContainer').html("<h2 style='text-align:center;'>Cart are empty </h2>");			
	
		}
		else {
			$('.scrollContainer').html(products);			
		}
		
	
		//all product price details 
		let priceDetails = `
							<div class="topCart">
								<h2>Price Details</h2>
							</div>
							<section class="priceDesc">
								<div class="priceBox">
									<p>PRice (`+cart.length+` items)</p>
									<p>&#8377 `+totalPrice+`</p>
								</div>
								<div class="priceBox">
									<p>Discount</p>
									<p>- &#8377 `+discountPrice+`</p>
								</div>
								<div class="priceBox">
									<p>Delivery Charges</p>
									<p>FREE</p>
								</div>
							</section>
							<div class="totalOf">
								<div class="priceBox">
									<h3>Total Amount</h3>
									<h3>&#8377 `+(totalPrice - discountPrice)+`</h3>
								</div>
							</div>
							<div class="save">
								<h3>You will save &#8377 `+discountPrice+` on this order</h3>
							</div>
							<div class="checkoutBtn">
								<button id="checkBtn">Checkout</button>
							</div>
							`;
							
		$('.cartPriceDetails').html(priceDetails);	
}

$(document).ready(()=>{
	setInterval(()=>{
		let cart = localStorage.getItem("cart");
		if(cart != null) {
			$('.totalCartItems').html(JSON.parse(cart).length);
		}
		else {
			$('.totalCartItems').html("0");
		}
		
	},500);
	
	$('#cart').click((e)=>{
		e.preventDefault();
		$('#cartView').css("display","block");
		loadCart();
	});
});





// function to remove product from to the localStorage
$(document).ready(()=>{
	$(document).on("click",".remove",function(){
		let productId = $(this).data("id");
		let cart = JSON.parse(localStorage.getItem("cart"));
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

function checkoutBtn() {
	var cart = JSON.parse(localStorage.getItem("cart"));
	if(cart.length == 0) {
		$('#checkBtn').hide();
		
	}
	else {
		$('#checkBtn').show();
	}
}



$(document).ready(function(){
	$(document).on("click","#cart",function(){
		checkoutBtn();
		if(JSON.parse(localStorage.getItem("cart")).length == 0) {
			tost("Cart are Empty?");
		}
	});
	
	$(document).on("click",".remove",function(){
		checkoutBtn();
	})
	
	$(document).on("click","#checkBtn",function(){
		window.location="checkout";
	});
});