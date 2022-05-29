let addCategory = document.querySelector(".addCategory");
let addProduct = document.querySelector(".addProduct");
let categoryForm = document.querySelector(".categoryForm");
let proForm = document.querySelector(".proForm");

addCategory.onclick = ()=> {
	categoryForm.style.display="block";
	proForm.style.display="none";
}
addProduct.onclick = ()=> {
	proForm.style.display="block";
	categoryForm.style.display="none";
}


let crossBar = document.querySelector(".crossBar");

function closeBar() {
	crossBar.style.display="none";
	categoryForm.style.display="none";
	proForm.style.display="none";
}

// this function is to close success messege div
let successCross  = document.querySelector(".successCross");
let success  = document.querySelector(".success");
successCross.onclick = ()=>{
	success.style.display="none";
}

// this function is to close error messege div
let errorCross  = document.getElementById("errorCross");
let error  = document.querySelector(".error");
errorCross.onclick = ()=>{
	error.style.display="none";
}




// this function is to get uploaded file name 

function getName_1(file) {
    var output = document.getElementById('file_name_1');
    var name = "";
    for(var i=0; i<file.files.length; ++i) {
        name += file.files.item(i).name;
    }

    output.innerText = name;
}


function getName_2(file) {
    var output = document.getElementById('file_name_2');
    var name = "";
    for(var i=0; i<file.files.length; ++i) {
        name += file.files.item(i).name;
    }

    output.innerText = name;
}


function getName_3(file) {
    var output = document.getElementById('file_name_3');
    var name = "";
    for(var i=0; i<file.files.length; ++i) {
        name += file.files.item(i).name;
    }

    output.innerText = name;
}
