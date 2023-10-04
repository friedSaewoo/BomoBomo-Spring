import * as boardNotify from './module/boardNotify.js';


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
    boardNotify.loadPage(1, getSearchVo());

});

//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    $('.keyword').val('');
    const page = $(this).data('num');
    boardNotify.loadPage(page, getSearchVo());


});


//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.notice-search-btn').on('click', function (){
    keywordTest = $('.keyword').val();
    boardNotify.loadPage(1, getSearchVo());
})








