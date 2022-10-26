console.log("start");

$("#btn").click(function(){
    console.log("Click이벤트 발생");
});

// const buttons = document.getElementsByClassName("buttons");
// const buttons = document.querySelectorAll(".buttons");

$(".buttons").click(function(){
    console.log("button");
});

$("#test").on("click", "#btn2", function(){});