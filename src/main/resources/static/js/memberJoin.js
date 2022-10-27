console.log("memberJoin")

// 약관동의 아이디 all 클릭이벤트
$("#all").click(function(){

    let ch = $(this).prop("checked");
    $(".check").prop("checked", ch);

});

// 약관동의 클래스 check 클릭이벤트
$(".check").click(function(){

    // let flag=true;
    $("#all").prop("checked", true);

    $(".check").each(function(idx, item){
        let ch = $(item).prop("checked");
        if(!ch){
            // flag=false;
            $("#all").prop("checked", false);
            console.log("ch : ", ch);
            return false;
        }
    });

    // 모두 check?? 아니면 하나이상 체크x??
    // $("#all").prop("checked", flag);

});

//id, pw, pwEquals, name, email
let results = [false, false, false, false, false];

//ID 확인
$("#ipId").blur(function(){

    let id = $("#ipId").val();

    let result = nullCheck(id, $("#ipIdResult"), "아이디는");
    results[0]=result;

    // ---------- ID 중복 확인 Ajax ----------
    // 단 ID가 비어있지 않을때
    if(result){
        $.get("./idCheck?id="+id, function(data){
            console.log("Data : "+data);
            if(data=='1'){
                $("#ipId").val("");
                $("#ipIdResult").html("이미 있는 아이디 입니다");
                results[0]=false;
            }else {
                $("#ipIdResult").html("사용 가능한 아이디 입니다");
                results[0]=true;
            }
        })
    }
    // // 1. 객체 생성
    // let xhttp = new XMLHttpRequest();
    // // 2. URL 준비
    // xhttp.open("POST", "/member/idCheck");
    // // 3. header 정보
    // xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // // 4. 요청발생
    // xhttp.send("id="+id);
    // xhttp.onreadystatechange=function(){
    //     if(this.readyState==4 && this.status==200){
    //         if(this.responseText.trim()=='1'){
    //             $("#ipIdResult").html("이미 있는 아이디 입니다");
    //         }else{
    //             $("#ipIdResult").html("사용 가능한 아이디 입니다");
    //         }
    //     }
    // }
});

//PW 확인
$("#ipPw").on({
    blur: function(){
            let result = nullCheck($("#ipPw").val(), $("#ipPwResult"), "PW");
            results[1]=result;
        },
    change: function(){
        if(results[1]){
            $("#ipPwCheck").val("");
            $("#ipPwCheckResult").html("바뀐 비밀번호가 일치하지않습니다");
            results[2]=false;
        }
    }

});

//PW 같은지 확인
$("#ipPwCheck").blur(function(){
    let result = equals($("#ipPw").val(), $("#ipPwCheck").val());
    if(result){
        $("#ipPwCheckResult").html("");
    }else{
        $("#ipPwCheckResult").html("비밀번호가 일치하지않습니다");
        $("#ipPwCheck").val("");
    }
    results[2]=result;
});

//Name 확인
$("#ipName").blur(function(){
    let result = nullCheck($("#ipName").val(), $("#ipNameResult"), "이름은");
    results[3]=result;
});

//Email 확인
$("#ipEmail").blur(function(){
    let result = nullCheck($("#ipEmail").val(), $("#ipEmailResult"), "이메일은");
    results[4]=result;
});

$("#joinBtn").click(function(){

    // 최근에 나온 함수
    if(results.includes(false)){
        alert("필수를 입력해주세요");
    }else{
        alert("회원가입을 축하합니다");
        //$("#joinForm").submit();
    }

    // let c = true;
    // $.each(results, function(idx, item){
    //     if(!item){
    //         alert("필수 입력을 확인해주세요");
    //         c=false;
    //         return c;
    //     }
    // })

    // if(c){
    //     //event 강제 실행
    //     $("#joinForm").submit();
    // }
});

$("#test").click(function(){
    let id = "123";
    let name = "iu";
    $.post("test", {
    // 파라미터이름:변수명
        id:id,
        name:name
    }, function(result){
        // {키:벨류} json형식으로 옴
        console.log("Result : ", result);
        // "{키:벨류}" json 형태의 문자열이 올 수 있음
        // JSON.parse() 함수를 사용 문자열을 json객체로 만들어줌
        result = JSON.parse(result);
        console.log("NAME : ", result.name);
    });
});

$("#test2").click(function(){
    let id = "abcd";
    $.ajax({
        type:"GET",
        url:"idCheck",
        data:{
            id:id
        },
        success:function(data){
            console.log("Data : ", data);
        },
        error:function(xhr,status,error){
            console.log("Xhr : ", xhr);
            console.log("Status : ", status);
            console.log("Error : ", error);
        }
    });
});

$("#test3").click(function(){
    let id="1234";
    let name="iu";
    let ar=[1,2,3];
    $.ajax({
        type:"POST",
        url:"test",
        traditional:true, //배열을 전송할 때 사용, true
        data:{
            id:id,
            name:name,
            ar:ar
        },
        success:function(result){
            console.log("result : ", result);
        }
    });
});

let count=3;
$("#s1Add").click(function(){
    // let add = '<option class="abc" id="abc'+count+'">'+count+'</opsion>';
    // $("#s1").append(add);
    // count++;
    $("#s1Add").remove();
});

$("#s1").click(function(){
    $("#s1").empty();
});