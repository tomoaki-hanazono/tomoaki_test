<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%  List<String> messageList = (List<String>)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addressbook/registJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<meta charset="UTF-8">
<title>住所録登録</title>
</head>
<body>
<% if (messageList != null && messageList.size() > 0) { %>
<% for (String message : messageList) { %>
<p class="errMessage"><%= message %></p>
<% } %>
<hr>
<% } %>
<form method="post" action="/JinjiKyuyo/addressbook/regist" >
<table class="addressInputAria">
	<tr id="nameAria">
		<th>氏名</th>
		<td>
			<input type="text" name="familyName" class="nameAria" placeholder="姓">
			<input type="text" name="firstName" class="nameAria" placeholder="名">
		</td>
	</tr>
	<tr id="kanaAria">
		<th>フリガナ</th>
		<td>
			<input type="text" name="kana1" class="nameAria" placeholder="セイ">
			<input type="text" name="kana2" class="nameAria" placeholder="メイ">
		</td>
	</tr>
	<tr id="birthdayAria">
		<th>生年月日</th>
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
			<select name="day" id="day">
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
	<tr id="zipCodeAria">
		<th>郵便番号</th>
		<td><input type="text" name="zipCode" class="numberAria" maxlength="7" onChange="checkNum(this)"></td>
	</tr>
	<tr id="address1Aria">
		<th>住所1</th>
		<td><input type="text" name="address1" placeholder="都道府県・市区町村・町名"></td>
	</tr>
	<tr id="address2Aria">
		<th>住所2</th>
		<td><input type="text" name="address2" placeholder="番地・建物名"></td>
	</tr>
	<tr id="phoneNumberAria">
		<th>固定電話番号</th>
		<td><input type="text" name="phoneNumber" class="numberAria" maxlength="10" onChange="checkNum(this)"></td>
	</tr>
	<tr id="mobileNumberAria">
		<th>携帯電話番号</th>
		<td><input type="text" name="mobileNumber" class="numberAria" maxlength="11" onChange="checkNum(this)"></td>
	</tr>
	<tr id="mailAddressAria">
		<th>メールアドレス</th>
		<td><input type="text" name="mailAddress"></td>
	</tr>
	<tr id="companyAria" class="noDisplay">
		<th>会社</th>
		<td><input type="text" name="company"></td>
	</tr>
	<tr id="departmentAria" class="noDisplay">
		<th>部署</th>
		<td><input type="text" class="noDisplay"></td>
	</tr>
	<tr id="positionAria" class="noDisplay">
		<th>役職</th>
		<td><input type="text" class="noDisplay"></td>
	</tr>
	<tr id="keishouAria" class="noDisplay">
		<th>敬称</th>
		<td><input type="text" name="keishou"></td>
	</tr>
	<tr id="remarksAria" class="noDisplay">
		<th>備考</th>
		<td><input type="text" name="remarks"></td>
	</tr>
</table>
<div class="center">
<input type="submit" name="regist" value="登録" class="button">
<a href="/JinjiKyuyo" class="button">戻る</a>
</div>
</form>
</body>
</html>