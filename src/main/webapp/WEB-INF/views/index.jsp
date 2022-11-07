<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
			<h1><spring:message code="hi"></spring:message> </h1>
		</div>
		<img src="./images/cat.jpg">
		<div class="row my-3">
			<h3><a href="./qna/list">QNA</a></h3>

			<!-- 로그인 성공 -->
			<!-- 인증이 되었습니까? 인증이 되었으면 보여주세요 -->
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="Principal" var="member"/>
				<h3><spring:message code="welcome" arguments="${member.name}"></spring:message> </h3>
				<h3><spring:message code="welcome2" arguments="${member.id},${member.name}" argumentSeparator=","></spring:message> </h3>
				<a href="./member/mypage">myPage</a>
				<a href="#" id="logout">Logout</a>
				<form action="./member/logout" id="outForm">
					<sec:csrfInput/>
					<button>Logout</button>
				</form>
				<%-- <h3>🎉${member.name}님 환영합니다~🎉</h3> --%>
				<h3><a href="./member/logout">로그아웃</a>	</h3>	
				
				<!-- SecurityConfig에 antMatchers 처럼 설정 -->
				<sec:authorize url="/admin">
					<a href="/admin">GO Admin</a>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('MANAGER', 'MEMBER')">
					<a href="/manager">GO Manager</a>
				</sec:authorize>
			</sec:authorize>

			<!-- 로그인 전 -->
			<sec:authorize access="!isAuthenticated()">
				<h3><a href="./member/join">회원가입</a></h3>
				<h3><a href="/oauth2/authorization/kakao">카카오로그인</a></h3>
				<h3><a href="./member/login">로그인</a></h3>
			</sec:authorize>


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
	<script type="text/javascript">
		$("#logout").click(function() {
			$("#outForm").submit();
		})
	</script>
</body>
</html>