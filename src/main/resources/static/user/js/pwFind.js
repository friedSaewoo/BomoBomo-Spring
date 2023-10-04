//아이디 중복확인
function checkId(){

    $('#userId').change(function() {

    var userId = $('#userId').val(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url:'/user/idCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{userId:userId},
        success:function(idCk){ //컨트롤러에서 넘어온 cnt값을 받는다

            if(idCk == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                $('.id_already').css("display","block");
                $('.idOk').css("display", "none");
            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
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
//이름 중복확인
function checkName() {

    $('#userName').change(function () {
        var userName = $('#userName').val(); //name값이 "name"인 입력란의 값을 저장

        $.ajax({
            url: '/user/nameCheck', //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: {userName: userName},
            success: function (nameCk) { //컨트롤러에서 넘어온 cnt값을 받는다

                if (nameCk == 0) { //cnt가 1이 아니면(=0일 경우) -> 등록이 안 된 이름
                    $('.nameAlready').css("display", "block");
                    $('.nameOk').css("display", "none");
                } else { // cnt가 1일 경우 -> 등록되어 있는 이름
                    $('.nameOk').css("display", "block");
                    $('.nameAlready').css("display", "none");
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });

    });

};


function verifiedCode() {
    let userId = $('#userId').val();
    let userName = $('#userName').val();
    let userEmail = $('#userEmail').val();

    if (userId == "") {
        Swal.fire({
            icon: 'error',
            title: '아이디를 기입하세요.',
            text: '',
        });
        return;
    }
    if ($('.id_already').css('display') == 'block') {
        Swal.fire({
            icon: 'error',
            title: '가입되지 않은 이름입니다.',
            text: '이름을 다시 입력해 주세요.',
        });
        return;
    }

    if (userName == "") {
        Swal.fire({
            icon: 'error',
            title: '이름을 기입하세요.',
            text: '',
        });
        return;
    }

    if ($('.nameAlready').css('display') == 'block') {
        Swal.fire({
            icon: 'error',
            title: '가입되지 않은 이름입니다.',
            text: '이름을 다시 입력해 주세요.',
        });
        return;
    }

    if(userEmail == "") {
        Swal.fire({
            icon: 'error',
            title: '이메일을 기입하세요',
            text: '올바른 이메일을 입력해 주세요.',
        });
        return;

    }
    if (!userEmail.includes('@')) {
        Swal.fire({
            icon: 'error',
            title: '이메일 형식이 아닙니다.',
            text: '올바른 이메일을 입력해 주세요.',
        });
        return;
    }

    if (userEmail && userName && userId) {
        Swal.fire({
            icon: 'success',
            title: '이메일이 송신되었습니다.',
            text: '인증번호를 받지 못하셨다면 이메일을 다시 확인해주세요.',
        });

    }


    //이메일 인증번호

    $.ajax({
        url:'/mail/mail', //Controller에서 요청 받을 주소
        type:"post",
        dataType:"json",
        data:{"mail" : $("#userEmail").val()},
        success: function(data){
            // alert("인증번호 발송");
            $('#verified').attr("readonly", false);
            $("#Confirm").attr("value",data);
        }
    });


}

function confirmNumber(){

    let number1 = $("#verified").val();
    let number2 = $("#Confirm").val();
    let userEmail = $("#userEmail").val();
    let userName = $("#userName").val();
    let userId = $("#userId").val();


    if(userName && userEmail && userId) {

        $.ajax({
            url: '/user/pwEmailCheck', //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: {userEmail: userEmail, userName: userName, userId: userId},
            success: function (emailCk) { //컨트롤러에서 넘어온 cnt값을 받는다
                if (emailCk == 0) { //emailCk 0일 경우 확인한 정보가 존지해지 않음
                    Swal.fire({
                        icon: 'error',
                        title: '잘못된 정보가 기입되었습니다.',
                        text: 'ID, 이름, 이메일을 다시 확인해주세요.',

                    });
                } else {

                    if(number1 == number2 && number1){


                        $.ajax({
                            url:'/mail/pwFindOk', //Controller에서 요청 받을 주소
                            type:"post",
                            dataType:"json",
                            data:{"userEmail" : userEmail, userId: userId},
                            success: function(data){
                                // alert("인증번호 발송");
                                $('#verified').attr("readonly", false);
                                $("#Confirm").attr("value",data);
                                location.href="/user/login";
                            }
                        });


                        // $('.form').submit();
                        alert("임시패스워드를 발송했습니다.");
                    }else{
                        Swal.fire({
                            icon: 'error',
                            title: '인증번호를 다시 확인해주세요.',
                            text: '올바른 인증번호를 입력하세요.',
                        });
                    }
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });


    } else {
        Swal.fire({
            icon: 'error',
            title: '정보를 입력하세요.',
            text: '아이디 혹은 이메일을 입력하세요.',
        });
    }


    // 이메일 임시패스워드



}
