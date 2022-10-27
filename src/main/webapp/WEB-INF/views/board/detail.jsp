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
								<td>${vo.num}</td>
								<td>${vo.title}</td>
								<td>${vo.contents}</td>
								<td>${vo.writer}</td>
								<td>${vo.regDate}</td>
								<td>${vo.hit}</td>
								<td>${vo.ref}</td>
								<td>${vo.step}</td>
								<td>${vo.depth}</td>
							</tr>
					</tbody>
				</table>
				<div class="row">
					<c:forEach items="${vo.qnaFileVOs}" var="fileVO">
						<p>
							<img alt="" src="/file/qna/${fileVO.fileName}">
							<%-- <a href="/file/qna/${fileVO.fileName}">${fileVO.oriName}</a> --%>
							<a href="/fileDown/qna?fileNum=${fileVO.fileNum}">${fileVO.oriName}</a>
						</p>
					</c:forEach>
				</div>
				
				<div>
					<a href="update?num=${vo.num}" class="btn btn-info">UPDATE</a>
				</div>	
				
				<div>
					<a href="./add" class="btn btn-info">WRITE</a>
				</div>	
			</div>
		</div>
	</div>
</body>
</html>