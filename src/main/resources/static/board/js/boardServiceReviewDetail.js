
//서비스리뷰 게시판 목록으로 이동
function showReviewListBtn(){

    let reviewListBtn = document.querySelector('.update-delete-btn');


    reviewListBtn.addEventListener('click', function(){
        location.href="/board/serviceReview"
    });
};



//해당 돌봄 서비스 리뷰 삭제
$('.deleteBtn').on('click', function (){
    let sitterBoardNumber = $(this).data('number');

    if(confirm("정말로 삭제하시겠습니까?"))
    {
        console.log(sitterBoardNumber);
        window.location.href = '/board/removeSReview?sitterBoardNumber=' + sitterBoardNumber;

    }else{

    }
})

//해당 돌봄 서비스 리뷰 수정
$('.updateBtn').on('click', function (){
    let sitterBoardNumber = $(this).data('number');
    window.location.href = '/board/modifyServiceReview?sitterBoardNumber=' + sitterBoardNumber;
});




