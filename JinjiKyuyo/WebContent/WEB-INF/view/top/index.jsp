<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<meta charset="UTF-8">
<title>人事給与システム</title>
</head>
<body>
<div class="center">
	<a href="./addressbook/regist" class="button">住所録登録</a>
	<br>
	<a href="./addressbook/list" class="button">住所録一覧</a>
	<br>
	<a href="./employmentInfo/employeeList" class="button">雇用情報</a>
	<br>
	<a href="./operatingTime/employeeList" class="button">稼働時間入力</a>
	<br>
	<a href="./salaryInfo/employeeList" class="button">給与情報</a>
	<br>
	<a href="./maintenance/changeRate" class="button">保険料率変更</a>
	<br>
</div>
</body>
</html>