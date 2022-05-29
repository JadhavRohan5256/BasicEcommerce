

let cross = document.querySelector(".cross");
let messege = document.querySelector(".messege");
cross.onclick = ()=> {
     cross.style.display = "none";
     messege.style.display="none";
}


// customized file tag 
function getName_1(file) {
    var output = document.getElementById('file_name_1');
    var name = "";
    for(var i=0; i<file.files.length; ++i) {
        name += file.files.item(i).name;
    }
	output.innerText = name;
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
