

// 페이징 처리

// 검색어 저장
let searchKeyword='';

// 첫 화면
$(document).ready(function () {
    loadPage(1,getSearchVo());
});

// 페이지 숫자 클릭
$(document).on('click', '.bt', function (e) {
    e.preventDefault();
    const page = $(this).data('num');
    $('.keyword').val('');
    loadPage(page, getSearchVo());
});

// 검색 버튼 클릭
$('.submit').on('click', function (){
    searchKeyword = $('.keyword').val();
    loadPage(1,getSearchVo());
})

// input 데이터
function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();
    return {
        cate : cate,
        keyword : searchKeyword
    };
}
// ajax
function loadPage(page, searchVo) {
    $.ajax({
        url: `/admin/rest/notice/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.adminNoticeList);

            loadNoticeList(result);

        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}
// ajax로 가져온 데이터를 사용해 페이징처리
function loadNoticeList(result){

    if(result.adminNoticecList!=0){
        let noticeList = $('.announce-list');
        noticeList.empty();

        $.each(result.adminNoticeList, function (index, notice) {
            let noticeDiv = $('<div class="post">');
            noticeDiv.append('<div class="announce-num">' + notice.noticeNumber + '</div>');
            noticeDiv.append('<div class="announce-title">' + notice.noticeTitle + '</div>');
            noticeDiv.append('<div class="date">' + notice.noticeRegisterDate + '</div>');
            noticeDiv.append('<div class="count">' + notice.noticeCount + '</div>');
            noticeList.append(noticeDiv);

            noticeDiv.on('click', function() {
                let url = `/admin/notice/detail?noticeNumber=${notice.noticeNumber}`;
                window.location.href = url;
            });
        });
    }else{
        let noticeList = $('.announce-list');
        console.log("데이터 없음!!");
        noticeList.empty(); // 기존 데이터 지우기
    }
    pagination(result.pageVo);
}
// 각 페이징 버튼 처리
function pagination(pageVo) {
    let $pagenation = $('.pagination-container');
    $pagenation.empty();

    if (pageVo.prev) {
        $pagenation.append(`
                    <a href="#" data-num="${pageVo.startPage-1}" class="bt prev">&lt;</a>
            `);
    }
    if(pageVo.realEnd!=0){
        for (let page = pageVo.startPage; page <= pageVo.endPage; page++) {
            if(page == pageVo.criteria.page){
                $pagenation.append(`
                    <a href="#" class="bt num on" data-num="${page}">${page}</a>
                `
                );

            }else {
                $pagenation.append(`
                    <a href="#" class="bt num" data-num="${page}">${page}</a>
                `
                );
            }

        }
    }else{

    }
    if (pageVo.next) {
        $pagenation.append(`
                <a href="#" data-num="${pageVo.endPage+1}"  class="bt next">&gt;</a>
            `);
    }
}

// 인풋박스에 엔터키를 누르면 search버튼 클릭 처리
function inputEnter(event) {
    if (event.key === "Enter") {
        document.getElementById("submit").click();
    }
}
document.getElementById("search").addEventListener("keyup", inputEnter);

let noticeRegist = $('.regist-btn');
noticeRegist.on('click', function() {
    let url = `/admin/noticeRegist`;
    window.location.href = url;
});
