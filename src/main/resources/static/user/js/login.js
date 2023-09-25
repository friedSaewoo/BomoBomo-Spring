$(".buttonLogin").click(function() {

    let userId = $("#userId").val();
    let userPassword = $("#userPassword").val();

    if(userId == "" || userId == undefined) {
        $('.labelId').css("display","block");
        $('.labelId').css("color","red");
        $('.labelPw').css("display","none");
        return false;
    } else if(userPassword == "" || userPassword == undefined) {
        $('.labelPw').css("display","block");
        $('.labelPw').css("color","red");
        $('.labelId').css("display","none");
        return false;
    }
    return true;
});














