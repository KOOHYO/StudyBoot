// 마우스 클릭 횟수 변수
let count=0;
$("#fileAdd").click(function(){
    //클릭 1번씩 증가시킴
    count++;
    
    console.log("click");
    if(count>5){
        alert("파일은 최대 5개만 가능합니다");
        return false;
    }

    let r = '<div class="mb-3">';
    r = r+'<label for="file" class="form-label">FILE</label>';
    r = r+'<input type="file" class="form-control" name="files">';
    r = r+'<button class="btn btn-danger">삭제</button>';
    r = r+'</div>';

    $("#add").append(r);
});