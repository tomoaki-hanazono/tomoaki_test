window.onload = function() {
	var now = new Date();
	var nowYear = now.getYear();
	if(nowYear < 2000) { nowYear += 1900; }
	var selectS = document.getElementById("startYear");
	var selectE = document.getElementById("endYear");
	var maxYear = nowYear + 3;
	
	for (let i = 7; i >= 0; i--) {
		var optionS = document.createElement("option");
		optionS.text = maxYear - i;
		optionS.value = maxYear - i;
		selectS.appendChild(optionS);
		
		var optionE = document.createElement("option");
		optionE.text = maxYear - i;
		optionE.value = maxYear - i;
		selectE.appendChild(optionE);
	}
}

function changeAria() {
	var aria = document.getElementById("addEmploymentAria");
	if (aria.classList.contains("noDisplay") == true) {
		aria.classList.remove("noDisplay");
	} else {
		aria.classList.add("noDisplay");
	}
}

function changeCheckBox($this) {
	var val = $this.value;
	var target = document.querySelectorAll("#employmentInfoAria_" + val + " input");
	var checkbox = document.getElementsByName("employmentId");
	var checked = 0;
	var button = document.getElementById("update");
	
	if($this.checked) {
		target.forEach(function(e) {
			e.disabled = false;
		})
	} else {
		target.forEach(function(e) {
			e.disabled = true;
		})
		changeDefault(val);
	}
	
	checkbox.forEach(function(e) {
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

function changeDefault(id) {
	var table = document.getElementById("employmentInfoAria_" + id);
	var lowerLimit = document.getElementById("lowerLimit_" + id);
	var upperLimit = document.getElementById("upperLimit_" + id);
	var excessMoney = document.getElementById("excessMoney_" + id);
	var eductionMoney = document.getElementById("eductionMoney_" + id);
	var basicSalary = document.getElementById("basicSalary_" + id);
	var dutiesAllowance = document.getElementById("dutiesAllowance_" + id);
	var commutingAllowance = document.getElementById("commutingAllowance_" + id);
	var overtimeAllowance = document.getElementById("overtimeAllowance_" + id);
	var otherAllowance = document.getElementById("otherAllowance_" + id);
	
	var hiddenLowerLimit = document.getElementById("hiddenLowerLimit_" + id);
	var hiddenUpperLimit = document.getElementById("hiddenUpperLimit_" + id);
	var hiddenExcessMoney = document.getElementById("hiddenExcessMoney_" + id);
	var hiddenEductionMoney = document.getElementById("hiddenEductionMoney_" + id);
	var hiddenBasicSalary = document.getElementById("hiddenBasicSalary_" + id);
	var hiddenDutiesAllowance = document.getElementById("hiddenDutiesAllowance_" + id);
	var hiddenCommutingAllowance = document.getElementById("hiddenCommutingAllowance_" + id);
	var hiddenOvertimeAllowance = document.getElementById("hiddenOvertimeAllowance_" + id);
	var hiddenOtherAllowance = document.getElementById("hiddenOtherAllowance_" + id);
	
	if (table.classList.contains("tableErr") == true) {
		table.classList.remove("tableErr");
	}
	lowerLimit.value = hiddenLowerLimit.value;
	upperLimit.value = hiddenUpperLimit.value;
	excessMoney.value = hiddenExcessMoney.value;
	eductionMoney.value = hiddenEductionMoney.value;
	basicSalary.value = hiddenBasicSalary.value;
	dutiesAllowance.value = hiddenDutiesAllowance.value;
	commutingAllowance.value = hiddenCommutingAllowance.value;
	overtimeAllowance.value = hiddenOvertimeAllowance.value;
	otherAllowance.value = hiddenOtherAllowance.value;
}

function checkDefault() {
	
	var checkbox = document.getElementsByName("employmentId");
	var err = 0;
	var messageAria = document.getElementById("messageAria");
	var line = document.createElement("hr");
	var message = document.createElement("p");
	var text = document.createTextNode("何も変更されていない雇用情報は修正できません。");
	message.classList.add("errMessage");
	message.appendChild(text);
	
	while (messageAria.firstChild) {
		messageAria.removeChild(messageAria.firstChild);
	}
	
	checkbox.forEach(function(e) {
		if(e.checked) {
			var id = e.value;
			
			var table = document.getElementById("employmentInfoAria_" + id);
			var lowerLimit = document.getElementById("lowerLimit_" + id);
			var upperLimit = document.getElementById("upperLimit_" + id);
			var excessMoney = document.getElementById("excessMoney_" + id);
			var eductionMoney = document.getElementById("eductionMoney_" + id);
			var basicSalary = document.getElementById("basicSalary_" + id);
			var dutiesAllowance = document.getElementById("dutiesAllowance_" + id);
			var commutingAllowance = document.getElementById("commutingAllowance_" + id);
			var overtimeAllowance = document.getElementById("overtimeAllowance_" + id);
			var otherAllowance = document.getElementById("otherAllowance_" + id);
			
			var hiddenLowerLimit = document.getElementById("hiddenLowerLimit_" + id);
			var hiddenUpperLimit = document.getElementById("hiddenUpperLimit_" + id);
			var hiddenExcessMoney = document.getElementById("hiddenExcessMoney_" + id);
			var hiddenEductionMoney = document.getElementById("hiddenEductionMoney_" + id);
			var hiddenBasicSalary = document.getElementById("hiddenBasicSalary_" + id);
			var hiddenDutiesAllowance = document.getElementById("hiddenDutiesAllowance_" + id);
			var hiddenCommutingAllowance = document.getElementById("hiddenCommutingAllowance_" + id);
			var hiddenOvertimeAllowance = document.getElementById("hiddenOvertimeAllowance_" + id);
			var hiddenOtherAllowance = document.getElementById("hiddenOtherAllowance_" + id);
			
			if( lowerLimit.value == hiddenLowerLimit.value
					&& upperLimit.value == hiddenUpperLimit.value
					&& excessMoney.value == hiddenExcessMoney.value
					&& eductionMoney.value == hiddenEductionMoney.value
					&& basicSalary.value == hiddenBasicSalary.value
					&& dutiesAllowance.value == hiddenDutiesAllowance.value
					&& commutingAllowance.value == hiddenCommutingAllowance.value
					&& overtimeAllowance.value == hiddenOvertimeAllowance.value
					&& otherAllowance.value == hiddenOtherAllowance.value) {
				table.classList.add("tableErr");
				err++;
			} else {
				table.classList.remove("tableErr");
			}
		}
	})
	
	if (err > 0) {
		messageAria.appendChild(message);
		messageAria.appendChild(line);
		return false;
	} else {
		return true;
	}
}