let cross = document.querySelector(".cross");
let messege = document.querySelector(".messege");
cross.onclick = ()=> {
     cross.style.display = "none";
     messege.style.display="none";
}

/*$(document).on("click","#btn",function(e){
	e.preventDefault();
    var datas = $("#form").FormData();
	console.log(datas);
   $.ajax({
    url:"register",
    type:"post",
    data:datas,
    success:function(data){
        if(data){
			$(".messege").html(data);
			
        }else{
            $(".messege").html("success");   
        }
    }
   });
    
});
*/