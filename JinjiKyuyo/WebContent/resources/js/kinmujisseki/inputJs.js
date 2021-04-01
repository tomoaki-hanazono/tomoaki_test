window.onload = function() {
	var wareki = document.getElementById("wareki");
	changeWreki(wareki)
}

function changeWreki($this) {
	var wareki = $this.value;
	var select = document.getElementById("year");
	removeChildren(select);
	
	if(wareki == "R") {
		var startYear = 2019;
		var now = new Date();
		var lastYear = now.getYear();
		if(lastYear < 2000) { lastYear += 1900; }
		var lastYearText = lastYear-startYear+1;
	} else if (wareki == "H") {
		var startYear = 2016;
		var lastYear = 2019;
		var lastYearText = 31;
	}
	
	while(lastYear >= startYear) {
		var option = document.createElement("option");
		option.text = lastYearText;
		option.value = lastYear;
		select.appendChild(option);
		lastYearText--;
		lastYear--;
	}
}

function removeChildren(x) {
	if (x.hasChildNodes()) {
		while (x.childNodes.length > 0) {
			x.removeChild(x.firstChild)
		}
	}
}

function getfilename($this) {
	var file = $this.files[0];
	var fileName = document.getElementById("fileName");
	var name = "選択してください";
	if(file) {
		name = file.name;
	}
	if(!name) {
		name = "選択してください";
	}
	fileName.innerHTML = name;
}