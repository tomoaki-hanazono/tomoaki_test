<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.EmploymentInfoBean" %>
<%@ page import="jinjikyuyo.bean.EmploymentInfoHistoryBean" %>
<%  EmploymentInfoBean employmentInfo = (EmploymentInfoBean)request.getAttribute("employmentInfo"); %>
<%  List<EmploymentInfoHistoryBean>employmentHistoryList = (List<EmploymentInfoHistoryBean>)request.getAttribute("employmentHistoryList"); %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  int employeeId = (int)request.getAttribute("employeeId"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/employmentInfo/confirmJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<meta charset="UTF-8">
<title>雇用情報</title>
</head>
<body>
<div class="center">
	<h1>雇用情報</h1>
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
<p><%= employeeName %></p>
<% if (employmentInfo == null || employmentInfo.getEmployeeId() == 0) { %>
<form method="post" action="/JinjiKyuyo/employmentInfo/confirm" class="center">
	<input type="hidden" name="type" value="add">
	<input type="hidden" name="employeeId" value="<%= employeeId %>">
	<input type="hidden" name="employeeName" value="<%= employeeName %>">
	
	<input type="button" name="addEmployment" value="雇用情報追加" class="button" onClick="changeAria()">
	<div id="addEmploymentAria" class="noDisplay">
		<table class="">
			<tr>
				<th>雇用期間</th>
				<td>
					<select name="startYear" id="startYear">
						<option value=""></option>
					</select>年
					<select name="startMonth" id="startMonth">
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
					<select name="startDay" id="startDay">
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
					から
					<select name="endYear" id="endYear">
						<option value=""></option>
					</select>年
					<select name="endMonth" id="endMonth">
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
					<select name="endDay" id="endDay">
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
				</td>
			</tr>
			<tr>
				<th>基本給</th>
				<td><input type="text" name="basicSalary" value="0" onChange="checkNumber(this)" class="moneyAria">円</td>
			</tr>
			<tr>
				<th>職務手当</th>
				<td><input type="text" name="dutiesAllowance" value="0" onChange="checkNumber(this)" class="moneyAria">円</td>
			</tr>
			<tr>
				<th>通勤手当</th>
				<td><input type="text" name="commutingAllowance" value="0" onChange="checkNumber(this)" class="moneyAria">円</td>
			</tr>
			<tr>
				<th>時間外手当</th>
				<td><input type="text" name="overtimeAllowance" value="0" onChange="checkNumber(this)" class="moneyAria">円</td>
			</tr>
			<tr>
				<th>その他手当</th>
				<td><input type="text" name="otherAllowance" value="0" onChange="checkNumber(this)" class="moneyAria">円</td>
			</tr>
			<tr>
				<th>基準時間</th>
				<td>
					<input type="text" name="lowerLimit" value="0" onChange="checkNumber(this)" class="timeAria">h〜<input type="text" name="upperLimit" value="0" onChange="checkNumber(this)" class="timeAria">h<br>
					超過:<input type="text" name="excessMoney" value="0" onChange="checkNumber(this)" class="moneyAria">円<br>
					控除:<input type="text" name="eductionMoney" value="0" onChange="checkNumber(this)" class="moneyAria">円
				</td>
			</tr>
			<tr class="noDisplay">
				<th>職務内容</th>
				<td><input type="text" name="jobDescription"></td>
			</tr>
			<tr class="noDisplay">
				<th>備考</th>
				<td><input type="text" name="remarks"></td>
			</tr>
		</table>
		<input type="submit" name="addEmployment" value="登録" class="button">
		<hr>
	</div>
</form>
<hr>
<h2 style="color:red">雇用情報が存在しません。</h2>
<% } else { %>
<form method="post" action="/JinjiKyuyo/employmentInfo/confirm" class="center" onsubmit="return checkDefault()">
	<input type="hidden" name="type" value="update">
	<input type="hidden" name="employeeName" value="<%= employeeName %>">
	<input type="checkbox" name="employeeId" value="<%= employeeId %>" onclick="changeCheckBox(this)">修正をする
	<table class="addressListAria" id="employmentInfoAria">
		<tr><th class="w1" colspan="2">雇用期間</th><th class="w1">基準時間</th><th class="w1">超過</th><th class="w1">控除</th></tr>
		<tr>
			<td colspan="2">
				<input type="text" id="changeStartYear" name="startYear" value="<%= employmentInfo.getEmploymentPeriodStart().substring(0,4) %>" onChange="checkNumber(this)" class="timeAria" disabled="disabled">年
				<input type="text" id="changeStartMonth" name="startMonth" value="<%= employmentInfo.getEmploymentPeriodStart().substring(4,6) %>" onChange="checkNumber(this)" class="dayAria" disabled="disabled">月
				<input type="text" id="changeStartDay" name="startDay" value="<%= employmentInfo.getEmploymentPeriodStart().substring(6,8) %>" onChange="checkNumber(this)" class="dayAria" disabled="disabled">日 から
				<input type="text" id="changeEndYear" name="endYear" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(0,4) %>" onChange="checkNumber(this)" class="timeAria" disabled="disabled">年
				<input type="text" id="changeEndMonth" name="endMonth" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(4,6) %>" onChange="checkNumber(this)" class="dayAria" disabled="disabled">月
				<input type="text" id="changeEndDay" name="endDay" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(6,8) %>" onChange="checkNumber(this)" class="dayAria" disabled="disabled">日
			</td>
			<td><input type="text" id="lowerLimit" name="lowerLimit" value="<%= employmentInfo.getLowerLimit() %>" onChange="checkNumber(this)" class="timeAria" disabled="disabled">h〜<input type="text" id="upperLimit" name="upperLimit" value="<%= employmentInfo.getUpperLimit() %>" onChange="checkNumber(this)" class="timeAria" disabled="disabled">h</td>
			<td><input type="text" id="excessMoney" name="excessMoney" value="<%= employmentInfo.getExcessMoney() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
			<td><input type="text" id="eductionMoney" name="eductionMoney" value="<%= employmentInfo.getEductionMoney() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
		</tr>
		<tr><th class="w1">基本給</th><th class="w1">職務手当</th><th class="w1">通勤手当</th><th class="w2">時間外手当</th><th class="w2">その他手当</th></tr>
		<tr>
			<td><input type="text" id="basicSalary" name="basicSalary" value="<%= employmentInfo.getBasicSalary() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
			<td><input type="text" id="dutiesAllowance" name="dutiesAllowance" value="<%= employmentInfo.getDutiesAllowance() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
			<td><input type="text" id="commutingAllowance" name="commutingAllowance" value="<%= employmentInfo.getCommutingAllowance() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
			<td><input type="text" id="overtimeAllowance" name="overtimeAllowance" value="<%= employmentInfo.getOvertimeAllowance() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
			<td><input type="text" id="otherAllowance" name="otherAllowance" value="<%= employmentInfo.getOtherAllowance() %>" onChange="checkNumber(this)" class="moneyAria" disabled="disabled">円</td>
		</tr>
	</table>
	<div id="employmentInfoHiddenAria">
		<input type="hidden" id="hiddenStartYear" value="<%= employmentInfo.getEmploymentPeriodStart().substring(0,4) %>">
		<input type="hidden" id="hiddenStartMonth" value="<%= employmentInfo.getEmploymentPeriodStart().substring(4,6) %>">
		<input type="hidden" id="hiddenStartDay" value="<%= employmentInfo.getEmploymentPeriodStart().substring(6,8) %>">
		<input type="hidden" id="hiddenEndYear" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(0,4) %>">
		<input type="hidden" id="hiddenEndMonth" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(4,6) %>">
		<input type="hidden" id="hiddenEndDay" value="<%= employmentInfo.getEmploymentPeriodEnd().substring(6,8) %>">
		<input type="hidden" id="hiddenLowerLimit" value="<%= employmentInfo.getLowerLimit() %>">
		<input type="hidden" id="hiddenUpperLimit" value="<%= employmentInfo.getUpperLimit() %>">
		<input type="hidden" id="hiddenExcessMoney" value="<%= employmentInfo.getExcessMoney() %>">
		<input type="hidden" id="hiddenEductionMoney" value="<%= employmentInfo.getEductionMoney() %>">
		<input type="hidden" id="hiddenBasicSalary" value="<%= employmentInfo.getBasicSalary() %>">
		<input type="hidden" id="hiddenDutiesAllowance" value="<%= employmentInfo.getDutiesAllowance() %>">
		<input type="hidden" id="hiddenCommutingAllowance" value="<%= employmentInfo.getCommutingAllowance() %>">
		<input type="hidden" id="hiddenOvertimeAllowance" value="<%= employmentInfo.getOvertimeAllowance() %>">
		<input type="hidden" id="hiddenOtherAllowance" value="<%= employmentInfo.getOtherAllowance() %>">
	</div>
	<input type="submit" id="update" name="update" value="修正" class="button" disabled="disabled">
	<br>
	<input type="button" name="history" value="過去雇用情報" class="button" onClick="changeHistoryAria()">
	<div id="historyAria" class="noDisplay">
		<% if (employmentHistoryList != null && employmentHistoryList.size() > 0) { %>
			<% for (EmploymentInfoHistoryBean employmentHistory : employmentHistoryList) { %>
				<table class="addressListAria">
					<tr><th class="w1" colspan="2">雇用期間</th><th class="w1">基準時間</th><th class="w1">超過</th><th class="w1">控除</th></tr>
					<tr>
						<td colspan="2">
							<%= employmentHistory.getEmploymentPeriodStart().substring(0,4) + "年"
								+ employmentHistory.getEmploymentPeriodStart().substring(4,6) + "月"
								+ employmentHistory.getEmploymentPeriodStart().substring(6,8) + "日"
								+ "から"
								+ employmentHistory.getEmploymentPeriodEnd().substring(0,4) + "年"
								+ employmentHistory.getEmploymentPeriodEnd().substring(4,6) + "月"
								+ employmentHistory.getEmploymentPeriodEnd().substring(6,8) + "日" %>
						</td>
						<td><%= employmentHistory.getLowerLimit() %>h〜<%= employmentHistory.getUpperLimit() %>h</td>
						<td><%= employmentHistory.getExcessMoney() %>円</td>
						<td><%= employmentHistory.getEductionMoney() %>円</td>
					</tr>
					<tr><th class="w1">基本給</th><th class="w1">職務手当</th><th class="w1">通勤手当</th><th class="w2">時間外手当</th><th class="w2">その他手当</th></tr>
					<tr>
						<td><%= employmentHistory.getBasicSalary() %>円</td>
						<td><%= employmentHistory.getDutiesAllowance() %>円</td>
						<td><%= employmentHistory.getCommutingAllowance() %>円</td>
						<td><%= employmentHistory.getOvertimeAllowance() %>円</td>
						<td><%= employmentHistory.getOtherAllowance() %>円</td>
					</tr>
				</table>
			<% } %>
		<% } else { %>
			<hr>
			<h2>過去の雇用情報は存在しません。</h2>
			<hr>
		<% } %>
	</div>
<% } %>
</form>
<div class="center">
	<a href="/JinjiKyuyo/employee/list" class="button">戻る</a>
</div>
</body>
</html>