<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  Map<String, String> shainMap = (Map<String, String>)request.getAttribute("shainMap"); %>
<%  String employeeId = (String)request.getAttribute("employeeId"); %>
<%  String birthday = (String)request.getAttribute("birthday"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
<%  String dependents = (String)request.getAttribute("dependents"); %>
<%  String year = (String)request.getAttribute("year"); %>
<%  String month = (String)request.getAttribute("month"); %>
<%  String operatingTime = (String)request.getAttribute("operatingTime"); %>
<%  String operatingTime1 = (String)request.getAttribute("operatingTime1"); %>
<%  String operatingTime2 = (String)request.getAttribute("operatingTime2"); %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/kinmujisseki/inputJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<meta charset="UTF-8">
<title>勤務実績表取込画面</title>
</head>
<div class="center">
	<h1>勤務実績表取込</h1>
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
<form method="post" enctype="multipart/form-data" action="/JinjiKyuyo/kinmuJisseki/input">
	社員：<select name="shain" id="shain">
		<option value=""></option>
		<% if (shainMap != null && shainMap.size() > 0) { %>
		<% for (String key : shainMap.keySet()) { %>
		<option value="<%= key %>"><%= shainMap.get(key) %></option>
		<% } %>
		<% } %>
	</select>
	稼働月：<select name="wareki" id="wareki" onChange="changeWreki(this)">
		<option value="R">令和</option>
		<option value="H">平成</option>
	</select>
	<select name="year" id="year">
		<option value=""></option>
	</select>年
	<select name="month" id="month">
		<option value=""></option>
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
	<label class="filebutton">
		<input type="file" name="kinmujissekihyo" id="kinmujissekihyo" accept=".xlsx" onChange="getfilename(this)" />
		ファイルを選択
	</label>
	<span id="fileName">選択されていません</span>
	<br />
	<input type="submit" name="torikomi" value="取込" class="button">
</form>
<% if(operatingTime != null) { %>
<hr>
<form method="post" action="/JinjiKyuyo/operatingTime/input">
	<input type="hidden" name="employeeId" value="<%= employeeId %>">
	<input type="hidden" name="birthday" value="<%= birthday %>">
	<input type="hidden" name="employeeName" value="<%= employeeName %>">
	<input type="hidden" name="dependents" value="<%= dependents %>">
	<input type="hidden" name="year" value="<%= year %>">
	<input type="hidden" name="month" value="<%= month %>">
	<input type="hidden" name="operatingTime1" value="<%= operatingTime1 %>">
	<input type="hidden" name="operatingTime2" value="<%= operatingTime2 %>">
	<input type="submit" name="confirm" value="給与計算" class="button">
	<span><%= year %>年<%= month %>月</span>
	<span>氏名：<%= employeeName %></span>
	<span>稼働時間：<%= operatingTime %></span>
	
</form>
<% } %>

<hr>
<a href="/JinjiKyuyo" class="button">戻る</a>
</body>
</html>