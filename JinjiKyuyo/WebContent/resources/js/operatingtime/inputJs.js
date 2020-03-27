window.onload = function() {
	var now = new Date();
	var nowYear = now.getYear();
	if(nowYear < 2000) { nowYear += 1900; }
	var select = document.getElementById("year");
	var maxYear = nowYear + 3;
	
	for (let i = 7; i >= 0; i--) {
		var option = document.createElement("option");
		option.text = maxYear - i;
		option.value = maxYear - i;
		select.appendChild(option);
	}
}

function checkNumber($this) {
	var val = $this.value;
	while(val.match(/[^0-9]/)){
		val=val.replace(/[^0-9]/,"");
	}
	if(!val) {
		val = 0;
	}
	$this.value = val;
}