<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
</head>
<body>
	<h1>Index Page</h1>
	<img src="./images/cat.jpg">
	<a href="./qna/list">QNA</a>
	<div>
		<img src="/file/qna/cat.jpg">
		<a href="/fileDown/qna?fileNum=17">DOWN</a>
	</div>
</body>
</html>