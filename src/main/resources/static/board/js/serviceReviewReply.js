import * as reply from './module/boardReply.js';



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

    if(getTextLength()>200){
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
    $("#lengthCheck").val("(0/ 200)");
    $('#reply-content').val('');
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
        <textarea class='modify-content'>${$content.text()}</textarea>
        <button type='button' class='modify-content-btn'>수정 완료</button>
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

        function getModifyTextLength() {
            let len = 0;
            for (let i = 0; i < replyContent.length; i++) {
                if (escape(replyContent.charAt(i)).length == 6) {
                    len++;
                }
                len++;
            }
            return len;
        }



        if(getModifyTextLength()>=200){
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




//글자수 체크(byte단위로 쪼개기)
function getTextLength() {
    let contents = $('#reply-content').val();

    let len = 0;
    for (let i = 0; i < contents.length; i++) {
        if (escape(contents.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}

$("#reply-content").keyup(function(e) {
    $(".word-count").html(`<span class="overWrite">  ${getTextLength() + " / 200"} </span>`); //실시간 글자수 카운팅
    if (getTextLength() > 200) {
        $('.overWrite').css('color', 'red')

    }
});







