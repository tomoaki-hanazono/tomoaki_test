<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="taijukiroku.bean.UserInfo" %>
<%@ page import="taijukiroku.bean.TaijuInfo" %>
<%  UserInfo userInfo=(UserInfo)request.getAttribute("userInfo"); %>
<%  List<TaijuInfo>TList=(List<TaijuInfo>)request.getAttribute("taijuInfoList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>体重記録</title>
</head>
<body>
<h1>体重記録</h1>
<hr>
<div>
<%= userInfo.getUserName() %>
生年月日:<%= userInfo.getBirthday().substring(0,4) %>年
<%= userInfo.getBirthday().substring(4,6) %>月
<%= userInfo.getBirthday().substring(6,8) %>日
身長:<%= userInfo.getHeight() %>cm
</div>
<from method="post" action="./main">
<select name="yaer"></select>年
<select name="month"></select>月
<select name="day"></select>日
<input type="text" name="taiju">kg
<input type="submit" value="登録" name="regist">
</from>
<hr>
<table>
	<tr><th>年月日</th><th>体重</th><th>BMI値</th><th>判定</th></tr>
	<% if (TList != null && TList.size() > 0) { %>
	<% for (TaijuInfo taijuInfo : TList) { %>
	<tr>
	<td>
	<%= taijuInfo.getDate().substring(0,4) %>年
	<%= taijuInfo.getDate().substring(4,6) %>月
	<%= taijuInfo.getDate().substring(6,8) %>日
	</td>
	<td><%= taijuInfo.getBodyWeight() %>kg</td>
	<td><%= taijuInfo.getBmi() %></td>
	<td><%= taijuInfo.getResult() %></td>
	</tr>
	<% } %>
	<% } %>
</table>
<div></div>
</body>
</html>