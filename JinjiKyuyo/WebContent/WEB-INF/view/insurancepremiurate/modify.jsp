<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.EmploymentBean" %>
<%  List<EmploymentBean>employmentList=(List<EmploymentBean>)request.getAttribute("employmentList"); %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/employmentInfo/confirmJs.js"></script>
<title>保険料率変更</title>
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
<form method="post" action="/JinjiKyuyo/insurancePremiumRate/modify" class="center">
	<input type="hidden" name="type" value="add">
	<input type="hidden" name="employeeId" value="<%=  %>">
	<input type="text" name="employeeName" value="<%=  %>" readonly>	
	<table class="">
		<tr>
			<th>健康保険料率</th>
			<td><input type="text" name="healthInsurance1" value="0" onInput="checkNumber(this)" class="moneyAria">.<input type="text" name="healthInsurance2" value="0" onInput="checkNumber(this)" class="moneyAria"></td>
		</tr>
		<tr>
			<th>介護保険料率</th>
			<td><input type="text" name="careInsurance1" value="0" onInput="checkNumber(this)" class="moneyAria">.<input type="text" name="careInsurance2" value="0" onInput="checkNumber(this)" class="moneyAria"></td>
		</tr>
		<tr>
			<th>厚生年金保険料率</th>
			<td><input type="text" name="employeepension1" value="0" onInput="checkNumber(this)" class="moneyAria">.<input type="text" name="employeepension2" value="0" onInput="checkNumber(this)" class="moneyAria"></td>
		</tr>
		<tr>
			<th>雇用保険料率</th>
			<td><input type="text" name="employmentInsurance1" value="0" onInput="checkNumber(this)" class="moneyAria">/<input type="text" name="employmentInsurance2" value="0" onInput="checkNumber(this)" class="moneyAria"></td>
		</tr>
	</table>
	
	<input type="submit" name="modify" value="変更" class="button">
</form>
</body>
</html>