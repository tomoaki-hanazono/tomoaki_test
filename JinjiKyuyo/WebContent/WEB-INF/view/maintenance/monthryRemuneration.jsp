<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="jinjikyuyo.bean.AddressBookBean" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  List<AddressBookBean>addressBookList=(List<AddressBookBean>)request.getAttribute("addressBookList"); %>
<%  Map<Integer, Map<String, Integer>> salaryMap = (Map<Integer, Map<String, Integer>>)request.getAttribute("salaryMap"); %>
<%  String targetYear = (String)request.getAttribute("year"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maintenance/monthryRemunerationJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<title>標準月額設定</title>
</head>
<body>
<div class="center">
	<h1>標準月額設定</h1>
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
<form method="post" action="/JinjiKyuyo/monthryRemuneration/get" class="center">
	対象年度：<select name="wareki" id="wareki" onChange="changeWreki(this)">
		<option value="R">令和</option>
		<option value="H">平成</option>
	</select>
	<select name="year" id="year">
		<option value=""></option>
	</select>年
	<input type="submit" name="get" value="確認" class="button">
</form>
<hr>
<% if (addressBookList != null && addressBookList.size() > 0) { %>
<form method="post" action="/JinjiKyuyo/monthryRemuneration/regist" class="center">
	<h4><%= targetYear %>年度分</h4>
	<input type="hidden" name="year" value="<%= targetYear %>">
 	<table class="monthryRemunerationAria">
	<% for (AddressBookBean addressBook : addressBookList) { %>
		<tr><th class="w0">対象</th><th class="w1">社員名</th><th class="w1">4月分</th><th class="w1">5月分</th><th class="w1">6月分</th><th class="w2">3ヶ月分平均</th></tr>
		<tr>
			<td><input type="checkbox" name="employeeId" value="<%= addressBook.getEmployeeId() %>"></td>
			<td><%= addressBook.getFullName() %></td>
			<% if(salaryMap.containsKey(addressBook.getEmployeeId())) { %>
				<% Map<String, Integer> salary = salaryMap.get(addressBook.getEmployeeId()); %>
				<% int res = 0; %>
				<% if(salary.containsKey(targetYear + "04")) { %>
					<td><%= salary.get(targetYear + "04") %>円</td>
					<% res += salary.get(targetYear + "04"); %>
				<% } else { %>
					<td>0円</td>
				<% } %>
				<% if(salary.containsKey(targetYear + "05")) { %>
					<td><%= salary.get(targetYear + "05") %>円</td>
					<% res += salary.get(targetYear + "05"); %>
				<% } else { %>
					<td>0円</td>
				<% } %>
				<% if(salary.containsKey(targetYear + "06")) { %>
					<td><%= salary.get(targetYear + "06") %>円</td>
					<% res += salary.get(targetYear + "06"); %>
				<% } else { %>
					<td>0円</td>
				<% } %>
				<td><input type="text" name="monthryRemunerat_<%= addressBook.getEmployeeId() %>" value="<%= res/3 %>"  class="moneyAria">円
			<% } else { %>
				<td>0円</td><td>0円</td><td>0円</td>
				<td><input type="text" name="monthryRemunerat_<%= addressBook.getEmployeeId() %>" value="0"  class="moneyAria">円
			<% } %>
		</tr>
	<% } %>
	</table>
	<input type="submit" name="get" value="登録" class="button">
</form>
<% } %>
<a href="/JinjiKyuyo" class="button">戻る</a>
</body>
</html>