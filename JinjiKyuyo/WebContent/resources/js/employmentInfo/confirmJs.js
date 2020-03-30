window.onload = function() {
	var now = new Date();
	var nowYear = now.getYear();
	if(nowYear < 2000) { nowYear += 1900; }
	var selectS = document.getElementById("startYear");
	var selectE = document.getElementById("endYear");
	var maxYear = nowYear + 3;
	
	if(selectS != null && selectE != null) {
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
}

function changeAria() {
	var aria = document.getElementById("addEmploymentAria");
	if (aria.classList.contains("noDisplay") == true) {
		aria.classList.remove("noDisplay");
	} else {
		aria.classList.add("noDisplay");
	}
}

function changeHistoryAria() {
	var aria = document.getElementById("historyAria");
	if (aria.classList.contains("noDisplay") == true) {
		aria.classList.remove("noDisplay");
	} else {
		aria.classList.add("noDisplay");
	}
}

function changeCheckBox($this) {
	var val = $this.value;
	var target = document.querySelectorAll("#employmentInfoAria input");
	var checkbox = document.getElementsByName("employeeId");
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
	var table = document.getElementById("employmentInfoAria");
	var changeStartYear = document.getElementById("changeStartYear");
	var changeStartMonth = document.getElementById("changeStartMonth");
	var changeStartDay = document.getElementById("changeStartDay");
	var changeEndYear = document.getElementById("changeEndYear");
	var changeEndMonth = document.getElementById("changeEndMonth");
	var changeEndDay = document.getElementById("changeEndDay");
	var lowerLimit = document.getElementById("lowerLimit");
	var upperLimit = document.getElementById("upperLimit");
	var excessMoney = document.getElementById("excessMoney");
	var eductionMoney = document.getElementById("eductionMoney");
	var basicSalary = document.getElementById("basicSalary");
	var dutiesAllowance = document.getElementById("dutiesAllowance");
	var commutingAllowance = document.getElementById("commutingAllowance");
	var overtimeAllowance = document.getElementById("overtimeAllowance");
	var otherAllowance = document.getElementById("otherAllowance");

	var hiddenStartYear = document.getElementById("hiddenStartYear");
	var hiddenStartMonth = document.getElementById("hiddenStartMonth");
	var hiddenStartDay = document.getElementById("hiddenStartDay");
	var hiddenEndYear = document.getElementById("hiddenEndYear");
	var hiddenEndMonth = document.getElementById("hiddenEndMonth");
	var hiddenEndDay = document.getElementById("hiddenEndDay");
	var hiddenLowerLimit = document.getElementById("hiddenLowerLimit");
	var hiddenUpperLimit = document.getElementById("hiddenUpperLimit");
	var hiddenExcessMoney = document.getElementById("hiddenExcessMoney");
	var hiddenEductionMoney = document.getElementById("hiddenEductionMoney");
	var hiddenBasicSalary = document.getElementById("hiddenBasicSalary");
	var hiddenDutiesAllowance = document.getElementById("hiddenDutiesAllowance");
	var hiddenCommutingAllowance = document.getElementById("hiddenCommutingAllowance");
	var hiddenOvertimeAllowance = document.getElementById("hiddenOvertimeAllowance");
	var hiddenOtherAllowance = document.getElementById("hiddenOtherAllowance");
	
	if (table.classList.contains("tableErr") == true) {
		table.classList.remove("tableErr");
	}
	changeStartYear.value = hiddenStartYear.value;
	changeStartMonth.value = hiddenStartMonth.value;
	changeStartDay.value = hiddenStartDay.value;
	changeEndYear.value = hiddenEndYear.value;
	changeEndMonth.value = hiddenEndMonth.value;
	changeEndDay.value = hiddenEndDay.value;
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
	
	var checkbox = document.getElementsByName("employeeId");
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
			
			var table = document.getElementById("employmentInfoAria");
			var changeStartYear = document.getElementById("changeStartYear");
			var changeStartMonth = document.getElementById("changeStartMonth");
			var changeStartDay = document.getElementById("changeStartDay");
			var changeEndYear = document.getElementById("changeEndYear");
			var changeEndMonth = document.getElementById("changeEndMonth");
			var changeEndDay = document.getElementById("changeEndDay");
			var lowerLimit = document.getElementById("lowerLimit");
			var upperLimit = document.getElementById("upperLimit");
			var excessMoney = document.getElementById("excessMoney");
			var eductionMoney = document.getElementById("eductionMoney");
			var basicSalary = document.getElementById("basicSalary");
			var dutiesAllowance = document.getElementById("dutiesAllowance");
			var commutingAllowance = document.getElementById("commutingAllowance");
			var overtimeAllowance = document.getElementById("overtimeAllowance");
			var otherAllowance = document.getElementById("otherAllowance");
			
			var hiddenStartYear = document.getElementById("hiddenStartYear");
			var hiddenStartMonth = document.getElementById("hiddenStartMonth");
			var hiddenStartDay = document.getElementById("hiddenStartDay");
			var hiddenEndYear = document.getElementById("hiddenEndYear");
			var hiddenEndMonth = document.getElementById("hiddenEndMonth");
			var hiddenEndDay = document.getElementById("hiddenEndDay");
			var hiddenLowerLimit = document.getElementById("hiddenLowerLimit");
			var hiddenUpperLimit = document.getElementById("hiddenUpperLimit");
			var hiddenExcessMoney = document.getElementById("hiddenExcessMoney");
			var hiddenEductionMoney = document.getElementById("hiddenEductionMoney");
			var hiddenBasicSalary = document.getElementById("hiddenBasicSalary");
			var hiddenDutiesAllowance = document.getElementById("hiddenDutiesAllowance");
			var hiddenCommutingAllowance = document.getElementById("hiddenCommutingAllowance");
			var hiddenOvertimeAllowance = document.getElementById("hiddenOvertimeAllowance");
			var hiddenOtherAllowance = document.getElementById("hiddenOtherAllowance");
			
			if( changeStartYear.value == hiddenStartYear.value
					&& changeStartMonth.value == hiddenStartMonth.value
					&& changeStartDay.value == hiddenStartDay.value
					&& changeEndYear.value == hiddenEndYear.value
					&& changeEndMonth.value == hiddenEndMonth.value
					&& changeEndDay.value == hiddenEndDay.value
					&& lowerLimit.value == hiddenLowerLimit.value
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