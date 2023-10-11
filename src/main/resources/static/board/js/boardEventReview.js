import * as paging from './module/boardPagination.js';
import * as boardReview from './module/boardReviews.js';

let keywordVar = '';


//이벤트 후기 게시판 이동
$('.service-review-btn').on('click', function (){
    window.location.href="/board/serviceReview";
})




//이벤트 후기 게시판 첫화면
$(document).ready(function (){
    boardReview.getEventReviewPage(1, getSearchVo(),eventReviewList)

})


//페이징처리
$(document).on('click', '.page-num a', function (e){
    e.preventDefault()
    $('.keyword').val('');

    const page = $(this).data('reviewnum');
    boardReview.getEventReviewPage(page, getSearchVo(),eventReviewList)

})


//검색처리
$('.sitter-search-btn>button').on('click', function (){

    keywordVar = $('.keyword').val();
    boardReview.getEventReviewPage(1, getSearchVo(),eventReviewList)

})



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




function eventReviewList(result){

    let text = '';

    result.eventReviewList.forEach(r => {

        text += `
        
        
                <li>
                    <a href="/board/reviewEventDetail?eventBoardNumber=${r.eventBoardNumber}">
                        <div class="review-sitter-img">
                        <img src="/reviews/img?fileFullName=${r.eventImgUploadPath +'/' + r.eventImgUuid + '_' + r.eventImgName}" alt="리뷰 보모사진"/>
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
   let $pagination = $('.review-pagenation-container ul');
    paging.updatePagination(result.pageEventReviewVo, $pagination)
}

