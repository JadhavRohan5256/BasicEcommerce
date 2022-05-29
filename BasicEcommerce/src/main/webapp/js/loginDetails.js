var cross = document.querySelector(".cross");
var messege = document.querySelector(".messege");
cross.onclick = ()=> {
     cross.style.display = "none";
     messege.style.display="none";
}

var cart = document.getElementById("cart");
cart.onclick = () => {
	var img = document.querySelector(".img");
	img.style.zIndex = "-1";
}