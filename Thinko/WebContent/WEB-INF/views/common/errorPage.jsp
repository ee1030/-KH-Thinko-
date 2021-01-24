<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">${errorMsg }</h1>
	
	<div align="center">
		<button onclick="history.back();">이전 페이지로 이동</button>
		
		<button onclick="location.href='${contextPath}'">메인 화면으로 돌아가기</button>
	</div>
</body>
</html>