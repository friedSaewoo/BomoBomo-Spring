// import * as boardNotice from './module/board.js';

//page 변수를 처음부터 1로 초기화
//첫화면이 1페이지
$(document).ready(function () {
   loadPage(1,getSearchVo());

});

//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('num');
    loadPage(page, getSearchVo());




});

//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.notice-search-btn').on('click', function (){
    loadPage(1,getSearchVo());
})

//input에서 받은 결과를 넘긴다.
function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();

    return {
        cate : cate,
        keyword : keyword
    };
}


//json으로 데이터를 받아온다.
function loadPage(page, searchVo) {
    $.ajax({
        url: `/notices/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.boardNoticeList);

            //받아온 데이터를 noticeList함수에 넣어주어 화면에 뿌려준다.
            noticeList(result);


        },
        error: function (a, b, c) {
            console.error(c);
        }
    });



}



function noticeList(result) {
    let text = '';


    if(result.boardNoticeList.length != 0){
        result.boardNoticeList.forEach(r => {

            text += `
                <tr>
                    <td>${r.noticeNumber}</td>
                    <td><a href="/board/detail?noticeNumber=${r.noticeNumber}"><strong>${r.noticeTitle}</strong></a></td>
                    <td>관리자</td>
                    <td>${r.noticeRegisterDate}</td>
                    <td>${r.noticeCount}</td>
                </tr>
            `;
        });

    }else {
        text = `
        
            <tr class="non-notice-search-result">
                <td > 검색결과가 없습니다.</td>
               
            </tr>
            <tr class="non-notice-search-result backBtn">
                <td colspan="3" ><a href="/board/notice">목록으로 돌아가기</a></td >
               <td></td>
               <td></td>
               <td></td>
               <td></td>
            </tr>
        `;
    }

    $('.content').html(text);
    //동시에 페이징처리
    updatePagination(result.pageVo);
}


//페이징처리
function updatePagination(pageVo) {
    let $pagenation = $('.notice-pagenation-container ul');
    $pagenation.empty();

    if (pageVo.prev) {
        $pagenation.append(`
                <li class="page-num"><a href="#" data-num="${pageVo.startPage-1}">&lt;</a></li>
            `);
    }

    for (let page = pageVo.startPage; page <= pageVo.endPage; page++) {
        $pagenation.append(`
                    <li class="page-num "><a href="#" class="on" data-num="${page}">${page}</a></li>
                `);

    }
    if (pageVo.next) {
        $pagenation.append(`
            <li class="page-num"> <a href="#" data-num="${pageVo.endPage+1}">&gt;</a></li>
            `);
    }
}