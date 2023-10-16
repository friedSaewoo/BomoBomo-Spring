import * as boardReview from './module/boardReviews.js';
import * as paging from './module/boardPagination.js';


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
    boardReview.getServiceReviewList(1, getSearchReviewVo(), serviceReviewList);

});

//이벤트 후기 게시판 이동
$('.event-review-btn').on('click', function (){
    window.location.href="/board/eventReview";
})


//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    $('.keyword').val('');

    const page = $(this).data('pagenum');
    boardReview.getServiceReviewList(page, getSearchReviewVo(), serviceReviewList);

});

//검색 버튼 클릭 시 검색결과 화면에 표시를하며 동시에 페이징처리
$('.sitter-search-btn>button' ).on('click', function (){

    keywordTest = $('.keyword').val();
    boardReview.getServiceReviewList(1, getSearchReviewVo(), serviceReviewList);

})

//검색버튼 결과가 없을 시 나타나는 버튼 클락하면 페이징 1번으로 이동
$(document).on('click', '.non-review-search-result-btn', function (){
    $('.keyword').val('');
    keywordTest='';
    boardReview.getServiceReviewList(1, getSearchReviewVo, serviceReviewList);

})


function serviceReviewList(result) {
    let text = '';

    if(result.serviceReviewList.length !=0){
        result.serviceReviewList.forEach(r => {

            text += `
                
                <li>
                    <a href="/board/reviewDetail?sitterBoardNumber=${r.sitterBoardNumber}" class="review-img-zoom">
                        <div class="review-sitter-img">
                            <img src="/reviews/empPic?empPicFullName=${r.empImgUploadPath + '/'+ r.empImgUuid + '_' + r.empImgName}" alt="리뷰 보모사진"/>
                        </div>
                        <div class="review-sitter-content">
                            <p><strong>${r.empName}</strong></p>
                                                        <div class="review-score">

                            `;

                if(r.rating==1){
                    text +=`
                                <span> ★☆☆☆☆</span>

                            `
                }else if(r.rating==2){
                    text +=`
                                <span> ★★☆☆☆</span>
                            `
                }
                else if(r.rating==3){
                    text +=`
                                <span> ★★★☆☆</span>
                            `
                }
                else if(r.rating==4){
                    text +=`
                                <span> ★★★★☆</span>
                            `
                }
                else if(r.rating==5){
                    text +=`
                                <span> ★★★★★</span>
                            `
                }


            text+=     `</div>
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

                     <div>
            
                <div class="none-search-img"><img src="/common/img/non-search-result.png" alt="검색결과 없음"/></div>
                <div class="non-review-search-result"><strong>검색 결과가 없습니다. 시터 정보를 다시 확인해주세요.</strong></div>
                <div class="non-review-search-result-btn"><button  type="button" data-pagenum="1">목록으로 돌아가기</button></div>
        
        </div>
      

            `;
    }

    $('.review-ul').html(text);
    //동시에 페이징처리
    let $pagination = $('.review-pagenation-container ul');
    paging.updatePagination(result.pageReviewVo, $pagination);
}









