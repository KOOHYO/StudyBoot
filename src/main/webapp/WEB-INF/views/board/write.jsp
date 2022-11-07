<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<c:import url="../temp/summer.jsp"></c:import>
<script defer src="/js/fileManager.js"></script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-7">
				<h1>Write Page</h1>
				<form:form action="./add" method="post" modelAttribute="qnaVO" enctype="multipart/form-data">
					<sec:csrfInput/>
					<div class="mb-3">
					  <label for="title" class="form-label">TITLE</label>
					  <form:input path="title" id="ipTitle" cssClass="form-control"/>
					  <form:errors path="title"></form:errors>
					  <div id="titleResult"></div>
					</div>

					<div class="mb-3">
					  <label for="writer" class="form-label">WRITER</label>
					  <form:input path="writer" id="ipWriter" cssClass="form-control"/>
					  <form:errors path="writer"></form:errors>
					  <div id="writerResult"></div>
					</div>

					<div class="mb-3">
					  <label for="contents" class="form-label">CONTENTS</label>
					  <form:textarea path="contents" id="ttContents" cssClass="form-control"/>
					  <form:errors path="contents"></form:errors>
					  <div id="contentsResult"></div>
					</div>

					<!-- <div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files">
					</div>
					<div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files">
					</div> -->

					<div class="mb-3" id="add">

					</div>
					<div class="mb-3">
						<button type="button" id="fileAdd" class="btn btn-outline-primary">FileAdd</button>
					</div>

					<div>
						<button type="submit" class="btn btn-info">WRITE</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>	
	
<script type="text/javascript">
	$('#ttContents').summernote({
        tabsize: 4,
        height: 250
	});
</script>
</body>
</html>