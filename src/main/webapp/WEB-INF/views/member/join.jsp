<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<!-- <script defer src="/js/memberJoin.js"></script> -->
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-7">
				<div class="my-4 text-center">
					<h1><b>Join Page</b></h1>
				</div>
				<%-- 회원 회원가입 form --%>
				<form:form modelAttribute="memberVO" method="post">
					<div class="my-4">
						<label for="ipId" class="form-label"><b>아이디</b></label>
						<form:input id="ipId" path="id" cssClass="form-control border-primary border-opacity-25"/>
						<form:errors path="id"></form:errors>
						<div class="text-danger" id="ipIdResult"></div>
					</div>
					
					<div class="my-4">
						<label for="ipPw" class="form-label"><b>비밀번호</b></label>
						<form:password path="pw" cssClass="form-control border-primary border-opacity-25" id="ipPw"/>
						<form:errors path="pw"></form:errors>
						<div class="text-danger" id="ipPwResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipPwCheck" class="form-label"><b>비밀번호 재확인</b></label>
						<form:password path="pwCheck" cssClass="form-control border-primary border-opacity-25" id="ipPwCheck"/>
						<form:errors path="pwCheck"></form:errors>
						<div class="text-danger" id="ipPwCheckResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipName" class="form-label"><b>이름</b></label>
						<form:input path="name" cssClass="form-control border-primary border-opacity-25" id="ipName"/>
						<form:errors path="name"></form:errors>
						<div class="text-danger" id="ipNameResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipEmail" class="form-label"><b>이메일</b></label>
						<form:input path="email" cssClass="form-control border-primary border-opacity-25" id="ipEmail"/>
						<form:errors path="email"></form:errors>
						<div class="text-danger" id="ipEmailResult"></div>
					</div>
					<div class="mb-4">
						<label for="ipAge" class="form-label"><b>나이</b></label>
						<form:input path="age" cssClass="form-control border-primary border-opacity-25" id="ipAge"/>
						<form:errors path="age"></form:errors>
						<div class="text-danger" id="ipAgeResult"></div>
					</div>
					<div class="mb-4">
						<label for="ipBirth" class="form-label"><b>생일</b></label>
						<form:input path="birth" cssClass="form-control border-primary border-opacity-25" id="ipBirth"/>
						<form:errors path="birth"></form:errors>
						<div class="text-danger" id="ipBirthResult"></div>
					</div>

					<!-- 구분선 -->
					<div class="text-primary">
						<hr class="my-5">
					</div>

					<!-- 회원가입 버튼 -->
					<div class="d-grid gap-2 mt-3">
						<button type="submit" id="joinBtn" class="btn btn-outline-primary"><b>✅회원가입</b></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>