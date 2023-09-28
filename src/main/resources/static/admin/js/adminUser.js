// var elements = document.querySelectorAll('.post>div');
//
// elements.forEach(function(element) {
//     element.addEventListener('click', function() {
//         var url = 'admin_user_detail.html';
//         window.location.href = url;
//     });
// });

// 첫 화면
$(document).ready(function () {
    loadPage(1,getSearchVo());
});
<<<<<<< HEAD
=======

$(document).on('click', '.bt', function (e) {
    e.preventDefault();
    const page = $(this).data('num');
    $('.keyword').val('');
    loadPage(page, getSearchVo());
});

$('.submit').on('click', function (){
    loadPage(1,getSearchVo());
})



>>>>>>> f52808a80e441a9c299c90736e4620aefdbfa187
function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();
    return {
        cate : cate,
        keyword : keyword
    };
}
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('num');
    loadPage(page, getSearchVo());
});
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
        var userList = $('.user-list');
        userList.empty(); // 기존 데이터 지우기
    }
    pagination(result.pageVo);
}

function pagination(pageVo) {
    let $pagenation = $('.pagination-container');
    $pagenation.empty();

    // <a href="#" class="bt prev">&lt;</a>
    // <a href="#" class="bt num on">1</a>
    // <a href="#" class="bt num">2</a>
    // <a href="#" class="bt num">3</a>
    // <a href="#" class="bt num">4</a>
    // <a href="#" class="bt num">5</a>
    // <a href="#" class="bt next">&gt;</a>

    if (pageVo.prev) {
        $pagenation.append(`
                    <a href="#" data-num="${pageVo.start()-1}" class="bt prev">&lt;</a>
<!--                <li class="page-num"><a href="#" data-num="${pageVo.startPage-1}">&lt;</a></li>-->
            `);
    }

    // 게시물이 1개도 존재하지 않는다면 페이징 표시 x
    // 한 개라도 존재할 때 페이징 번호가 나타난다.
    if(pageVo.realEnd!=0){
        for (let page = pageVo.startPage; page <= pageVo.endPage; page++) {
            if(page == pageVo.criteria.page){
                $pagenation.append(`
                    <a href="#" class="bt num on" data-num="${page}">${page}</a>
<!--                    <a href="#" class="on" data-num="${page}">${page}</a>-->
                `
                );

            }else {
                $pagenation.append(`
                    <a href="#" class="bt num" data-num="${page}">${page}</a>
<!--                    <a href="#" class="on" data-num="${page}">${page}</a>-->
                `
                );
            }

        }
    }else{

    }




    if (pageVo.next) {
        $pagenation.append(`
                <a href="#" data-num="${pageVo.endPage+1}"  class="bt next">&gt;</a>
<!--            <li class="page-num"> <a href="#" data-num="${pageVo.endPage+1}">&gt;</a></li>-->
            `);
    }
}






