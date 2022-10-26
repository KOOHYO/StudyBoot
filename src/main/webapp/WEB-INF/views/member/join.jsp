<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer src="/js/memberJoin.js"></script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-7">
				<div class="my-4 text-center">
					<h1><b>Join Page</b></h1>
				</div>
				<%-- 회원 회원가입 form --%>
				<form action="./join" method="post" id="joinForm">
					<div class="my-4">
						<label for="ipId" class="form-label"><b>아이디</b></label>
						<input type="text" name="id" class="form-control border-primary border-opacity-25" id="ipId" placeholder="아이디를 입력해주세요">
						<div class="text-danger" id="ipIdResult"></div>
					</div>
					
					<div class="my-4">
						<label for="ipPw" class="form-label"><b>비밀번호</b></label>
						<input type="password" name="pw" class="form-control border-primary border-opacity-25" id="ipPw" placeholder="비밀번호를 입력해주세요">
						<div class="text-danger" id="ipPwResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipPwCheck" class="form-label"><b>비밀번호 재확인</b></label>
						<input type="password" class="form-control border-primary border-opacity-25" id="ipPwCheck" placeholder="비밀번호를 다시 입력해주세요">
						<div class="text-danger" id="ipPwCheckResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipName" class="form-label"><b>이름</b></label>
						<input type="text" name="name" class="form-control border-primary border-opacity-25" id="ipName" placeholder="이름을 입력해주세요">
						<div class="text-danger" id="ipNameResult"></div>
					</div>
					
					<div class="mb-4">
						<label for="ipEmail" class="form-label"><b>이메일</b></label>
						<input type="text" name="email" class="form-control border-primary border-opacity-25" id="ipEmail" placeholder="이메일을 입력해주세요">
						<div class="text-danger" id="ipEmailResult"></div>
					</div>

					<!-- 구분선 -->
					<div class="text-primary">
						<hr class="my-5">
					</div>

					<!-- 회원가입 버튼 -->
					<div class="d-grid gap-2 mt-3">
						<button type="button" id="joinBtn" class="btn btn-outline-primary"><b>✅회원가입</b></button>
					</div>
				</form>

				<!-- 약관 Test -->
				<div class="my-5">
					<div>
						ALL <input type="checkbox" id="all">
					</div>
					<div>
						동의1 <input type="checkbox" class="check">
						<div>
							약관1
						</div>
					</div>
					<div>
						동의2 <input type="checkbox" class="check">
						<div>
							약관2
						</div>
					</div>
					<div>
						동의3 <input type="checkbox" class="check">
						<div>
							약관3
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>