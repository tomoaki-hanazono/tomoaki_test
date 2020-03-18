<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="jinjikyuyo.bean.EmploymentBean" %>
<%  List<EmploymentBean>employmentList=(List<EmploymentBean>)request.getAttribute("employmentList"); %>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<%  int employeeId = (int)request.getAttribute("employeeId"); %>
<%  String employeeName = (String)request.getAttribute("employeeName"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/employmentInfo/confirmJs.js"></script>
<meta charset="UTF-8">
<title>雇用情報</title>
</head>
<body>
<div id="messageAria">
	<% if (messageList != null && messageList.size() > 0) { %>
	<% for (String message : messageList) { %>
	<p class="errMessage"><%= message %></p>
	<% } %>
	<hr>
	<% } %>
</div>
<form method="post" action="/JinjiKyuyo/employmentInfo/confirm" class="center">
	<input type="hidden" name="type" value="add">
	<input type="hidden" name="employeeId" value="<%= employeeId %>">
	<input type="text" name="employeeName" value="<%= employeeName %>" readonly>
	
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
				<td><input type="text" name="basicSalary" value="0" onInput="checkNumber(this)">円</td>
			</tr>
			<tr>
				<th>職務手当</th>
				<td><input type="text" name="dutiesAllowance" value="0" onInput="checkNumber(this)">円</td>
			</tr>
			<tr>
				<th>通勤手当</th>
				<td><input type="text" name="commutingAllowance" value="0" onInput="checkNumber(this)">円</td>
			</tr>
			<tr>
				<th>時間外手当</th>
				<td><input type="text" name="overtimeAllowance" value="0" onInput="checkNumber(this)">円</td>
			</tr>
			<tr>
				<th>その他手当</th>
				<td><input type="text" name="otherAllowance" value="0" onInput="checkNumber(this)">円</td>
			</tr>
			<tr>
				<th>基準時間</th>
				<td>
					<input type="text" name="lowerLimit" value="0" onInput="checkNumber(this)">h〜<input type="text" name="upperLimit" value="0" onInput="checkNumber(this)">h<br>
					超過:<input type="text" name="excessMoney" value="0" onInput="checkNumber(this)">円<br>
					控除:<input type="text" name="eductionMoney" value="0" onInput="checkNumber(this)">円
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
	</div>
</form>
<form method="post" action="/JinjiKyuyo/employmentInfo/confirm" class="center" onsubmit="return checkDefault()">
	<input type="hidden" name="type" value="update">
	<input type="hidden" name="employeeId" value="<%= employeeId %>">
	<input type="hidden" name="employeeName" value="<%= employeeName %>">
	<% if (employmentList != null && employmentList.size() > 0) { %>
		<% for (EmploymentBean employment : employmentList) { %>
			<input type="checkbox" name="employmentId" value="<%= employment.getEmploymentId() %>" onclick="changeCheckBox(this)">修正をする
			<table class="addressListAria" id="employmentInfoAria_<%= employment.getEmploymentId() %>">
				<tr><th class="w1" colspan="2">雇用期間</th><th class="w1">基準時間</th><th class="w1">超過</th><th class="w1">控除</th></tr>
				<tr>
					<td colspan="2">
						<%= employment.getEmploymentPeriodStart().substring(0,4) + "年"
							+ employment.getEmploymentPeriodStart().substring(4,6) + "月"
							+ employment.getEmploymentPeriodStart().substring(6,8) + "日"
							+ "から"
							+ employment.getEmploymentPeriodEnd().substring(0,4) + "年"
							+ employment.getEmploymentPeriodEnd().substring(4,6) + "月"
							+ employment.getEmploymentPeriodEnd().substring(6,8) + "日" %>
					</td>
					<td><input type="text" id="lowerLimit_<%= employment.getEmploymentId() %>" name="lowerLimit_<%= employment.getEmploymentId() %>" value="<%= employment.getLowerLimit() %>" onInput="checkNumber(this)" disabled="disabled">
						h 〜
						<input type="text" id="upperLimit_<%= employment.getEmploymentId() %>" name="upperLimit_<%= employment.getEmploymentId() %>" value="<%= employment.getUpperLimit() %>" onInput="checkNumber(this)" disabled="disabled">h</td>
					<td><input type="text" id="excessMoney_<%= employment.getEmploymentId() %>" name="excessMoney_<%= employment.getEmploymentId() %>" value="<%= employment.getExcessMoney() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
					<td><input type="text" id="eductionMoney_<%= employment.getEmploymentId() %>" name="eductionMoney_<%= employment.getEmploymentId() %>" value="<%= employment.getEductionMoney() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
				</tr>
				<tr><th class="w1">基本給</th><th class="w1">職務手当</th><th class="w1">通勤手当</th><th class="w2">時間外手当</th><th class="w2">その他手当</th></tr>
				<tr>
					<td><input type="text" id="basicSalary_<%= employment.getEmploymentId() %>" name="basicSalary_<%= employment.getEmploymentId() %>" value="<%= employment.getBasicSalary() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
					<td><input type="text" id="dutiesAllowance_<%= employment.getEmploymentId() %>" name="dutiesAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getDutiesAllowance() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
					<td><input type="text" id="commutingAllowance_<%= employment.getEmploymentId() %>" name="commutingAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getCommutingAllowance() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
					<td><input type="text" id="overtimeAllowance_<%= employment.getEmploymentId() %>" name="overtimeAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getOvertimeAllowance() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
					<td><input type="text" id="otherAllowance_<%= employment.getEmploymentId() %>" name="otherAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getOtherAllowance() %>" onInput="checkNumber(this)" disabled="disabled">円</td>
				</tr>
			</table>
			<div id="employmentInfoHiddenAria_<%= employment.getEmploymentId() %>">
				<input type="hidden" id="hiddenLowerLimit_<%= employment.getEmploymentId() %>" value="<%= employment.getLowerLimit() %>">
				<input type="hidden" id="hiddenUpperLimit_<%= employment.getEmploymentId() %>" value="<%= employment.getUpperLimit() %>">
				<input type="hidden" id="hiddenExcessMoney_<%= employment.getEmploymentId() %>" value="<%= employment.getExcessMoney() %>">
				<input type="hidden" id="hiddenEductionMoney_<%= employment.getEmploymentId() %>" value="<%= employment.getEductionMoney() %>">
				<input type="hidden" id="hiddenBasicSalary_<%= employment.getEmploymentId() %>" value="<%= employment.getBasicSalary() %>">
				<input type="hidden" id="hiddenDutiesAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getDutiesAllowance() %>">
				<input type="hidden" id="hiddenCommutingAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getCommutingAllowance() %>">
				<input type="hidden" id="hiddenOvertimeAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getOvertimeAllowance() %>">
				<input type="hidden" id="hiddenOtherAllowance_<%= employment.getEmploymentId() %>" value="<%= employment.getOtherAllowance() %>">
			</div>
		<% } %>
	<% } else { %>
		<hr>
		<h2 style="color:red">雇用情報が存在しません。</h2>
		<hr>
	<% } %>
	<input type="submit" id="update" name="update" value="修正" class="button" disabled="disabled">
	<a href="/JinjiKyuyo/employmentInfo/employeeList" class="button">戻る</a>
</form>

</body>
</html>