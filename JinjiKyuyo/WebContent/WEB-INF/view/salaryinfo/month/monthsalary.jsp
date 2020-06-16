<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.Salary" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  List<Salary> salaryList=(List<Salary>)request.getAttribute("salaryList"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/salaryinfo/monthsalaryJs.js"></script>
<meta charset="UTF-8">
<title>給与一覧</title>
</head>
<body>
<div class="center noPrint">
	<h1>給与一覧</h1>
	<hr>
</div>
<div id="messageAria noPrint">
	<% if (messageList != null && messageList.size() > 0) { %>
	<% for (String message : messageList) { %>
	<p class="errMessage"><%= message %></p>
	<% } %>
	<hr>
	<% } %>
</div>
<form method="post" action="/JinjiKyuyo/salaryInfo/month" class="noPrint">
照会月：<select name="wareki" id="wareki" onChange="changeWreki(this)">
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
	<input type="submit" name="serch" value="照会">
	<input type="button" value="印刷する" onclick="window.print();">
<hr>
</form>
<% if(salaryList != null && salaryList.size() > 0) { %>
<% for(Salary salary : salaryList) { %>
	
	<table class="salaryTitelAria">
		<tr>
			<th colspan="3">株式会社エム業務</th>	
			<th colspan="9" rowspan="2" class="title">給与明細書</th>
			<th colspan="3"></th>
		</tr>
		<tr>
			<td colspan="3"><%= salary.getOperatingMonth().substring(0,4) %>年<%= salary.getOperatingMonth().substring(4,6) %>月支給分</td>
			<td colspan="3">氏名：<%= salary.getEmployeeName() %></td>
		</tr>
	</table>
	<table class="payslipAria">
		<tr>
			<th>基準時間</th>
			<td><%= salary.getLowerLimit() %>〜<%= salary.getUpperLimit() %></td>
			<th>実働時間</th>
			<td><%= salary.getOperatingTime() %>h</td>
			<th>超過時間</th>
			<td><%= salary.getUpperTime() %>h</td>
			<th>控除時間</th>
			<td><%= salary.getLowarTime() %>h</td>
			<td colspan="7"></td>
		</tr>
		<tr>
			<td colspan="15"></td>
		</tr>
		<tr>
			<th>基本給</th>
			<td><%= salary.getBasicSalary() %>円</td>
			<th>職務手当</th>
			<td><%= salary.getDutiesAllowance() %>円</td>
			<th>通勤手当</th>
			<td><%= salary.getCommutingAllowance() %>円</td>
			<th>時間外手当</th>
			<td><%= salary.getOvertimeAllowance() %>円</td>
			<th>その他手当</th>
			<td><%= salary.getOtherAllowance() %>円</td>
			<th>時間不足控除</th>
			<td><%= salary.getShortageDeduction() %>円</td>
			<td></td>
			<th>総支給額</th>
			<td><%= salary.getTotalPayment() %>円</td>
		</tr>
		<tr>
			<td colspan="12"></td><td></td><td colspan="2"></td>
		</tr>
		<tr>
			<th>健康保険</th>
			<td><%= salary.getHealthInsurance() %>円</td>
			<th>厚生年金</th>
			<td><%= salary.getEmployeePension() %>円</td>
			<th>雇用保険</th>
			<td><%= salary.getEmploymentInsurance() %>円</td>
			<th>所得税</th>
			<td><%= salary.getIncomeTax() %>円</td>
			<th>住民税</th>
			<td><%= salary.getResidentTax() %>円</td>
			<td colspan="2"></td><td></td>
			<th>控除合計</th>
			<td><%= salary.getTotalDeduction() %>円</td>
		</tr>
		<tr>
			<td colspan="12"></td><td></td><td colspan="2"></td>
		</tr>
		<tr>
			<th>超過</th>
			<td><%= salary.getExcessMoney() %>円</td>
			<th>控除</th>
			<td><%= salary.getEductionMoney() %>円</td>
			<td colspan="8"></td><td></td>
			<th>差引支給</th>
			<td><%= salary.getPayment() %>円</td>
		</tr>
	</table>
<% } %>
<% } %>
<hr>
<div class="noPrint">
<a href="/JinjiKyuyo/employee/list" class="button">戻る</a>
</div>
</body>
</html>