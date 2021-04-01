<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.SalaryBean" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  List<SalaryBean>salaryList=(List<SalaryBean>)request.getAttribute("salaryList"); %>
<%  int employeeId = (int)request.getAttribute("employeeId"); %>
<%  String birthday = (String)request.getAttribute("birthday"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/salaryinfo/listJs.js"></script>
<meta charset="UTF-8">
<title>給与情報一覧</title>
</head>
<body>
<div class="center">
	<h1>給与情報一覧</h1>
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
<input type="text" name="employeeName" value="<%= employeeName %>" class="nameAria" readonly><br>
<input type="hidden" name="employeeId" value="<%= employeeId %>">
<input type="hidden" name="birthday" value="<%= birthday %>">
<% if(salaryList != null && salaryList.size() > 0) { %>
<% for(SalaryBean salary : salaryList) { %>
	<%= salary.getOperatingMonth().substring(0,4) %>年<%= salary.getOperatingMonth().substring(4,6) %>月支給分
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
<% } else {%>
		<hr>
		<h2 style="color:red">給与情報が存在しません。</h2>
		<hr>
<% } %>
<a href="/JinjiKyuyo/employee/list" class="button">戻る</a>
</body>
</html>