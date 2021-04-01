<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  int employeeId = (int)request.getAttribute("employeeId"); %>
<%  String birthday = (String)request.getAttribute("birthday"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
<%  String dependents = (String)request.getAttribute("dependents"); %>
<%  String year = (String)request.getAttribute("year"); %>
<%  String month = (String)request.getAttribute("month"); %>
<%  String lowerLimit = (String)request.getAttribute("lowerLimit"); %>
<%  String upperLimit = (String)request.getAttribute("upperLimit"); %>
<%  String operatingTime = (String)request.getAttribute("operatingTime"); %>
<%  String upperTime = (String)request.getAttribute("upperTime"); %>
<%  String lowarTime = (String)request.getAttribute("lowarTime"); %>
<%  String basicSalary = (String)request.getAttribute("basicSalary"); %>
<%  String dutiesAllowance = (String)request.getAttribute("dutiesAllowance"); %>
<%  String commutingAllowance = (String)request.getAttribute("commutingAllowance"); %>
<%  String overtimeAllowance = (String)request.getAttribute("overtimeAllowance"); %>
<%  String otherAllowance = (String)request.getAttribute("otherAllowance"); %>
<%  String totalPayment = (String)request.getAttribute("totalPayment"); %>
<%  String healthInsurance = (String)request.getAttribute("healthInsurance"); %>
<%  String employeePension = (String)request.getAttribute("employeePension"); %>
<%  String employmentInsurance = (String)request.getAttribute("employmentInsurance"); %>
<%  String shortageDeduction = (String)request.getAttribute("shortageDeduction"); %>
<%  String totalDeduction = (String)request.getAttribute("totalDeduction"); %>
<%  String payment = (String)request.getAttribute("payment"); %>
<%  String incomeTax = (String)request.getAttribute("incomeTax"); %>
<%  String excessMoney = (String)request.getAttribute("excessMoney"); %>
<%  String eductionMoney = (String)request.getAttribute("eductionMoney"); %>
<%  String residentTax = (String)request.getAttribute("residentTax"); %>
<%  String beforePoolFlag = (String)request.getAttribute("beforePoolFlag"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/operatingtime/inputJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<meta charset="UTF-8">
<title>稼働時間入力</title>
</head>
<body>
<div class="center">
	<h1>稼働時間入力</h1>
	<hr>
</div>
<div id="messageAria">
	<% if (messageList != null && messageList.size() > 0) { %>
	<% for (String message : messageList) { %>
	<p class="errMessage"><%= message %></p>
	<% } %>
	<hr>
	<% } %>
</div>
<form method="post" action="/JinjiKyuyo/operatingTime/input">
	<input type="text" name="employeeName" value="<%= employeeName %>" class="nameAria" readonly><br>
	<input type="hidden" name="employeeId" value="<%= employeeId %>">
	<input type="hidden" name="birthday" value="<%= birthday %>">
	<input type="hidden" name="dependents" value="<%= dependents %>">
	稼働月：<select name="wareki" id="wareki" onChange="changeWreki(this)">
		<option value="R">令和</option>
		<option value="H">平成</option>
	</select>
	<select name="year" id="year">
		<option value=""></option>
	</select>年
	<select name="month" id="month">
		<option value=""></option>
		<option value="01">1</option>
		<option value="02">2</option>
		<option value="03">3</option>
		<option value="04">4</option>
		<option value="05">5</option>
		<option value="06">6</option>
		<option value="07">7</option>
		<option value="08">8</option>
		<option value="09">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>月
	稼働時間：<input type="text" name="operatingTime1" value="0"  class="moneyAria" onChange="checkNumber(this)">時間
	<select name="operatingTime2">
		<option value="0">0分</option>
		<option value="25">15分</option>
		<option value="50">30分</option>
		<option value="75">45分</option>
	</select>
	<input type="submit" name="confirm" value="確認" class="button">
</form>
<% if(totalPayment != null) { %>
<form method="post" action="/JinjiKyuyo/salaryInfo/regist">
<input type="hidden" name="employeeName" value="<%= employeeName %>"><br>
<input type="hidden" name="employeeId" value="<%= employeeId %>">
<input type="hidden" name="birthday" value="<%= birthday %>">
<input type="hidden" name="dependents" value="<%= dependents %>">
<div style="width=1200px;">
	<input type="text" name="year" value="<%= year %>" class="numberAria" readonly>年<input type="text" name="month" value="<%= month %>" class="numberAria" readonly>月支給分
	
	<table style="margin-left:0;">
		<tr>
			<th rowspan="2">時<br>間</th>
			<th>基準時間</th>
			<th>実働時間</th>
			<th>超過時間</th>
			<th>控除時間</th>
		</tr>
		<tr>
			<td><input type="text" name="lowerLimit" value="<%= lowerLimit %>" class="timeAria" readonly>〜<input type="text" name="upperLimit" value="<%= upperLimit %>" class="timeAria" readonly></td>
			<td><input type="text" name="operatingTime" value="<%= operatingTime %>" class="timeAria" readonly>h</td>
			<td><input type="text" name="upperTime" value="<%= upperTime %>" class="timeAria" readonly>h</td>
			<td><input type="text" name="lowarTime" value="<%= lowarTime %>" class="timeAria" readonly>h</td>
		</tr>
	</table>
	<div>
		<table style="display: inline-block;">
			<tr>
				<th rowspan="2">支<br>給</th>
				<th>基本給</th>
				<th>職務手当</th>
				<th>その他手当</th>
				<th>通勤手当</th>
				<th>時間外手当</th>
				<th>時間不足控除</th>
			</tr>
			<tr>
				<td><input type="text" name="basicSalary" value="<%= basicSalary %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="dutiesAllowance" value="<%= dutiesAllowance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="otherAllowance" value="<%= otherAllowance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="commutingAllowance" value="<%= commutingAllowance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="overtimeAllowance" value="<%= overtimeAllowance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="shortageDeduction" value="<%= shortageDeduction %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
		<table style="float: right;">
			<tr>
				<th>総支給額</th>
			</tr>
			<tr>
				<td><input type="text" name="totalPayment" value="<%= totalPayment %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
	</div>
	<div>
		<table style="display: inline-block;">
			<tr>
				<th rowspan="2">控<br>除</th>
				<th>健康保険</th>
				<th>厚生年金</th>
				<th>雇用保険</th>
				<th>所得税</th>
				<th>住民税</th>
			</tr>
			<tr>
				<td><input type="text" name="healthInsurance" value="<%= healthInsurance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="employeePension" value="<%= employeePension %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="employmentInsurance" value="<%= employmentInsurance %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="incomeTax" value="<%= incomeTax %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="residentTax" value="<%= residentTax %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
		<table style="float: right;">
			<tr>
				<th>控除合計</th>
			</tr>
			<tr>
				<td><input type="text" name="totalDeduction" value="<%= totalDeduction %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
	</div>
	<div>
		<table style="display: inline-block;">
			<tr>
				<th rowspan="2">単<br>価</th>
				<th>超過</th>
				<th>控除</th>
			</tr>
			<tr>
				<td><input type="text" name="excessMoney" value="<%= excessMoney %>" class="moneyAria" readonly>円</td>
				<td><input type="text" name="eductionMoney" value="<%= eductionMoney %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
		<table style="float: right;">
			<tr>
				<th>差引支給</th>
			</tr>
			<tr>
				<td><input type="text" name="payment" value="<%= payment %>" class="moneyAria" readonly>円</td>
			</tr>
		</table>
	</div>
	<br>
	<% if (month.equals("04") || month.equals("05") || month.equals("06")) { %>
	<% if (beforePoolFlag.equals("1")) { %>
	<input type="radio" name="poolFlag" value="1" checked="checked">時間外手当をプールする
	<% } else if (beforePoolFlag.equals("2")) {%>
	<input type="radio" name="poolFlag" value="2" checked="checked">時間外手当をプールしない
	<% } else {%>
	<input type="radio" name="poolFlag" value="1">時間外手当をプールする
	<input type="radio" name="poolFlag" value="2">時間外手当をプールしない
	<% } %>
	<br>
	<% } %>
</div>
<input type="submit" name="regist" value="登録" class="button">
</form>
<% } %>
<br>
<a href="/JinjiKyuyo/employee/list" class="button">戻る</a>
</body>
</html>