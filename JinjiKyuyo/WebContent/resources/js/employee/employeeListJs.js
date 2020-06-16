function checkRadio() {
	var radio = document.getElementsByName("employeeId");
	var checked = 0;
	var button = document.getElementsByName("next");
	
	radio.forEach(function(e) {
		if(e.checked) {
			checked++;
		}
	})
	
	if (checked > 0) {
		button.forEach(function(e) {
			e.disabled = false;
		})
	} else {
		button.forEach(function(e) {
			button.disabled = true;
		})
	}
}

function moveNext($this) {
	
	var hidden = document.getElementById("nextAction");
	if($this.value == "雇用情報確認") {
		hidden.value = "employmentInfo";
	} else if($this.value == "作業時間入力") {
		hidden.value = "operatingTime";
	} else if($this.value == "個別給与情報一覧") {
		hidden.value = "salaryInfo";
	}
	
	document.nextForm.submit();
}