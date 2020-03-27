window.onload = function() {
	var now = new Date();
	var nowYear = now.getYear();
	if(nowYear < 2000) { nowYear += 1900; }
	var select = document.getElementById("year");
	
	for (let i = 100; i >= 0; i--) {
		var option = document.createElement("option");
		option.text = nowYear - i;
		option.value = nowYear - i;
		select.appendChild(option);
	}
}