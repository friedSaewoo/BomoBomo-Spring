import * as serviceReview from './module/boardService.js';

let keywordTest = '';

//검색결과
function getSearchReviewVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();

    console.log(cate);
    console.log(keyword);

    return{
        cate : cate,
        keyword : keywordTest
    };
}

//첫화면 로드
$(document).ready(function () {
    serviceReview.showServiceReviewList(1, getSearchReviewVo());

});

//이벤트 후기 게시판 이동
$('.event-review-btn').on('click', function (){
    window.location.href="/board/eventReview";
})


//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    $('.keyword').val('');

    const page = $(this).data('reviewnum');
    serviceReview.showServiceReviewList(page, getSearchReviewVo());

});

//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.sitter-search-btn>button' ).on('click', function (){

    keywordTest = $('.keyword').val();
    serviceReview.showServiceReviewList(1, getSearchReviewVo());

})

//검색버튼 결과가 없을 시 나타나는 버튼 클락하면 페이징 1번으로 이동
$(document).on('click', '.non-review-search-result-btn', function (){
    $('.keyword').val('');
    keywordTest='';
    serviceReview.showServiceReviewList(1, getSearchReviewVo);

})








