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
	<h2>人事給与システム</h2>
	<hr>
	<a href="./employee/list" class="button">社員一覧</a>
	<br>
	<a href="./kinmuJisseki/input" class="button">勤務実績票取込</a>
	<br>
	<a href="./maintenance/changeRate" class="button">保険料率変更</a>
	<br>
	<a href="./monthryRemuneration/get" class="button">標準月額設定</a>
	<br>
</div>
</body>
</html>