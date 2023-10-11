import * as reply from './module/boardServiceReply.js';
import * as text from './module/textLength.js'



//input hidden에 넣어놓은 값 가져오기
let sitterBoardNumber = $('.reviewDetail-num').val();
console.log(sitterBoardNumber);

//댓글 불러오기
let page = 1;
reply.getListPage({sitterBoardNumber,page:1}, reply.showReply);



//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('pagenum');
    reply.getListPage({sitterBoardNumber, page}, reply.showReply);


    $('.page-num a').removeClass('active-page');
    $(this).addClass('active-page');

});


//댓글 등록
$('.btn-reply').on('click', function () {
    let content = $('#reply-content').val();

    if(!(content)){
        alert('내용을 입력해주세요');
        return;
    }

    if(text.limitText()>200){
        alert("200자 이내로만 작성 가능합니다.");
        return;
    }

    let replyObj = {
        sitterCommentContent : content,
        sitterBoardNumber : sitterBoardNumber,
        userNumber : loginNumber
    };

    reply.addReply(replyObj, function(){
        reply.getListPage({sitterBoardNumber,page:1}, reply.showReply);
    });
    
    //등록버튼 누르면 글자수 카운팅 초기화 및 입력내용도 초기화
    $('#reply-content').val('');
    $(".word-count").text('');
});




let listInfo = {

    sitterBoardNumber : sitterBoardNumber,
    page : 1

}



//댓글 수정 삭제버튼 팝업
$('.review-reply').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');
    $('.reply-btns__box').addClass('none');
    $replyBtnBox.toggleClass('none');
});


$('body').click(function (e) {
    if ($(e.target).hasClass('reply-btns')) {
        //console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});


//댓글 수정창 팝업
$('.review-reply').on('click', '.reply-modify-btn', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
      <div class='modify-box'>
        <textarea class='modify-content' cols="30" rows="2">${$content.text()}</textarea>
        <button type='button' class='modify-content-btn'>수정 완료</button>
      </div>
      <div><span class="textLengthCheck">${text.getTextLength($content.text()) + ' / 200'}</span>
</div>
    `);
    $('.reply-btns__box').addClass('none');



});


// 리플 삭제 버튼 처리
$('.review-reply').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');

    let sitterCommentNumber = $(this).closest('.reply').find('.reply-remove-btn').data('deletenum');
    reply.remove(sitterCommentNumber, function (){
        
        //댓글 삭제 시 페이징 1번으로 이동
        reply.getListPage({sitterBoardNumber,page:1}, reply.showReply);
    });
});



// 리플 수정 완료 처리
    $('.review-reply').on('click', '.modify-content-btn', function () {

        console.log('modify!!!');
        let sitterCommentNumber = $(this).closest('.reply').find('.reply-modify-btn').data('modifynum');
        
        //해당 페이징에서 머무를 수 있도록 페이징값 가져오기
        let page = $('.active-page a').data('pagenum');
        let replyContent = $(this).closest('.modify-box').find('.modify-content').val();


        if(text.limitModifyText()>200){
            alert("200자 이내로 작성해주세요")
            return;
        }

        let replyObj = {
            sitterCommentContent : replyContent
        };

        reply.modify(sitterCommentNumber, replyObj, function (){
            reply.getListPage({sitterBoardNumber,page:page}, reply.showReply);
        });
    });


    

//댓글 입력창 및 수정창 글자 수 제한
text.limitText();
text.limitModifyText();

