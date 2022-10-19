<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/summer.jsp"></c:import>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-7">
				<h1>Wite Page</h1>
				<form action="add" method="post" enctype="multipart/form-data">
					<div class="mb-3">
					  <label for="titel" class="form-label">TITEL</label>
					  <input type="text" class="form-control" name="titel" id="titel" placeholder="제목을 입력하세요">
					</div>
					<div class="mb-3">
					  <label for="writer" class="form-label">WRITER</label>
					  <input type="text" class="form-control" name="writer" id="writer" placeholder="작성자를 입력하세요">
					</div>
					<div class="mb-3">
					  <label for="contents" class="form-label">CONTENTS</label>
					  <textarea class="form-control" name="contents" id="contents" rows="3"></textarea>
					</div>
					<div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files" id="file">
					</div>
					<div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files" id="file">
					</div>
					<div>
						<button class="btn btn-info">WRITE</button>
					</div>
				</form>
			</div>
		</div>
	</div>	
	
<script type="text/javascript">
	$("#contents").summernote({
        tabsize: 2,
        height: 100
	});
</script>
</body>
</html>