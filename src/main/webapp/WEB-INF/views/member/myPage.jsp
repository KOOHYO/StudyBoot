<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<div class="container ilseok">
		<div class="row justify-content-md-center">
			<div class="col-md-8 col-lg-7 mt-5">

				<div class="mb-5 text-center">
					<h1><b>ํ๋กํ๐</b></h1>
				</div>

				<%-- ํ๋กํ ์์  form --%>
				<form action="./profile" method="post" enctype="multipart/form-data">
					
					<!-- ํ๋กํ์ฌ์ง ์นด๋ -->
					<div class="card border-success mb-3">
						<div class="row my-4">
							<img id="picture" src="http://20.249.88.100/resources/member/${member.memberFileDTO.f_name}" class="border border-success p-2 mb-2 border-opacity-50 mx-auto d-block" style=" max-width: 300px;  height: 300px;  border-radius: 75px;">
						</div>
						<div class="row">
							<div class="col-6 text-end" id="addFile">
								<!--ํ๋จ ํ์ผ ์ถ๊ฐ input๊ณผ ์ฐ๊ฒฐ๋ ๋ผ๋ฒจ-->
								<label for="file" class="file_add btn btn-outline-success btn-sm" id="file"><b>์ฌ์ง์ถ๊ฐ๐ท</b></label>
							</div>
							<div class="col-6 text-start">
								<input type="hidden" name="id" id="ipId" value="${member.id}">
								<button type="button" class="btn btn-outline-success btn-sm" id="defile"><b>์ฌ์ง์ญ์ โ</b></button>
							</div>
						</div>
						<div class="row">
							<!-- ํ์ผ ์ถ๊ฐ input -->
							<input type="file" id="file" style="display: none;">
						</div>
						<div class="card-body">
							<!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
							<ul class="list-group list-group-flush">
								<li class="list-group-item border-success text-center mb-1">
									<sec:authentication property="Principal" var="user"/>
									<label for="ipId" class="form-label card-title"><h4>${user.id}</h4></label>
									<p class="card-text"><input type="hidden" class="form-control" id="ipId" value="${member.id}"></p>
								</li>
								<li class="list-group-item border-success my-2 mt-3">
									<label for="ipNname" class="form-label card-title"><h6><b>์ด๋ฆ</b></h6></label>
									<h6><sec:authentication property="Principal.name"/></h6>
									<p class="card-text"><input type="text" name="n_name" class="form-control border-success border-opacity-25" id="ipNname" value="${member.n_name}"></p>
								</li>
							</ul>
						</div>
					</div>

					
					<!-- ๋น๋ฐ๋ฒํธ๋ณ๊ฒฝ ํ์ด์ง๋ก ๊ฐ๋ aํ๊ทธ -->
					<div class="d-grid gap-2 col-6 mx-auto my-3">
						<a href="./updatePw" class="file_add btn btn-outline-success btn-sm"><b>๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ</b></a>
					</div>
					
					<!-- ํ๋กํ์ ๋ณด ์นด๋ -->
					<div class="card border-success mb-3">
						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item border-success">
									<div class="mb-3">
										<label for="ipGender" class="form-label card-title"><h6><b>๋ฑ๊ธ</b></h6></label>
										<p class="card-text"><input type="text" name="gender" class="form-control border-success border-opacity-25" id="ipGender" value="${member.gender}"></p>
									</div>
								</li>
								<li class="list-group-item border-success">
									<div class="my-3">
										<label for="ipEmail" class="form-label card-title"><h6><b>์ด๋ฉ์ผ</b></h6></label>
										<h6><sec:authentication property="Principal.email"/></h6>
										<p class="card-text"><input type="text" name="email" class="form-control border-success border-opacity-25" id="ipEmail" value="${member.email}"></p>
									</div>
								</li>
								<li class="list-group-item border-success my-2 mt-3">
									<label for="ipPhone" class="form-label card-title"><h6><b>์ ํ๋ฒํธ</b></h6></label>
									<p class="card-text"><input type="tel" name="phone" class="form-control border-success border-opacity-25" id="ipPhone" value="${member.phone}"></p>
								</li>
							</ul>
						</div>
					</div>

					<!-- ๊ตฌ๋ถ์  -->
					<div class="text-success">
						<hr class="my-4">
					</div>

					<!-- ํ์๊ฐ์ ๋ฒํผ -->
					<div class="d-grid gap-2 mt-3">
						<button type="submit" class="btn btn-outline-success"><b>ํ๋กํ์์ โ</b></button>
					</div>

					<div class="row mt-4 text-muted">
						<div class="text-start" id="tcherProfile">
							<!-- ํ์ํํดํ์ด์ง๋ก ๊ฐ๋ aํ๊ทธ -->
							<a href="./deleteMember" class="text-decoration-none text-reset"><b>ํ์ํํด</b> ></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>