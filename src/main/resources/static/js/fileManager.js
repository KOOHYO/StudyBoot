// 마우스 클릭 횟수 변수
let count=0;

function setCount(c){
    if(c<1 || c>5){
        c=1;
    }
    count=c;
}

// 
let flag=true;
$("#fileAdd").click(function(){

    if(flag){
        let size = $("#add").attr("data-file-size");
        
        // size가 없다면 0으로 초기화해줌
        if(size==undefined){
            size=0;
        }
        count=size;
        flag=false;
    }

    if(count<5){
        let r = '<div class="mb-3">';
        r = r+'<label for="file" class="form-label">FILE</label>';
        r = r+'<input type="file" class="form-control" name="files">';
        r = r+'<button type="button" class="btn btn-danger del">삭제</button>';
        r = r+'</div>';
        
        $("#add").append(r);
        
        //클릭 1번씩 증가시킴
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

    if(flag){
        let size = $("#add").attr("data-file-size");
        count=size;
        flag=false;
    }

    // confirm에 확인을 눌렀을때
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
                // 여기서 this는 XhttpRequest 객체이다
                console.log("After Result This : ", $(this));
                $(btn).parent().remove();
                // 
                count--;
            },
            error:function(){
                console.log("Error 발생");
            }
        });
    }
});
