function checkRadio() {
	var radio = document.getElementsByName("employeeId");
	var checked = 0;
	var button = document.getElementById("confirm");
	
	radio.forEach(function(e) {
		if(e.checked) {
			checked++;
		}
	})
	
	if (checked > 0) {
		button.disabled = false;
	} else {
		button.disabled = true;
	}
}