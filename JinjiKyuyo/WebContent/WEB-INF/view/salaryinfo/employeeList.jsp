<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.AddressBookBean" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  List<AddressBookBean>addressBookList=(List<AddressBookBean>)request.getAttribute("addressBookList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/employmentInfo/employeeListJs.js"></script>
<title>社員一覧</title>
</head>
<body>
<% if (messageList != null && messageList.size() > 0) { %>
<% for (String message : messageList) { %>
<p class="errMessage"><%= message %></p>
<% } %>
<hr>
<% } %>
<form method="post" action="/JinjiKyuyo/salaryInfo/employeeList" class="center">
	<table class="employeeListAria">
		<tr><th class="w0"></th><th class="w1">氏名</th><th class="w1">生年月日</th><th class="w1">郵便番号</th><th class="w2">住所</th></tr>
		<% if (addressBookList != null && addressBookList.size() > 0) { %>
		<% for (AddressBookBean addressBook : addressBookList) { %>
		<tr>
			<td><input type="radio" name="employeeId" value="<%= addressBook.getEmployeeId() %>" onclick="checkRadio()"></td>
			<td><input type="text" name="fullName_<%= addressBook.getEmployeeId() %>" value="<%= addressBook.getFullName() %>" class="nameAria" readonly></td>
			<td>
			<%= addressBook.getBirthday().substring(0,4) + "年"
			    + addressBook.getBirthday().substring(4,6) + "月"
			    + addressBook.getBirthday().substring(6,8) + "日" %>
			    <input type="hidden" name="birthday_<%= addressBook.getEmployeeId() %>" value="<%= addressBook.getBirthday() %>">
			</td>
			<td><%= addressBook.getZipCode() %></td>
			<td><%= addressBook.getAddress1() + addressBook.getAddress2() %></td>
		</tr>
		<% } %>
		<% } %>
	</table>
	<input type="submit" name="confirm" id="confirm" value="確認" class="button" disabled="disabled">
	<a href="/JinjiKyuyo" class="button">戻る</a>
</form>
</body>
</html>