// var elements = document.querySelectorAll('.post>div');
//
// elements.forEach(function(element) {
//     element.addEventListener('click', function() {
//         var url = 'admin_user_detail.html';
//         window.location.href = url;
//     });
// });

$(document).ready(function () {
    loadPage(1,getSearchVo());
});

function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();
    return {
        cate : cate,
        keyword : keyword
    };
}

function loadPage(page, searchVo) {
    $.ajax({
        url: `/admin/user/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.adminUserList);

            loadUserList(result);

        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

function loadUserList(result){
    if(result.adminUserList!=0){
        var userList = $('.user-list');
        userList.empty(); // 기존 데이터 지우기

        $.each(result.adminUserList, function (index, user) {
            var userDiv = $('<div class="post">');
            userDiv.append('<div class="user-num">' + user.userNumber + '</div>');
            userDiv.append('<div class="user-name">' + user.userName + '</div>');
            userDiv.append('<div class="user-id">' + user.userId + '</div>');
            userDiv.append('<div class="date">' + user.registerDate + '</div>');
            userDiv.append('<div class="review"></div>');
            userDiv.append('<div class="status"></div>');
            userList.append(userDiv);
        });
    }else{

    }
}








