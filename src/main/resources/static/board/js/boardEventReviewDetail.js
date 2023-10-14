
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

//해당 돌봄 서비스 리뷰 수정
$('.updateBtn').on('click', function (){
    let eventBoardNumber = $(this).data('number');
    window.location.href = '/board/modifyEventReview?eventBoardNumber=' + eventBoardNumber;
});


$('.hovers').hide();
$('.sitter-avg').on('mouseenter', function (){
    $('.hovers').show();
})
$('.sitter-avg').on('mouseleave', function (){
    $('.hovers').hide();
})


let avg = $('.sitter-avg-rating').data('rating')
console.log(avg)