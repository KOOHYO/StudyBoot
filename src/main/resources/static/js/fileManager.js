// 마우스 클릭 횟수 변수
let count=0;
$("#fileAdd").click(function(){
    //클릭 1번씩 증가시킴
    
    console.log("click");
    if(count<5){
        let r = '<div class="mb-3">';
        r = r+'<label for="file" class="form-label">FILE</label>';
        r = r+'<input type="file" class="form-control" name="files">';
        r = r+'<button type="button" class="btn btn-danger del">삭제</button>';
        r = r+'</div>';
        
        $("#add").append(r);

        count++;
    }else {
        alert("파일은 최대 5개만 가능합니다");
    }


});

$("#add").on("click", ".del", function(){
    console.log("Delete");
    $(this).parent().remove();
    count--;
});

// 글 수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB, HDD에 파일 삭제
    let check = confirm("삭제 됩니다.. 복구 불가");

    if(check){
        // POST
        // /qna/fileDelete
        // 파라미터 fileNum
        let fileNum = $(this).attr("data-file-num");
        console.log("fileNum : ", fileNum);
        // 여기서 this는 button이다
        console.log("Befor Result This : ", $(this));
        const btn = $(this);
        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("result : ", result);
                //여기서 this는 XhttpRequest 객체이다
                console.log("After Result This : ", $(this));
                $(btn).parent().remove.();
            },
            error:function(){
                console.log("Error 발생");
            }
        });
    }
});
