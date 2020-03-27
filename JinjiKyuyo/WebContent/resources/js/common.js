function checkNum($this) {
	var val = $this.value;
	while(val.match(/[^0-9]/)){
		val=val.replace(/[^0-9]/,"");
	}
	$this.value = val;
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