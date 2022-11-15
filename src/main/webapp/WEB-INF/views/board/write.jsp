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
					<input type="hidden" name="num" value="123">
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
					  <form:textarea path="contents" id="contents" cssClass="form-control"/>
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
	$('#contents').summernote({
        tabsize: 4,
        height: 250,
		// 오버라이딩과 비슷함 -> 콘솔만찍으면 이미지가 안나옴 왜냐하면 섬머노트내에 코드가 사라지기때문
		callbacks:{
			onImageUpload:function(file){
				console.log("ImageUpload");
				console.log("file ", file);
				// ajax file server로 upload후 경로를 받아서 사용
				console.log("fileName => ", file[0].name);
				uploadFile(file);
				//const imgNode = '<img src="https://item.kakaocdn.net/do/a6e27cf6e332b269ce6d72726b3317fc9f5287469802eca457586a25a096fd31">'
				$("#contents").summernote('pasteHTML', imgNode);
			},
			// 이번에 새로 나온 이벤트
			onMediaDelete:function(file){
				console.log("Delete Media");
				console.log("DeleteFile => ", file);
				deleteFile(file);
			},
			onChange: function(contents, $editable) {
				console.log('onChange:', contents, $editable);
			}
		}
	});

	function deleteFile(file){
		console.log("SRC ", file.attr("src"));
		$.post("./summerFileDelete", {fileName:file.attr("src")}, function(result){
			console.log("result => ", result);
		})
	}

	//Ajax 함수
	function uploadFile(file){
		console.log("file", file);
		//<form>
		const formData = new FormData();
		//<input type="file">
		formData.append("files", file[0]);
		
		$.ajax({
			type:"POST",
			url:"summerFile",
			data:formData,
			//header
			cache:false,
			processData:false,
			contentType:false,
			enctype:"multpart/form-data",
			success:function(img){
				console.log("Image => ", img);
				img = '<img src="'+img+'">'
				$("#contents").summernote('pasteHTML', img, file[0].name);
			},
			error:function(){
				console.log("Image upload Fail");
			}
		});

	}
</script>
</body>
</html>