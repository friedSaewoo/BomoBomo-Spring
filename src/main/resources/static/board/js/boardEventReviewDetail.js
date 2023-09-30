

//해당 돌봄 서비스 리뷰 삭제
$('.deleteBtn').on('click', function (){
    let eventBoardNumber = $(this).data('number');

    if(confirm("정말로 삭제하시겠습니까?"))
    {
        console.log(eventBoardNumber);
        window.location.href = '/board/removeEReview?eventBoardNumber=' + eventBoardNumber;

    }else{

    }
})