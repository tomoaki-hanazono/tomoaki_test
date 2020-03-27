<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  int employeeId = (int)request.getAttribute("employeeId"); %>
<%  String birthday = (String)request.getAttribute("birthday"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
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
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/operatingtime/inputJs.js"></script>
<meta charset="UTF-8">
<title>稼働時間入力</title>
</head>
<body>
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
	稼働月：<select name="year" id="year">
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
	<input type="submit" name="confirm" value="確認">
</form>
<% if(totalPayment != null) { %>
<form method="post" action="/JinjiKyuyo/salaryInfo/regist">
<input type="hidden" name="employeeName" value="<%= employeeName %>"><br>
<input type="hidden" name="employeeId" value="<%= employeeId %>">
<input type="hidden" name="birthday" value="<%= birthday %>">
稼働月：<input type="text" name="year" value="<%= year %>" class="numberAria" readonly>年<input type="text" name="month" value="<%= month %>" class="numberAria" readonly>月
<table>
	<tr>
		<th>基準時間</th>
		<td><input type="text" name="lowerLimit" value="<%= lowerLimit %>" class="timeAria" readonly>〜<input type="text" name="upperLimit" value="<%= upperLimit %>" class="timeAria" readonly></td>
		<th>実働時間</th>
		<td><input type="text" name="operatingTime" value="<%= operatingTime %>" class="timeAria" readonly>h</td>
		<th>超過時間</th>
		<td><input type="text" name="upperTime" value="<%= upperTime %>" class="timeAria" readonly>h</td>
		<th>不足時間</th>
		<td><input type="text" name="lowarTime" value="<%= lowarTime %>" class="timeAria" readonly>h</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<th>基本給</th>
		<td><input type="text" name="basicSalary" value="<%= basicSalary %>" class="moneyAria" readonly>円</td>
		<th>職務手当</th>
		<td><input type="text" name="dutiesAllowance" value="<%= dutiesAllowance %>" class="moneyAria" readonly>円</td>
		<th>通勤手当</th>
		<td><input type="text" name="commutingAllowance" value="<%= commutingAllowance %>" class="moneyAria" readonly>円</td>
		<th>時間外手当</th>
		<td><input type="text" name="overtimeAllowance" value="<%= overtimeAllowance %>" class="moneyAria" readonly>円</td>
		<th>その他手当</th>
		<td><input type="text" name="otherAllowance" value="<%= otherAllowance %>" class="moneyAria" readonly>円</td>
	</tr>
	<tr>
		<th>健康保険</th>
		<td><input type="text" name="healthInsurance" value="<%= healthInsurance %>" class="moneyAria" readonly>円</td>
		<th>厚生年金</th>
		<td><input type="text" name="employeePension" value="<%= employeePension %>" class="moneyAria" readonly>円</td>
		<th>雇用保険</th>
		<td><input type="text" name="employmentInsurance" value="<%= employmentInsurance %>" class="moneyAria" readonly>円</td>
		<th>所得税</th>
		<td><input type="text" name="incomeTax" value="<%= incomeTax %>" class="moneyAria" readonly>円</td>
		<th>時間不足控除</th>
		<td><input type="text" name="shortageDeduction" value="<%= shortageDeduction %>" class="moneyAria" readonly>円</td>
	</tr>
	<tr>
		<th>超過</th>
		<td><input type="text" name="excessMoney" value="<%= excessMoney %>" class="moneyAria" readonly>円</td>
		<th>控除</th>
		<td><input type="text" name="eductionMoney" value="<%= eductionMoney %>" class="moneyAria" readonly>円</td>
		<td colspan="6"></td>
	</tr>
	<tr>
		<th>総支給額</th>
		<td><input type="text" name="totalPayment" value="<%= totalPayment %>" class="moneyAria" readonly>円</td>
		<th>控除合計</th>
		<td><input type="text" name="totalDeduction" value="<%= totalDeduction %>" class="moneyAria" readonly>円</td>
		<th>差引支給</th>
		<td><input type="text" name="payment" value="<%= payment %>" class="moneyAria" readonly>円</td>
		<td colspan="4"></td>
	</tr>
</table>
<input type="submit" name="regist" value="登録">
</form>
<% } %>
<br>
<a href="/JinjiKyuyo/operatingTime/employeeList" class="button">戻る</a>
</body>
</html>