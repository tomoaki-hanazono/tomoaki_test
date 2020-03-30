<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="jinjikyuyo.bean.InsurancePremiumRateBean" %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  Map<String, InsurancePremiumRateBean> insurancePremiumRateMap = (Map<String, InsurancePremiumRateBean>)request.getAttribute("insurancePremiumRateList"); %>
<%  String HEALTH_INSURANCE = "health_insurance"; %>
<%  String CARE_INSURANCE = "care_insurance"; %>
<%  String EMPLOYEE_PENSION = "employee_pension"; %>
<%  String EMPLOYMENT_INSURANCE = "employment_insurance"; %>
<%  InsurancePremiumRateBean healthInsurance =  insurancePremiumRateMap.get(HEALTH_INSURANCE);%>
<%  InsurancePremiumRateBean careInsurance =  insurancePremiumRateMap.get(CARE_INSURANCE);%>
<%  InsurancePremiumRateBean employeePension =  insurancePremiumRateMap.get(EMPLOYEE_PENSION);%>
<%  InsurancePremiumRateBean employmentInsurance =  insurancePremiumRateMap.get(EMPLOYMENT_INSURANCE);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maintenance/changeRateJs.js"></script>
<title>保険料率変更</title>
</head>
<body>
<div class="center">
	<h1>保険料率変更</h1>
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
<form method="post" action="/JinjiKyuyo/maintenance/changeRate" class="center">
	<table>
		<tr>
			<th>適用開始月</th>
			<td>
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
			</td>
		</tr>
		<tr>
			<th><%= healthInsurance.getInsuranceName() %></th>
			<td><input type="text" name="healthInsurance_1" value="<%= healthInsurance.getInsurancePremiumRate1() %>" class="timeAria" onChange="checkNumber(this)">.<input type="text" name="healthInsurance_2" value="<%= healthInsurance.getInsurancePremiumRate2() %>" class="timeAria" onChange="checkNumber(this)"></td>
		</tr>
		<tr>
			<th><%= careInsurance.getInsuranceName() %></th>
			<td><input type="text" name="careInsurance_1" value="<%= careInsurance.getInsurancePremiumRate1() %>" class="timeAria" onChange="checkNumber(this)">.<input type="text" name="careInsurance_2" value="<%= careInsurance.getInsurancePremiumRate2() %>" class="timeAria" onChange="checkNumber(this)"></td>
		</tr>
		<tr>
			<th><%= employeePension.getInsuranceName() %></th>
			<td><input type="text" name="employeePension_1" value="<%= employeePension.getInsurancePremiumRate1() %>" class="timeAria" onChange="checkNumber(this)">.<input type="text" name="employeePension_2" value="<%= employeePension.getInsurancePremiumRate2() %>" class="timeAria" onChange="checkNumber(this)"></td>
		</tr>
		<tr>
			<th><%= employmentInsurance.getInsuranceName() %></th>
			<td><input type="text" name="employmentInsurance_1" value="<%= employmentInsurance.getInsurancePremiumRate1() %>" class="timeAria" onChange="checkNumber(this)">/<input type="text" name="employmentInsurance_2" value="<%= employmentInsurance.getInsurancePremiumRate2() %>" class="timeAria" onChange="checkNumber(this)"></td>
		</tr>
	</table>
	<input type="hidden" name="b_healthInsurance_startDate" value="<%= healthInsurance.getStartDate() %>">
	<input type="hidden" name="b_healthInsurance_1" value="<%= healthInsurance.getInsurancePremiumRate1() %>">
	<input type="hidden" name="b_healthInsurance_2" value="<%= healthInsurance.getInsurancePremiumRate2() %>">
	<input type="hidden" name="b_careInsurance_startDate" value="<%= careInsurance.getStartDate() %>">
	<input type="hidden" name="b_careInsurance_1" value="<%= careInsurance.getInsurancePremiumRate1() %>">
	<input type="hidden" name="b_careInsurance_2" value="<%= careInsurance.getInsurancePremiumRate2() %>">
	<input type="hidden" name="b_employeePension_startDate" value="<%= employeePension.getStartDate() %>">
	<input type="hidden" name="b_employeePension_1" value="<%= employeePension.getInsurancePremiumRate1() %>">
	<input type="hidden" name="b_employeePension_2" value="<%= employeePension.getInsurancePremiumRate2() %>">
	<input type="hidden" name="b_employmentInsurance_startDate" value="<%= employmentInsurance.getStartDate() %>">
	<input type="hidden" name="b_employmentInsurance_1" value="<%= employmentInsurance.getInsurancePremiumRate1() %>">
	<input type="hidden" name="b_employmentInsurance_2" value="<%= employmentInsurance.getInsurancePremiumRate2() %>">
	<input type="submit" name="regist" value="変更" class="button">
	<a href="/JinjiKyuyo" class="button">戻る</a>
</form>
</body>
</html>