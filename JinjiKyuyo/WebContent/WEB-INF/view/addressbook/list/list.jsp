<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.AddressBookBean" %>
<%  List<AddressBookBean>addressBookList=(List<AddressBookBean>)request.getAttribute("addressBookList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録一覧</title>
</head>
<body>
<table>
	<tr><th>氏名</th><th>フリガナ</th><th>生年月日</th><th>郵便番号</th><th>住所</th><th>固定電話番号</th><th>携帯電話番号</th><th>メールアドレス</th></tr>
	<% if (addressBookList != null && addressBookList.size() > 0) { %>
	<% for (AddressBookBean addressBook : addressBookList) { %>
	<tr>
		<td><%= addressBook.getFullName() %></td>
		<td><%= addressBook.getNameKana() %></td>
		<td>
		<%= addressBook.getBirthday().substring(0,4) %>年
		<%= addressBook.getBirthday().substring(4,6) %>月
		<%= addressBook.getBirthday().substring(6,8) %>日
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
<a href="/JinjiKyuyo">戻る</a>
</body>
</html>