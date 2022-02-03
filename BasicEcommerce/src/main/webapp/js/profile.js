/**
 * 
 */

let messegeCross = document.querySelector('.successCross');
let success = document.querySelector('.successMessege');
messegeCross.onclick=()=>{
	messegeCross.style.display="none";
	success.style.display="none";
}
let messegeCross2 = document.querySelector('.errorCross');
let errorMessege = document.querySelector('.errorMessege');
messegeCross2.onclick=()=>{
	messegeCross2.style.display="none";
	errorMessege.style.display="none";
}