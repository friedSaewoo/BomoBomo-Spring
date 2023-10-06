
//돌봄 서비스 리뷰 불러오기(검색포함)
export function showServiceReviewList(page, searchReviewVo){

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

//돌봄 서비스 리뷰 
export function serviceReviewList(result) {
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
                            <button class="non-review-search-result-btn" type="button" data-reviewnum="1">목록으로 돌아가기</button></h3>

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