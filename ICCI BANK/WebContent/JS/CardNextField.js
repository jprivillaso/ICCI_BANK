//Function to navigate thru the input fields
	function nextInput(field){
		var x= document.forms[0];
		var i=1;
		//Check input position
		for(i;i<x.length;i++){
			if(x.elements[i]==field){
				break;
			}
		}
		//if the length is 4 focus next input
		if(x.elements[i].value.length==4 && i!=4){
			x.elements[i+1].focus();
		}
		//if the length is 0 focus previous input
		if(x.elements[i].value.length==0 && i!=1){
			var value = x.elements[i-1].value;
			x.elements[i-1].value="";
			x.elements[i-1].focus();
			x.elements[i-1].value= value;
		}
	}
//Function to focus the needed input field
	function whenSelected(field){
		var x= document.forms[0];
		var i=1;
		//Check input position
		for(i;i<x.length;i++){
			if(x.elements[i]==field){
				break;
			}
		}
		//Check the what input to focus on
		for(j=i-1;j>=1;j--){
			if(x.elements[j].value.length<4){
				var value = x.elements[j].value;
				x.elements[j].focus();
				x.elements[j].value= value;
			}
		}
	}