
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
				<h1>List Page</h1>
				<table class="table">
					<thead>
						<tr>
							<th>NUM</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>DATE</th>
							<th>HIT</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="dto">
							<tr>
								<td>${dto.num}</td>
								<td><a href="detail?num=${dto.num}">${dto.title}</a></td>
								<td>${dto.writer}</td>
								<td>${dto.regDate}</td>
								<td>${dto.hit}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					
				<div>
					<a href="./add" class="btn btn-info">WRITE</a>
				</div>	
			</div>
		</div>
	</div>
	
<script type="text/javascript">
	let result = '${param.result}';
	if(result != ""){
		if(result == '1'){
			alert('등록성공');
		}else {
			alert('등록실패');
		}
	}
</script>
</body>
</html>