<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザ登録</title>
</head>
<body>
<% if (messageList != null && messageList.size() > 0) { %>
<% for (String message : messageList) { %>
<p style="color:red"><%= message %></p>
<% } %>
<hr>
<% } %>
<form method="post" action="./userRegist">
	名前
	<input type="text" name="userName">
	<br>
	生年月日
	<select name="year">
		<option value="1921">1921</option>
		<option value="1931">1931</option>
		<option value="1941">1941</option>
		<option value="1951">1951</option>
		<option value="1961">1961</option>
		<option value="1971">1971</option>
		<option value="1981">1981</option>
		<option value="1991">1991</option>
		<option value="2001">2001</option>
		<option value="2019">2019</option>
		<option value="2020">2020</option>
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
	<br>
	電話番号
	<input type="text" name="phoneNum">
	<br>
	身長
	<input type="text" name="height">
	<br>
	<input type="submit" name="regist" value="登録">
</form>
<hr>
<a href="./Login">戻る</a>
</body>
</html>