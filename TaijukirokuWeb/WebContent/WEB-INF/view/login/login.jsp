<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="taijukiroku.bean.UserInfo" %>
<%  List<UserInfo>UList=(List<UserInfo>)request.getAttribute("userInfoList"); %>
<%  String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<hr>
<% if (message != null) { %>
<p style="color:red"><%= message %></p>
<hr>
<% } %>
<form method="post" action="./Login">
	<div>
	名前
	<select name="userNo">
	<option value="0"></option>
	<% if (UList != null && UList.size() > 0) { %>
	<% for (UserInfo userInfo : UList) { %>
	<option value="<%= userInfo.getUserNo() %>">
	<%= userInfo.getUserName() %>
	</option>
	<% } %>
	<% } %>
	</select>
	</div>
	<input type="submit" value="ログイン" id="login">
	<br>
	<a href="./userRegist">新規ユーザ登録</a>
</form>
</body>
</html>