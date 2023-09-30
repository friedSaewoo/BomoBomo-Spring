$(".buttonLogin").click(function() {

    let userId = $("#userId").val();
    let userPassword = $("#userPassword").val();

    if(userId == "" || userId == undefined) {
        $('.labelId').css("display","block");
        $('.labelPw').css("display","none");
        $('.idCheck').css("display","none");
        return false;
    } else if(userPassword == "" || userPassword == undefined) {
        $('.labelPw').css("display","block");
        $('.labelId').css("display","none");
        $('.idCheck').css("display","none");
        return false;
    }
    return true;
});














