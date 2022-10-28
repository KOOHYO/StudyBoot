<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h1>Write Update Page</h1>
				<form action="add" method="post" enctype="multipart/form-data">
					<div class="mb-3">
					  <input type="hidden" class="form-control" value="${vo.num}" name="num" id="num">
					</div>
				
					<div class="mb-3">
					  <label for="title" class="form-label">TITLE</label>
					  <input type="text" class="form-control" value="${vo.title}" name="title" id="title" placeholder="제목을 입력하세요">
					</div>

					<div class="mb-3">
					  <label for="writer" class="form-label">WRITER</label>
					  <input type="text" class="form-control" value="${vo.writer}" name="writer" id="writer" placeholder="작성자를 입력하세요">
					</div>

					<div class="mb-3">
					  <label for="contents" class="form-label">CONTENTS</label>
					  <textarea class="form-control" name="contents" id="contents"></textarea>
					</div>

					<!-- <div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files">
					</div>
					<div class="mb-3">
					  <label for="file" class="form-label">FILE</label>
					  <input type="file" class="form-control" name="files">
					</div> -->

					<div class="mb-3" id="add" data-file-size="${vo.qnaFileVOs.size()}">
						<c:forEach items="${vo.qnaFileVOs}" var="fileVO">
							<!-- 여기에 img는 button의 형제 -->
							<!-- 그래서 부모는 id="add"인 div임 -->
							<!-- 그래서 안에 다른 img도 없어지기 때문에 새로운 div로 감싼다 -->
							<div>
								<img src="/file/qna/${fileVO.fileName}"><button type="button" class="btn btn-danger deleteFile" data-file-num="${fileVO.fileNum}">삭제</button>
							</div>
						</c:forEach>
					</div>
					<div class="mb-3">
						<button type="button" id="fileAdd" class="btn btn-outline-primary">FileAdd</button>
					</div>

					<div>
						<button class="btn btn-info">UPDATE</button>
					</div>
				</form>
			</div>
		</div>
	</div>	
	
<script type="text/javascript">
	$('#contents').summernote({
        tabsize: 4,
        height: 250
	});
	
	$('#contents').summernote('code', '${vo.contents}')
	
</script>
</body>
</html>