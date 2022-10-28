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
	<div class="container">
		<div class="my-5">
			<h1><b>Index Page</b></h1>
		</div>
		<img src="./images/cat.jpg">
		<div class="row my-3">
			<h3><a href="./qna/list">QNA</a></h3>
			<c:choose>
				<c:when test="${not empty member}">
					<h3>🎉${member.name}님 환영합니다~🎉</h3>
					<h3><a href="./member/logout">로그아웃</a>	</h3>	
				</c:when>
				<c:when test="${empty member}">
					<h3><a href="./member/join">회원가입</a></h3>
					<h3><a href="./member/login">로그인</a></h3>
				</c:when>
			</c:choose>
		</div>
		
		<!-- 구분선 -->
		<div class="text-primary">
			<hr class="my-5">
		</div>
		
		<div class="row my-3">
			<img src="/file/qna/cat.jpg">
			<img src="/file/notice/cat3.jpg">
			<a href="/fileDown/qna?fileNum=17">QNADOWN</a>
			<a href="/fileDown/notice?fileNum=17">NOTICEDOWN</a>
		</div>
	</div>
</body>
</html>