<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="taijukiroku.bean.UserInfo" %>
<%@ page import="taijukiroku.bean.TaijuInfo" %>
<%  UserInfo userInfo=(UserInfo)request.getAttribute("userInfo"); %>
<%  List<TaijuInfo>TList=(List<TaijuInfo>)request.getAttribute("taijuInfoList"); %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
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
<form method="post" action="./main">
	<select name="yaer">
		<option value="2019">2019</option>
		<option value="2020">2020</option>
		<option value="2021">2021</option>
		<option value="2022">2022</option>
	</select>年
	<select name="month">
		<option value="01">1</option>
		<option value="02">2</option>
		<option value="03">3</option>
		<option value="04">4</option>
		<option value="05">5</option>
		<option value="06">6</option>
		<option value="07">7</option>
		<option value="08">8</option>
		<option value="09">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>月
	<select name="day">
		<option value="01">1</option>
		<option value="02">2</option>
		<option value="03">3</option>
		<option value="04">4</option>
		<option value="05">5</option>
		<option value="06">6</option>
		<option value="07">7</option>
		<option value="08">8</option>
		<option value="09">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
		<option value="13">13</option>
		<option value="14">14</option>
		<option value="15">15</option>
		<option value="16">16</option>
		<option value="17">17</option>
		<option value="18">18</option>
		<option value="19">19</option>
		<option value="20">20</option>
		<option value="21">21</option>
		<option value="22">22</option>
		<option value="23">23</option>
		<option value="24">24</option>
		<option value="25">25</option>
		<option value="26">26</option>
		<option value="27">27</option>
		<option value="28">28</option>
		<option value="29">29</option>
		<option value="30">30</option>
		<option value="31">31</option>
	</select>日
	<input type="text" name="weight">kg
	<input type="hidden" name="userNo" value="<%= userInfo.getUserNo() %>">
	<input type="hidden" name="height" value="<%= userInfo.getHeight() %>">
	<input type="submit" value="登録" name="regist">
</form>
<hr>
<% if (messageList != null && messageList.size() > 0) { %>
<% for (String message : messageList) { %>
<p style="color:red"><%= message %></p>
<% } %>
<hr>
<% } %>
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
<hr>
<a href="./Login">戻る</a>
</form>
</body>
</html>