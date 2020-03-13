<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.AddressBookBean" %>
<%  List<AddressBookBean>addressBookList=(List<AddressBookBean>)request.getAttribute("addressBookList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<title>住所録一覧</title>
</head>
<body>
<div class="center">
	<table class="addressListAria">
		<tr><th class="w1">氏名</th><th class="w1">フリガナ</th><th class="w1">生年月日</th><th class="w1">郵便番号</th><th class="w2">住所</th><th class="w1">固定電話番号</th><th class="w1">携帯電話番号</th><th class="w2">メールアドレス</th></tr>
		<% if (addressBookList != null && addressBookList.size() > 0) { %>
		<% for (AddressBookBean addressBook : addressBookList) { %>
		<tr>
			<td><%= addressBook.getFullName() %></td>
			<td><%= addressBook.getNameKana() %></td>
			<td>
			<%= addressBook.getBirthday().substring(0,4) + "年"
			    + addressBook.getBirthday().substring(4,6) + "月"
			    + addressBook.getBirthday().substring(6,8) + "日" %>
			</td>
			<td><%= addressBook.getZipCode() %></td>
			<td><%= addressBook.getAddress1() + addressBook.getAddress2() %></td>
			<td><%= addressBook.getPhoneNumber() %></td>
			<td><%= addressBook.getMobileNumber() %></td>
			<td><%= addressBook.getMailAddress() %></td>
		</tr>
		<% } %>
		<% } %>
	</table>
	<a href="/JinjiKyuyo" class="button">戻る</a>
</div>
</body>
</html>