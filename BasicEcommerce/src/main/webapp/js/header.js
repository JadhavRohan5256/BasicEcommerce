/**
 * 
 */

 var cate = document.querySelector(".cate");
 var ul = document.querySelector(".category ul ul");
 cate.onmouseover = () => {
     ul.style.display= "block";
 }
 cate.onmouseleave = () => {
     ul.style.display= "none";
 }
 
 
 // toggle menu bar 
 let bar = document.querySelector(".toggle");
 let navBar = document.querySelector(".category");
  bar.onclick = ()=> {
      bar.classList.toggle("active");
      navBar.classList.toggle("active");
  }