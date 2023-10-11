
//이벤트 리뷰 게시판 목록으로 이동
function showReviewListBtn(){

    let reviewListBtn = document.querySelector('.update-delete-btn');


    reviewListBtn.addEventListener('click', function(){
        location.href="/board/eventReview"
    });
};


//해당 이벤트 서비스 리뷰 삭제
$('.deleteBtn').on('click', function (){
    let eventBoardNumber = $(this).data('number');

    if(confirm("정말로 삭제하시겠습니까?"))
    {
        console.log(eventBoardNumber);
        window.location.href = '/board/removeEReview?eventBoardNumber=' + eventBoardNumber;

    }else{

    }
})