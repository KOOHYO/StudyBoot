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
			<div class="col-7">
				<h1>Detail Page</h1>
				<table class="table">
					<thead>
						<tr>
							<th>NUM</th>
							<th>TITLE</th>
							<th>CONTENTS</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>HIT</th>
							<th>REF</th>
							<th>STEP</th>
							<th>DEPTH</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>${dto.num}</td>
								<td>${dto.title}</td>
								<td>${dto.writer}</td>
								<td>${dto.regDate}</td>
								<td>${dto.hit}</td>
							</tr>
					</tbody>
				</table>
					
				<div>
					<a href="./add" class="btn btn-info">WRITE</a>
				</div>	
			</div>
		</div>
	</div>
</body>
</html>