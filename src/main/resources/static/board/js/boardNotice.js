import * as boardNotify from './module/boardNotify.js';
import * as paging from './module/boardPagination.js';


let keywordTest = '';

//input에서 받은 결과를 넘긴다.
function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();

    console.log(keyword);

    return {
        cate : cate,
        keyword : keywordTest
    };
}

//page 변수를 처음부터 1로 초기화
//첫화면이 1페이지
$(document).ready(function () {
    boardNotify.loadPage(1, getSearchVo(), noticeList);

});

//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    $('.keyword').val('');
    const page = $(this).data('pagenum');
    boardNotify.loadPage(page, getSearchVo(), noticeList);


});


//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.notice-search-btn').on('click', function (){
    keywordTest = $('.keyword').val();
    boardNotify.loadPage(1, getSearchVo(), noticeList);
})


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
    let $pagenation = $('.notice-pagenation-container ul');

    paging.updatePagination(result.pageVo, $pagenation);
}






