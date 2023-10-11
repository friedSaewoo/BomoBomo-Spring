import * as reply from './module/boardEventReply.js';
import * as text from './module/textLength.js'


//input hidden에 넣어놓은 값 가져오기
let eventBoardNumber = $('.reviewDetail-num').val();
console.log(eventBoardNumber);


let page = 1;
reply.getListPage({eventBoardNumber,page:1}, reply.showReply);


//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('pagenum');
    reply.getListPage({eventBoardNumber, page}, reply.showReply);


    $('.page-num a').removeClass('active-page');
    $(this).addClass('active-page');

});


let listInfo = {

    eventBoardNumber : eventBoardNumber,
    page : 1

}



//댓글 등록
$('.btn-reply').on('click', function (){
    let content = $('#reply-content').val();

    if(!(content)){
        alert('내용을 입력해주세요');
        return;
    };

    let replyObj = {
        eventCommentContent: content,
        eventBoardNumber : eventBoardNumber,
        userNumber : loginNumber
    };

    reply.addReply(replyObj, function (){
        reply.getListPage({eventBoardNumber:eventBoardNumber, page:1}, reply.showReply)
    })
})




text.limitText();
text.limitModifyText();
