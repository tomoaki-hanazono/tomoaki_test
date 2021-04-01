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
	<input type="submit" name="serch" value="照会" class="button">
	<input type="button" value="印刷する" onclick="window.print();" class="button">
<hr>
</form>
<% if(salaryList != null && salaryList.size() > 0) { %>
<% for(Salary salary : salaryList) { %>
<div style="witdh:1200px;">
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
	<table class="payslipAria" style="margin-left:0;">
		<tr>
			<th class="hd" rowspan="2">時<br>間</th>
			<th>基準時間</th>
			<th>実働時間</th>
			<th>超過時間</th>
			<th>控除時間</th>
		</tr>
		<tr>
			<td><%= salary.getLowerLimit() %>〜<%= salary.getUpperLimit() %></td>
			<td><%= salary.getOperatingTime() %>h</td>
			<td><%= salary.getUpperTime() %>h</td>
			<td><%= salary.getLowarTime() %>h</td>
		</tr>
	</table>
	<div>
		<table class="payslipAria" style="display: inline-block;">
			<tr>
				<th class="hd" rowspan="2">支<br>給</th>
				<th>基本給</th>
				<th>職務手当</th>
				<th>その他手当</th>
				<th>通勤手当</th>
				<th>時間外手当</th>
				<th>時間不足控除</th>
			</tr>
			<tr>
				<td><%= salary.getBasicSalary() %></td>
				<td><%= salary.getDutiesAllowance() %></td>
				<td><%= salary.getOtherAllowance() %></td>
				<td><%= salary.getCommutingAllowance() %></td>
				<td><%= salary.getOvertimeAllowance() %></td>
				<td><%= salary.getShortageDeduction() %></td>
			</tr>
		</table>
		<table class="payslipAria" style="float: right;">
			<tr>
				<th>総支給額</th>
			</tr>
			<tr>
				<td><%= salary.getTotalPayment() %></td>
			</tr>
		</table>
	</div>
	<div>
		<table class="payslipAria" style="display: inline-block;">
			<tr>
				<th class="hd" rowspan="2">控<br>除</th>
				<th>健康保険</th>
				<th>厚生年金</th>
				<th>雇用保険</th>
				<th>所得税</th>
				<th>住民税</th>
			</tr>
			<tr>
				<td><%= salary.getHealthInsurance() %></td>
				<td><%= salary.getEmployeePension() %></td>
				<td><%= salary.getEmploymentInsurance() %></td>
				<td><%= salary.getIncomeTax() %></td>
				<td><%= salary.getResidentTax() %></td>
			</tr>
		</table>
		<table class="payslipAria" style="float: right;">
			<tr>
				<th>控除合計</th>
			</tr>
			<tr>
				<td><%= salary.getTotalDeduction() %></td>
			</tr>
		</table>
	</div>
	<div>
		<table class="payslipAria" style="display: inline-block;">
			<tr>
				<th class="hd" rowspan="2">単<br>価</th>
				<th>超過</th>
				<th>控除</th>
			</tr>
			<tr>
				<td><%= salary.getExcessMoney() %></td>
				<td><%= salary.getEductionMoney() %></td>
			</tr>
		</table>
		<table class="payslipAria" style="float: right;">
			<tr>
				<th>差引支給</th>
			</tr>
			<tr>
				<td><%= salary.getPayment() %>円</td>
			</tr>
		</table>
	</div>
<% } %>
<% } %>
</div>
<hr>
<div class="noPrint">
<a href="/JinjiKyuyo/employee/list" class="button">戻る</a>
</div>
</body>
</html>