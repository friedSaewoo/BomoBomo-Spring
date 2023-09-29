let keywordVar = '';


//이벤트 후기 게시판 이동
$('.service-review-btn').on('click', function (){
    window.location.href="/board/serviceReview";
})




//이벤트 후기 게시판 첫화면
$(document).ready(function (){
    getEventReviewPage(1, getSearchVo())

})


//페이징처리
$(document).on('click', '.page-num a', function (e){
    e.preventDefault()
    $('.keyword').val('');

    const page = $(this).data('reviewnum');
    getEventReviewPage(page, getSearchVo())

})


//검색처리
$('.sitter-search-btn>button').on('click', function (){

    keywordVar = $('.keyword').val();
    getEventReviewPage(1, getSearchVo())

})


// //페이징 이동처리
// $(document).on('click','.page-num a', function (e){
//     e.preventDefault();
//     const page = $('.page-num a').data('reviewnum');
//     loadPage(page, eventReviewList)
//
// })


function getSearchVo(){

    let cate = $('.cate').val();
    let keyword = $('.keyword').val();

    console.log(cate);
    console.log(keyword);

    return{

        cate : cate,
        keyword : keywordVar

    }


}

function getEventReviewPage(page, searchReviewVo){
    $.ajax({

        url:`/reviews/eventReview/${page}`,
        type:'get',
        data:searchReviewVo,
        dataType: 'json',
        success : function (result){
            console.log(result.eventReviewList);
            console.log(result.pageEventReviewVo)


            eventReviewList(result)


        },error : function (a,b,c){
            console.error(c)
        }


    })
}


// function loadPage(page, callback){
//
//     $.ajax({
//
//         url:`/reviews/eventReview/${page}`,
//         type:'get',
//         dataType:'json',
//         success : function (result){
//
//             eventReviewList(result)
//
//             console.log(result.eventReviewList)
//         }, error : function (a,b,c){
//             console.error(c);
//         }
//     })
// }

function eventReviewList(result){

    let text = '';

    result.eventReviewList.forEach(r => {

        text += `
        
        
                <li>
                    <a href="/board/reviewEventDetail?eventBoardNumber=${r.eventBoardNumber}">
                        <div class="review-sitter-img">
                            <img src="/common/img/보모사진1.jpg" alt="리뷰 보모사진"/>
                        </div>
                        <div class="review-sitter-content">
                            <p><strong>${r.eventName}</strong></p>
                            <div class="review-score">
                                <img src="/common/img/star.png"><span> ${r.rating} / 5</span>
                            </div>
                        </div>
                        <div class="reivew-text-content">
                            <dl>
                                <dt><strong>${r.userId}</strong></dt>
                                <dd>
                                    <p>
                                        ${r.eventBoardContent}
                                    </p>
                                </dd>
                            </dl>
                        </div>
                    </a>
                </li>
        `;
    })

    $('.review-ul').html(text);
    updatePagination(result.pageEventReviewVo)
}

//페이징처리
function updatePagination(pageEventReviewVo) {
    let $pagenation = $('.review-pagenation-container ul');
    $pagenation.empty();

    if (pageEventReviewVo.prev) {
        $pagenation.append(`
                <li class="page-num"><a href="#" data-reviewnum="${pageEventReviewVo.startPage-1}">&lt;</a></li>
            `);
    }



    //게시물이 1개도 존재하지 않는다면 페이징 표시 x
    //한 개라도 존재할 때 페이징 번호가 나타난다.
    if(pageEventReviewVo.realEnd!=0){
        for (let page = pageEventReviewVo.startPage; page <= pageEventReviewVo.endPage; page++) {
            if(page == pageEventReviewVo.criteria.page){
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




    if (pageEventReviewVo.next) {
        $pagenation.append(`
            <li class="page-num"> <a href="#" data-reviewnum="${pageEventReviewVo.endPage+1}">&gt;</a></li>
            `);
    }
}
