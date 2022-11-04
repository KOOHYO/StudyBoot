<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-5">
				<div class="my-4 text-center">
					<h1><b>Login Page</b></h1>
				</div>
				<form action="./login" method="post">
				  <div class="mb-3">
				    <label for="ipId" class="form-label">아이디</label>
				    <input type="text" name="id" class="form-control border-primary border-opacity-25" id="ipId">
				  </div>
				  <div class="mb-3">
				    <label for="ipPw" class="form-label">비밀번호</label>
				    <input type="password" name="pw" class="form-control border-primary border-opacity-25" id="ipPw">
				  </div>
				  <div class="mb-3">
				    <label for="ipPw" class="form-label">ID기억하기</label>
				    <input type="checkbox" name="rememberId" class="form-check-input" id="ipPw">
				  </div>
				  <button type="submit" class="btn btn-outline-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>