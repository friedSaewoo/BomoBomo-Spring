$(".pwFindBtn").click(function() {

    // let user = $("#id").val();
    let userName = $("#userName").val();
    let userEmail = $("#userEmail").val();

    if(id && userName && userEmail ) {

    } else {
        alert("모든 정보를 기입 해주세요.");
        return false;
    }

    if(userEmail.includes('@')) {
    } else {
        $('.labelEm').css('display','block')
        $('.labelPh').css('display','none')
    }


    alert("임시 패스워드가 이메일로 송신되었습니다.");
    // location.href = "/user/html/login.html";
});


$(".verifiedCh").click(function() {
    let userEmail = $("#userEmail").val();

    if(userEmail.includes('@')) {
        alert("인증번호가 송신되었습니다.");
        return false;
    } else {
        alert("올바른 이메일을 입력하세요.");
        return false;

    }

})

$(".joinBtn").click(function() {

    $(".idFindOk").css("display", "block");
})



