//비로그인 상태일 때 로그인 페이지 이동
$('.login-btn').on('click', function (){
    window.location.href="/user/login";
})

//로그인 상태일 때 마이 페이지로 이동
$('.myPage-btn').on('click', function (){
    window.location.href="/mypage/main";
})

//로그인 여부 상관 없이 홈으로 이동
$('.index-btn').on('click', function (){
    window.location.href="/common/index";
})