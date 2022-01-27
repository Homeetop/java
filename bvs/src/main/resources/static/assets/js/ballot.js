

var log = document.getElementById("logout");
var candCard = document.getElementById("cand-card")
log.addEventListener("mouseover",onMouse);
log.addEventListener("mouseout",outMouse);
//candCard.addEventListener("click", onClick(this));
//candCard.addEventListener("dblclick", ondblClick);
//candCard.addEventListener("mouseover",cardMouseMove);
//candCard.addEventListener("mouseout",cardMouseOut);

const selector =".cand-card";
const radioB = document.querySelectorAll(selector);
var prev =null;
console.log(radioB);
radioB.forEach(function(e){

  e.addEventListener("click",function(){

    if(e == this){
      if(prev != null){prev.removeAttribute('style','opacity: 1;');}
      this.setAttribute('style','opacity: 0.5;');
      let r = this.getElementsByClassName('form-check-input');
      console.log(r);
      check(r);
      prev = this;
     }

  });});

function check(ev){
  ev[0].checked=true;
}


let previousText = log.innerText;
function onMouse(){
  log.innerHTML = 'Logout';
}
function outMouse(){
  log.innerHTML =previousText;
}
