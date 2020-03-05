<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="taijukiroku.bean.UserInfo" %>
<%  List<UserInfo>UList=(List<UserInfo>)request.getAttribute("userInfoList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<hr>
<form method="post" action="./Login">
	<div>
	名前
	<select name="userName">
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
</form>
</body>
</html>