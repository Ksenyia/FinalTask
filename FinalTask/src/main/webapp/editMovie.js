var a = 0;
function newSelect(x,y,z) {
   i = document.getElementById(x).selectedIndex;
   l = document.getElementById(x).length;
   var newInput = document.getElementById(y);
   if(i==(l-1)&&!newInput){
         AddSelect(y,z,y);
         
    }
    else{
    	DeleteSelect(y,z);
    }
}

function newSelects(x,y,z) {
		var bool = false;
		var optionsToSelect = 'other';
		var element = document.getElementById(x);
		var selectedOptions = element.selectedOptions;
		for (var i = 0; i < selectedOptions.length; i++) {
			if(optionsToSelect == selectedOptions[i].value){
				bool = true;
			}
		}
		var deleteId = "delete" + y;
		var addId = "add" + y;
	    var newInput = document.getElementById(deleteId);
	    var newP = document.getElementById(z); 
	   if(!newInput && bool){
	         Add(y,z,z);
			var newButton = document.createElement("BUTTON");
			newButton.setAttribute("onclick", "Delete(\'"+y+"\',\'"+z+"\')");
			newButton.setAttribute("id", deleteId);
			newButton.innerHTML =  window.tr.mes2;
			newP.appendChild(newButton);
			var newButtonAdd = document.createElement("BUTTON");
			newButtonAdd.setAttribute("onclick", "Add(\'"+y+"\',\'"+z+"\',\'"+z+"\')");
			newButtonAdd.setAttribute("id", addId);
			newButtonAdd.innerHTML =  window.tr.mes1;
			newP.appendChild(newButtonAdd);
	    } 
		if(!bool){
			newP.innerHTML = "";
		}
	}

function Add(y,z,x) {
	a = a+1;
	var demo = document.getElementById(z);
    var newInputRU = document.createElement("INPUT");
    newInputRU.setAttribute("type", "text");
	var id = y+"RU" + a;
    newInputRU.setAttribute("id", id);
    var name = x + "RU";
    newInputRU.setAttribute("name", name);
	newInputRU.setAttribute("autocomplete", "off");
    demo.appendChild(newInputRU);
    
    var newInputEN = document.createElement("INPUT");
    newInputEN.setAttribute("type", "text");
	id = y+"EN" + a 
	name = x + "EN"
    newInputEN.setAttribute("id", id);
    newInputEN.setAttribute("name", name);
	newInputEN.setAttribute("autocomplete", "off");
    demo.appendChild(newInputEN);
}
function Delete(x,z) {
	var demo = document.getElementById(z);
    var newInputRU = document.getElementById(x + "RU" +a);
	if (newInputRU.parentNode) {
		demo.removeChild(newInputRU);
		a = a-1;
	}
    var newInputEN = document.getElementById(x + "EN" +a);
	if (newInputEN.parentNode) {
		demo.removeChild(newInputEN);
	}
}

function AddSelect(y,z,x) {
	var demo = document.getElementById(z);
    var newInput = document.createElement("INPUT");
    newInput.setAttribute("type", "text");
    newInput.setAttribute("id", y);
    newInput.setAttribute("name", x);
	newInput.setAttribute("autocomplete", "off");
    demo.appendChild(newInput);
}
function DeleteSelect(x,z) {
	var demo = document.getElementById(z);
    var newInput = document.getElementById(x);
	if (newInput.parentNode) {
		demo.removeChild(newInput);
	}
}