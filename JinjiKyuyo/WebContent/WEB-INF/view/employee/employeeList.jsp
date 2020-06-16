<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.AddressBookBean" %>
<%@ page import="jinjikyuyo.util.DateFormatUtil" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  List<AddressBookBean>addressBookList=(List<AddressBookBean>)request.getAttribute("addressBookList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/employee/employeeListJs.js"></script>
<title>社員一覧</title>
</head>
<body>
<div class="center">
	<h1>社員一覧</h1>
	<hr>
</div>
<% if (messageList != null && messageList.size() > 0) { %>
<% for (String message : messageList) { %>
<p class="errMessage"><%= message %></p>
<% } %>
<hr>
<% } %>
<form name="nextForm" method="post" action="/JinjiKyuyo/employee/list" class="center">
	<input type="hidden" name="nextAction" id="nextAction" value="">
	<a href="/JinjiKyuyo/addressbook/regist" class="button">社員情報追加</a>
	<a href="/JinjiKyuyo/salaryInfo/month" class="button">給与一覧</a>
	<input type="button" name="next" id="employmentInfo" value="雇用情報確認" class="button" onClick="moveNext(this)" disabled="disabled">
	<input type="button" name="next" id="operatingTime" value="作業時間入力" class="button" onClick="moveNext(this)" disabled="disabled">
	<input type="button" name="next" id="salaryInfo" value="個別給与情報一覧" class="button" onClick="moveNext(this)" disabled="disabled">
	<br>
	<table class="employeeListAria">
		<tr><th class="w0"></th><th class="w1">氏名</th><th class="w1">生年月日</th><th class="w1">郵便番号</th><th class="w2">住所</th><th class="w1">固定電話番号</th><th class="w1">携帯電話番号</th><th class="w2">メールアドレス</th><th class="w1">扶養家族</th></tr>
		<% if (addressBookList != null && addressBookList.size() > 0) { %>
		<% for (AddressBookBean addressBook : addressBookList) { %>
		<tr>
			<td><input type="radio" name="employeeId" value="<%= addressBook.getEmployeeId() %>" onclick="checkRadio()"></td>
			<td><input type="text" name="fullName_<%= addressBook.getEmployeeId() %>" value="<%= addressBook.getFullName() %>" class="nameAria" readonly></td>
			<td>
			<%= DateFormatUtil.chageWareki(addressBook.getBirthday().substring(0,4))
			    + addressBook.getBirthday().substring(4,6) + "月"
			    + addressBook.getBirthday().substring(6,8) + "日" %>
			    <input type="hidden" name="birthday_<%= addressBook.getEmployeeId() %>" value="<%= addressBook.getBirthday() %>">
			</td>
			<td><%= addressBook.getZipCode() %></td>
			<td><%= addressBook.getAddress1() + addressBook.getAddress2() %></td>
			<td><%= addressBook.getPhoneNumber() %></td>
			<td><%= addressBook.getMobileNumber() %></td>
			<td><%= addressBook.getMailAddress() %></td>
			<td><input type="text" name="dependents_<%= addressBook.getEmployeeId() %>" value="<%= addressBook.getDependents() %>" class="moneyAria" readonly>人</td>
		</tr>
		<% } %>
		<% } %>
	</table>
	<a href="/JinjiKyuyo" class="button">戻る</a>
</form>
</body>
</html>