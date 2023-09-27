let keywordTest = '';
//첫화면 로드
$(document).ready(function () {
    showServiceReviewList(1,getSearchReviewVo());

});

//이벤트 후기 게시판 이동
$('.event-review-btn').on('click', function (){
    window.location.href="/board/eventReview";
})

//검색결과 없을 시 해당 리뷰게시판 페이지로 이동
$(document).on('click', '.non-review-search-result-btn' ,function (){
    window.location.href="/board/serviceReview";
})


//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    $('.keyword').val('');

    const page = $(this).data('reviewnum');
    showServiceReviewList(page,getSearchReviewVo());

});

//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.sitter-search-btn>button' ).on('click', function (){

    keywordTest = $('.keyword').val();
    showServiceReviewList(1, getSearchReviewVo());


})



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


function loadPage(page, callback) {
    $.ajax({
        url: `/reviews/service/${page}`,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            console.log(result.pageReviewVo);
            console.log(result.serviceReviewList);

            //받아온 데이터를 noticeList함수에 넣어주어 화면에 뿌려준다.
            noticeList(result);


        },
        error: function (a, b, c) {
            console.error(c);
        }
    });
}

function showServiceReviewList(page, searchReviewVo){

    $.ajax({

        url:`/reviews/service/${page}`,
        type:'get',
        data: searchReviewVo,
        dataType:'json',
        success :function (result){
            console.log(result.pageReviewVo);
            console.log(result.serviceReviewList);

            serviceReviewList(result)


        }, error :
        function (a,b,c){
            console.error(c);
        }

    });
}


function serviceReviewList(result) {
    let text = '';

    if(result.serviceReviewList.length !=0){
        result.serviceReviewList.forEach(r => {

            text += `
                
                <li>
                    <a href="/board/reviewDetail?sitterBoardNumber=${r.sitterBoardNumber}">
                        <div class="review-sitter-img">
                            <img src="/common/img/보모사진1.jpg" alt="리뷰 보모사진"/>
                        </div>
                        <div class="review-sitter-content">
                            <p><strong>${r.empName}</strong></p>
                            <div class="review-score">
                                <img src="/common/img/star.png"><span> ${r.rating} / 5</span>
                            </div>
                        </div>
                        <div class="reivew-text-content">
                            <dl>
                                <dt><strong>${r.userId}</strong></dt>
                                <dd>
                                    <p>
                                        ${r.sitterBoardContent}
                                    </p>
                                </dd>
                            </dl>
                        </div>
                    </a>
                </li>
            `;
        });
        }else{
            text=`

                    <h3 class="non-review-search-result">검색 결과가 없습니다. 시터님 정보를 다시 확인해주세요.<br>
                            <button class="non-review-search-result-btn" type="button">목록으로 돌아가기</button></h3>

            `;
        }

    $('.review-ul').html(text);
    //동시에 페이징처리
    updatePagination(result.pageReviewVo);
}





//페이징처리
function updatePagination(pageReviewVo) {
    let $pagenation = $('.review-pagenation-container ul');
    $pagenation.empty();

    if (pageReviewVo.prev) {
        $pagenation.append(`
                <li class="page-num"><a href="#" data-reviewnum="${pageReviewVo.startPage-1}">&lt;</a></li>
            `);
    }



    //게시물이 1개도 존재하지 않는다면 페이징 표시 x
    //한 개라도 존재할 때 페이징 번호가 나타난다.
    if(pageReviewVo.realEnd!=0){
        for (let page = pageReviewVo.startPage; page <= pageReviewVo.endPage; page++) {
            if(page == pageReviewVo.criteria.page){
                $pagenation.append(`
                    <li class="page-num active-page"><a href="#" class="on" data-reviewnum="${page}">${page}</a></li>
                `);

            }
                else{
                $pagenation.append(`
                    <li class="page-num"><a href="#" class="on" data-reviewnum="${page}">${page}</a></li>
                `);
                }
            }
    }else{
        `<li></li>`
    }




    if (pageReviewVo.next) {
        $pagenation.append(`
            <li class="page-num"> <a href="#" data-reviewnum="${pageReviewVo.endPage+1}">&gt;</a></li>
            `);
    }
}