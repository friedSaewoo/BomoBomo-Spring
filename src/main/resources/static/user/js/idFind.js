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

$(".joinBtn").click(function() {

    $(".idFindOk").css("display", "block");
})



//아이디 중복확인
function checkId(){


    $('#userName').change(function() {


    var userName = $('#userName').val(); //id값이 "id"인 입력란의 값을 저장

    $.ajax({
        url:'/user/nameCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{userName:userName},
        success:function(idCk){ //컨트롤러에서 넘어온 cnt값을 받는다

            if(idCk == 0){ //cnt가 1이 아니면(=0일 경우) -> 등록이 안 된 이름
                $('.id_already').css("display","block");
                $('.idOk').css("display", "none");
            } else { // cnt가 1일 경우 -> 등록되어 있는 이름
                $('.idOk').css("display","block");
                $('.id_already').css("display", "none");
                // $('#userId').val('');
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });

    });

};


function verifiedCode() {

    let userName = $('#userName').val();
    let userEmail = $('#userEmail').val();

    alert("이름 : " + userName + "    이메일 : " + userEmail);

    if(userName == "") {
        Swal.fire({
            icon: 'error',
            title: '이름을 기입해 주세요.',
            text: '',
          });
        return;
    }

    if($('.idOk').css('display') == 'block'){
        Swal.fire({
            icon: 'error',
            title: '가입되지 않은 이름입니다.',
            text: '이름을 다시 입력해 주세요.',
          });
        return;
    }


    if(!userEmail.includes('@')) {
        Swal.fire({
            icon: 'error',
            title: '이메일 형식이 아닙니다.',
            text: '올바른 이메일을 입력해 주세요.',
          });
        return;
    }





    if(userEmail && userName) {
        Swal.fire({
            icon: 'success',
            title: '이메일이 송신되었습니다.',
            text: '인증번호를 입력해주세요.',
          });

        $('#verified').attr("readonly", false);
    }

    // $.ajax({
    //     url:'/user/idCheck', //Controller에서 요청 받을 주소
    //     type:'post', //POST 방식으로 전달
    //     data:{userId:userId},
    //     success:function(idCk){ //컨트롤러에서 넘어온 cnt값을 받는다
    //
    //         if(idCk == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
    //             $('.idOk').css("display","block");
    //             $('.id_already').css("display", "none");
    //         } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
    //             $('.id_already').css("display","block");
    //             $('.idOk').css("display", "none");
    //             // $('#userId').val('');
    //         }
    //     },
    //     error:function(){
    //         alert("에러입니다");
    //     }
    // });

}