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
	稼働月：<%= salary.getOperatingMonth().substring(0,4) %>年<%= salary.getOperatingMonth().substring(4,6) %>月
	<table>
		<tr>
			<th>基準時間</th>
			<td><input type="text" name="lowerLimit_<%= salary.getOperatingMonth() %>" value="<%= salary.getLowerLimit() %>" class="timeAria" readonly>〜<input type="text" name="upperLimit_<%= salary.getOperatingMonth() %>" value="<%= salary.getUpperLimit() %>" class="timeAria" readonly></td>
			<th>実働時間</th>
			<td><input type="text" name="operatingTime_<%= salary.getOperatingMonth() %>" value="<%= salary.getOperatingTime() %>" class="timeAria" readonly>h</td>
			<th>超過時間</th>
			<td><input type="text" name="upperTime_<%= salary.getOperatingMonth() %>" value="<%= salary.getUpperTime() %>" class="timeAria" readonly>h</td>
			<th>不足時間</th>
			<td><input type="text" name="lowarTime_<%= salary.getOperatingMonth() %>" value="<%= salary.getLowarTime() %>" class="timeAria" readonly>h</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<th>基本給</th>
			<td><input type="text" name="basicSalary_<%= salary.getOperatingMonth() %>" value="<%= salary.getBasicSalary() %>" class="moneyAria" readonly>円</td>
			<th>職務手当</th>
			<td><input type="text" name="dutiesAllowance_<%= salary.getOperatingMonth() %>" value="<%= salary.getDutiesAllowance() %>" class="moneyAria" readonly>円</td>
			<th>通勤手当</th>
			<td><input type="text" name="commutingAllowance_<%= salary.getOperatingMonth() %>" value="<%= salary.getCommutingAllowance() %>" class="moneyAria" readonly>円</td>
			<th>時間外手当</th>
			<td><input type="text" name="overtimeAllowance_<%= salary.getOperatingMonth() %>" value="<%= salary.getOvertimeAllowance() %>" class="moneyAria" readonly>円</td>
			<th>その他手当</th>
			<td><input type="text" name="otherAllowance_<%= salary.getOperatingMonth() %>" value="<%= salary.getOtherAllowance() %>" class="moneyAria" readonly>円</td>
		</tr>
		<tr>
			<th>健康保険</th>
			<td><input type="text" name="healthInsurance_<%= salary.getOperatingMonth() %>" value="<%= salary.getHealthInsurance() %>" class="moneyAria" readonly>円</td>
			<th>厚生年金</th>
			<td><input type="text" name="employeePension_<%= salary.getOperatingMonth() %>" value="<%= salary.getEmployeePension() %>" class="moneyAria" readonly>円</td>
			<th>雇用保険</th>
			<td><input type="text" name="employmentInsurance_<%= salary.getOperatingMonth() %>" value="<%= salary.getEmploymentInsurance() %>" class="moneyAria" readonly>円</td>
			<th>所得税</th>
			<td><input type="text" name="incomeTax_<%= salary.getOperatingMonth() %>" value="<%= salary.getIncomeTax() %>" class="moneyAria" readonly>円</td>
			<th>時間不足控除</th>
			<td><input type="text" name="shortageDeduction_<%= salary.getOperatingMonth() %>" value="<%= salary.getShortageDeduction() %>" class="moneyAria" readonly>円</td>
		</tr>
		<tr>
			<th>超過</th>
			<td><input type="text" name="excessMoney_<%= salary.getOperatingMonth() %>" value="<%= salary.getExcessMoney() %>" class="moneyAria" readonly>円</td>
			<th>控除</th>
			<td><input type="text" name="eductionMoney_<%= salary.getOperatingMonth() %>" value="<%= salary.getEductionMoney() %>" class="moneyAria" readonly>円</td>
			<td colspan="6"></td>
		</tr>
		<tr>
			<th>総支給額</th>
			<td><input type="text" name="totalPayment_<%= salary.getOperatingMonth() %>" value="<%= salary.getTotalPayment() %>" class="moneyAria" readonly>円</td>
			<th>控除合計</th>
			<td><input type="text" name="totalDeduction_<%= salary.getOperatingMonth() %>" value="<%= salary.getTotalDeduction() %>" class="moneyAria" readonly>円</td>
			<th>差引支給</th>
			<td><input type="text" name="payment_<%= salary.getOperatingMonth() %>" value="<%= salary.getPayment() %>" class="moneyAria" readonly>円</td>
			<td colspan="4"></td>
		</tr>
	</table>
<% } %>
<% } else {%>
		<hr>
		<h2 style="color:red">給与情報が存在しません。</h2>
		<hr>
<% } %>
<a href="/JinjiKyuyo/salaryInfo/employeeList" class="button">戻る</a>
</body>
</html>